package api.businessController;

import api.daos.DaoFactory;
import api.dtos.PersonDto;
import api.dtos.SongDto;
import api.dtos.SongIdTitleDto;
import api.entities.Category;
import api.entities.Person;
import api.entities.Song;
import api.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<SongIdTitleDto> readAll() {
        return DaoFactory.getDaoFactory().getSongDao().findAll()
                .stream().map(SongIdTitleDto::new)
                .collect(Collectors.toList());
    }

    public void updateSong(String songId, SongDto songDto) {
        Song song = DaoFactory.getDaoFactory().getSongDao().read(songId)
                .orElseThrow(() -> new NotFoundException("Song id: " + songId));
        song.setTitle(songDto.getTitle());
        song.setCategory(songDto.getCategory());
        DaoFactory.getDaoFactory().getSongDao().save(song);
    }

    public void updateCategorySong(String songId, Category category) {
        Song song = DaoFactory.getDaoFactory().getSongDao().read(songId)
                .orElseThrow(() -> new NotFoundException("Song (" + songId + ")"));
        song.setCategory(category);
        DaoFactory.getDaoFactory().getSongDao().save(song);
    }


}
