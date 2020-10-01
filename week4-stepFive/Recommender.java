
/**
 * Write a description of interface Recommender here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public interface Recommender {
    public ArrayList<String> getItemsToRate();
    public void printRecommendationsFor(String webRaterID);
}
