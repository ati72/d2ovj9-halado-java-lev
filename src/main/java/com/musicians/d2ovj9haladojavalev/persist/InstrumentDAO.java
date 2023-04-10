package com.musicians.d2ovj9haladojavalev.persist;

import com.musicians.d2ovj9haladojavalev.entity.Instrument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentDAO extends CrudRepository<Instrument, Long> {
}
