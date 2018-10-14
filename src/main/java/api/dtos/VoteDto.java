package api.dtos;

import java.time.LocalDateTime;

public class VoteDto {

    private int value;
    private String comment;
    private boolean positive;
    private LocalDateTime date;

    public VoteDto(int value) {
        this.value = value;
    }

    public VoteDto(int value, String comment, boolean positive) {
        this.value = value;
        this.comment = comment;
        this.positive = positive;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "VoteDto{" +
                "value=" + value +
                ", comment='" + comment + '\'' +
                ", positive=" + positive +
                ", date=" + date +
                '}';
    }
}
