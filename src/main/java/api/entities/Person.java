package api.entities;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String id;
    private String nick;
    private List<String> votes;

    public Person(String nick) {
        this.nick = nick;
        votes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public List<String> getVotes() {
        return votes;
    }

    public void addVotes(String voteId) {
        this.votes.add(voteId);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", nick='" + nick + '\'' +
                ", votes=" + votes +
                '}';
    }
}
