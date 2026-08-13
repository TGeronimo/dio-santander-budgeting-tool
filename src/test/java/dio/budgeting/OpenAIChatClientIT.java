package dio.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
class OpenAIChatClientIT {

    @Autowired
    OpenAiChatModel openAiChatModel;

    @Test
    void should_executeSum_whenPrompted(){
        var chatClient = ChatClient.builder(openAiChatModel)
                .defaultSystem("Você é um matemático.")
                .build();

        var response = chatClient.prompt("Some 25 com 42 e depois subtraia 30 do resultado. Exiba apenas o resultado, sem dar explicações.")
                .call()
                .content();

        assertThat(response).contains("37");
        System.out.println(response);
    }

}
