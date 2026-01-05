package com.conveniencestore.gui.utils;

public class ComboItem<T> {
    private final T value;
    private final String label;

    public ComboItem(T value, String label) {
        this.value = value;
        this.label = label;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return label;
    }
}
