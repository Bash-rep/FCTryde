package ryde;

import dataStructures.Map;
import dataStructures.MapWithJavaClass;
import exception.InvalidTripDateException;
import exception.TripHasRidesException;
import exception.TwoTripsOnSameDayException;

public class UserClass implements User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Map<Date, Trip> rides;// boleias
	Map<Date, Trip> trips;// deslocacoes
	String email, password, nomeCompleto;
	int visits;

	public UserClass(String email, String password, String nomeCompleto) {
		this.email = email;
		this.password = encrypt(password);
		this.nomeCompleto = nomeCompleto;
		this.visits = 0;
		rides = new MapWithJavaClass<Date, Trip>();
		trips = new MapWithJavaClass<Date, Trip>();
	}

	private String encrypt(String pwd) {
		String aux = "";
		for (int i = 0; i < pwd.length(); i++) {
			aux += (char) (pwd.charAt(i) + 1);
		}
		return aux;
	}

	private String decrypt(String pwd) {
		String aux = "";
		for (int i = 0; i < pwd.length(); i++) {
			aux += (char) (pwd.charAt(i) - 1);
		}
		return aux;
	}

	@Override
	public boolean checkPassword(String pwd) {
		if (decrypt(password).equals(pwd)) {
			return true;
		}
		return false;
	}

	@Override
	public int addTrip(Date date, Trip trip) throws TwoTripsOnSameDayException {
		if (rides.find(date) != null || trips.find(date) != null) {
			throw new TwoTripsOnSameDayException();
		}
		trips.insert(date, trip);
		return trips.size();
	}

	@Override
	public Trip removeTrip(Date date) throws TripHasRidesException, InvalidTripDateException {
		Trip trip;
		if ((trip = trips.find(date)) == null) {
			throw new InvalidTripDateException();
		}
		if (trip.takenSeats() > 0) {
			throw new TripHasRidesException();
		}
		return trips.remove(date);
	}

	@Override
	public int addRide(Date date, Trip trip) throws TwoTripsOnSameDayException {
		if (rides.find(date) != null || trips.find(date) != null) {
			throw new TwoTripsOnSameDayException();
		}
		rides.insert(date, trip);
		return rides.size();
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public int incNumberOfVisits() {
		return ++visits;
	}

	@Override
	public String getName() {
		return nomeCompleto;
	}

	@Override
	public Trip getTrip(Date date) throws InvalidTripDateException {
		Trip trip = trips.find(date);
		if (trip == null) {
			throw new InvalidTripDateException();
		} else {
			return trip;
		}
	}

	@Override
	public Trip removeRide(Date date, User current) throws InvalidTripDateException {
		Trip trip;
		
		if ((trip = rides.remove(date)) == null) {
			throw new InvalidTripDateException();
		}
		return trip;
	}

}
