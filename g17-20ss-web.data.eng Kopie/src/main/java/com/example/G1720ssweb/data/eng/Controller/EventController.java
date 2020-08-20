package com.example.G1720ssweb.data.eng.Controller;

import com.example.G1720ssweb.data.eng.DataAccessObjects.EventRepository;
import com.example.G1720ssweb.data.eng.DataTransferObjects.Event;
import com.example.G1720ssweb.data.eng.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventService eventService;

    @GetMapping("/")
    public String getHomepage(Model model) {
        getIndex(model, "0");
        return "index";
    }

    @GetMapping("/error")
    public String getError(){
        return "error";
    }

    @GetMapping("/index")
    public String getIndex(Model model, @RequestParam(value = "filter", required = false) String filter) {
        Date today = Date.from(java.time.ZonedDateTime.now().toInstant());
        List<Event> eventsAllTime = eventRepository.findAllByEventIdLessThanAndDateAfterOrDateEquals(21, today, today);
        if(eventsAllTime.size() < 3) {
            model.addAttribute("error", true);
        } else {
            Collections.reverse(eventsAllTime);
            eventService.determinePlace(eventsAllTime, model);
            model.addAttribute("firstAllTime", (Event) model.getAttribute("firstPlace"));
            model.addAttribute("secondAllTime", (Event) model.getAttribute("secondPlace"));
            model.addAttribute("thirdAllTime", (Event) model.getAttribute("thirdPlace"));
            model.addAttribute("eventsAllTime", eventsAllTime);
        }

        List<Event> eventsByEventType;
        List<Event> eventsSearch = null;

        switch (filter) {
            case "0":
                eventsByEventType = eventsAllTime;
                break;
            case "1":
                eventsByEventType = eventRepository.findAllByEventTypeAndDateAfterOrDateEquals("Ausstellung", today, today);
                break;
            case "2":
                eventsByEventType = eventRepository.findAllByEventTypeAndDateAfterOrDateEquals("Festival", today, today);
                break;
            case "3":
                eventsByEventType = eventRepository.findAllByEventTypeAndDateAfterOrDateEquals("Konzert", today, today);
                break;
            case "4":
                eventsByEventType = eventRepository.findAllByEventTypeAndDateAfterOrDateEquals("Show", today, today);
                break;
            case "6":
                String searchString = (String) model.getAttribute("search");
                eventsSearch = eventRepository.findEventsByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(searchString, searchString);
            default:
                eventsByEventType = eventsAllTime;

        }
        if (filter.equals("6")){
            model.addAttribute("eventsSearch", eventsSearch);
            model.addAttribute("searched", true);
        }else {
            if (eventsByEventType.size() == 0) {
                model.addAttribute("noEvents", 0);
            } else if (eventsByEventType.size() < 3) {
                model.addAttribute("placeError", true);
                model.addAttribute("events", eventsByEventType);
            } else {
                Collections.sort(eventsByEventType, new Comparator<Event>() {
                    @Override
                    public int compare(Event event1, Event event2) {
                        return event1.getDate().compareTo(event2.getDate());
                    }
                });

                //Events will be sorted by their date, highest to smallest
                Collections.reverse(eventsByEventType);

                model.addAttribute("events", eventsByEventType);
                eventService.determinePlace(eventsByEventType, model);
            }
            model.addAttribute("searched", false);
        }
        return "index";
    }

    @GetMapping("/create_event")
    public String getCreateEventSite(@ModelAttribute Event event){
        return "create_event";
    }

    @PostMapping("/create_event")
    public String postCreateEventSite(@ModelAttribute Event event, Model model, @RequestParam("eventType") String eventType){
        boolean existing = false;
        List<Event> events = eventRepository.findAll();

        for (Event eventFor : events) {
            if (event.getName().equals(eventFor.getName())) {
                existing = true;
                break;
            }
        }
        if (existing) {
            model.addAttribute("existing", true);
            return "create_event";
        }
        else {

            int id = 0;

            for (Event ev : events) {
                if (ev.getEventId() > id) {
                    id = ev.getEventId();
                }
            }

            event.setEventId(id + 1);
            event.setName(event.getName());
            event.setLocation(event.getLocation());
            event.setDate(event.getDate());
            event.setDescription(event.getDescription());
            event.setDisinterest(0);
            event.setInterest(0);
            event.setIsDisliked(false);
            event.setIsLiked(false);

            switch (eventType) {
                case "1":
                    event.setEventType("Ausstellung");
                    break;
                case "2":
                    event.setEventType("Festival");
                    break;
                case "3":
                    event.setEventType("Konzert");
                    break;
                case "4":
                    event.setEventType("Show");
                    break;
            }
            model.addAttribute("existing", false);
            model.addAttribute("event", event);
            eventRepository.save(event);
        }

        getIndex(model, "0");
        return "/index";
    }

    @PostMapping("/search")
    public String postSearch(Model model, @RequestParam("searchstring") String searchString, @RequestParam(value = "searchbutton", required = false) String isPressed) {
        model.addAttribute("search", searchString);
        getIndex(model, isPressed);

        return "index";
    }


    @PostMapping("/ratingLike")
    public String postRatingLike(@RequestParam("eventId") Integer eventId, Model model) {
        Event event = eventRepository.findByEventId(eventId);
        if(event.getIsDisliked()){
            event.setDisinterest(event.getDisinterest() - 1);
        }
        event.setInterest(event.getInterest() + 1);
        event.setIsLiked(true);
        event.setIsDisliked(false);
        eventRepository.save(event);

        model.addAttribute(event);

        return "event_details";
    }


    @PostMapping("/ratingDislike")
    public String postRatingDislike(@RequestParam("eventId") Integer eventId, Model model) {
        Event event = eventRepository.findByEventId(eventId);
        if(event.getIsLiked()){
            event.setInterest(event.getInterest() - 1);
        }
        event.setDisinterest(event.getDisinterest() + 1);
        event.setIsLiked(false);
        event.setIsDisliked(true);
        eventRepository.save(event);

        model.addAttribute(event);

        return "event_details";
    }

    @GetMapping("/event_details")
    public String getEventDetails(Model model, @RequestParam("eventId") Integer eventId) {
        Event event = eventRepository.findByEventId(eventId);
        model.addAttribute("event", event);
        return "event_details";
    }


    @GetMapping("/index_all")
    public String getIndexAll(Model model) {
        List<Event> eventsAllTime = eventRepository.findAll();
        if(eventsAllTime.size() < 3) {
            model.addAttribute("error", true);
        } else {
            Collections.reverse(eventsAllTime);
            eventService.determinePlace(eventsAllTime, model);
            model.addAttribute("firstAllTime", (Event) model.getAttribute("firstPlace"));
            model.addAttribute("secondAllTime", (Event) model.getAttribute("secondPlace"));
            model.addAttribute("thirdAllTime", (Event) model.getAttribute("thirdPlace"));
            model.addAttribute("eventsAllTime", eventsAllTime);
        }

        Collections.sort(eventsAllTime, new Comparator<Event>() {
            @Override
            public int compare(Event event1, Event event2) {
                return event1.getDate().compareTo(event2.getDate());
            }
        });
        //Events will be sorted by their date, highest to smallest
        Collections.reverse(eventsAllTime);

        model.addAttribute("events", eventsAllTime);
        eventService.determinePlace(eventsAllTime, model);

        return "index_all";
    }

}