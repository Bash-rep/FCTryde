package ryde;

import dataStructures.*;

public class TripClass implements Trip {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Queue<User> queue;
	Map<String, User> inCar;
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
		inCar = new MapWithJavaClass<>();
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
		if (inCar.find(current.getEmail()) != null) {
			System.out.println("ERRO: Isto nao pode acontecer (TripClass)");
		}
		return allocateUser(current);
	}

	private int allocateUser(User current) {
		if(freeSeats()>0) {
			inCar.insert(current.getEmail(), current);
			return 0;
		}
		queue.enqueue(current);
		return inQueue();
	}
}
