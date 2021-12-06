/*
 * Car object, for storage of all data associated with each car. An array of this object is created for each Entry.
 * A String array called 'tags' is set up for any terms not stored individually, so that options and such can be listed. 
 * 
 */



import java.io.Serializable;
import java.util.ArrayList;

public class car implements Serializable
{
	ArrayList<String> tags;
	int uID, cid, year, miles, price;
	String brand, type, model, vin;
	
	
	public car(int tcid, String tbrand, String tmodel, String ttype, int tyear, ArrayList<String>ttags, int tuid, int tprice, String tvin, int tmiles) {
		tags = new ArrayList<String>(ttags);
		year = tyear;
		brand = tbrand;
		type = ttype;
		model = tmodel;
		uID = tuid;
		cid = tcid;
		miles = tmiles;
		vin = tvin;
		price = tprice;
		
	}
	//get and set ALL THE THINGS
	void setmodel(String tmodel) {
		model = tmodel;
	}
	
	void setbrand(String tbrand) {
		brand = tbrand;
	}
	
	void settype(String ttype) {
		type = ttype;
	}
	
	void setyear(int tyear) {
		year = tyear;
	}
	void setvin(String tvin) {
		vin = tvin;
	}
	void setprice(int tprice) {
		price = tprice;
	}
	void setmiles(int tmiles) {
		miles = tmiles;
	}
	
	String getmodel() {
		return model;
	}
	String getbrand() {
		return brand;
	}
	String gettype() {
		return type;
	}
	String getvin() {
		return vin;
	}
	int getmiles() {
		return miles;
	}
	int getprice() {
		return price;
	}
	int getyear() {
		return year;
	}
	void settag(ArrayList<String> ttags) {
		tags = new ArrayList<String>(ttags);
	}
	ArrayList<String> gettags(){
		return tags;
	}
	void addtag(String ttag) {
		tags.add(ttag);
	}
	void remtag(String ttag) {
		tags.remove(ttag);
	}
	String gettag(int ttag) {
		return tags.get(ttag);
	}
	int getuID() {
		return uID;
	}
	int getcID() {
		return cid;
	}
}