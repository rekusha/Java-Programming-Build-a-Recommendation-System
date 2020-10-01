/**
 * Write a description of class FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;


public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movieData = new ArrayList();
        CSVParser parser = new FileResource(filename).getCSVParser();
        for (CSVRecord record : parser){
            String id = record.get("id").trim();
            String title = record.get("title").trim();
            String year = record.get("year").trim();
            String country = record.get("country").trim();
            String genre = record.get("genre").trim();
            String director = record.get("director").trim();
            String poster = record.get("poster").trim();
            int minutes = Integer.parseInt(record.get("minutes").trim());
            Movie mov = new Movie(id,title,year,genre,director,country,poster,minutes);
            movieData.add(mov);
        }
        return movieData;
    }
    
    public ArrayList<EfficientRater> loadRaters(String filename){
        CSVParser parser = new FileResource(filename).getCSVParser();
        HashMap <String, EfficientRater> map = new HashMap();
        for (CSVRecord record : parser){
        String rater_id = record.get("rater_id");
        String movie_id = record.get("movie_id");
        String rating = record.get("rating");
            if (map.keySet().contains(rater_id)){
                map.get(rater_id).addRating(movie_id, Double.parseDouble(rating));
            }else{
                map.put(rater_id, new EfficientRater(rater_id));
                map.get(rater_id).addRating(movie_id, Double.parseDouble(rating));
            }
        }
        ArrayList<EfficientRater> rater = new ArrayList(map.values());
        return rater;
    }
    
    public void testLoadRaters(){
        ArrayList<EfficientRater> data = loadRaters("data/ratings.csv");
        System.out.println("total number of raters: " + data.size());
        String rater_id = "193";
        int max_rates = 0;
        HashMap<String, Integer> numRatesMovie = new HashMap();
        for (EfficientRater x : data){
            if (x.getID().equals(rater_id)){
                System.out.println("number of ratings for a particular rater (by rater_id): " + x.numRatings());
            }
            if (x.numRatings()>max_rates){
                max_rates = x.numRatings();
            }
            for (String movie_id : x.getItemsRated()){
                if (!numRatesMovie.keySet().contains(movie_id)){
                    numRatesMovie.put(movie_id, 1);
                } else {
                    numRatesMovie.put(movie_id, numRatesMovie.get(movie_id)+1);
                }
            }
        }
        System.out.println("MAX number of ratings by any rater: " + max_rates);
        System.out.println("number of ratings a particular movie (by movie_id): " + numRatesMovie.get("1798709"));
        System.out.println("how many different movies have been rated by all these raters: " + numRatesMovie.keySet().size());
        for (EfficientRater x : data){
            if (x.numRatings()==max_rates){
                System.out.println(x.getID());
            }
        }
    }
    
    
    public void testLoadMovies (){
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("number of movies: " + movies.size());
        int comedyCount = 0;
        int lenCount = 0;
        HashMap<String, Integer> numOfMov = new HashMap();
        int maxFilms = 0;
        
        /*
        for (int i=0; i<movies.size(); i++){
            System.out.println(movies.get(i));
        }
        */
        
        for (int i=0; i<movies.size(); i++){
            if (movies.get(i).getGenres().contains("Comedy")){
                comedyCount++;
            }
            if (movies.get(i).getMinutes()>150){
                lenCount++;
            }
            for (String n : movies.get(i).getDirector().split(",")){
                if (numOfMov.containsKey(n)){
                    numOfMov.replace(n, numOfMov.get(n)+1);
                } else {
                numOfMov.put(n, 1);
                }
            }
        }
        maxFilms = Collections.max(numOfMov.values());
        System.out.println("movies include the Comedy genre: " + comedyCount);
        System.out.println("movies are greater than 150 minutes: " + lenCount);
        System.out.println("------------------///-----------------");
        System.out.println("maximum number of movies by any director: " + maxFilms);
        for (String name : numOfMov.keySet()){
            if (numOfMov.get(name) == maxFilms){
                System.out.println(name);
            }
        }
        System.out.println("------------------///-----------------");
    }
}
