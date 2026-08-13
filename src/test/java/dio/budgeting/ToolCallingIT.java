package dio.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
class ToolCallingIT {

    @Autowired
    OpenAiChatModel openAiChatModel;

    static class MathTools {
        @Tool(description = "soma dois números inteiros, a e b")
        public int sum(int a, int b) {
            return a + b;
        }

        @Tool(description = "subtrai dois números inteiros, a e b")
        public int diff(int a, int b) {
            return a - b;
        }
    }

    @Test
    void should_executeSum_whenPrompted(){
        var chatClient = ChatClient.builder(openAiChatModel)
                .defaultSystem("Você é um matemático.")
                .defaultTools(new MathTools())
                .build();

        var response = chatClient.prompt("Some 25 com 42 e depois subtraia 30 do resultado. Exiba apenas o resultado em uma frase curta, sem dar explicações.")
                .call()
                .content();

        assertThat(response).contains("37");
        System.out.println(response);
    }

}
