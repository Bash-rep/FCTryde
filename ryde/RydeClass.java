package ryde;

import dataStructures.Map;
import dataStructures.MapWithJavaClass;
import exception.*;

public class RydeClass implements Ryde {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Map<String, Map<String, Trip>> ridesByDate;
	Map<String, User> users;
	User current;

	public RydeClass() {
		this.ridesByDate = new MapWithJavaClass<String, Map<String, Trip>>();
		
		this.users = new MapWithJavaClass<String, User>();
		
		this.current = null;
	}
	
	@Override
	public String logOut() {
		String prevLoggedEmail = current.getName();
		
		current = null;
		
		return prevLoggedEmail;
	}

	@Override
	public int logIn(String email, String pwd)
			throws InvalidPasswordException {
		User user;
		
	    if (!(user = users.find(email)).checkPassword(pwd)) throw new InvalidPasswordException();
	    
		else
			current = user;

		return current.incNumberOfVisits();
	}

	@Override
	public int addUser(String email, String nome, String password) {
		User user = new UserClass(email, password, nome);
		
		users.insert(email, user);
		
		return users.size();
	}


	@Override
	public int addTrip(String start, String end, Date date, int duration, int seats) throws TwoTripsOnSameDayException {
		return current.addTrip(date, new TripClass(current, start, end, date, duration, seats));
	}

	@Override
	public Trip removeTrip(Date date) throws TripHasRidesException, InvalidTripDateException {
		return current.removeTrip(date);
	}

	@Override
	public int addRide(String driver, Date date)
			throws DuplicateUserException, InvalidTripDateException, TwoTripsOnSameDayException {
		User owner = users.find(driver);
		
		Trip trip = owner.getTrip(date);
		
		int inQueue; 
		if((inQueue = trip.add(current))>0) {
			return inQueue;
		}

		return current.addRide(date, trip);
	}

	@Override
	public String removeRide(Date date) throws InvalidTripDateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trip getTripInfo(String owner, Date date) throws InvalidTripDateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentUserEmail() {
		if(current == null) return null;
		return current.getEmail();
	}

	@Override
	public boolean hasUser(String email) {
		return users.find(email) != null;
	}

	@Override
	public String getCurrentUserName() {
		return current.getName();
	}

}
