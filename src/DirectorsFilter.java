
public class DirectorsFilter implements Filter{
	private String directors;
	
	public DirectorsFilter(String someDirectors) {
		directors = someDirectors;
	}
	
	@Override
	public boolean satisfies(String id) {
		String[] currDirectors = MovieDatabase.getDirector(id).split(",");
		for(String director : currDirectors) {
			director = director.trim();
			if(directors.contains(director))
				return true;
		}
		return false;
	}

}
