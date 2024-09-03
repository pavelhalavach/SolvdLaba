package main.java.org.solvd.structure.application;

import java.util.Objects;

public class Rating {
    private String comment;
    private int mark;

    public Rating(String comment, int mark) {
        this.comment = comment;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "{comment='" + comment + '\'' +
                ", mark=" + mark + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return mark == rating.mark && Objects.equals(comment, rating.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, mark);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
