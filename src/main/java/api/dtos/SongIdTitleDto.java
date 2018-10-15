package api.dtos;

import api.entities.Song;

public class SongIdTitleDto {

    private String id;
    private String title;

    public SongIdTitleDto(Song song) {
        this.id = song.getId();
        this.title = song.getTitle();
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

    @Override
    public String toString() {
        return "SongIdTitleDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
