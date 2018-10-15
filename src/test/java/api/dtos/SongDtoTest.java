package api.dtos;

import api.entities.Category;
import api.entities.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongDtoTest {

    Song song;
    SongDto songDto;

    @BeforeEach
    void init() {
        song = new Song("", null);
        song.setId("1");
        song.setTitle("song1");
        song.setCategory(Category.ROCK);
        songDto = null;
    }

    @Test
    void testSongSetsAndGets() {
        assertEquals("1", song.getId());
        assertEquals("song1", song.getTitle());
        assertEquals(Category.ROCK, song.getCategory());
    }

    @Test
    void testSongDtoSetsAndGets() {
        songDto = new SongDto("", null, null);
        songDto.setTitle("Song1");
        songDto.setCategory(Category.ROCK);
        songDto.setPersonId("person1");
        assertEquals("Song1", songDto.getTitle());
        assertEquals(Category.ROCK, songDto.getCategory());
        assertEquals("person1", songDto.getPersonId());
    }
}
