package ryde;

public class Date implements Comparable<Date> {
	int year, month, day, hour, minute;

	public Date(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	@Override
	public boolean equals(Object o) {
		Date d = (Date) o;
		if (this.day == d.getDay() && this.getMonth() == d.getMonth() && this.getYear() == d.getYear()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * if the date parameter is more recent than this it returns a value less than
	 * zero. if this is more recent than parameter returns greater than 0. if they
	 * are equal return 0
	 * 
	 * this.compareTo(parameter) this>parameter = 1; parameter>this = -1
	 */
	@Override
	public int compareTo(Date o) {
		int aux;
		if ((aux = this.getYear() - o.getYear()) == 0)
			if ((aux = this.getMonth() - o.getMonth()) == 0)
				aux = this.getDay() - o.getDay();
		return aux;
	}

}
