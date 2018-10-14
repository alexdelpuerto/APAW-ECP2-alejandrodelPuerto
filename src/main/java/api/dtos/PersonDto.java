package api.dtos;

public class PersonDto {

    private String nick;

    public PersonDto(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "nick='" + nick + '\'' +
                '}';
    }
}
