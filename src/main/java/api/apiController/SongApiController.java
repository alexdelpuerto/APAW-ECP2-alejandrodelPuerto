package api.apiController;

import api.businessController.SongBusinessController;
import api.dtos.SongDto;
import api.entities.Category;
import api.exceptions.ArgumentNotValidException;

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

    public void updateCategory(String songId, Category category) {
        this.validate(category, "category");
        this.songBusinessController.updateCategorySong(songId, category);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
