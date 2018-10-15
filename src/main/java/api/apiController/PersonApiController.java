package api.apiController;

import api.businessController.PersonBusinessController;
import api.dtos.PersonDto;
import api.exceptions.ArgumentNotValidException;

public class PersonApiController {

    public static final String PERSONS = "/persons";
    public static final String ID_ID = "/{id}";

    private PersonBusinessController personBusinessController = new PersonBusinessController();

    public String create(PersonDto personDto) {
        this.validate(personDto, "personDto");
        this.validate(personDto.getNick(), "PersonDto Nick");
        return this.personBusinessController.create(personDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }
}
