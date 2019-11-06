package ryde;

import java.io.Serializable;

import dataStructures.Iterator;
import exception.CannotCatchOwnRideException;
import exception.InvalidPasswordException;
import exception.InvalidTripDateException;

import exception.TripHasRidesException;
import exception.TwoTripsOnSameDayException;

public interface Ryde extends Serializable {
	/**
	 * Adds a new user to the database and returns the number of registered users at
	 * the moment. If there's already a user with that email, throws an exception.
	 * 
	 * @param email
	 * @param nome
	 * @param password
	 * @return number of users registered (including newly registered user)
	 * @throws UserAlreadyExistsException
	 */
	int addUser(String email, String nome, String password);

	/**
	 * Logs the current user out and returns their email. If there is no one logged
	 * in, throws an exception.
	 * 
	 * @return the newly logged out user's email
	 * @throws NoLogInException
	 */
	String logOut();

	/**
	 * Logs a user in and returns the number of times this user has logged in. If
	 * the password is incorrect, another user is logged in, or the email provided
	 * doesn't belong to any user in the system, throws an exception.
	 * 
	 * @return number of times newly logged in user has logged in
	 * @throws InvalidPasswordException
	 */
	int logIn(String email, String pwd) throws InvalidPasswordException;

	/**
	 * Adds a new trip to the user currently logged in and returns the number of
	 * trips already registered by this user. If no one is logged in or the logged
	 * in user already has a trip/ride on that day, throws an exception.
	 * 
	 * @param start
	 * @param end
	 * @param date
	 * @param duration
	 * @param seats
	 * @return number of trips registered by currently logged in user (including
	 *         newly registered trip)
	 * @throws NoLogInException
	 * @throws TwoTripsOnSameDayException
	 */
	int addTrip(String start, String end, Date date, int duration, int seats) throws TwoTripsOnSameDayException;

	/**
	 * Removes a trip on the provided date from the logged in user. If there are
	 * rides registered on this trip already, throws an exception. If no one is
	 * logged in or there are no trips registered on that day for the logged in
	 * user, throws an exception.
	 * 
	 * @param date
	 * @return returns the removed trip object .toString()
	 * @throws TripHasRidesException
	 * @throws NoLogInException
	 * @throws InvalidTripDateException
	 */
	String removeTrip(Date date) throws TripHasRidesException, InvalidTripDateException;

	/**
	 * Adds a new ride to the currently logged in user and returns 0 if the ride was
	 * registered successfully or n when this ride is already full. Where (n > 0)
	 * and (n = number of users in queue) (including the currently logged in user).
	 * When this happens, the logged in user has not technically registered a ride
	 * for the provided date, and will be able to register rides or trips on this
	 * date for as long as they are in queue. If the currently logged in user has a
	 * ride or trip registered for the provided date, throws an exception. If the
	 * trip specified by the driver + date combination does not exist, throws an
	 * exception. If the specified driver is the currently logged in user, throws an
	 * exception.
	 * 
	 * @return 0 or the number of users in queue
	 * @throws DuplicateUserException
	 * @throws NoLogInException
	 * @throws NoSuchUserException
	 * @throws InvalidTripDateException
	 * @throws CannotCatchOwnRideException
	 */
	int addRide(String driver, Date date)
			throws InvalidTripDateException, TwoTripsOnSameDayException, CannotCatchOwnRideException;

	/**
	 * Removes the ride the logged in user registered for the specified date.
	 * Succeeds only if the logged in user has a ride on the specified date. Returns
	 * the name of the current user.
	 * 
	 * @param driver
	 * @param date
	 * @return the current user name
	 * @throws NoLogInException
	 * @throws InvalidTripDateException
	 */
	String removeRide(Date date) throws InvalidTripDateException;

	/**
	 * Gets the information about the trip happening on this date from this user. It
	 * returns the trip if it succeeds or throws an exception if there's no such
	 * user or it's not logged in or there is no trip on this date
	 * 
	 * @param owner
	 * @param date
	 * @return this date's trip
	 * @throws NoLogInException
	 * @throws NoSuchUserException
	 * @throws InvalidTripDateException
	 */
	String getTripInfo(String owner, Date date) throws InvalidTripDateException;

	/**
	 * Checks whether a user with the email </email> exists. Returns true if yes,
	 * false otherwise
	 * 
	 * @param email
	 * @return true if user exists, false if not
	 */
	boolean hasUser(String email);

	/**
	 * Returns the currently logged in user's email
	 * 
	 * @return current user email
	 */
	String getCurrentUserEmail();

	/**
	 * Returns the currently logged in user's name
	 * 
	 * @return current user name
	 */
	String getCurrentUserName();
	
	/**
	 * gets an iterator to List the current user Trips 
	 * @return
	 */
	Iterator<Trip> tripsIterator();
	
	/**
	 * gets an iterator to List the current user rides
	 * @return
	 */
	Iterator<Trip> ridesIterator();
	
	/**
	 * gets an iterator to List the given user rides.
	 * @param email
	 * @return
	 */
	Iterator<Trip> tripsIterator(String email);
}
