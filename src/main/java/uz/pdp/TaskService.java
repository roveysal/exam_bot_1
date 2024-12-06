package uz.pdp;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private MyBot myBot;
    private List<Event> eventList = new ArrayList<Event>();

    public TaskService(MyBot myBot) {
        this.myBot = myBot;
    }

    public Event getEventById(Long id) {
        return eventList.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Event create(Long id) {
        Event user = Event.builder()
                .id(id)
                .build();
        eventList.add(user);
        return user;
    }
}
