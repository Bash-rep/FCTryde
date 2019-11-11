package ryde;

import dataStructures.*;
import exception.TwoTripsOnSameDayException;

public class TripClass implements Trip {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Queue<User> queue;
	List<User> inCar;
	User owner;
	String start, end;
	Date date;
	int duration;
	int seats;

	public TripClass(User owner, String start, String end, Date date, int duration, int seats) {
		this.owner = owner;
		this.start = start;
		this.end = end;
		this.date = date;
		this.duration = duration;
		this.seats = seats;
		inCar = new DoublyLinkedList<>();
		queue = new QueueInArray<>();
	}

	@Override
	public int inQueue() {
		return queue.size();
	}

	@Override
	public int freeSeats() {
		return seats - inCar.size();
	}

	@Override
	public int takenSeats() {
		return inCar.size();
	}

	@Override
	public String getStart() {
		return start;
	}

	@Override
	public String getEnd() {
		return end;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public int getDuration() {
		return duration;
	}

	@Override
	public User getOwner() {
		return owner;
	}

	@Override
	public int add(User current) {
		if (inCar.find(current) == -1) {
			System.out.println("ERRO: Isto nao pode acontecer (TripClass)");
		}
		return allocateUser(current);
	}

	@Override
	public void removeRide(User current) {
		if (inCar.remove(inCar.find(current)).getEmail() == null) {
			System.out.println("ERRO: Isto nao pode acontecer (TripClass)");
		}
		if (queue.size() > 0) {
			seatNextUserInQueue();
		}
	}

	@Override
	public String toString() {
		
		Iterator<User> it;

		String carPeople = "Boleias: ";

		if (inCar.size() > 0) {
			carPeople = carPeople.substring(0, Math.min(carPeople.length(), carPeople.length() - 2));
			it = inCar.iterator();
			while (it.hasNext()) {
				carPeople += it.next().getEmail() + "; ";
			}
		} else {
			carPeople = "Sem boleias registadas.";
		}
		return owner.getEmail() + "\n" + start + "-" + end + "\n" + date + " " + duration + "\n" + "Lugares vagos: "
				+ freeSeats() + "\n" + carPeople + "\n" + "Em espera: " + inQueue();
	}

	/**
	 * called by removeRide if there are users in queue. fills empty seats in the
	 * trip with users from the queue, if they haven't registered a trip or ride in
	 * the meantime
	 */
	private void seatNextUserInQueue() {
		User nextUserInQueue;
		while (queue.size() > 0 && freeSeats() > 0) {
			nextUserInQueue = queue.dequeue();
			try {
				nextUserInQueue.addRide(getDate(), this);
				inCar.addLast(nextUserInQueue);
			} catch (TwoTripsOnSameDayException e) {
			}
		}
	}

	private int allocateUser(User current) {
		if (freeSeats() > 0) {
			inCar.addLast(current);
			return 0;
		}
		queue.enqueue(current);
		return inQueue();
	}
}
