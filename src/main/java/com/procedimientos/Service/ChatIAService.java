package com.procedimientos.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.procedimientos.Model.ChatChoiceWrapper;
import com.procedimientos.Model.ChatRequest;
import com.procedimientos.Model.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ChatIAService {

    @Value("${openrouter.api.key}")
    private String apiKey;

    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    public ChatResponse getIAResponse(ChatRequest request) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String body = """
                {
                    "model": "meta-llama/llama-3-8b-instruct",
                    "messages": [
                        {
                            "role": "system",
                            "content": "Eres un asistente técnico experto en normativas eléctricas colombianas. Cuando el usuario haga una pregunta relacionada con el RETIE, RETILAP o normas eléctricas legales en Colombia, debes responder de forma directa, profesional y específica, sin saludar ni pedir contexto adicional. No repitas que estás listo para ayudar. Si la pregunta no tiene sentido, responde 'No tengo información suficiente para responder eso', solo responde de este tema y no de ninguno más."
                        },
                        {
                            "role": "user",
                            "content": "%s"
                        }
                    ]
                }
            """.formatted(request.getQuestion());

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("HTTP-Referer", "https://procedimientos.onrender.com")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());

            if (root.has("error")) {
                String errorMsg = root.get("error").get("message").asText();
                return new ChatResponse("❌ Error del modelo: " + errorMsg);
            }

            ChatChoiceWrapper result = mapper.treeToValue(root, ChatChoiceWrapper.class);
            String content = result.choices.get(0).message.content;

            return new ChatResponse(content);

        } catch (Exception e) {
            return new ChatResponse("❌ Error al procesar la solicitud: " + e.getMessage());
        }
    }
}
