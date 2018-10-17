package api.businessController;

import api.daos.DaoFactory;
import api.dtos.SongDto;
import api.dtos.SongIdTitleDto;
import api.entities.Category;
import api.entities.Person;
import api.entities.Song;
import api.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void delete(String songId) {
        DaoFactory.getDaoFactory().getSongDao().deleteById(songId);
    }

    public ArrayList<String> findByVoteGreaterOrEqualsTo(int value) {
        ArrayList<String> results = new ArrayList<>();
        int media = 0;
        List<Song> songs = DaoFactory.getDaoFactory().getSongDao().findAll();
        for (Song s : songs) {
            List<String> votes = s.getPerson().getVotes();
            for (String vote : votes) {
                media += DaoFactory.getDaoFactory().getVoteDao().read(vote).get().getValue();
            }
            media = media / votes.size();
            if (media >= value) {
                results.add("{id:" + s.getId() + ",title:" + s.getTitle() + "}");
            }
        }
        return results;
    }
}
