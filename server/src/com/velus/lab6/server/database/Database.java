package com.velus.lab6.server.database;

import com.velus.lab6.server.exception.*;
import com.velus.lab6.common.types.Authentication;
import com.velus.lab6.common.types.Worker;
import com.velus.lab6.server.exception.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class Database {
    private final Map<Integer, Integer> elementOwners = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();
    private final PriorityQueue<Worker> elements = new PriorityQueue<>();
    private final LocalDate initializationDate = LocalDate.now();
    private final String databaseUrl;


    public Database(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        loadUsers();
        loadWorkers();
        try {
            createUser(new Authentication(DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD));
        } catch (Exception ignored) { }
    }

    public User readUser(Authentication authentication) {
        if (authentication.getUsername().isEmpty()) throw new AuthenticationRequiredException();
        User user = users.get(authentication.getUsername());
        if (user == null) throw new UnknownUserException();
        if (user.getPassword().equals(authentication.getPassword())) {
            return user;
        }
        throw new WrongCredentialsException();
    }

    public void createUser(Authentication authentication) {
        if (users.containsKey(authentication.getUsername())) {
            throw new UserAlreadyRegisteredException();
        }
        try {
            Connection connection = connect();
            int id = getNextId(connection, "user_id_sequence");
            PreparedStatement s = connection.prepareStatement("INSERT INTO users (id, username, password) VALUES(?, ?, ?)");
            s.setInt(1, id);
            s.setString(2, authentication.getUsername());
            s.setString(3, authentication.getPassword());
            s.executeUpdate();
            User user = new User(id, authentication.getUsername(), authentication.getPassword());
            users.put(user.getUsername(), user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public LocalDate getInitializationDate() {
        return initializationDate;
    }

    public Worker createWorker(Authentication authentication, Worker value) {
        User user = readUser(authentication);
        try {
            Connection connection = connect();
            Integer id = getNextId(connection, "worker_id_sequence");
            Worker completed = new Worker(value, id);
            PreparedStatement s = connection.prepareStatement("INSERT INTO workers (id, bytes, owner_id) VALUES(?, ?, ?)");
            s.setInt(1, id);
            s.setBytes(2, Serializer.serialize(value));
            s.setInt(3, user.getId());
            s.executeUpdate();
            elements.add(completed);
            elementOwners.put(completed.getId(), user.getId());
            return completed;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Stream<Worker> readWorkers(Authentication authentication) {
        User user = readUser(authentication);
        return elements.stream().filter(
                (Worker worker) ->
                        elementOwners.get(worker.getId()) == user.getId()
                        || Objects.equals(authentication.getUsername(), DatabaseConfig.USERNAME)
        );
    }

    public void updateWorker(Authentication authentication, Worker key, Worker value) {
        throwIfNotOwner(authentication, key);
        try {
            Connection connection = connect();
            Worker completed = new Worker(key, value);
            PreparedStatement s = connection.prepareStatement("UPDATE workers SET bytes = ? WHERE id = ?");
            s.setBytes(1, Serializer.serialize(completed));
            s.setInt(2, key.getId());
            elements.removeIf(worker -> worker.getId().equals(key.getId()));
            elements.add(completed);
            s.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteWorker(Authentication authentication, Worker key) {
        if (key == null) return;
        throwIfNotOwner(authentication, key);
        try {
            Connection connection = connect();
            PreparedStatement s = connection.prepareStatement("DELETE FROM workers WHERE id = ?");
            s.setInt(1, key.getId());
            s.executeUpdate();
            elementOwners.remove(key.getId());
            elements.remove(key);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void throwIfNotOwner(Authentication authentication, Worker value) {
        User user = readUser(authentication);
        if (!isOwner(user, value)) {
            throw new AccessRestrictedException();
        }
    }

    private boolean isOwner(User user, Worker value) {
        if (isAdmin(user)) return true;
        Integer ownerId = elementOwners.get(value.getId());
        return ownerId != null && ownerId == user.getId();
    }

    private boolean isAdmin(User user) {
        return user.getUsername().equals(DatabaseConfig.USERNAME);
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(databaseUrl, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
    }

    private Integer getNextId(Connection connection, String sequenceName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery(String.format("SELECT nextval('%s')", sequenceName));
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private void loadUsers() {
        try {
            users.clear();
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = new User(id, username, password);
                users.put(username, user);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadWorkers() {
        try {
            elementOwners.clear();
            elements.clear();
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM workers");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                byte[] bytes = resultSet.getBytes("bytes");
                Integer ownerId = resultSet.getInt("owner_id");
                Worker worker = (Worker) Serializer.deserialize(bytes);
                Worker complete = new Worker(worker, id);
                elements.add(complete);
                elementOwners.put(complete.getId(), ownerId);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
