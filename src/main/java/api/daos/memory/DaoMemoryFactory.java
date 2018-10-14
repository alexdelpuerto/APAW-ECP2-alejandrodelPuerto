package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.PersonDao;

public class DaoMemoryFactory extends DaoFactory {

    private PersonDao personDao;

    @Override
    public PersonDao getPersonDao() {
        if (this.personDao == null) {
            this.personDao = new PersonDaoMemory();
        }
        return this.personDao;
    }
}
