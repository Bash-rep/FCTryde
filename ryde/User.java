package ryde;

import java.io.Serializable;

import dataStructures.Entry;
import dataStructures.Iterator;
import exception.InvalidTripDateException;
import exception.TripHasRidesException;
import exception.TwoTripsOnSameDayException;

public interface User extends Serializable {
	/**
	 * Password verifier. The password sorted is encrypted and this method does what
	 * is needed to check if the password is valid. Returns true if the password is
	 * valid.
	 * 
	 * @param pwd
	 * @return true if match
	 */
	boolean checkPassword(String pwd);

	/**
	 * adds a new ride to this user if there's already another ride or trip
	 * scheduled to this date it throws an exception.on success returns how many
	 * rides this user has
	 * 
	 * @param date
	 * @param trip
	 * @return number of trips
	 * @throws TwoTripsOnSameDayException
	 */
	int addRide(Date date, Trip trip) throws TwoTripsOnSameDayException;

	/**
	 * adds a new trip to this user. if there's already another ride or trip
	 * scheduled to this date it throws an exception. on success returns 0 and if
	 * the car was full it doesn't add the ride to the user but puts it in queue
	 * 
	 * @param date
	 * @param trip
	 * @return number of trips
	 * @throws TwoTripsOnSameDayException
	 */
	int addTrip(Date date, Trip trip) throws TwoTripsOnSameDayException;

	/**
	 * returns this user's email
	 * 
	 * @return
	 */
	String getEmail();

	/**
	 * removes the trip this user has on this date. on success returns the trip that
	 * was removed. If there is no trip on this date or the trip as already other
	 * users on the ride it throws an exception
	 * 
	 * @param date
	 * @return removed trip
	 * @throws TripHasRidesException
	 * @throws InvalidTripDateException
	 */
	Trip removeTrip(Date date) throws TripHasRidesException, InvalidTripDateException;

	/**
	 * increments the number of visits from this user on the application. always
	 * succeeds
	 * 
	 * @return
	 */
	int incNumberOfVisits();

	/**
	 * gets this user name
	 * 
	 * @return
	 */
	String getName();

	/**
	 * gets a trip this user has on this date
	 * 
	 * @param date
	 * @return trip on given date
	 * @throws InvalidTripDateException
	 */
	Trip getTrip(Date date) throws InvalidTripDateException;

	/**
	 * removes a trip this user has on this date
	 * 
	 * @param date
	 * @param current
	 * @return
	 * @throws InvalidTripDateException
	 */
	Trip removeRide(Date date, User current) throws InvalidTripDateException;
	
	/**
	 * returns an iterator of this user trips
	 * @return
	 */
	Iterator<Entry<Integer, Trip>> getTripsIterator();
	
	/**
	 * returns an iterator of this user rides
	 * @return
	 */
	Iterator<Entry<Integer, Trip>> getRidesIterator();

}
