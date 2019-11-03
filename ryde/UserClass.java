package ryde;

import dataStructures.Map;

public class UserClass implements User {
	Map<Date,Trip> rides;//boleias
	Map<Date,Trip> trips;//deslocacoes
	String email,passowrd,nomeCompleto;
	
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
	
}
