package ryde;

import dataStructures.Map;
import dataStructures.MapWithJavaClass;

public class UserClass implements User {
	Map<Date,Trip> rides;//boleias
	Map<Date,Trip> trips;//deslocacoes
	String email,password,nomeCompleto;
	
	public UserClass(String email, String password, String nomeCompleto) {
		this.email = email;
		this.password = password;
		this.nomeCompleto = nomeCompleto;
		rides = new MapWithJavaClass<Date, Trip>();
		trips = new MapWithJavaClass<Date, Trip>();
	}
	
	@Override
	public boolean checkPassword(String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addRide(Date date, Trip trip) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addTrip(Date date, Trip trip) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
