import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class FirstRatings {
	
	public ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		
		for(CSVRecord record : parser) {
			String id = record.get("id");
			String title = record.get("title");
			String year = record.get("year");
			String genres = record.get("genre");
			String director = record.get("director");
			String country = record.get("country");
			String poster = record.get("poster");
			int minutes = Integer.parseInt(record.get("minutes"));
			Movie movie = new Movie(id, title, year, genres, director, country, poster, minutes);
			movieList.add(movie);
		}
		return movieList;	
	}
	
	public ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> raterList = new ArrayList<Rater>();
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();

		int index = 0;
		for(CSVRecord record : parser) {
			String raterId = record.get("rater_id");
			String movieId = record.get("movie_id");
			double rating = Double.parseDouble(record.get("rating"));
			
			if(index == 0) {
				Rater rater = new EfficientRater(raterId);
				rater.addRating(movieId, rating);
				raterList.add(rater);
				index++;
			}
			
			else {
				String prevId = raterList.get(index-1).getID();
				if(prevId.equals(raterId)) {
					raterList.get(index-1).addRating(movieId, rating);
				}
				
				else {
					Rater rater = new EfficientRater(raterId);
					rater.addRating(movieId, rating);
					raterList.add(rater);
					index++;
				}
			}
		}
		return raterList;
	}
	
	public void testLoadMovies() {
		ArrayList<Movie> movieList = loadMovies("data/ratedmoviesfull.csv");
		System.out.println("# Movies : " + movieList.size());
//		for(Movie movie : movieList) {
//			System.out.println(movie);
//		}
		
		int comedyCount = 0, count150 = 0;
		HashMap<String, ArrayList<String>> directorMap = new HashMap<String, ArrayList<String>>();
		for(Movie movie : movieList) {
			if(movie.getGenres().contains("Comedy"))
				comedyCount++;
			if(movie.getMinutes() > 150)
				count150++;

			String[] directors = movie.getDirector().split(",");
			
			for(String director : directors) {
				director = director.trim();
				if(!directorMap.containsKey(director)) {
					ArrayList<String> movies = new ArrayList<String>();
					movies.add(movie.getTitle());
					directorMap.put(director, movies);
				}
				
				else {
					directorMap.get(director).add(movie.getTitle());
				}
			}
		}
		
		System.out.println("# Comedy genre movies : " + comedyCount);
		System.out.println("# Movies with length > 150 minutes : " + count150);
		
		int maxSize = 0;
		ArrayList<String> maxDirector = new ArrayList<String>();
		for(String director : directorMap.keySet()) {
			int currSize = directorMap.get(director).size();
			if(currSize >= maxSize) {
				if(currSize == maxSize) {
					maxDirector.add(director);
					maxSize = currSize;
				}
				
				else if(currSize > maxSize) {
					maxDirector.clear();
					maxDirector.add(director);
					maxSize = currSize;
				}
			}
		}
		
		System.out.println("Maximum # movies by any director : " + maxSize + ", by " + maxDirector);
	}
	
	public void testLoadRaters() {
		ArrayList<Rater> raterList = loadRaters("data/ratings.csv");
		System.out.println("# Raters : " + raterList.size());
		
		String reqId = "193";
		int maxRatings = 0, movieCount = 0;
		String reqMovie = "1798709";
		ArrayList<String> maxRaters = new ArrayList<String>();
		ArrayList<String> diffMoviesRated = new ArrayList<String>();
		for(Rater rater : raterList) {
//			System.out.println("Rater ID : " + rater.getID() + "\t# Ratings : " + rater.numRatings());
			ArrayList<String> moviesRated = rater.getItemsRated();
			for(String movie : moviesRated) {
//				System.out.println("Movie ID : " + movie + "\tRating : " + rater.getRating(movie));
				if(movie.equals(reqMovie))
					movieCount++;
				
				if(!diffMoviesRated.contains(movie))
					diffMoviesRated.add(movie);
			}
			
			if(rater.getID().equals(reqId))
				System.out.println("# Ratings by rater_id " + reqId + " : " + rater.numRatings());
			
			
			
			if(rater.numRatings() >= maxRatings) {
				if(rater.numRatings() == maxRatings) {
					maxRaters.add(rater.getID());
					maxRatings = rater.numRatings();
				}
				
				else if(rater.numRatings() > maxRatings) {
					maxRaters.clear();
					maxRaters.add(rater.getID());
					maxRatings = rater.numRatings();
				}
			}
		}
		
		System.out.println("Maximum # ratings by any rater : " + maxRatings);
		System.out.println(maxRaters.size() + " raters have maximum # ratings. Their rater_ids are : ");
		for(String raterId : maxRaters) {
			System.out.println(raterId);
		}
		System.out.println("# Raters that rated movie with movie_id " + reqMovie + " : " + movieCount);
		System.out.println("# Different movies rated by raters : " + diffMoviesRated.size());
	}
	
	public static void main(String[] args) {
		new MovieRunnerWithFilters().printAverageRatingsByDirectorsAndMinutes();
	}
}
