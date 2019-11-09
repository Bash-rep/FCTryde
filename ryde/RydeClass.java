package ryde;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.Map;
import dataStructures.MapWithJavaClass;
import dataStructures.SortedMapWithJavaClass;
import exception.*;

public class RydeClass implements Ryde {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Map<Date, Map<String, Trip>> tripsByDate;
	Map<String, User> users;
	User current;

	public RydeClass() {
		this.tripsByDate = new SortedMapWithJavaClass<Date, Map<String, Trip>>(0);

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
	public int logIn(String email, String pwd) throws InvalidPasswordException {
		User user;

		if (!(user = users.find(email)).checkPassword(pwd))
			throw new InvalidPasswordException();

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
		Trip trip = new TripClass(current, start, end, date, duration, seats);
		
		int a = current.addTrip(date, trip);
		
		Map<String,Trip> map = tripsByDate.find(date);
		
		if(map == null) {
			map = new SortedMapWithJavaClass<String, Trip>(0);
			tripsByDate.insert(date, map);
		}
		
		map.insert(current.getEmail(), trip);

		return a;
	}



	@Override
	public String removeTrip(Date date) throws TripHasRidesException, InvalidTripDateException {
		String s = current.removeTrip(date).toString();
		tripsByDate.find(date).remove(current.getEmail());
		return s;
	}

	@Override
	public int addRide(String driver, Date date)
			throws InvalidTripDateException, TwoTripsOnSameDayException, CannotCatchOwnRideException {
		User owner = users.find(driver);
		Trip trip = owner.getTrip(date);
		
		if (driver.equals(current.getEmail())) {
			throw new CannotCatchOwnRideException();
		}
		
		return current.addRide(date, trip);
	}

	@Override
	public String removeRide(Date date) throws InvalidTripDateException {
		Trip trip = current.removeRide(date, current);
		trip.removeRide(current);
		return current.getName();
	}

	@Override
	public String getTripInfo(String owner, Date date) throws InvalidTripDateException {
		return users.find(owner).getTrip(date).toString();
	}

	@Override
	public String getCurrentUserEmail() {
		if (current == null)
			return null;
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

	@Override
	public Iterator<Entry<Integer, Trip>> tripsIterator() {
		return current.getTripsIterator();
	}

	@Override
	public Iterator<Entry<Integer, Trip>> ridesIterator() {
		return current.getRidesIterator();
	}

	@Override
	public Iterator<Entry<Integer, Trip>> tripsIterator(String email) {
		return users.find(email).getTripsIterator();
	}

	@Override
	public Iterator<Entry<String, Trip>> getAllTripsOnThisDate(Date date) throws InvalidTripDateException {
		Map<String, Trip> map;
		if((map = tripsByDate.find(date)) == null)
			throw new InvalidTripDateException();		
		return map.iterator();
	}
	
	@Override
	public Iterator<Entry<Date, Map<String, Trip>>> getAllTrips() {
		return tripsByDate.iterator();
	}

}
