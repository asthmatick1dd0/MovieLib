package com.asthmkd.app.MovieLib.Service;

import com.asthmkd.app.MovieLib.Models.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OMDbService {
    private final WebClient webClient;
    private final String apiKey = "a5ddde9a";

    public OMDbService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://www.omdbapi.com").build();
    }

    public Movie fetchMovieDetails(String title) {
        String url = "http://www.omdbapi.com/?apikey=" + apiKey + "&t=" + title;

        OMDbResponse response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(OMDbResponse.class)
                .block();

        if (response == null ) {
            throw new RuntimeException("Movie not found in OMDb API");
        }


        String jsonResponse = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class) // Получите ответ в виде строки
                .block();

        System.out.println("JSON Response: " + jsonResponse); // Логируем ответ

        //Преобразуем в объект Movie
        Movie movie = new Movie();
        movie.setTitle(response.getTitle());
        movie.setGenre(response.getGenre());
        movie.setReleaseDate(response.getReleaseDate());
        movie.setUserRating(movie.getUserRating());
        movie.setStatus(movie.getStatus());
        return movie;
    }
}
