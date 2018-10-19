package api.entities;

public class Song {
    private String id;
    private String title;
    private Person person;
    private Category category;

    public Song(String title, Person person) {
        this.title = title;
        this.person = person;
    }

    public Song(String title, Person person, Category category) {
        this.title = title;
        this.person = person;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", person=" + person +
                ", category=" + category +
                '}';
    }
}
