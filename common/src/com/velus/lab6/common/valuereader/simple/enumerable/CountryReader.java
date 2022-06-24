package com.velus.lab6.common.valuereader.simple.enumerable;

import com.velus.lab6.common.util.IOManager;
import com.velus.lab6.common.types.Country;

public class CountryReader extends EnumReader<Country> {
    public CountryReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    Country[] getEnumValues() {
        return Country.values();
    }

    @Override
    Country valueOf(String name) {
        return Country.valueOf(name);
    }
}