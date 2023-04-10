package com.musicians.d2ovj9haladojavalev.service;

import com.musicians.d2ovj9haladojavalev.entity.Instrument;
import com.musicians.d2ovj9haladojavalev.persist.InstrumentDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InstrumentService {

    private final InstrumentDAO instrumentDAO;

    public InstrumentService(InstrumentDAO instrumentDAO) {
        this.instrumentDAO = instrumentDAO;
    }

    public ArrayList<Instrument> getAllInstrument() {
        return (ArrayList<Instrument>) instrumentDAO.findAll();
    }

    public Instrument getInstrumentById(Long id) {
        return instrumentDAO.findById(id).get();
    }

    public void addInstrument(Instrument instrument) {
        instrumentDAO.save(instrument);
    }

    public void deleteInstrumentById(Long id) {
        instrumentDAO.deleteById(id);
    }
}
