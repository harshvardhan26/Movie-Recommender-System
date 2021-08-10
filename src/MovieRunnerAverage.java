import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

	public void printAverageRatings() {
		SecondRatings sr = new SecondRatings();
		System.out.println("# Movies : " + sr.getMovieSize());
		System.out.println("# Raters : " + sr.getRaterSize());
		int minimalRaters = 12;
		ArrayList<Rating> ratingList = sr.getAverageRatings(minimalRaters);
		System.out.println("# Movies with ratings more than " + minimalRaters + " : " + ratingList.size());
		Collections.sort(ratingList);
		for(Rating rating : ratingList) {
			System.out.println(rating.getValue() + "\t" + sr.getTitle(rating.getItem()));
		}
	}
	
	public void getAverageRatingOneMovie() {
		SecondRatings sr = new SecondRatings();
		int minimalRaters = 3;
		ArrayList<Rating> ratingList = sr.getAverageRatings(minimalRaters);
		String reqMovie = "Vacation";
		String reqId = sr.getID(reqMovie);
		if(reqId.equals("NO SUCH TITLE"))
			System.out.println(reqId);
		
		else {
			for(Rating rating : ratingList) {
				if(rating.getItem().equals(reqId))
					System.out.println("Average rating for " + reqMovie + " : " + rating.getValue());
			}
		}
	}
}
