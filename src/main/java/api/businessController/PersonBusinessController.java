package api.businessController;

import api.daos.DaoFactory;
import api.dtos.PersonDto;
import api.entities.Person;
import api.exceptions.NotFoundException;

public class PersonBusinessController {

    public String create(PersonDto personDto) {
        Person person = new Person(personDto.getNick());
        DaoFactory.getDaoFactory().getPersonDao().save(person);
        return person.getId();
    }

    public Person getPerson(String personId) {
        return DaoFactory.getDaoFactory().getPersonDao().read(personId).orElseThrow(
                () -> new NotFoundException("Person (" + personId + ")"));
    }
}
