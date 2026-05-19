package spring_boot.session04ex03.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    static class Movie {
        private String movieId;
        private String movieName;
        private String genre;
        private double rating;

        public Movie() {
        }

        public Movie(String movieId, String movieName, String genre, double rating) {
            this.movieId = movieId;
            this.movieName = movieName;
            this.genre = genre;
            this.rating = rating;
        }

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }

    private List<Movie> movieList() {

        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("M001", "Harry Potter", "Fantasy", 8.8));
        movies.add(new Movie("M002", "Queen Of Tears", "KDrama", 8.6));
        movies.add(new Movie("M003", "Annabel", "Sci-Fi", 8.7));

        return movies;
    }

    //Tìm phim theo id
    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable String movieId) {
        return movieList()
                .stream()
                .filter(movie -> movie.getMovieId().equals(movieId))
                .findFirst()
                .orElse(null);
    }

    //Tìm phim theo thể loại
    @GetMapping
    public List<Movie> getMoviesByGenre(@RequestParam String genre) {
        return movieList()
                .stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }
}
