package Ollama;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.chat.OllamaChatMessageRole;
import io.github.ollama4j.models.chat.OllamaChatRequest;
import io.github.ollama4j.models.chat.OllamaChatRequestBuilder;
import io.github.ollama4j.models.chat.OllamaChatResult;

import java.util.Scanner;

public class ChatWithCustomSystemPrompt {

    public static void main(String[] args) throws Exception {
        Ollama ollama = new Ollama("http://localhost:11434");
        String model = "mistral:7b";
        Scanner sc = new Scanner(System.in);

        String systemPrompt ="Você é Alex, um tutor de inglês prestativo e paciente. "+
                "Seu objetivo principal é ajudar o usuário a melhorar suas habilidades Inglês com pronuncias, por exemplo," +
                " ensinar como deve ser feita a pronuncia de maçã, em inglês apple, que deve ser dito Lentamente ap-ul." +
                "REGRAS:" +
                "1. SEMPRE responda exclusivamente em português, no caso, PT-BR." +
                "2. Mantenha a conversa natural e envolvente. Faça perguntas complementares." +
                "3. Se o usuário cometer um erro significativo de gramática ou vocabulário, corrija-o gentilmente. Forneça a versão correta e uma explicação breve e simples." +
                "4. Após a correção, continue a conversa suavemente. O objetivo principal é a prática, não a perfeição." +
                "5. Comece a conversa se apresentando e perguntando ao usuário sobre o que ele gostaria de conversar hoje."+
                "6. Em toda interação, comece com um emoji que represente uma 'emoção' com relação ao assunto"+
                "7. A pronuncia a ser ensinada deve ser lido como se fosse em português mas soar como se estivesse falando em inglês americano!";

        System.out.println("Chat with Alex, your English Language Tutor!");

        while (true) {
            System.out.print("You: ");
            String userInput = sc.nextLine();

            if ("out".equalsIgnoreCase(userInput)) {
                System.out.println("Alex: Bye, see you again.");
                break;
            }
            OllamaChatRequest requestModel = OllamaChatRequestBuilder.builder()
                    .withModel(model)
                    .withMessage(OllamaChatMessageRole.SYSTEM, systemPrompt)
                    .withMessage(OllamaChatMessageRole.USER, userInput)
                    .build();

            OllamaChatResult chatResult = ollama.chat(requestModel, null);

            String aiResponseText = chatResult.getResponseModel().getMessage().getResponse();

            System.out.println("Alex: " + aiResponseText);
        }
        sc.close();
    }
}