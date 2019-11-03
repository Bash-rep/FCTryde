package ryde;

import dataStructures.Map;
import dataStructures.MapWithJavaClass;
import exception.*;

public class RydeClass implements Ryde {

	Map<String, Map<String, Trip>> ridesByDate;
	Map<String, User> users;
	User current;

	public RydeClass() {
		this.ridesByDate = new MapWithJavaClass<String, Map<String, Trip>>();
		this.users = new MapWithJavaClass<String, User>();
		this.current = null;
	}

	@Override
	public boolean hasUser(String email) {
		return users.find(email) != null;
	}

	@Override
	public int addUser(String email, String nome, String password) {
		User user = new UserClass(email, password, nome);
		users.insert(email, user);
		return users.size();
	}

	@Override
	public User getCurrentUser() {
		return current;
	}

	@Override
	public String logOut() {
		String prevLoggedEmail;
		prevLoggedEmail = current.getEmail();
		current = null;
		return prevLoggedEmail;
	}

	@Override
	public int logIn(String email, String pwd)
			throws InvalidPasswordException, MultipleLogInException, NoSuchUserException {
		User user;

		if (current != null)
			throw new MultipleLogInException();
		else if (!hasUser(email))
			throw new NoSuchUserException();
		else if (!(user = users.find(email)).checkPassword(pwd))
			throw new InvalidPasswordException();
		else
			current = user;

		return 0;
	}

	@Override
	public int addTrip(String start, String end, Date date, int duration, int seats) throws TwoTripsOnSameDayException {

		return 0;
	}

	@Override
	public Trip removeTrip(Date date) throws TripHasRidesException, InvalidTripDateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addRide(String driver, Date date)
			throws DuplicateUserException, NoSuchUserException, InvalidTripDateException, TwoTripsOnSameDayException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String removeRide(Date date) throws InvalidTripDateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trip getTripInfo(String owner, Date date) throws NoSuchUserException, InvalidTripDateException {
		// TODO Auto-generated method stub
		return null;
	}
}
