package api.apiController;

import api.businessController.SongBusinessController;
import api.dtos.SongDto;
import api.exceptions.ArgumentNotValidException;

public class SongApiController {

    public static final String SONGS = "/songs";

    private SongBusinessController songBusinessController = new SongBusinessController();

    public String create(SongDto songDto) {
        this.validate(songDto, "songDto");
        this.validate(songDto.getTitle(), "songDto title");
        return this.songBusinessController.create(songDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
