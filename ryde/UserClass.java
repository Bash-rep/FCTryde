package ryde;

import dataStructures.Map;

public class UserClass implements User {
	Map<Date,Trip> rides;//boleias
	Map<Date,Trip> trips;//deslocacoes
	String email,password,nomeCompleto;
	
	public UserClass(String email, String password, String nomeCompleto) {
		this.email = email;
		this.password = password;
		this.nomeCompleto = nomeCompleto;
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
