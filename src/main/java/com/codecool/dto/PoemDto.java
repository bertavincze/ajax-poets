package com.codecool.dto;

import com.codecool.model.Poem;
import com.codecool.model.Poet;

import java.util.List;

public final class PoemDto {

    private final Poet poet;
    private final List<Poem> poems;

    public PoemDto(Poet poet, List<Poem> poems) {
        this.poet = poet;
        this.poems = poems;
    }

    public Poet getPoet() {
        return poet;
    }

    public List<Poem> getPoems() {
        return poems;
    }
}
