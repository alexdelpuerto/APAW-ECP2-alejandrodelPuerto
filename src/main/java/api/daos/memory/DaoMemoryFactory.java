package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.PersonDao;
import api.daos.SongDao;
import api.daos.VoteDao;


public class DaoMemoryFactory extends DaoFactory {

    private PersonDao personDao;

    private VoteDao voteDao;

    private SongDao songDao;


    @Override
    public PersonDao getPersonDao() {
        if (this.personDao == null) {
            this.personDao = new PersonDaoMemory();
        }
        return this.personDao;
    }

    @Override
    public VoteDao getVoteDao() {
        if (this.voteDao == null) {
            this.voteDao = new VoteDaoMemory();
        }
        return this.voteDao;
    }

    @Override
    public SongDao getSongDao() {
        if (this.songDao == null) {
            this.songDao = new SongDaoMemory();
        }
        return this.songDao;
    }
}
