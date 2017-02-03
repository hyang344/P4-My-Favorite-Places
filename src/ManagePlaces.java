import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Main Class File:  MyFavoritePlaces.java
//File:             ManagePlaces.java
//Semester:         CS 302 Spring 2016
//
//Author:           Hao-Yu Yang; hyang344@wisc.edu
//CS Login:         hao-yu
//Lecturer's Name:  Gary Dahl
//Lab Section:      335
////////////////////////////80 columns wide //////////////////////////////////

/**
 * This class manages places using a arraylist filled with Places object. It 
 * allows the user to add, remove, retrieve, or sort places in the arraylist.
 *
 * no known bugs
 *
 * @author Hao-Yu Yang
 */
public class ManagePlaces{
	
	/** The arraylist that stores Places objects */
	private ArrayList<Places> places = new ArrayList<>();
	
	/** no args constructor */
	public ManagePlaces(){}
	
	/** this method allows user to access the places arraylist */
	public ArrayList<Places> getPlaces(){
		return places;
	}
	
	/** This method add places to the arrayList using the name, address,
	 * latitude, and longtitude.
	 * @param name the name of the place
	 * @param address the address of the place
	 * @param latitude the latitude of the place
	 * @param longtitude the longtitude of the place
	 * @param url the url of the place on google maps
	 * 
	 */
	public void addPlaces(String name, String address, 
			double latitude, double longtitude, String URL){
		places.add(new Places(name, address, latitude, longtitude, URL));
	}
	/** This method remove a place object at the location according to the 
	 * index.
	@param index the index in the arrayList
	*/
	public void removePlaces(int index){
		places.remove(index);
		if (index == 0){
			Places.setCurrentPlaceToNull();;
		}
	}
	
	/** This method returns a formatted String with all the info of a place
	 * according to the index of the arrayList
	@param i the index in the arrayList
	*/
	public String retreivePlaces(int i){
		return places.get(i).getName() + "\n" + places.get(i).getAddress()
				+ "\n" + places.get(i).getLatitude() + "," 
				+ places.get(i).getLongtitude() + "\n" 
				+ places.get(i).getURL();
	}
	
	/** This method checks whether there are places loaded and return true
	 * if there are; otherwise false
	 * @return places.size > 0 whether arrayList contains places 
	 */
	public boolean placesExist(){
		return places.size() > 0;
	}
	
	/** This method returns the number of places inside the places arrayList
	 * @return the number of places in the arrayList
	 */
	public int numberOfPlaces(){
		return places.size();
	}
	
	/** This method sorts the places listed in the arrayList in order regulated
	 * by the CompareTo method in Places Class
	 */
	public void sort(){
		Collections.sort(places);
	}
}
