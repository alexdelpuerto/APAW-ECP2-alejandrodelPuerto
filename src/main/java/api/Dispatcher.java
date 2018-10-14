package api;

import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.exceptions.ArgumentNotValidException;
import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class Dispatcher {

    static {
        DaoFactory.setDaoFactory(new DaoMemoryFactory());
    }

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
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
}
