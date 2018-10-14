package api.businessController;

import api.daos.DaoFactory;
import api.dtos.PersonDto;
import api.entities.Person;

public class PersonBusinessController {

    public String create(PersonDto personDto) {
        Person person = new Person(personDto.getNick());
        DaoFactory.getDaoFactory().getPersonDao().save(person);
        return person.getId();
    }
}
