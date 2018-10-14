package api.businessController;

import api.daos.DaoFactory;
import api.dtos.SongDto;
import api.entities.Person;
import api.entities.Song;
import api.exceptions.NotFoundException;

public class SongBusinessController {

    public String create(SongDto songDto) {
        Person person = null;
        if (songDto.getPersonId() != null) {
            person = DaoFactory.getDaoFactory().getPersonDao().read(songDto.getPersonId())
                    .orElseThrow(() -> new NotFoundException("Person (" + songDto.getPersonId() + ")"));
        }
        Song song = new Song(songDto.getTitle(), person, songDto.getCategory());
        DaoFactory.getDaoFactory().getSongDao().save(song);
        return song.getId();
    }
}
