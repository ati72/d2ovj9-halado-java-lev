package com.musicians.d2ovj9haladojavalev.controller;

import com.musicians.d2ovj9haladojavalev.entity.Instrument;
import com.musicians.d2ovj9haladojavalev.service.InstrumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class InstrumentController {
    private final InstrumentService instrumentService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping("/instrument")
    public ArrayList<Instrument> getAllInstrument() {
        return instrumentService.getAllInstrument();
    }

    @GetMapping("/instrument/{id}")
    public Instrument getInstrumentById(Long id) {
        return instrumentService.getInstrumentById(id);
    }

    @PostMapping("/instrument")
    public Instrument addInstrument(@Valid @RequestBody Instrument instrument,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        instrumentService.addInstrument(instrument);
        return  instrument;
    }

    @DeleteMapping("/instrument/{id}")
    public void deleteInstrumentById(Long id) {
        instrumentService.deleteInstrumentById(id);
    }
}
