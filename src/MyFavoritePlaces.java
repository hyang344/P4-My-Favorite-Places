import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.*;
///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Title:            My Favorite Places
//Files:            MyFavoritePlaces.java; Places.java; ManagePlaces.java
//Semester:         CS 302 Spring 2016
//
//Author:           Hao-Yu Yang
//Email:            hyang344@wisc.edu
//CS Login:         hao-yu
//Lecturer's Name:  Gary Dahl
//Lab Section:      335
//
////////////////////////////80 columns wide //////////////////////////////////

/**
 * This is the main user class of the My Favorite Places project. Users can add
 * , show, edit, delete, current, read, and write their favorite places with 
 * this code.
 *
 * @author Hao-Yu Yang
 */
public class MyFavoritePlaces{
	/** The temporary saving of the name of a place */
	private String name;
	
	/** The temporary saving of the address of a place */
	private String address;
	
	/** The temporary saving of the latitude of a place */
	private double latitude;
	
	/** The temporary saving of the longtitude of a place */
	private double longtitude;
	
	/** The temporary saving of the url of a place on google maps*/
	private String url;
	
	/** Scanner to read keyboard input */
	Scanner in = new Scanner (System.in);
	
	private ManagePlaces managePlaces = new ManagePlaces();
	private Places place = new Places();
	
	/** This variable checks whether there are places loaded yet*/
	static boolean placeLoad = false;
	
	/**
	 * This method prints the chart (or display list) in the beginning and 
	 * throughout the process when the user finish an action.
	 *
	 */
	public void printChart(){
		System.out.println();
		System.out.println("My Favorite Places 2016");
		System.out.println("--------------------------");
		if (placeLoad){
			managePlaces.sort();
			if (Places.getCurrentPlace() != null)
				System.out.println("distance from " + 
						managePlaces.getPlaces().get(0));
			for (int i = 0; i < managePlaces.getPlaces().size(); i++){
				System.out.print( (i+1) + ") " + 
							managePlaces.getPlaces().get(i) );
				if (Places.getCurrentPlace() == null)
					System.out.println();
				else
					System.out.format(" (%.2f miles)%n",
							managePlaces.getPlaces().get(i).getDistance());
			}
			System.out.println("--------------------------");
			System.out.print("A)dd S)how E)dit D)elete C)urrent"
					+ " R)ead W)rite Q)uit : ");
		}
		else {
			System.out.println("No places loaded.");
			System.out.println("--------------------------");
			System.out.print("A)dd R)ead Q)uit : ");
		}
	}
	
	// milestone 1 left: overwrite equals
	// milestone 2 left: none
	
	/**
	 * This method is the main method of My Favorite Place and mainly check 
	 * whether the user wants to A)dd S)how E)dit D)elete C)urrent R)ead W)rite
	 * or Q)uit the program.
	 *
	 */
	public static void main(String[] args) throws Exception {
		boolean quit = false;
		MyFavoritePlaces favoritePlaces = new MyFavoritePlaces();
		favoritePlaces.printChart();
		favoritePlaces.checkNameExtension();
		while (!quit){
			String input = favoritePlaces.in.nextLine();
				if (input.equals("a") || input.equals("A"))
					favoritePlaces.inputA();
				else if (input.equals("r") || input.equals("R"))
					favoritePlaces.inputR();
				else if (input.equals("q") || input.equals("Q")){
					System.out.println("Thank you for using My "
							+ "Favorite Places 2016!");
					quit = true;
				}
				else if (input.equals("s") || input.equals("S")){
					if (!placeLoad)
						favoritePlaces.printChart();
					else{
						favoritePlaces.inputS();
					}
				}
				else if (input.equals("e") || input.equals("E")){
					if (!placeLoad)
						favoritePlaces.printChart();
					else{
						favoritePlaces.inputE(); 
					}
				}
				else if (input.equals("d") || input.equals("D")){
					if (!placeLoad)
						favoritePlaces.printChart();
					else
						favoritePlaces.inputD();
				}
				else if (input.equals("c") || input.equals("C")){
					if (!placeLoad)
						favoritePlaces.printChart();
					else
						favoritePlaces.inputC(); 
				}
				else if (input.equals("w") || input.equals("W")){
					if (!placeLoad)
						favoritePlaces.printChart();
					else{
						favoritePlaces.inputW();
					}
				}
				else{
					favoritePlaces.printChart();
				}
			}
	}
	
	/**
	 * This method is called when the user inputs "A" in the selection. It asks
	 * for the name & address of the place the user wants to add and saves it. 
	 * If the name & the address are the same, it will print the chart & ask 
	 * the user for another action. This method also updates the address, 
	 * latitude & longtitude from the GeocodeResponse class.
	 * @throws IOException
	 */
	public void inputA() throws IOException{
		try{
			System.out.print("Enter the name: ");
			name = in.nextLine();
			System.out.print("Enter the address: ");
			address = in.nextLine();
			
			// get formatted info 
			String jsonResponse = Geocoding.find(address);
			GResponse gResponse = GeocodeResponse.parse(jsonResponse);
			address = gResponse.getFormattedAddress();
			latitude = gResponse.getLatitude();
			longtitude = gResponse.getLongitude();
			url = place.makeURL(address, latitude, longtitude);
			managePlaces.addPlaces(name, address, latitude, longtitude, url);
			placeLoad  = true;
			
			printChart();
		} catch (Exception e){
			System.out.println("Place not found using address: " + address);
			pressEnterToContinue();
		}
	}
	
