import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/*
 * 
 * The UI. Either as a command line/javaGui. All the calls made by the GUI should call on this.
 * Class to act as an in-between for the database and the user interface.
 * 
 * added a hash method to help readability.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 */
public class Interface {
	//variables

	
	int status;
	String pwhash;
	String id;
	Entry user;
	Storage store;
	
	
	
	//constructors
	public Interface() throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
		store = new Storage();
	}
	
	//members
	public boolean login(String username, String hash) {
		if(store.get(username).PW.equals(hash)){
			status = store.get(username).type;
			user = store.get(username);
			id = username;
			return true;
		}
		return false;
	}
	
	public Entry getUser() {
		return user;
	}
	
	public int status() {

		//TESTING IGNORE System.out.println(store.get(id).type);
		return status;
	}
	
	public ArrayList<car> search(ArrayList<String> tags, ArrayList<Integer> types) {
	
		System.out.println("Searching. " + tags.get(0));
		ArrayList<car> results;
		ArrayList<car> temp = new ArrayList<car>(store.search(tags.get(0), types.get(0)));
		
		
		for(int i = 1; i < tags.size(); i++) {
		temp = new ArrayList<car>(store.refine(tags.get(i), types.get(i), temp));			
		}
	
	results = new ArrayList<car>(temp);
	
	
	//System.out.println(temp.get(0));
	return temp;
	}
	
	public void list(String tbrand, String tmodel, String ttype, int tyear, ArrayList<String>ttags, String tID,int tprice, String tvin, int tmiles) throws IOException {
		store.get(tID).newCar(tbrand, tmodel,ttype,tyear,ttags,tprice,tvin,tmiles);
		store.write();
	}
	
	public void buy(int seller, int car) throws IOException {
		store.get(seller).sellcar(car);
		store.write();
		}
	public void edit(String seller, int car, String tbrand, String tmodel, String ttype, int tyear, ArrayList<String> ttags ) {
		store.get(seller).cars.get(car).setbrand(tbrand);
		store.get(seller).cars.get(car).setmodel(tmodel);
		store.get(seller).cars.get(car).settype(ttype);
		store.get(seller).cars.get(car).setyear(tyear);
		store.get(seller).cars.get(car).settag(ttags);
	}
	
	// call this, then call the constructor +  login again. 
	public void save() throws IOException {
		store.write();
	}
	
	
	
	public void remUser(String tID) throws IOException {
		store.remUser(tID);
	}
	
	//new user
	public void newuser(String tID, String tPW, int ttype) throws IOException {
		store.addUser(tID, tPW, ttype);
	}
	
	public boolean duplicateID(String tID) {
		return store.idcheck(tID);
	}
	public ArrayList<String> getusers(){
		return store.getusers();
	}
	
	
	//simple hash method. Can use different, if done in the gui.
	public String hash(String dfPW) throws NoSuchAlgorithmException {
		/* MessageDigest instance for MD5. */
		MessageDigest m = MessageDigest.getInstance("MD5");

		/* Add plain-text password bytes to digest using MD5 update() method. */
		m.update(dfPW.getBytes());

		/* Convert the hash value into bytes */
		byte[] bytes = m.digest();

		/*
		 * The bytes array has bytes in decimal form. Converting it into hexadecimal
		 * format.
		 */
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		/* Complete hashed password in hexadecimal format */
		String dfhPW = s.toString();
		
		return dfhPW;
	}
	
}
