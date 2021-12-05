
/*
 * 		Main. Will start everything up~ Also contains a basic interface. 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {

		boolean valid = false;
		Interface inter = new Interface();
		int selection = 0;
		int status = 0;
		int selection1 = 1;
		int selection2 = 0;
		int tmp, tmp1, tmp2;
		String intemp;
		String rPW;
		String hPW;
		String ID = "";
		Scanner in = new Scanner(System.in);
		String atype;
		ArrayList<car> results;
		ArrayList<String> tags = new ArrayList<String>();
		ArrayList<Integer> tagtype = new ArrayList<Integer>();
		
		
		

		System.out.println("Welcome to the CarLot. Please Login or create an account. admin/admin for default admin account.");
		System.out.println("1: Login, 2: create new user, 3: quit");
		while (selection > 3 || selection < 1) {
			
			intemp = in.next();
			try {
				selection = Integer.parseInt(intemp);
			}
				catch (NumberFormatException e) {
					System.out.println("Invalid selection. Try again.");
				}
			
			if (selection == 3)
				return;
		}

		if (selection == 2) {

			
			while (!valid) {
				System.out.println("Please enter your requested username.");
				ID = in.next();
				if (inter.duplicateID(ID))
					System.out.println("ID taken, select another.");
				if (!inter.duplicateID(ID))
					valid = true;
			}
			System.out.println("Please select a password");
			rPW = in.next();

			/* MessageDigest instance for MD5. */
			MessageDigest m = MessageDigest.getInstance("MD5");

			/* Add plain-text password bytes to digest using MD5 update() method. */
			m.update(rPW.getBytes());

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
			hPW = s.toString();

			inter.newuser(ID, hPW, 1);
			inter.login(ID, hPW);
			selection = 1;
			status = 1;
			

		}
		
		if (selection == 1) {
			while (!valid) {
				System.out.println("Username:");
				ID = in.next();
				System.out.println("Password:");
				rPW = in.next();

				/* MessageDigest instance for MD5. */
				MessageDigest m = MessageDigest.getInstance("MD5");

				/* Add plain-text password bytes to digest using MD5 update() method. */
				m.update(rPW.getBytes());

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
				hPW = s.toString();
				
				if(inter.duplicateID(ID)) {
				valid = inter.login(ID, hPW);
				}else
					System.out.println("Username not found");
				
				if (!valid) {
					System.out.println("Username/Password Error.");
					//TESTING IGNORE System.out.println(hPW);
					

			}
		}
			status = inter.status();
		switch (status) {
		case 0:
			atype = "Guest";
			break;
		case 1:
			atype = "User";
			break;
		case 2:
			atype = "Seller";
			break;
		case 3:
			atype = "ADMINISTRATOR";
			break;
		default:
			atype = "Login Error";
			break;
		}

		boolean active = true;
		selection = 0;

		while (active) {
		
		
			System.out.println("Top Menu  :  Make Selection with numbers  :  " + atype);
			
			System.out.println("1:Search Listings");
			if(status > 1) {
				System.out.println("2:My Listings");
			}
			if(status > 2) {
				System.out.println("3: ADMIN PANEL");
			}
			System.out.println("0:Quit");
			
			intemp = in.next();
			try {
				selection = Integer.parseInt(intemp);
			}
				catch (NumberFormatException e) {
					System.out.println("Invalid selection. Try again.");
				}
			
			if(selection == 0)
				active = false;
			
			while(selection == 1) {
				System.out.println("Search category: 0:Brand, 1:Model, 2:Type, 3:Year, 4:Tags.");
				System.out.println("Enter category number");
				while(true) {
				intemp = in.next();
				try {
					selection1 = Integer.parseInt(intemp);
				
					break;
				}
					catch (NumberFormatException e) {
						System.out.println("Invalid selection. Try again.");
					}
				}
				if(selection1 >= 0 && selection1 < 5) {
					System.out.println("Enter search term");
					tags.add(in.next());
					tagtype.add(selection1);
					System.out.println("Would you like to refine the search further? 1:Yes 2:No");
					tmp = 0;
					while(true) {
					intemp = in.next();
					try {
						tmp = Integer.parseInt(intemp);
					
						break;
					}
						catch (NumberFormatException e) {
							System.out.println("Invalid selection. Try again.");
						}
					}
					if(tmp != 1)
						selection = 10;
					
				}
			if(selection > 9) {
				results = new ArrayList<car>(inter.search(tags, tagtype));
				System.out.println("Search Results:");
				for(int i = 0; i < results.size(); i++) {
					System.out.print((i) + ":");
					showcar(results.get(i));
				}
				System.out.println("Select a vehicle to purchase");
				
				while(true) {
				intemp = in.next();
				try {
					selection2 = Integer.parseInt(intemp);
					
					break;
					
				}
					catch (NumberFormatException e) {
						System.out.println("Invalid selection. Try again.");
					}
				}
				if (selection2 < results.size()) {
					showcar(results.get(selection2));
					
					System.out.println("Are you sure? 1:Yes, 2:No");

					while(true) {
						intemp = in.next();
						try {
							selection1 = Integer.parseInt(intemp);
							
							break;
							
						}
							catch (NumberFormatException e) {
								System.out.println("Invalid selection. Try again.");
							}
						}
					
						if(selection1 == 1)
							inter.buy(results.get(selection2).getuID(), results.get(selection2).getcID());
						
				}else {
					selection = 0;
				}
				
			}

			}
			
			while(selection == 2 && status > 1) {
				System.out.println("Current Listings: ");
				
				for(int i = 0; i < inter.user.getcars().size(); i++) {
					showcar(inter.user.cars.get(i));
				}
				System.out.println("1:Add listing, 2:remove listing, other:Quit");
				while(true) {
					intemp = in.next();
					try {
						tmp1 = Integer.parseInt(intemp);
						
						break;
						
					}
						catch (NumberFormatException e) {
							System.out.println("Invalid selection. Try again.");
						}
					}

				
				if(tmp1 == 1 || tmp1 == 2) {
					if(tmp1 == 1) {
						System.out.println("Enter Brand:");
						String brand = in.next();
						System.out.println("Enter Model:");
						String model = in.next();
						System.out.println("Enter Type:");
						String type = in.next();
						System.out.println("Enter Year:");
						int year;
						while(true) {
							intemp = in.next();
							try {
								year = Integer.parseInt(intemp);
								
								break;
								
							}
								catch (NumberFormatException e) {
									System.out.println("Invalid selection. Try again.");
								}
							}
						System.out.println("How many Tags?");
						int tagnum;
						while(true) {
							intemp = in.next();
							try {
								tagnum = Integer.parseInt(intemp);
								
								break;
								
							}
								catch (NumberFormatException e) {
									System.out.println("Invalid selection. Try again.");
								}
							}
						ArrayList<String> atags = new ArrayList<String>();
						for(int i =0; i < tagnum; i++) {
							System.out.println("Enter Tag:");
							atags.add(in.next());
						}
						inter.list(brand, model, type, year, atags, ID);	
						
					}
					
					if(tmp1 == 2) {
						System.out.println("Which listing to Remove?");
						inter.user.remcar(in.nextInt());
						System.out.println("removed");
					}
					System.out.println("Continue? 1:yes, 2:no");
					while(true) {
						intemp = in.next();
						try {
							tmp = Integer.parseInt(intemp);
							
							break;
							
						}
							catch (NumberFormatException e) {
								System.out.println("Invalid selection. Try again.");
							}
						}
					if(tmp != 1)
						selection = 0;
					
				}
				if(tmp1 != 1 && tmp1 != 2)
				selection = 0;	
				
						
			}
			while(selection ==3 && status == 3) {
				String nID = "";
				valid = false;
				System.out.println("!ADMIN PANEL!  1:Add User  2: Remove User  3:Quit");
				while(true) {
					intemp = in.next();
					try {
						tmp1 = Integer.parseInt(intemp);
						
						break;
						
					}
						catch (NumberFormatException e) {
							System.out.println("Invalid selection. Try again.");
						}
					}
				if(tmp1 != 1 && tmp1 != 2)
					selection =0;
				if(tmp1 ==  1) {
					while(!valid) {
					System.out.println("Username:");
					nID = in.next();
					if (inter.duplicateID(nID))
						System.out.println("ID taken, select another.");
					if (!inter.duplicateID(nID))
						valid = true;
					}
					
					
					System.out.println("Password:");
					String nrPW = in.next();

					/* MessageDigest instance for MD5. */
					MessageDigest m = MessageDigest.getInstance("MD5");

					/* Add plain-text password bytes to digest using MD5 update() method. */
					m.update(nrPW.getBytes());

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
					String nhPW = s.toString();
					
					
					System.out.println("User Type 1:user  2: staff 3: Admin");
					
					while(true) {
						intemp = in.next();
						try {
							tmp2 = Integer.parseInt(intemp);
							
							break;
							
						}
							catch (NumberFormatException e) {
								System.out.println("Invalid selection. Try again.");
							}
						}
					inter.newuser(nID, nhPW, tmp2);
					
				}
				if(tmp1 == 2) {
					System.out.println("User name to remove:");
					inter.remUser(in.next());
				}
			}
		}
		}
		in.close();
			
		
	}
	static void showcar(car car1) {
		System.out.print(car1.year + " " + car1.brand +  " " + car1.model + " " + car1.type);
		for(int i =0; i < car1.tags.size(); i++) {
			System.out.print(car1.tags.get(i) + " ");
		}
		System.out.println(" ");
	}
}
