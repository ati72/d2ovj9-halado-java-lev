package com.musicians.d2ovj9haladojavalev.persist;

import com.musicians.d2ovj9haladojavalev.entity.Musician;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicianDAO extends CrudRepository<Musician, Long> {
    Musician findMusicianByInstrument(String instrument);
}
