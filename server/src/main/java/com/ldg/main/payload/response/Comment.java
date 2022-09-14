package com.ldg.main.payload.response;

public class Comment {
    public int mark;
    public String comment;
    public UserShortView user;

    public Comment(int mark, String comment, UserShortView user) {
        this.mark = mark;
        this.comment = comment;
        this.user = user;
    }
}
