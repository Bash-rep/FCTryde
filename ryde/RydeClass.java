package ryde;

import dataStructures.Map;
import exception.*;

public class RydeClass implements Ryde{
	Map<String,Map<String,Trip>> ridesByDate;
	Map<String,User> users;
	User current;
	@Override
	public int addUser(String email, String nome, String password) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String logOut() throws NoLogInException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int logIn(String email, String pwd)
			throws InvalidPasswordException, MultipleLogInException, NoSuchUserException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int addTrip(String start, String end, Date date, int duration, int seats)
			throws NoLogInException, TwoTripsOnSameDayException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Trip removeTrip(Date date) throws TripHasRidesException, NoLogInException, InvalidTripDateException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int addRide(String driver, Date date) throws DuplicateUserException, NoLogInException, NoSuchUserException,
			InvalidTripDateException, TwoTripsOnSameDayException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String removeRide(Date date) throws NoLogInException, InvalidTripDateException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Trip getTripInfo(String owner, Date date)
			throws NoLogInException, NoSuchUserException, InvalidTripDateException {
		// TODO Auto-generated method stub
		return null;
	}
}
