package api.apiController;

import api.businessController.SongBusinessController;
import api.dtos.SongDto;
import api.dtos.SongIdTitleDto;
import api.entities.Category;
import api.exceptions.ArgumentNotValidException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongApiController {

    public static final String SONGS = "/songs";
    public static final String ID_ID = "/{id}";
    public static final String CATEGORY = "/category";
    public static final String SEARCH = "/search";

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

    public ArrayList<String> findByVoteGreaterOrEqualsTo(String query) {
        this.validate(query, "query param q");
        if (!"vote".equals(query.split(":>=")[0])) {
            throw new ArgumentNotValidException("query param is incorrect, missing 'vote:>='");
        } else if (!isValid(query)) {
            throw new ArgumentNotValidException("vote must be between 0 and 10");
        }
        return this.songBusinessController.findByVoteGreaterOrEqualsTo(Integer.valueOf(query.split(":>=")[1]));
    }

    private boolean isValid(String query) {
        int value = Integer.valueOf(query.split(":>=")[1]);
        return (value >= 0 && value <= 10);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
