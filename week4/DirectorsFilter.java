
/**
 * Write a description of class DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Arrays;

public class DirectorsFilter implements Filter{
    private String director;
    
    public DirectorsFilter(String director){
        this.director = director;
    }
    
    public boolean satisfies(String id){
        ArrayList<String> dirList = new ArrayList(Arrays.asList(director.split(",")));
        for (String name : dirList){
            if (MovieDatabase.getDirector(id).contains(name)){
                return true;
            }
        }
        return false;
    }
}
