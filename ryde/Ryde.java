package ryde;

import java.io.Serializable;

import exception.DuplicateUserException;
import exception.InvalidPasswordException;
import exception.InvalidTripDateException;

import exception.TripHasRidesException;
import exception.TwoTripsOnSameDayException;

public interface Ryde extends Serializable {
	/**
	 * Adds a new user to the database and returns the number of registered users at
	 * the moment. If there's one already with that email it throws an exception
	 * 
	 * @param email
	 * @param nome
	 * @param password
	 * @return the number of users registered
	 * @throws UserAlreadyExistsException
	 */
	int addUser(String email, String nome, String password);

	/**
	 * Logs the current user out and returns it. If there is no one logged id it
	 * throws an exception
	 * 
	 * @return the previous logged in user
	 * @throws NoLogInException
	 */
	String logOut();

	/**
	 * logs a user to the system and returns the number of logins of this user. If
	 * the password is invalid or another user is logged in or the email given isn't
	 * a key for any user in the system it throws an exception
	 * 
	 * @return number of log ins
	 * @throws InvalidPasswordException
	 */
	int logIn(String email, String pwd) throws InvalidPasswordException;

	/**
	 * Adds a new trip to the person currently logged in and returns the number of
	 * trips already registered by this user. if there is no one logged in or the
	 * user already has a trip or ride on that day it throws an exception
	 * 
	 * @param start
	 * @param end
	 * @param date
	 * @param duration
	 * @param seats
	 * @return number of trips
	 * @throws NoLogInException
	 * @throws TwoTripsOnSameDayException
	 */
	int addTrip(String start, String end, Date date, int duration, int seats)
			throws  TwoTripsOnSameDayException;

	/**
	 * removes a trip on this given date from the logged in user. if there is rides
	 * registered on this trip already it throws an exception. If no one is logged
	 * in or there's no trips registered on that day for the logged in user it
	 * throws an exception
	 * 
	 * @param date
	 * @return
	 * @throws TripHasRidesException
	 * @throws NoLogInException
	 * @throws InvalidTripDateException
	 */
	Trip removeTrip(Date date) throws TripHasRidesException , InvalidTripDateException;

	/**
	 * Adds a new ride to the user who is logged in (who cannot have a trip or ride
	 * registered on that day) and returns 0 if the ride was added or n when this
	 * ride is already full. Where n>0 and n=number of people in queue (including
	 * you). When this happens the rider goes to a queue.
	 * 
	 * @return 0 or the queue number
	 * @throws DuplicateUserException
	 * @throws NoLogInException
	 * @throws NoSuchUserException
	 * @throws InvalidTripDateException
	 */
	int addRide(String driver, Date date) throws DuplicateUserException,
			InvalidTripDateException, TwoTripsOnSameDayException;

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
	String removeRide(Date date) throws  InvalidTripDateException;

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
	Trip getTripInfo(String owner, Date date) throws InvalidTripDateException;

	/**
	 * Checks whether a user with the email </email> exists. Returns true if yes,
	 * false otherwise
	 * 
	 * @param email
	 * @return true if user exists, false if not
	 */
	boolean hasUser(String email);

	/**
	 * Returns the currently logged in user email
	 * 
	 * @return current user
	 */
	String getCurrentUserEmail();

	String getCurrentUserName();
}