	/**
	 * This method is called when the user inputs "S" in the selection. It asks
	 * for the index of the place the user wants to show and display in the 
	 * console as well as open a google map webpage accordingly. When the index
	 * is invalid, the invalid value is displayed and the program asks for a 
	 * new action
	 *
	 */
	public void inputS(){
		String temp = "";
		try{
			System.out.print("Enter number of place to Show: "); 
			temp = in.nextLine();
			int index = Integer.parseInt(temp);
			if (index <= managePlaces.getPlaces().size() && index > 0){
				System.out.println(managePlaces.retreivePlaces(index - 1));
				try {
					Geocoding.openBrowser(managePlaces.getPlaces().
							get(index - 1).getURL());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				pressEnterToContinue();
			}
			else{
				System.out.println("Invalid value: " + index);
				in.nextLine(); // scanner overwrite
				printChart();
			}
		} catch (Exception e){
			System.out.println("Invalid value: " + temp);
			printChart();
		}
	}
	
	/**
	 * This method is called when the user inputs "E" in the selection. It asks
	 * for the index of the place the user wants to edit and asks the user to 
	 * enter a new name & address. After entering, the program updates a new
	 * name, address, latitude, longtitude & URL respectively. When the index 
	 * is invalid, the invalid value is displayed and the program asks for a 
	 * new action.
	 * @throws IOException
	 */
	public void inputE() throws IOException{
		String temp = "";
		try{
			System.out.print("Enter number of place to Edit: ");
			temp = in.nextLine();
			int index = Integer.parseInt(temp);
			
			if (index <= managePlaces.getPlaces().size() && index > 0){
				System.out.println("Current name: " + 
						managePlaces.getPlaces().get(index - 1).getName());
				System.out.print("Enter a new name: ");
				
				name = in.nextLine();
				managePlaces.getPlaces().get(index - 1).setName(name);
				System.out.println("Current address: " + 
						managePlaces.getPlaces().get(index - 1).getAddress());
				System.out.print("Enter a new address: ");
				address = in.nextLine();
				
				// get formatted info 
				String jsonResponse = Geocoding.find(address);
				GResponse gResponse = GeocodeResponse.parse(jsonResponse);
				address = gResponse.getFormattedAddress();
				latitude = gResponse.getLatitude();
				longtitude = gResponse.getLongitude();
				url = place.makeURL(address, latitude, longtitude);
				
				managePlaces.getPlaces().get(index - 1).setAddress(address);
				managePlaces.getPlaces().get(index - 1).setLatitude(latitude);
				managePlaces.getPlaces().
						get(index - 1).setLongtitude(longtitude);
				url = place.makeURL(address, latitude, longtitude);
				
			}
			else{
				System.out.println("Invalid value: " + index);
			}
		} catch(NoSuchElementException e){}
		  catch (Exception e){
			System.out.println("Invalid value: " + temp);
		}
		printChart();
	}

	/**
	 * This method is called when the user inputs "D" in the selection. It asks
	 * for the index of the place the user wants to delete and remove the 
	 * place. When the index is invalid, the invalid value is displayed and the
	 * program asks for a new action.
	 *
	 */
	public void inputD() {
		String temp = "";
		try{
			System.out.print("Enter number of place to Delete: ");
			temp = in.nextLine();
			int index = Integer.parseInt(temp);
			if (index <= managePlaces.getPlaces().size() && index > 0){
				System.out.println(managePlaces.getPlaces().
						get(index - 1).getName() + " deleted.");
				managePlaces.removePlaces(index - 1);
				placeLoad = managePlaces.placesExist();
				pressEnterToContinue();
			}
			else{
				System.out.println("Invalid value: " + index);
				printChart();
			}
		} catch (Exception e){
			System.out.println("Invalid value: " + temp);
			printChart();
		}
	}
	
	/**
	 * This method is called when the user inputs "R" in the selection. It 
	 * displays the available files & ask the user to choose from one. After
	 * the file is read, the info inside the files are added to the place 
	 * loaded. When the file name is invalid, the invalid name is displayed 
	 * and the program asks for a new action.
	 *
	 */
	public void inputR(){
		System.out.println("Available Files:");
		System.out.println(checkNameExtension());
		System.out.print("Enter filename: ");
		String fileName = in.nextLine();
		File file = new File(fileName);
		try{
			readFiles(file); // milestone 2
			managePlaces.sort();
			placeLoad = true;
		}catch(FileNotFoundException e){
			if (fileName != null)
				System.out.println("Unable to read from file: "
						+ fileName);
			else
				System.out.println("Unable to read from file: ");
		}
		printChart();
			
	}
	
	/**
	 * This method is called when the user inputs "C" in the selection. It 
	 * ask the user to input the place number to serve as the current place. 
	 * When the index is invalid, the invalid index is displayed and the 
	 * program asks for a new action.
	 *
	 */
	public void inputC(){
		String temp = "";
		try{
			System.out.print("Enter number of place to be Current place: ");
			temp = in.nextLine();
			int index = Integer.parseInt(temp);
			place.setCurrentPlace(index, managePlaces);
			System.out.println(managePlaces.getPlaces().get(index - 1).getName()
				+ " set as Current place.");
			pressEnterToContinue();
		}
		 catch (IndexOutOfBoundsException e){
			  System.out.println("Invalid value: " + temp);
			  printChart();
		  }
	}

	/**
	 * This method is called when the user inputs "R" in the selection. It 
	 * displays the available files & ask the user to choose from one. After
	 * the file is read, the info inside the files are added to the place 
	 * loaded. When the file name is invalid, the invalid name is displayed 
	 * and the program asks for a new action.
	 * @throws Exception
	 */
	public void inputW() throws Exception{
		System.out.println("Current Files:");
		System.out.println(checkNameExtension());
		System.out.print("Enter filename: ");
		
		String fileName = in.nextLine();
		File file = new File(fileName);
		try{
		writeFiles(file); 
		} catch(Exception e){
			if (fileName != null)
				System.out.println("Unable to write to file: "
						+ fileName);
			else
				System.out.println("Unable to write to file: ");
		}
		printChart();
	}
	
	/**
	 * This method reads the content of a .txt file and add it to the loaded
	 * places.The name, address, latitude & longtitude are separated using 
	 * semicolons. If a file is not found, a FileNotFoundException is thrown
	 * and is handled in the inputR() method.
	 * @throws FileNotFoundException
	 */
	public void readFiles(File file) throws FileNotFoundException{
		if (!file.exists())
			throw new FileNotFoundException("Unable to read from file: "
					+ file );
		else{
			Scanner in = new Scanner(file);
			while(in.hasNextLine()){
				String [] array = in.nextLine().split(";");
				if (!sameData(array)){
					name = array[0];
					address = array[1];
					latitude = Double.parseDouble(array[2]);
					longtitude = Double.parseDouble(array[3]);
					url = place.makeURL(address, latitude, longtitude);
					managePlaces.addPlaces(name, address, latitude
							, longtitude, url);
				}
			}
			in.close();
		}
	}
	
	/**
	 * This method checks whether the new data read from file already exists in
	 * the loaded places. It takes the name & the address from the file & 
	 * compare to the one listed; true is returned if both are the same, false
	 * otherwise.
	 * @param array[] a single line from the text files (which contain info
	 * 		about name & address)
	 * @return whether the name & address are both the same
	 */
	public boolean sameData(String [] array){
		for (int i = 0; i < managePlaces.getPlaces().size(); i++){
			boolean inList = managePlaces.getPlaces().get(i).getName().
					equals(array[0])
					&& managePlaces.getPlaces().get(i).getAddress()
					.equals(array[1]);
			if(inList){
				System.out.println("Place " + 
						managePlaces.getPlaces().get(i).getName() + 
					" already in list.");
				return inList;
			}
		}
		return false;
	}
	
	/**
	 * This method writes the loaded data shown in the console into a txt file
	 * mentioned by the user. It is written into the same format as the example
	 * reading file so it can be reused. An Exception is thrown if the name of 
	 * the file is not valid.
	 * @param file the file the writer wants to write to.
	 * @throws Exception
	 */
	public void writeFiles(File file) throws Exception{
		PrintWriter out = new PrintWriter(file);
		try{
			for (int i = 0; i < managePlaces.getPlaces().size(); i++){
				out.print(managePlaces.getPlaces().get(i).getName() + ";");
				out.print(managePlaces.getPlaces().get(i).getAddress() + ";");
				out.print(managePlaces.getPlaces().get(i).getLatitude() + ";"
					+ managePlaces.getPlaces().get(i).getLongtitude() + "\n");
			}
		} catch(Exception e){
			System.out.println("Unable to write to file: " + file);
		}
		out.close();
	}
	
	/**
	 * This method checks the files with the same name extension (for this 
	 * case it is .mfp) in the same directory and returns it as available files
	 * when reading files.
	 * @return availableFiles name of files that is readable
	 */
	public String checkNameExtension(){
		File folder = new File(".");
		String availableFiles = "";
		for ( File file : folder.listFiles()) {
		  if ( file.getName().endsWith(".mfp")) {
			  availableFiles += ("        " + file.getName() + "\n");  
		  }
		}
		return availableFiles;
	}
	
	/**
	 * This method simply ask the user to press enter to continue. If other 
	 * keys are pressed, it still move on as of pressed enter.
	 */
	public void pressEnterToContinue(){
		System.out.print("Press Enter to continue.");
		in.nextLine();
		printChart();
	}
		
}
	
