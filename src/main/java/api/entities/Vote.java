package api.entities;

import java.time.LocalDateTime;

public class Vote {
    private String id;
    private int value;
    private LocalDateTime date;
    private String comment;
    private boolean positive;

    public Vote(String id, int value, String comment) {
        this.value = value;
        this.comment = comment;
        this.positive = value >= 5;
        this.date = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                ", positive=" + positive +
                '}';
    }
}
