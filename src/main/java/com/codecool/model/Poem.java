package com.codecool.model;

public final class Poem extends AbstractModel {

    private final String title;
    private final String content;
    private final int poet_id;

    public Poem(int id, String title, String content, int poet_id) {
        super(id);
        this.title = title;
        this.content = content;
        this.poet_id = poet_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getPoet_id() {
        return poet_id;
    }
}
