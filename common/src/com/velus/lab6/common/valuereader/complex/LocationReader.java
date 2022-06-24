package com.velus.lab6.common.valuereader.complex;

import com.velus.lab6.common.util.IOManager;
import com.velus.lab6.common.types.Location;
import com.velus.lab6.common.valuereader.simple.StringReader;
import com.velus.lab6.common.valuereader.simple.number.FloatReader;

public class LocationReader extends ValueComplexReader<Location> {
    public LocationReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected Location readFields() {
        String name = new StringReader(ioManager).setNullable(true).read("название");
        float x = new FloatReader(ioManager).setNullable(false).read("x");
        float y = new FloatReader(ioManager).setNullable(false).read("y");
        float z = new FloatReader(ioManager).setNullable(false).read("z");
        return new Location(x, y, z, name);
    }

    @Override
    protected String getTypeName() {
        return Location.class.getSimpleName();
    }
}
