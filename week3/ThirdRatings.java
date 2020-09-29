
/**
 * Write a description of class ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings data = new FirstRatings();
        myRaters = data.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){
        int count = 0;
        double sumRates = 0.0;
        for (EfficientRater rate : myRaters){
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
            for (String mov : movies){
                rate = getAverageByID(mov, minimalRaters);
                if (rate > 0.0){
                    result.add(new Rating(mov, rate));
                }
            }
        return result;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String id : movies){
            double avg = getAverageByID(id, minimalRaters);
            if(avg>0.0){
                Rating currRating = new Rating(id,avg);
                result.add(currRating);
            }
        }
        
        return result;
    }
    
}
