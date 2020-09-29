
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings data = new FirstRatings();
        myMovies = data.loadMovies(moviefile);
        myRaters = data.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){
        int count = 0;
        double sumRates = 0.0;
        for (Rater rate : myRaters){
            if (rate.getItemsRated().contains(id)){
                if (rate.hasRating(id)){
                    sumRates += rate.getRating(id);
                }
                count +=1;
            }
        }
        if (count >= minimalRaters){ 
            return sumRates/count;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        double rate;
        ArrayList<Rating> result = new ArrayList<Rating>();
            for (Movie mov : myMovies){
                rate = getAverageByID(mov.getID(), minimalRaters);
                if (rate > 0.0){
                    result.add(new Rating(mov.getID(), rate));
                }
            }
        return result;
    }
    
    public String getTitle(String id){
        for (Movie x : myMovies){
            if (x.getID().equals(id)){
                return x.getTitle();
            }
        }
        return "ID was not found";
    }
    
    public String getID(String title){
        for (Movie x : myMovies){
            if (x.getTitle().equals(title)){
                return x.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}
