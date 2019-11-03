package ryde;

public interface Trip {	

	/**
	 * returns the person who created this trip
	 * @return owner
	 */
	User getOwner();

	/**
	 * returns the duration of this trip
	 * @return duration
	 */
	int getDuration();

	/**
	 * returns the starting location of this trip
	 * @return start
	 */
	String getStart();

	/**
	 * returns the destination of this trip
	 * @return destionation
	 */
	String getEnd();

	/**
	 * returns the date of when this trip is happening
	 * @return date
	 */
	Date getDate();
	
	/**
	 * returns the number of people in queue
	 * @return
	 */
	int inQueue();

	/**
	 * returns the number of free seats
	 * @return
	 */
	int freeSeats();
	
	/**
	 * returns the number of taken seats.
	 * @return
	 */
	int takenSeats();
	
	
}
