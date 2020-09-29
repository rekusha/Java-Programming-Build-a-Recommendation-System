
/**
 * Write a description of class MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*; 

public class MovieRunnerAverage {
    String fileMovies = "data/ratedmoviesfull.csv";
    String fileRatings = "data/ratings.csv";
    public void printAverageRatings(){
        SecondRatings obj = new SecondRatings(fileMovies, fileRatings);
        System.out.println("rater size " + obj.getRaterSize());
        System.out.println("movie size " + obj.getMovieSize());
        System.out.println("rating avg for 1798709 " + obj.getAverageByID("1798709", 1));
     
        System.out.println("------------------------//--------------------------");
        ArrayList<Rating> x = new ArrayList(obj.getAverageRatings(12));
        Collections.sort(x);
        for (Rating s : x){
            System.out.println(s.getItem() + " | " + s.getValue() +" " + obj.getTitle(s.getItem()));
        }
        System.out.println("------------------------//--------------------------");
        System.out.println(obj.getID("Her"));
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings obj = new SecondRatings(fileMovies, fileRatings);
        String name = new String("Vacation");
        System.out.println("rating avg for " + name + ": " + obj.getAverageByID(obj.getID(name), 0));
    }
    
}
