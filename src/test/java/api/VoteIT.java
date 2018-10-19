package api;

import api.apiController.PersonApiController;
import api.apiController.VoteApiController;
import api.dtos.PersonDto;
import api.dtos.VoteDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VoteIT {

    @Test
    void testCreateVote() {
        this.createVote();
    }

    public String createVote() {
        String personId = this.createPerson();
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS + "/" + personId + VoteApiController.VOTES)
                .body(new VoteDto(5, "Me ha gustado mucho", true)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createPerson() {
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS).body(new PersonDto("Person1")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testCreateVotePersonIdNotFound() {
        VoteDto voteDto = new VoteDto(1, "Muy malas canciones", false);
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS + "/123" + VoteApiController.VOTES).body(voteDto).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testCreateVoteWithInvalidValue() {
        String personId = this.createPerson();
        VoteDto voteDto = new VoteDto(23, "Muy malas canciones", false);
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS + "/" + personId + VoteApiController.VOTES).body(voteDto).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
