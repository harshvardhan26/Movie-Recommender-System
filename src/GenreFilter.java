
public class GenreFilter implements Filter {
	private String genre;
	
	public GenreFilter(String aGenre) {
		genre = aGenre;
	}
	
	@Override
	public boolean satisfies(String id) {
		String[] genres = MovieDatabase.getGenres(id).split(",");
		for(String currGenre : genres) {
			currGenre = currGenre.trim();
			if(genre.equals(currGenre))
				return true;
		}
		
		return false;
	}

}
