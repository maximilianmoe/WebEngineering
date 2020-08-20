package com.example.G1720ssweb.data.eng.DataAccessObjects;

import com.example.G1720ssweb.data.eng.DataTransferObjects.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findAll();
    List<Event> findEventsByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(String name, String location);
    Event findByEventId(Integer eventId);
    List<Event> findAllByEventIdLessThan(Integer amount);
    List<Event> findAllByEventIdLessThanAndDateAfterOrDateEquals(Integer amount, Date date1, Date date2);
    List<Event> findAllByEventTypeAndDateAfterOrDateEquals(String eventType, Date date1, Date date2);


}
