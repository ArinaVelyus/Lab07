package com.velus.lab6.common.valuereader.complex;

import com.velus.lab6.common.util.IOManager;
import com.velus.lab6.common.types.Country;
import com.velus.lab6.common.types.Location;
import com.velus.lab6.common.types.Person;
import com.velus.lab6.common.valuereader.simple.enumerable.CountryReader;
import com.velus.lab6.common.valuereader.simple.number.IntegerReader;

public class PersonReader extends ValueComplexReader<Person> {
    public PersonReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected Person readFields() {
        Integer weight = new IntegerReader(ioManager).setLowerBound(0).setNullable(true).read("вес");
        Country nationality = new CountryReader(ioManager).setNullable(false).read("национальность");
        Location location = new LocationReader(ioManager).setNullable(false).read("местоположение");
        return new Person(weight, nationality, location);
    }

    @Override
    protected String getTypeName() {
        return Person.class.getSimpleName();
    }
}