package uz.pdp;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    private AuthService authService;
    private final long ADMIN_ID = 6662050382l;

    public MyBot(AuthService authService) {
        this.authService = authService;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            User user = authService.getUserByChatId(chatId);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            if (user.getRole().equals(Role.ADMIN)) {
                //logika

                return;
            }

            switch (text) {

                case "/start" -> sendMessage.setText("Assalomu alaykum hurmatli foydalanuvchi. \n" +
                        "Botdan to'liq foydalanish uchun /register bo'limidan foydalanib ro'yxardan o'ting.");
                case "/register" -> {
                    user.setChatId(chatId);
                    user.setName(update.getMessage().getFrom().getFirstName());

                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Tabriklaymiz siz muvaffaqiyatli qo'yxatdan o'tdingiz\n" +
                            "/events komandasi orqali mavjud tadbirlarni ko'rishingiz mumkin");
                }
                case "/events" -> {
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("");
                }
                case "/my_events" -> {
                    sendMessage.setChatId(chatId);
                    sendMessage.setText(user.getEventList().toString());
                }
            }

            execute(sendMessage);
        }
    }

    public Message sendMessage(long chatId, String message) {
        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(chatId)
                .text(message)
                .build();
        try {
            return execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    public MyBot() {
        super("7631684843:AAFDfMxQEqaq53TCc4FLfRp0l99IXqSw1Ik");
    }

    @Override
    public String getBotUsername() {
        return "https://t.me/todoistR24_bot";
    }

}
