import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {	
	public void printAverageRatings() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println("# Raters : " + tr.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("# Movies : " + MovieDatabase.size());
		
		int minimalRaters = 35;
		ArrayList<Rating> ratingList = tr.getAverageRatings(minimalRaters);
		System.out.println("# Movies with ratings more than " + minimalRaters + " : " + ratingList.size());
		Collections.sort(ratingList);
		for(Rating rating : ratingList) {
			System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
		}
	}
	
	public void printAverageRatingsByYearAfter() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println(" # Raters : " + tr.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println(" # Movies : " + MovieDatabase.size());
		
		int minimalRaters = 20;
		int year = 2000;
		Filter yearFilter = new YearAfterFilter(year);
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, yearFilter);
		System.out.println(" # Movies that match the given criteria : " + ratingList.size());
		Collections.sort(ratingList);
		for(Rating rating : ratingList) {
			System.out.println(rating.getValue() + "\t" + MovieDatabase.getYear(rating.getItem()) + "\t" +  MovieDatabase.getTitle(rating.getItem()));
		}
	}
	
	public void printAverageRatingsByGenre() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println(" # Raters : " + tr.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println(" # Movies : " + MovieDatabase.size());
		
		int minimalRaters = 20;
		String genre = "Comedy";
		Filter genreFilter = new GenreFilter(genre);
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, genreFilter);
		System.out.println(" # Movies that match the given criteria : " + ratingList.size());
		Collections.sort(ratingList);
		for(Rating rating : ratingList) {
			System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
		}
	}
	
	public void printAverageRatingsByMinutes() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println(" # Raters : " + tr.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println(" # Movies : " + MovieDatabase.size());
		
		int minimalRaters = 5;
		int minMinutes = 105, maxMinutes = 135;
		Filter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, minutesFilter);
		System.out.println(" # Movies that match the given criteria : " + ratingList.size());
		Collections.sort(ratingList);
		for(Rating rating : ratingList) {
			System.out.println(rating.getValue() + "\tTime : " + MovieDatabase.getMinutes(rating.getItem()) +
							"\t" + MovieDatabase.getTitle(rating.getItem()));
		}
	}
	
	public void printAverageRatingsByDirectors() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println(" # Raters : " + tr.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println(" # Movies : " + MovieDatabase.size());
		
		int minimalRaters = 4;
		String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		Filter directorFilter = new DirectorsFilter(directors);
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, directorFilter);
		System.out.println(" # Movies that match the given criteria : " + ratingList.size());
		Collections.sort(ratingList);
		for(Rating rating : ratingList) {
			System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
			System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
		}
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println(" # Raters : " + tr.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println(" # Movies : " + MovieDatabase.size());
		
		int minimalRaters = 8;
		int year = 1990;
		String genre = "Drama";
		Filter yearFilter = new YearAfterFilter(year);
		Filter genreFilter = new GenreFilter(genre);
		AllFilters filters = new AllFilters();
		filters.addFilter(yearFilter);
		filters.addFilter(genreFilter);
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, filters);
		System.out.println(" # Movies that match the given criteria : " + ratingList.size());
		Collections.sort(ratingList);
		for(Rating rating : ratingList) {
			System.out.println(rating.getValue() + "\t" + MovieDatabase.getYear(rating.getItem()) +
							"\t" + MovieDatabase.getTitle(rating.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
		}
	}

	public void printAverageRatingsByDirectorsAndMinutes() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println(" # Raters : " + tr.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println(" # Movies : " + MovieDatabase.size());
		
		int minimalRaters = 3;
		String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		int minMinutes = 90, maxMinutes = 180;
		Filter directorFilter = new DirectorsFilter(directors);
		Filter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
		AllFilters filters = new AllFilters();
		filters.addFilter(directorFilter);
		filters.addFilter(minutesFilter);
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, filters);
		System.out.println("# Movies that match the given criteria : " + ratingList.size());
		Collections.sort(ratingList);
		for(Rating rating : ratingList) {
			System.out.println(rating.getValue() + "\tTime : " + MovieDatabase.getMinutes(rating.getItem()) + 
							"\t" + MovieDatabase.getTitle(rating.getItem()));
			System.out.println("\t\t" + MovieDatabase.getDirector(rating.getItem()));
		}
	}
}
