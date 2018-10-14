package api.dtos;

import api.entities.Category;

public class SongDto {

    private String title;
    private String personId;
    private Category category;

    public SongDto(String title, Category category, String personId) {
        this.title = title;
        this.category = category;
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "SongDto{" +
                "title='" + title + '\'' +
                ", personId='" + personId + '\'' +
                ", category=" + category +
                '}';
    }
}
