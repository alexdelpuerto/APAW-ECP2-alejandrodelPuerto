package api;

import api.apiController.PersonApiController;
import api.apiController.SongApiController;
import api.apiController.VoteApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PersonDto;
import api.dtos.SongDto;
import api.dtos.VoteDto;
import api.entities.Category;
import api.exceptions.ArgumentNotValidException;
import api.exceptions.NotFoundException;
import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class Dispatcher {

    static {
        DaoFactory.setDaoFactory(new DaoMemoryFactory());
    }

    private PersonApiController personApiController = new PersonApiController();

    private VoteApiController voteApiController = new VoteApiController();

    private SongApiController songApiController = new SongApiController();


    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case GET:
                    throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
                case PUT:
                case PATCH:
                    this.doPatch(request, response);
                    break;
                case DELETE:
                default:
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(PersonApiController.PERSONS)) {
            response.setBody(this.personApiController.create((PersonDto) request.getBody()));

        } else if (request.isEqualsPath(PersonApiController.PERSONS + PersonApiController.ID_ID + VoteApiController.VOTES)) {
            response.setBody(this.voteApiController.create((VoteDto) request.getBody(), request.getPath(1)));

        } else if (request.isEqualsPath(SongApiController.SONGS)) {
            response.setBody(this.songApiController.create((SongDto) request.getBody()));
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPatch(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(SongApiController.SONGS + SongApiController.ID_ID + SongApiController.CATEGORY)) {
            this.songApiController.updateCategory(request.getPath(1), (Category) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }
}
