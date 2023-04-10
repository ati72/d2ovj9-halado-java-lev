package com.musicians.d2ovj9haladojavalev.persist;

import com.musicians.d2ovj9haladojavalev.entity.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherDAO extends CrudRepository<Publisher, Long> {
}
