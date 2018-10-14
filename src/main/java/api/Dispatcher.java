package api;

import api.apiController.PersonApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PersonDto;
import api.exceptions.ArgumentNotValidException;
import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class Dispatcher {

    static {
        DaoFactory.setDaoFactory(new DaoMemoryFactory());
    }

    private PersonApiController personApiController = new PersonApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                case GET:
                    throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
                case PUT:
                case PATCH:
                case DELETE:
                default:
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(PersonApiController.PERSONS)) {
            response.setBody(this.personApiController.create((PersonDto) request.getBody()));
        }
    }
}
