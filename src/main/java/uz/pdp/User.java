package uz.pdp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
public class User {
    private Long chatId;
    private String name;
    private Role role;
    private List<Event> eventList;
}
