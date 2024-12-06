package uz.pdp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthService {
    private MyBot myBot;
    private List<User> users = new ArrayList<User>();

    public AuthService(MyBot myBot) {
        this.myBot = myBot;
    }

    public User getUserByChatId(Long chatId) {
        Optional<User> user = users.stream()
                .filter(u -> u.getChatId() == chatId)
                .findFirst();

        return user.orElseGet(() -> create(chatId));
    }

    public User create(Long chatId) {
        User user = User.builder()
                .chatId(chatId)
                .role(Role.USER)
                .build();
        users.add(user);
        return user;
    }
}
