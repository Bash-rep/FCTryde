package ryde;

import dataStructures.*;

public class TripClass implements Trip {
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
		this.date= date;
		this.duration = duration;
		this.seats = seats;
	}
	
	public User queue(User user) {
		queue.enqueue(user);
		return user;
	}
	
	public User cancelRide(User user) {
		
		return owner;
	}
	
	@Override
	public int inQueue() {
		return queue.size();
	}
	
	@Override
	public int freeSeats() {
		return seats-inCar.size();
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
}
