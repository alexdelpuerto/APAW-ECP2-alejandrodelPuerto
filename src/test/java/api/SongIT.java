package api;

import api.apiController.PersonApiController;
import api.apiController.SongApiController;
import api.dtos.PersonDto;
import api.dtos.SongDto;
import api.entities.Category;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SongIT {

    @Test
    void testCreateSong() {
        this.createSong("Song1");
    }

    private String createSong(String title) {
        String personId = this.createPerson();
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS)
                .body(new SongDto(title, Category.ROCK, personId)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createPerson() {
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS).body(new PersonDto("Person1")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testCreateSongPersonIdNotFound() {
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS)
                .body(new SongDto("Song1", Category.ELECTRONIC, "asdasd")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testCreateSongWithoutCategoryPerson() {
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS)
                .body(new SongDto("Song1", null, null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
