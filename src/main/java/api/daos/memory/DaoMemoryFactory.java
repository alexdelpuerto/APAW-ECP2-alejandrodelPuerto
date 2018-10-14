package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.PersonDao;
import api.daos.SongDao;

public class DaoMemoryFactory extends DaoFactory {

    private PersonDao personDao;
    private SongDao songDao;

    @Override
    public PersonDao getPersonDao() {
        if (this.personDao == null) {
            this.personDao = new PersonDaoMemory();
        }
        return this.personDao;
    }

    @Override
    public SongDao getSongDao() {
        if (this.songDao == null) {
            this.songDao = new SongDaoMemory();
        }
        return this.songDao;
    }
}
