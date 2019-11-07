package ryde;

import dataStructures.DoublyLinkedList;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.Map;
import dataStructures.MapWithJavaClass;
import dataStructures.RadixSort;
import exception.InvalidTripDateException;
import exception.TripHasRidesException;
import exception.TwoTripsOnSameDayException;

public class UserClass implements User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Map<Integer, Trip> rides;// boleias
	Map<Integer, Trip> trips;// deslocacoes
	String email, password, nomeCompleto;
	int visits;

	public UserClass(String email, String password, String nomeCompleto) {
		this.email = email;
		this.password = encrypt(password);
		this.nomeCompleto = nomeCompleto;
		this.visits = 0;
		rides = new MapWithJavaClass<>();
		trips = new MapWithJavaClass<>();
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
		if (rides.find(date.toInt()) != null || trips.find(date.toInt()) != null) {
			throw new TwoTripsOnSameDayException();
		}
		trips.insert(date.toInt(), trip);
		return trips.size();
	}

	@Override
	public Trip removeTrip(Date date) throws TripHasRidesException, InvalidTripDateException {
		Trip trip;
		if ((trip = trips.find(date.toInt())) == null) {
			throw new InvalidTripDateException();
		}
		if (trip.takenSeats() > 0) {
			throw new TripHasRidesException();
		}
		return trips.remove(date.toInt());
	}

	@Override
	public int addRide(Date date, Trip trip) throws TwoTripsOnSameDayException {
		int inQueue;
		if (rides.find(date.toInt()) != null || trips.find(date.toInt()) != null) {
			throw new TwoTripsOnSameDayException();
		}
		if ((inQueue = trip.add(this)) > 0) {
			return inQueue;
		}
		rides.insert(date.toInt(), trip);
		return 0;
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
		Trip trip = trips.find(date.toInt());
		if (trip == null) {
			throw new InvalidTripDateException();
		} else {
			return trip;
		}
	}

	@Override
	public Trip removeRide(Date date, User current) throws InvalidTripDateException {
		Trip trip;
		
		if ((trip = rides.remove(date.toInt())) == null) {
			throw new InvalidTripDateException();
		}
		return trip;
	}

	@Override
	public Iterator<Entry<Integer, Trip>> getTripsIterator() {
		Iterator<Entry<Integer, Trip>> it = trips.iterator();
		DoublyLinkedList<Entry<Integer, Trip>> toSort = new DoublyLinkedList<>();

		if(!it.hasNext()) {
			return trips.iterator();
		}
		while(it.hasNext()) {
			toSort.addLast(it.next());
		}
		return RadixSort.sort(toSort, 8).iterator();
	}

	@Override
	public Iterator<Entry<Integer, Trip>> getRidesIterator() {
		Iterator<Entry<Integer, Trip>> it = rides.iterator();
		DoublyLinkedList<Entry<Integer, Trip>> toSort = new DoublyLinkedList<>();
		
		if(!it.hasNext()) {
			return rides.iterator();
		}
		
		while(it.hasNext()) {
			toSort.addLast(it.next());
		}
		return RadixSort.sort(toSort, 8).iterator();
	}

}
