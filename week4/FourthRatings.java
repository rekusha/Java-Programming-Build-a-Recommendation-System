
/**
 * Write a description of class FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile){
        RaterDatabase.initialize(ratingsfile);
    }

    public double getAverageByID(String id, int minimalRaters){
        int count = 0;
        double sumRates = 0.0;
        for (Rater rate : RaterDatabase.getRaters()){
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
    
    private double dotProduct(Rater me, Rater r){
        double sum = 0;
        for (String movie : me.getItemsRated()){
            if(r.hasRating(movie)){
                sum += ((me.getRating(movie)-5) * (r.getRating(movie)-5));
            }
        }
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> result = new ArrayList();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()){
            double compare = dotProduct(me, r);
            if ((r!=me) & (compare >0)){
                result.add(new Rating(r.getID(), compare));
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> resultList = new ArrayList();
        ArrayList<Rating> raterList = getSimilarities(id);
        ArrayList<String> movieIDList = new ArrayList();
        
        HashMap<String,Double> similarMap = new HashMap();
        int minInd = Math.min(getSimilarities(id).size(), numSimilarRaters);
        
        for(Rating similar : getSimilarities(id).subList(0, minInd)){
            if(similar.getValue() > 0){
		similarMap.put(similar.getItem(), similar.getValue());
            }
	}
    
	for(String movieID : MovieDatabase.filterBy(filterCriteria)){
	    int count = 0;
	    double total = 0;
	    for(Rater curRater : RaterDatabase.getRaters()){
		double rating = -1;
		if(similarMap.containsKey(curRater.getID()) && curRater.hasRating(movieID)){
    		    rating = curRater.getRating(movieID) * similarMap.get(curRater.getID());
		}

		if(rating != -1){
    		    count++;
    		    total += rating;
    		}
            }

            if(count >= minimalRaters & total > 0){
                resultList.add(new Rating(movieID, total/count));
            }
	}
	Collections.sort(resultList, Collections.reverseOrder());
        return resultList;
    }
}
