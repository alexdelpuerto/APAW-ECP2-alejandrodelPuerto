package api.daos;

import api.entities.Song;
import org.apache.logging.log4j.LogManager;

public abstract class DaoFactory {

    private static DaoFactory daoFactory = null;

    public static DaoFactory getDaoFactory() {
        assert daoFactory != null;
        return daoFactory;
    }

    public static void setDaoFactory(DaoFactory daoFactory) {
        DaoFactory.daoFactory = daoFactory;
        LogManager.getLogger(DaoFactory.class).debug("   create DaoMemoryFactory");
    }

    public abstract PersonDao getPersonDao();

    public abstract VoteDao getVoteDao();

    public abstract SongDao getSongDao();


}
