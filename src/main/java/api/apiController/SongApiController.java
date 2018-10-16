package api.apiController;

import api.businessController.SongBusinessController;
import api.dtos.SongDto;
import api.dtos.SongIdTitleDto;
import api.entities.Category;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class SongApiController {

    public static final String SONGS = "/songs";
    public static final String ID_ID = "/{id}";
    public static final String CATEGORY = "/category";

    private SongBusinessController songBusinessController = new SongBusinessController();

    public String create(SongDto songDto) {
        this.validate(songDto, "songDto");
        this.validate(songDto.getTitle(), "songDto title");
        return this.songBusinessController.create(songDto);
    }

    public List<SongIdTitleDto> readAll() {
        return this.songBusinessController.readAll();
    }

    public void updateSong(String sondId, SongDto songDto) {
        this.validate(songDto, "songDto");
        this.validate(songDto.getTitle(), "SongDto Title");
        this.songBusinessController.updateSong(sondId, songDto);
    }

    public void updateCategory(String songId, Category category) {
        this.validate(category, "category");
        this.songBusinessController.updateCategorySong(songId, category);
    }

    public void delete(String songId) {
        this.songBusinessController.delete(songId);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
