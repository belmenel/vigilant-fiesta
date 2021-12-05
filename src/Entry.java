import java.io.Serializable;
import java.util.ArrayList;

/*
 * Class for the common storage object containing a single user ID and its related information. Mostly used by other classes to pass information needed.
 * 
 * 
 * 
 * 
 */


public class Entry implements Serializable{

	//variables
	boolean valid; //set when pasword is checked.
	int type;//1-3, 1 = user, 2 = seller, 3 = admin
	int idnum;
	int count = 0;
	String PW; //password hash/encrypted password.
	String ID;
	ArrayList<car> cars; // car listings sorted by carID, with tags etc.
	ArrayList<car> sold;
	//cosntructor
	public Entry(String tID, String tPW, int ttype) {
		ID = tID;
		PW = tPW;
		type = ttype;
		cars = new ArrayList<car>();
		sold = new ArrayList<car>();
		
		
	}
	
	public void setID(int tidnum) {
		idnum = tidnum;
	}
	
	public void newCar(String tbrand, String tmodel, String ttype, int tyear, ArrayList<String>ttags) {
		cars.add(count, new car(count, tbrand, tmodel, ttype,tyear,ttags, idnum));
		count++;
	}
	
	public void editCar(int tcid, String tbrand, String tmodel, String ttype, int tyear, ArrayList<String>ttags) {
		cars.set(tcid, new car(tcid, tbrand, tmodel, ttype,tyear,ttags, idnum));
		}
	public void remcar(int tindex) {
		cars.remove(tindex);
	}
	public void sellcar(int tindex) {
		sold.add(cars.get(tindex));
		cars.remove(tindex);
	}
	
	public ArrayList<car> getcars(){
		return cars;
	}
	
	public car getcar(int tcid) {
		return cars.get(tcid);
	}

	ArrayList<Integer> tagSearch(String stag){
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		for(int i = 0; i < cars.size();i++) {
			for(int j =0; j < cars.get(i).tags.size(); j++) {
				if(stag.equalsIgnoreCase(cars.get(i).tags.get(j))) {
					results.add(i);
				}
			}
		}
		
		return results;
	}
}

	
	
	
	
	
	
	
	
	
	
