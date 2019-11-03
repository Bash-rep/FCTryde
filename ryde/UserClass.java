package ryde;

import dataStructures.Map;

public class UserClass implements User {
	Map<String,Trip> rides;//boleias
	Map<String,Trip> trips;//deslocacoes
	String email,passowrd,nomeCompleto;
	
	@Override
	public boolean checkPassword(String pwd) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
