package api;

import api.apiController.PersonApiController;
import api.dtos.PersonDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonIT {

    @Test
    void testCreatePerson() {
        this.createPerson();
    }

    private String createPerson() {
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS).body(new PersonDto("uno")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testPersonInvalidRequest() {
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS).path("/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePersonWithoutPersonDto() {
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePersonWithoutUserDtoNick() {
        HttpRequest request = HttpRequest.builder(PersonApiController.PERSONS).body(new PersonDto(null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
