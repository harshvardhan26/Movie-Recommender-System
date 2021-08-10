
import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String, Rating> myRatings;
    
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
//        for(int k=0; k < myRatings.size(); k++){
//            if (myRatings.get(k).getItem().equals(item)){
//                return true;
//            }
//        }
//        
//        return false;
    	if(myRatings.get(item) != null)
    		return true;
    	else
    		return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
//        for(int k=0; k < myRatings.size(); k++){
//            if (myRatings.get(k).getItem().equals(item)){
//                return myRatings.get(k).getValue();
//            }
//        }
//        
//        return -1;
    	
    	Rating rating = myRatings.get(item);
    	
    	if(rating != null)
    		return myRatings.get(item).getValue();
    	else
    		return -1;
    	
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
//        for(int k=0; k < myRatings.size(); k++){
//            list.add(myRatings.get(k).getItem());
//        }
//        
//        return list;
    	
    	for(String item : myRatings.keySet()) {
    		list.add(item);
    	}
    	
    	return list;
    }
}
