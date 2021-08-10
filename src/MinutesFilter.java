
public class MinutesFilter implements Filter{
	private int minMinutes, maxMinutes;
	
	public MinutesFilter(int min, int max) {
		minMinutes = min;
		maxMinutes = max;
	}
	
	@Override
	public boolean satisfies(String id) {
		int minutes = MovieDatabase.getMinutes(id);
		if(minutes >= minMinutes && minutes <= maxMinutes)
			return true;
		else
			return false;
	}

}
