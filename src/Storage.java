/*
 *		Storage Class 
 * 
 * 
 * 
 * 
 * 
 */

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Storage {
	//variables
	public boolean busy;
	
	boolean newfiles = true;
	static private LinkedList<Entry> mainlist;
	static private ArrayList<String> users;
	static boolean active = false;
	//Path file1 = Paths.get("database.dat");
	//Path file2 = Paths.get("users.txt");
	
	
	//constructors
	public Storage() throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
		//if(!Files.exists(file1)){
			//Files.createFile(file1);
			//Files.createFile(file2);
		
			mainlist = new LinkedList<Entry>();
			users = new ArrayList<String>();
			active = true;
			
			if(new File("data.dat").isFile())
				read();
			
			if(!(new File("data.dat").isFile())) {
			String dfPW = "admin"; 
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
			
			addUser("admin", dfhPW, 3);
			
			write();
			}
			//TESTING IGNORE System.out.println(mainlist.get(0).PW);
	
	}
			
				
	
		

		

	//read from storage
	public void read() throws IOException, ClassNotFoundException {
		System.out.println("Reading Data.");
		FileInputStream fin = new FileInputStream("data.dat");
		ObjectInputStream in = new ObjectInputStream(fin);
		mainlist = (LinkedList<Entry>)in.readObject();
		
		FileInputStream fin1 = new FileInputStream("users.dat");
		ObjectInputStream in1 = new ObjectInputStream(fin1);
		users = (ArrayList<String>)in1.readObject();

		in1.close();
		in.close();
		/*TESTING IGNORE
		for(int i =0; i < mainlist.size(); i++) {
		System.out.println(mainlist.size() + " " + mainlist.get(i).ID + " " + mainlist.get(i).idnum);
		System.out.println(users.get(i));
		}*/

		/*try{
		    FileInputStream readData1 = new FileInputStream("database.dat");
		    ObjectInputStream readStream1 = new ObjectInputStream(readData1);

		    mainlist = new LinkedList<Entry>((LinkedList<Entry>)readStream1.readObject());
		    readStream1.close();
		    
		    FileInputStream readData2 = new FileInputStream("users.txt");
		    ObjectInputStream readStream2 = new ObjectInputStream(readData2);

		    users = new ArrayList<String>((ArrayList<String>)readStream2.readObject());
		    readStream1.close();
		   
		}catch (Exception e) {
			System.out.println("Error reading database files");
		}*/
		
		
	}
	
	//write to storage
	public void write() throws IOException {
	
	System.out.println("Writing Data");
		FileOutputStream fout = new FileOutputStream("data.dat");
		ObjectOutputStream out = new ObjectOutputStream(fout);
		out.writeObject(mainlist);
		
		
		FileOutputStream fout1 = new FileOutputStream("users.dat");
		ObjectOutputStream out1 = new ObjectOutputStream(fout1);
		out1.writeObject(users);
		
		out.close();
		out1.close();
		/*try{
	    
			FileOutputStream writeData1 = new FileOutputStream("database.dat");
			ObjectOutputStream writeStream1 = new ObjectOutputStream(writeData1);

			writeStream1.writeObject(mainlist);
			writeStream1.flush();
			writeStream1.close();
	    
			FileOutputStream writeData2 = new FileOutputStream("users.txt");
			ObjectOutputStream writeStream2 = new ObjectOutputStream(writeData2);

			writeStream2.writeObject(users);
			writeStream2.flush();
			writeStream2.close();
	
			}catch (IOException e) {
	 
				System.out.println("Error writing database files");
	
			}*/
	}
	//search function.
	public ArrayList<car> search(String tag, int type) {
	
		
		ArrayList<car> results = new ArrayList<car>();
		switch (type) {
		case 0:
		
		
			System.out.println("Brandcheck");
			for(int i = 0; i < mainlist.size(); i++) {			
				for(int j = 0; j < mainlist.get(i).cars.size();j++) {
					if(tag.equalsIgnoreCase(mainlist.get(i).cars.get(j).getbrand())) {
						results.add(mainlist.get(i).cars.get(j));
						System.out.println("search hit");
					}				
				}
			}
			break;
		case 1:
			
			System.out.println("typecheck");
			for(int i = 0; i < mainlist.size(); i++) {			
				for(int j = 0; j < mainlist.get(i).cars.size();j++) {
					if(tag.equalsIgnoreCase(mainlist.get(i).cars.get(j).gettype())) {
						results.add(mainlist.get(i).cars.get(j));
					}				
				}
			}
			break;
		case 2:
			for(int i = 0; i < mainlist.size(); i++) {			
				for(int j = 0; j < mainlist.get(i).cars.size();j++) {
					if(tag.equalsIgnoreCase(mainlist.get(i).cars.get(j).getmodel())) {
						results.add(mainlist.get(i).cars.get(j));
					}				
				}
			}
			break;
		case 3:
		for(int i = 0; i < mainlist.size(); i++) {			
			for(int j = 0; j < mainlist.get(i).cars.size();j++) {
				if(Integer.parseInt(tag) == (mainlist.get(i).cars.get(j).getyear())) {
					results.add(mainlist.get(i).cars.get(j));
				}				
			}
		}
		break;
		case  4:
		for(int i = 0; i < mainlist.size(); i++) {			
			for(int j = 0; j < mainlist.get(i).cars.size();j++) {
				for(int k = 0; k < mainlist.get(i).cars.get(j).tags.size(); k++) {								
					if(tag.equalsIgnoreCase(mainlist.get(i).cars.get(j).gettags().get(k))) {
					
						results.add(mainlist.get(i).cars.get(j));
					}
				}				
			}
		}
		break;
			default:
				System.out.println("invalid search type");
				break;
		}
		return results;
	}
	
	public ArrayList<car> refine(String tag, int type, ArrayList<car> tlist){
		
		
		
		
		ArrayList<car> results = new ArrayList<car>();
		switch (type) {
		case 0:
		
		
			
			for(int i = 0; i <tlist.size(); i++) {			
				if(tag.equalsIgnoreCase(tlist.get(i).getbrand())) {
						results.add(tlist.get(i));
									
				}
			}
			break;
		case 1:
			
			
			for(int i = 0; i <tlist.size(); i++) {			
				if(tag.equalsIgnoreCase(tlist.get(i).gettype())) {
						results.add(tlist.get(i));
									
				}
			}
			break;
		case 2:
			for(int i = 0; i <tlist.size(); i++) {			
				if(tag.equalsIgnoreCase(tlist.get(i).getmodel())) {
						results.add(tlist.get(i));
									
				}
			}
			break;
		case 3:
			for(int i = 0; i <tlist.size(); i++) {			
				if(Integer.parseInt(tag) == (tlist.get(i).getyear())) {
						results.add(tlist.get(i));
								
			}
		}
		break;
		case 4:
		for(int i = 0; i <tlist.size(); i++) {
			for(int k = 0; k <tlist.get(i).tags.size(); k++)
			if(tag.equalsIgnoreCase(tlist.get(i).getbrand())) {				
					if(tag.equalsIgnoreCase(tlist.get(i).gettags().get(k))) {
					
						results.add(tlist.get(i));
					}
								
			}
		}
		break;
			default:
				System.out.println("invalid search type");
				break;
		}
		return results;
	}
	
	public Entry get(int tindex) {
		return mainlist.get(tindex);
	}
	
	
	public Entry get(String tID) {
		if(users.indexOf(tID) >= 0)
			return mainlist.get(users.indexOf(tID));
			
		System.out.println(tID + " Index not found.");
		return null;
	}
	
	public void addUser(String tID, String tPW, int ttype) throws IOException {
		users.add(tID);
		//mainlist.add(null);
		mainlist.addLast(new Entry(tID, tPW, ttype));
		mainlist.get(users.indexOf(tID)).setID(users.indexOf(tID));
		write();
	}
	
	public void remUser(String tID) throws IOException {
		mainlist.remove(mainlist.get(users.indexOf(tID)));
		write();
	}
	 
	public boolean idcheck(String tID) {
		//TESTING IGNORE System.out.println(mainlist.size());
		for(int i = 0; i < users.size(); i++) {
			//TESTING IGNORE System.out.println(users.get(i) + " " + tID);
			if(tID.equalsIgnoreCase(users.get(i)))
				return true;
		}
		return false;
	}
	
	
}