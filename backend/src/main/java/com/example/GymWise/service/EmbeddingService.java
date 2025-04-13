package com.example.GymWise.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class EmbeddingService {

    private final WebClient webClient;

    public EmbeddingService(@Value("${OPENAI_API_KEY}") String apiKey) {

        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public CompletableFuture<List<Double>> generateEmbeddingAsync(String input) {
        Map<String, Object> request = Map.of(
                "model", "text-embedding-ada-002",
                "input", input
        );

        return webClient.post()
                .uri("/embeddings")
                .bodyValue(request)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class).flatMap(errorBody -> {
                            System.err.println("OpenAI API Error: " + errorBody);
                            return Mono.error(new RuntimeException("Embedding generation failed: " + errorBody));
                        }))
                .bodyToMono(Map.class)
                .map(response -> {
                    List<?> data = (List<?>) response.get("data");
                    Map<?, ?> firstItem = (Map<?, ?>) data.get(0);
                    return (List<Double>) firstItem.get("embedding");
                })
                .toFuture();
    }




}
