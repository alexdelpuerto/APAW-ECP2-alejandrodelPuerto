package api.dtos;

import api.entities.Category;

public class SongDto {

    private String title;
    private Category category;
    private String personId;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "SongDto{" +
                "title='" + title + '\'' +
                ", category=" + category +
                ", personId='" + personId + '\'' +
                '}';
    }
}
