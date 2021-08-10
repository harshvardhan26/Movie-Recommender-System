
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings(String ratingsfile) {
    	FirstRatings fr = new FirstRatings();
    	myRaters = fr.loadRaters("data/" + ratingsfile);
    }
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public int getRaterSize() {
    	return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
    	double total = 0, raterCount = 0;
    	for(Rater rater : myRaters) {
    		if(rater.hasRating(id)) {
    			double rating = rater.getRating(id);
    			total += rating;
    			raterCount++;
    		}
    	}
    	
    	if(raterCount >= minimalRaters)
    		return total / raterCount;
    	
    	else
    		return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    	ArrayList<Rating> ratingList = new ArrayList<Rating>();
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	for(String movieId : movies) {
    		double averageRating = getAverageByID(movieId, minimalRaters);
    		if(averageRating != 0.0)
    			ratingList.add(new Rating(movieId, averageRating));
    	}
    	
    	return ratingList;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
    	ArrayList<Rating> ratingList = new ArrayList<Rating>();
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
    	for(String movieId : movies) {
    		double averageRating = getAverageByID(movieId, minimalRaters);
    		if(averageRating != 0.0)
    			ratingList.add(new Rating(movieId, averageRating));
    	}
    	
    	return ratingList;
    }
}