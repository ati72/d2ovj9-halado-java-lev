package com.musicians.d2ovj9haladojavalev.persist;


import com.musicians.d2ovj9haladojavalev.entity.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ArtistDAO extends CrudRepository<Artist, Long> {
    ArrayList<Artist> findArtistsByGenre(String genre);

    ArrayList<Artist> findArtistsByFormedAfter(int year);


}
