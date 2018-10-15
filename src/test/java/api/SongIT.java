package api;

import api.apiController.PersonApiController;
import api.apiController.SongApiController;
import api.dtos.PersonDto;
import api.dtos.SongDto;
import api.dtos.SongIdTitleDto;
import api.entities.Category;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SongIT {

    @Test
    void testCreateSong() {
        this.createSong("Song");
    }

    private String createSong(String title) {
        String personId = this.createPerson();
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).body(new SongDto(title, Category.POP, personId)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createPerson() {
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS).body(new PersonDto("Person1")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testSongInvalidRequest() {
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).path("/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateSongWithoutSongDto() {
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateSongWithoutSongDtoTitle() {
        String personId = this.createPerson();
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).body(new SongDto(null, Category.ROCK, personId)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateSongWithPersonIdNotFound() {
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).body(new SongDto("Song1", Category.REGGAETON, "asda")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testUpdateCategory() {
        String songID = this.createSong("Song");
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).path(SongApiController.ID_ID)
                .expandPath(songID).path(SongApiController.CATEGORY).body(Category.ELECTRONIC).patch();
        new Client().submit(request);
    }

    @Test
    void testUpdateCategoryWithSongIdNotFound() {
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).path(SongApiController.ID_ID)
                .expandPath("adsdad").path(SongApiController.CATEGORY).body(Category.ELECTRONIC).patch();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testUpdateCategoryWithInvalidCategory() {
        String songId = this.createSong("Song");
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).path(SongApiController.ID_ID)
                .expandPath(songId).path(SongApiController.CATEGORY).body(null).patch();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testReadAll() {
        for (int i = 0; i < 10; i++) {
            this.createSong("Song " + i);
        }
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).get();
        List<SongIdTitleDto> songsList = (List<SongIdTitleDto>) new Client().submit(request).getBody();
        assertTrue(songsList.size() >= 10);
    }
}
