package com.example.G1720ssweb.data.eng.Controller;


import com.example.G1720ssweb.data.eng.DataAccessObjects.EventRepository;
import com.example.G1720ssweb.data.eng.DataTransferObjects.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApiController {
    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events{n}")
    public ResponseEntity<List<Event>> getEvents(@RequestParam Integer n ){
        List<Event> eventsAllTime = eventRepository.findAllByEventIdLessThan(n+1);

        return new ResponseEntity<>(eventsAllTime, HttpStatus.OK);
    }
}
