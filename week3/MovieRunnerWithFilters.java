
/**
 * Write a description of class MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    String fileRatings = "data/ratings.csv";
    String fileMovies = "data/ratedmoviesfull.csv";
    int minimumRating = 3;
    int yearFilter = 1990;
    String filtreGenre = "Drama";
    int minLength = 90;
    int maxLength = 180;
    String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
    
    public void printAverageRatings(){
        ThirdRatings obj = new ThirdRatings(fileRatings);
        System.out.println("read data for " +  obj.getRaterSize() + " raters");
        MovieDatabase.initialize(fileMovies);
        System.out.println("read data for "  + MovieDatabase.size() + " movies");
        ArrayList<Rating> x = new ArrayList(obj.getAverageRatings(minimumRating));
        System.out.println("found "+x.size()+" movies ");
        Collections.sort(x);
        for (Rating s : x){
            System.out.println(s.getValue() +" " + MovieDatabase.getTitle(s.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings obj = new ThirdRatings(fileRatings);
        System.out.println("read data for " +  obj.getRaterSize() + " raters");
        MovieDatabase.initialize(fileMovies);
        System.out.println("read data for "  + MovieDatabase.size() + " movies");
        ArrayList<Rating> x = new ArrayList(obj.getAverageRatingsByFilter(minimumRating, new YearAfterFilter(yearFilter)));
        System.out.println("found "+x.size()+" movies ");
        Collections.sort(x);
        for (Rating s : x){
            System.out.println(s.getValue() +" " + MovieDatabase.getYear(s.getItem()) + " " + MovieDatabase.getTitle(s.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        GenreFilter filter = new GenreFilter(filtreGenre);
        ThirdRatings obj = new ThirdRatings(fileRatings);
        System.out.println("read data for " +  obj.getRaterSize() + " raters");
        MovieDatabase.initialize(fileMovies);
        System.out.println("read data for "  + MovieDatabase.size() + " movies");
        ArrayList<Rating> x = new ArrayList(obj.getAverageRatingsByFilter(minimumRating, filter));
        System.out.println("found "+x.size()+" movies ");
        Collections.sort(x);
        for (Rating s : x){
            System.out.println(s.getValue() +" " + MovieDatabase.getTitle(s.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(s.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        MinutesFilter filter = new MinutesFilter(minLength, maxLength);
        ThirdRatings obj = new ThirdRatings(fileRatings);
        System.out.println("read data for " +  obj.getRaterSize() + " raters");
        MovieDatabase.initialize(fileMovies);
        System.out.println("read data for "  + MovieDatabase.size() + " movies");
        ArrayList<Rating> x = new ArrayList(obj.getAverageRatingsByFilter(minimumRating, filter));
        System.out.println("found "+x.size()+" movies ");
        Collections.sort(x);
        for (Rating s : x){
            System.out.println(s.getValue() +" Time: " + MovieDatabase.getMinutes(s.getItem()) + " " + MovieDatabase.getTitle(s.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors(){
        DirectorsFilter filter = new DirectorsFilter(directors);
        ThirdRatings obj = new ThirdRatings(fileRatings);
        System.out.println("read data for " +  obj.getRaterSize() + " raters");
        MovieDatabase.initialize(fileMovies);
        System.out.println("read data for "  + MovieDatabase.size() + " movies");
        ArrayList<Rating> x = new ArrayList(obj.getAverageRatingsByFilter(minimumRating, filter));
        System.out.println("found "+x.size()+" movies ");
        Collections.sort(x);
        for (Rating s : x){
            System.out.println(s.getValue() +" " + MovieDatabase.getTitle(s.getItem()));
            System.out.println("    "+MovieDatabase.getDirector(s.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters filters = new AllFilters();
        ThirdRatings obj = new ThirdRatings(fileRatings);
        MovieDatabase.initialize(fileMovies);
        
        filters.addFilter(new YearAfterFilter(yearFilter));
        filters.addFilter(new GenreFilter(filtreGenre));
        
        System.out.println("read data for " +  obj.getRaterSize() + " raters");
        System.out.println("read data for "  + MovieDatabase.size() + " movies");
        ArrayList<Rating> x = new ArrayList(obj.getAverageRatingsByFilter(minimumRating, filters));
        System.out.println("found "+x.size()+" movies ");
        Collections.sort(x);
        for (Rating s : x){
            System.out.println(s.getValue() +" " + MovieDatabase.getYear(s.getItem()) + " " + MovieDatabase.getTitle(s.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(s.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        AllFilters filters = new AllFilters();
        ThirdRatings obj = new ThirdRatings(fileRatings);
        MovieDatabase.initialize(fileMovies);
        
        filters.addFilter(new MinutesFilter(minLength, maxLength));
        filters.addFilter(new DirectorsFilter(directors));
        
        System.out.println("read data for " +  obj.getRaterSize() + " raters");
        System.out.println("read data for "  + MovieDatabase.size() + " movies");
        ArrayList<Rating> x = new ArrayList(obj.getAverageRatingsByFilter(minimumRating, filters));
        System.out.println("found "+x.size()+" movies ");
        Collections.sort(x);
        for (Rating s : x){
            System.out.println(s.getValue() +" Time: " + MovieDatabase.getMinutes(s.getItem()) + " " + MovieDatabase.getTitle(s.getItem()));
            System.out.println("    "+MovieDatabase.getDirector(s.getItem()));
        }
    }
}
