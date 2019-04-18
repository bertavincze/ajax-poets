package com.codecool.model;

import java.util.Objects;

public final class Poet extends AbstractModel {

    private final String name;
    private final String password;

    public Poet(int id, String name, String password) {
        super(id);
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Poet poet = (Poet) o;
        return Objects.equals(name, poet.name) &&
            Objects.equals(password, poet.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, password);
    }
}
