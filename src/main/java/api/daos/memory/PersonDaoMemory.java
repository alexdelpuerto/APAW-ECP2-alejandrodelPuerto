package api.daos.memory;

import api.daos.PersonDao;
import api.entities.Person;

public class PersonDaoMemory extends GenericDaoMemory<Person> implements PersonDao {

    @Override
    public String getId(Person person) {
        return person.getId();
    }

    @Override
    public void setId(Person person, String id) {
        person.setId(id);
    }
}
