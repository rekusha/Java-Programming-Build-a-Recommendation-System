
/**
 * Write a description of class MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Collections;
public class MovieRunnerSimilarRatings {
    int minimumRating = 3;
    String fileRatings = "ratings.csv";
    String fileMovies = "ratedmoviesfull.csv";
    int yearFilter = 1975;
    int minLength = 70;
    int maxLength = 200;
    String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
    String filtreGenre = "Drama";
    int minimalRaters = 5;
    String id = "314";
    int numSimilarRaters = 10;
    
    public void printAverageRatings(){
        FourthRatings obj = new FourthRatings(fileRatings);
        System.out.println("read data for " +  RaterDatabase.size() + " raters");
        
        MovieDatabase.initialize(fileMovies);
        
        System.out.println("read data for "  + MovieDatabase.size() + " movies");
        
        ArrayList<Rating> x = new ArrayList(obj.getAverageRatings(minimumRating));
        
        System.out.println("found "+x.size()+" movies ");
        Collections.sort(x);
        for (Rating s : x){
            System.out.println(s.getValue() +" " + MovieDatabase.getTitle(s.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters filters = new AllFilters();
        FourthRatings obj = new FourthRatings(fileRatings);
        MovieDatabase.initialize(fileMovies);
        
        filters.addFilter(new YearAfterFilter(yearFilter));
        filters.addFilter(new GenreFilter(filtreGenre));
        
        System.out.println("read data for " +  RaterDatabase.size() + " raters");
        System.out.println("read data for "  + MovieDatabase.size() + " movies");
        
        ArrayList<Rating> x = new ArrayList(obj.getAverageRatingsByFilter(minimumRating, filters));
        
        System.out.println("found "+x.size()+" movies ");
        Collections.sort(x);
        for (Rating s : x){
            System.out.println(s.getValue() +" " + MovieDatabase.getYear(s.getItem()) + " " + MovieDatabase.getTitle(s.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(s.getItem()));
        }
    }
    
    public void printSimiliarRatings(){
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize(fileRatings);
        MovieDatabase.initialize(fileMovies);

        ArrayList<Rating> recommendations= obj.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        
        System.out.println(recommendations.size() + " movie matched");
        System.out.println("movie Sim Ratings: " + recommendations);

        for(Rating rating : recommendations){
            String movieTitle = MovieDatabase.getTitle(rating.getItem());
            System.out.println(movieTitle + " : " + rating.getValue());
        }
    }
    
    public void printSimiliarRatingsByGenre(){
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize(fileRatings);
        MovieDatabase.initialize(fileMovies);
        
        Filter gr = new GenreFilter(filtreGenre);
        
        ArrayList<Rating> recommendations = obj.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, gr);
        
        System.out.println(recommendations.size() + " movie matched");

        for(Rating rating : recommendations){
            String movieTitle = MovieDatabase.getTitle(rating.getItem());
            System.out.println(movieTitle + " : " + rating.getValue());
        }
    }
    
    public void printSimiliarRatingsByDirector(){
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize(fileRatings);
        MovieDatabase.initialize(fileMovies);
        
        Filter dr = new DirectorsFilter(directors);
        
        ArrayList<Rating> recommendations = obj.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, dr);
        
        System.out.println(recommendations.size() + " movie matched");

        for(Rating rating : recommendations){
            String movieTitle = MovieDatabase.getTitle(rating.getItem());
            System.out.println(movieTitle + " : " + rating.getValue());
        }
    }
    
    public void printSimiliarRatingsByGenreAndMinutes(){
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize(fileRatings);
        MovieDatabase.initialize(fileMovies);

   	Filter gr = new GenreFilter(filtreGenre);        
        Filter mr = new MinutesFilter(minLength, maxLength);
        
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(mr);
        filtersList.addFilter(gr);

        ArrayList<Rating> recommendations = obj.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filtersList);
        System.out.println(recommendations.size() + " movie matched");

        for(Rating rating : recommendations){
            String movieTitle = MovieDatabase.getTitle(rating.getItem());
            System.out.println(movieTitle + " : " + rating.getValue());
        }
    }
    
    public void printSimiliarRatingsByYearAfterAndMinutes(){
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize(fileRatings);
        MovieDatabase.initialize(fileMovies);

        Filter yf = new YearAfterFilter(yearFilter);
        Filter mr = new MinutesFilter(minLength, maxLength);
        
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(mr);
        filtersList.addFilter(yf);

        ArrayList<Rating> recommendations = obj.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filtersList);
        System.out.println(recommendations.size() + " movie matched");

        for(Rating rating : recommendations){
            String movieTitle = MovieDatabase.getTitle(rating.getItem());
            System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }
}
