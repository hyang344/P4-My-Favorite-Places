import java.io.IOException;
import java.net.URLEncoder;
///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Main Class File:  MyFavoritePlaces.java
//File:             Places.java
//Semester:         CS 302 Spring 2016
//
//Author:           Hao-Yu Yang; hyang344@wisc.edu
//CS Login:         hao-yu
//Lecturer's Name:  Gary Dahl
//Lab Section:      335
////////////////////////////80 columns wide //////////////////////////////////

/**
 * The Places class lists the variables, getters, and setters of the components
 * (name, address, latitude, longtitude & url) of a single Place. It also sets 
 * the currentPlace used by the current function in MyFavoritePlace class, 
 * implements the comparable to sort places in alphabetical order or distance, 
 * & overwrite the equals method in the Object class
 *
 * no known bugs
 *
 * @author Hao-Yu Yang
 */
public class Places implements Comparable<Places>{
	/** The name of a place */
	private String name;
	
	/** The address of a place */
	private String address;
	
	/** The latitude of a place */
	private double latitude;
	
	/** The longtitude of a place */
	private double longtitude;
	
	/** The url of a place on google maps*/
	private String url;
	
	/** The place object that is set to the current Place */
	private static Places currentPlaceObject;
	
	/** The name of the place object that is set to the current Place */
	private static String currentPlace;
	
	
	/** no arg constructor */
	public Places(){}
	
	/** gets the current Place name 
	 * @return currentPlace the current place name*/
	public static String getCurrentPlace(){
		return currentPlace;
	}
	
	/** gets the current Place name 
	 * @return currentPlace the current place name*/
	public static void setCurrentPlaceToNull(){
		currentPlace = null;
		currentPlaceObject = null;
	}
	
	/** gets the current Place object 
	 * @return currentPlaceObject the current place object
	 */
	public static Places getCurrentPlaceObject(){
		return currentPlaceObject;
	}
	
	/** sets the current place object & name */
	public void setCurrentPlace(int index, ManagePlaces managePlaces){
		currentPlaceObject =  managePlaces.getPlaces().get(index - 1);
		currentPlace = managePlaces.getPlaces().get(index - 1).getName();
	}
	
	/** overwrite toString method 
	 * @return getName() the name of the place*/
	public String toString(){
		return getName();
	}
	
	/** constructor */
	public Places(String name, String address, 
			double latitude, double longtitude, String url){
		setName(name);
		setAddress(address);
		setLatitude(latitude);
		setLongtitude(longtitude);
		this.url = url;
	}
	
	/** gets the name of the place 
	 * @return name the name of the place*/
	public String getName(){
		return name;
	}
	
	/** sets the name of the place */
	public void setName(String name){
		this.name = name;
	}
	
	/** gets the address of the place 
	 * @return address the address of the place*/
	public String getAddress(){
		return address;
	}
	
	/** sets the address of the place */
	public void setAddress(String address){
		this.address = address;
	}
	
	/** gets the latitude of the place 
	 * @return latitude the latitude of the place
	 */
	public double getLatitude(){
		return latitude;
	}
	
	/** sets the latitude of the place */
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
	
	/** gets the longtitude of the place 
	 * @return longtitude the longtitude of the place
	 */
	public double getLongtitude(){
		return longtitude;
	}
	
	/** sets the longtitude of the place */
	public void setLongtitude(double longtitude){
		this.longtitude = longtitude;
	}
	
	@Override /** equals method */
	public boolean equals(Object obj){
		return this.equals(obj);
	}
	
	/** This method uses the address, latitude, and longtitude to determine the
	 * 	url */
	public String makeURL(String address, double latitude, double longtitude){
		url = "https://www.google.com/maps/place/";
		try {
			url = url + URLEncoder.encode(address, "UTF-8") + "/@" + latitude
					+ "," + longtitude + ",17z/";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	/** gets the url of the place */
	public String getURL(){
		return url;
	}
	
	/** returns the distance between this place and the current place */
	public double getDistance(){
		return Geocoding.distance(currentPlaceObject.getLatitude(),
				currentPlaceObject.getLongtitude(),
				latitude, longtitude);
	}
	
	@Override /** the compareTo method compares the alphabetical order or the
	 	distance between the current place and return an integer 
	 	@param o the object to be compared.
		@return a negative integer, zero, or a positive integer as this object 
		is less than, equal to, or greater than the specified object.*/
	public int compareTo(Places o) {
		if (currentPlace == null)
			return this.name.compareTo(o.name);
		else{
			if (this.getDistance() < o.getDistance())
				return -1;
			else if (this.getDistance() > o.getDistance())
				return 1;
			else
				return 0;
		}
	}

}
