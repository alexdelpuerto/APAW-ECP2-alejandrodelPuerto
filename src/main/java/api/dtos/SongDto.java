package api.dtos;

public class SongDto {

    private String title;

    public SongDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SongDto{" +
                "title='" + title + '\'' +
                '}';
    }
}
