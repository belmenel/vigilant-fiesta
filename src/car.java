import java.io.Serializable;
import java.util.ArrayList;

public class car implements Serializable
{
	ArrayList<String> tags;
	int uID, cid, year;
	String brand, type, model;
	
	
	public car(int tcid, String tbrand, String tmodel, String ttype, int tyear, ArrayList<String>ttags, int tuid) {
		tags = new ArrayList<String>(ttags);
		year = tyear;
		brand = tbrand;
		type = ttype;
		model = tmodel;
		uID = tuid;
		cid = tcid;
	}
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
	
	String getmodel() {
		return model;
	}
	String getbrand() {
		return brand;
	}
	String gettype() {
		return type;
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