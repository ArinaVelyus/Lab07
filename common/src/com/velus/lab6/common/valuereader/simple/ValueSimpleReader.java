package com.velus.lab6.common.valuereader.simple;

import com.velus.lab6.common.util.IOManager;
import com.velus.lab6.common.valuereader.ValueReader;

/**
 * Класс, позволяющий определить правила чтения простых значений.
 */
public abstract class ValueSimpleReader<T> extends ValueReader<T> {
    protected ValueSimpleReader(IOManager ioManager) {
        super(ioManager);
    }
}
