package com.procedimientos.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.procedimientos.Model.ChatChoiceWrapper;
import com.procedimientos.Model.ChatRequest;
import com.procedimientos.Model.ChatResponse;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ChatIAService {

    private static final String API_KEY = "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" ; // <-- tu API key aquí
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
                            "content": "Eres un asistente técnico experto en normativas eléctricas colombianas. Cuando el usuario haga una pregunta relacionada con el RETIE, RETILAP o normas eléctricas legales en Colombia, debes responder de forma directa, profesional y específica, sin saludar ni pedir contexto adicional. No repitas que estás listo para ayudar. Si la pregunta no tiene sentido, responde 'No tengo información suficiente para responder eso', solo responde de este tema y no de ninguno mas."
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
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("HTTP-Referer", "https://procedimientos.onrender.com")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());

            // 🔍 Verifica si hay error
            if (root.has("error")) {
                String errorMsg = root.get("error").get("message").asText();
                return new ChatResponse("❌ Error del modelo: " + errorMsg);
            }

            // ✅ Extrae respuesta del modelo
            ChatChoiceWrapper result = mapper.treeToValue(root, ChatChoiceWrapper.class);
            String content = result.choices.get(0).message.content;

            return new ChatResponse(content);

        } catch (Exception e) {
            return new ChatResponse("❌ Error al procesar la solicitud: " + e.getMessage());
        }
    }

}
