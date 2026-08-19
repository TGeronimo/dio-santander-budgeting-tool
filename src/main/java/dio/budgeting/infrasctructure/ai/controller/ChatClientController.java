package dio.budgeting.infrasctructure.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatClientController {

    private final ChatClient chatClient;


//    O SpringBoot não sabe instanciar o ChatClient, então foi necessário criar um bean em BudgetingApplication.java
    public ChatClientController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat(String prompt) {
        return this.chatClient.prompt().user(prompt).call().content();
    }

}
