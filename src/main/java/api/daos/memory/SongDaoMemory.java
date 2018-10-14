package api.daos.memory;

import api.daos.SongDao;
import api.entities.Song;

public class SongDaoMemory extends GenericDaoMemory<Song> implements SongDao {

    @Override
    public String getId(Song song) {
        return song.getId();
    }

    @Override
    public void setId(Song song, String id) {
        song.setId(id);
    }
}
