package com.nasaimages;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) {
        String apiKey = "DEMO_KEY";
        String url = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            String title = jsonObject.get("title").getAsString();
            String explanation = jsonObject.get("explanation").getAsString();
            String imageUrl = jsonObject.get("url").getAsString();

            System.out.println("Título: " + title);
            System.out.println("Descrição: " + explanation);
            System.out.println("Imagem do dia: " + imageUrl);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

