
/**
 * Write a description of class RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * https://www.dukelearntoprogram.com//capstone/recommender.php?id=WUK6M0jokFxcP3
 */
import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> result = new ArrayList();
        ArrayList<String> movID = MovieDatabase.filterBy(new TrueFilter());
        Random rand = new Random();
        for (int i = 0; result.size() < 10; i++) {
            int random = rand.nextInt(movID.size());
            if (!result.contains(movID.get(random)))
                result.add(movID.get(random));
        }
        return result;
    }
    
    public void printRecommendationsFor(String webRaterID){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        ArrayList<Rating> ratingList = (new FourthRatings()).getSimilarRatings(webRaterID, 20, 5);
        if (ratingList.size() == 0) {
            System.out.println("<h2>no movie based on your rating!</h2>");
        } else {
            ArrayList<String> movieToBeRate = getItemsToRate();
            ArrayList<Rating> outID = new ArrayList<>();
            int count = 0;
            for (int i = 0; outID.size() + count != ratingList.size() && outID.size() < 10; i++) {
                if (!movieToBeRate.contains(ratingList.get(i).getItem())) {
                    outID.add(ratingList.get(i));
                } else {
                    count++;
                }
            }
            System.out.println("size = " + outID.size());
            System.out.println("<style>");
            System.out.println("h2,h3{text-align:center;height:50px;line-height:50px;font-family:Arial,Helvetica,sans-serif;color:#ff6600}");
            System.out.println("table{border-collapse:collapse;margin:auto;}");
            System.out.println("table,th,td{font-size:15px;padding: 1px 4px 1px 4px;}");
            System.out.println("td img{display:block;margin-left:auto;margin-right:auto;}");
            System.out.println("th {height:40px;font-size:18px;background-color:black;color:white;text-align:center;}");
            System.out.println("tr:nth-child(even) {background-color:#f2f2f2;}");
            System.out.println("tr:nth-child(odd) {background-color:#cccccc;}");
            System.out.println("tr:hover {background-color:#666666;color:white;}");
            System.out.println("table td:first-child {text-align:center;}");
            System.out.println("tr {font-family:Arial,Helvetica,sans-serif;}");
            System.out.println(".rating{color:#ff6600;padding:0px 10px;font-weight:bold;}");
            System.out.println("</style>");
            System.out.println("<h2>Best Movies</h2><table id = \"rater\"><tr><th>Rank</th><th>Poster</th><th>Title & Rating</th><th>Genre</th><th>Country</th></tr>");

            int rank = 1;
            for (Rating i : outID) {
                System.out.println(
                "<tr><td>" + 
                rank + 
                "</td><td><img src = \"" + 
                MovieDatabase.getPoster(i.getItem()) + 
                "\" width=\"100\" height=\"140\"></td><td>" + 
                MovieDatabase.getYear(i.getItem()) + 
                "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
                i.getItem() + 
                "\">" + 
                MovieDatabase.getTitle(i.getItem()) + 
                "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;" + 
                String.format("%.2f", i.getValue()) + 
                "/10</td><td>" + 
                MovieDatabase.getGenres(i.getItem()) + 
                "</td><td>" + 
                MovieDatabase.getCountry(i.getItem()) + 
                "</td></tr>");
                rank++;
            }
        }
        System.out.println("</table>");
    }
}
