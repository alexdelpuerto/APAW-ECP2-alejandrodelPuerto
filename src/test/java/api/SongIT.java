package api;

import api.apiController.PersonApiController;
import api.apiController.SongApiController;
import api.apiController.VoteApiController;
import api.dtos.PersonDto;
import api.dtos.SongDto;
import api.dtos.SongIdTitleDto;
import api.dtos.VoteDto;
import api.entities.Category;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SongIT {

    @Test
    void testCreateSong() {
        this.createSong("Song");
    }

    private String createSong(String title) {
        String personId = this.createPerson("person");
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).body(new SongDto(title, Category.POP, personId)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createSong(String title, String personId) {
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).body(new SongDto(title, Category.POP, personId)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createPerson(String nick) {
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS).body(new PersonDto(nick)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createVote(int value, String comment, String personId) {
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS + "/" + personId + VoteApiController.VOTES)
                .body(new VoteDto(value, comment, value >= 5)).post();
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
        String personId = this.createPerson("person");
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
    void testUpdateSong() {
        String personId = this.createPerson("person");
        String songID = this.createSong("Song");
        SongDto songDto = new SongDto("Cancion", Category.REGGAETON, personId);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).path(SongApiController.ID_ID)
                .expandPath(songID).body(songDto).put();
        new Client().submit(request);
    }

    @Test
    void testUpdateSongWithSongIdNotFound() {
        String personId = this.createPerson("person");
        SongDto songDto = new SongDto("Cancion", Category.REGGAETON, personId);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).path(SongApiController.ID_ID)
                .expandPath("dasdasd").body(songDto).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testUpdateSongWithoutSongDtoTitle() {
        String personId = this.createPerson("person");
        String songId = this.createSong("Song");
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).path(SongApiController.ID_ID).expandPath(songId).body(new SongDto(null, Category.ROCK, personId)).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
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

    @Test
    void testDeleteSong() {
        String songId = this.createSong("Song");
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).get();
        int countSong = ((List<SongIdTitleDto>) new Client().submit(request).getBody()).size();
        HttpRequest request2 = HttpRequest.builder(SongApiController.SONGS).path(SongApiController.ID_ID)
                .expandPath(songId).delete();
        new Client().submit(request2);
        assertTrue(((List<SongIdTitleDto>) new Client().submit(request).getBody()).size() < countSong);
    }

    @Test
    void testSearchVotes() {
        String person1 = this.createPerson("Juan");
        String person2 = this.createPerson("Pepe");
        this.createSong("It's my life", person1);
        this.createSong("Have a nice day", person2);
        this.createVote(3, "", person1);
        this.createVote(6, "", person1);
        this.createVote(9, "", person2);
        this.createVote(1, "", person2);

        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).path(SongApiController.SEARCH)
                .param("q", "vote:>=5").get();
        new Client().submit(request);
    }

    @Test
    void testSearchVotesWithoutParamQ() {
        String person1 = this.createPerson("Juan");
        this.createSong("It's my life", person1);
        this.createVote(3, "", person1);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).path(SongApiController.SEARCH)
                .param("error", "vote:>=5").get();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testSearchVotesWithInvalidParamQ() {
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).path(SongApiController.SEARCH)
                .param("q", "vote:>=15").get();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
