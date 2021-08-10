
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
    
    public SecondRatings(String moviefile, String ratingsfile) {
    	FirstRatings fr = new FirstRatings();
    	myMovies = fr.loadMovies("data/" + moviefile);
    	myRaters = fr.loadRaters("data/" + ratingsfile);
    }
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public int getMovieSize() {
    	return myMovies.size();
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
    	for(Movie movie : myMovies) {
    		String movieId= movie.getID();
    		double averageRating = getAverageByID(movieId, minimalRaters);
    		if(averageRating != 0.0)
    			ratingList.add(new Rating(movieId, averageRating));
    	}
    	
    	return ratingList;
    }

    public String getTitle(String id) {
    	for(Movie movie : myMovies) {
    		if(movie.getID().equals(id))
    			return movie.getTitle();
    	}
    	return "Movie ID not found";
    }
    
    public String getID(String title) {
    	for(Movie movie : myMovies) {
    		if(movie.getTitle().equals(title))
    			return movie.getID();
    	}
    	return "NO SUCH TITLE";
    }
}