package com.example.G1720ssweb.data.eng.Service;

import com.example.G1720ssweb.data.eng.DataAccessObjects.EventRepository;
import com.example.G1720ssweb.data.eng.DataTransferObjects.Event;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    EventRepository eventRepository;

    public List<Event> compareDateToday(Date today, List<Event> events){
        List<Event> eventsByEventType = null;
        for (Event ev :events) {
            if (ev.getDate().compareTo(today) == 0 || ev.getDate().compareTo(today) > 0){
                eventsByEventType.add(ev);
            }
        }
        return eventsByEventType;
    }

    public void determinePlace(List<Event> events, Model model) {
        Event firstPlace = null, secondPlace = null, thirdPlace = null;
        Integer likesFirstPlace = 0, likesSecondPlace = 0, likesThirdPlace = 0;
        for (Event event : events) {
            if (event.getInterest() > likesFirstPlace) {
                thirdPlace = secondPlace;
                secondPlace = firstPlace;
                firstPlace = event;
                if (secondPlace != null && thirdPlace != null) {
                    likesFirstPlace = firstPlace.getInterest();
                    likesSecondPlace = secondPlace.getInterest();
                    likesThirdPlace = thirdPlace.getInterest();
                }
                if (secondPlace != null && thirdPlace == null) {
                    likesFirstPlace = firstPlace.getInterest();
                    likesSecondPlace = secondPlace.getInterest();
                }
                if (secondPlace == null) {
                    likesFirstPlace = firstPlace.getInterest();
                }
            } else if (event.getInterest() > likesSecondPlace) {
                thirdPlace = secondPlace;
                secondPlace = event;
                if (thirdPlace != null) {
                    likesSecondPlace = secondPlace.getInterest();
                    likesThirdPlace = thirdPlace.getInterest();
                } else {
                    likesSecondPlace = secondPlace.getInterest();
                }
            } else if (event.getInterest() > likesThirdPlace) {
                thirdPlace = event;
                likesThirdPlace = event.getInterest();
            }
            model.addAttribute("firstPlace", firstPlace);
            model.addAttribute("secondPlace", secondPlace);
            model.addAttribute("thirdPlace", thirdPlace);
        }
    }
}
