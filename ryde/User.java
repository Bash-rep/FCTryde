package ryde;

public interface User {
	/**
	 * Password verifier. The password sorted is encrypted and this method does what
	 * is needed to check if the password is valid. Returns true if the password is
	 * valid.
	 * 
	 * @param pwd
	 * @return
	 */
	boolean checkPassword(String pwd);

	/**
	 * Adds a new ride to the user. It adds this trip on the rides map and uses the
	 * date as key
	 * 
	 * @param date
	 * @param trip
	 * @return
	 */
	int addRide(Date date, Trip trip);

	/**
	 * Adds a new trip to the user. It adds this trip to the trips map and uses date
	 * as key
	 * 
	 * @param date
	 * @param trip
	 * @return
	 */
	int addTrip(Date date, Trip trip);
	
	/**
	 * returns this user's  email
	 * @return
	 */
	String getEmail();
	
}
