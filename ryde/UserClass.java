package ryde;

import dataStructures.Map;
import dataStructures.MapWithJavaClass;

public class UserClass implements User {
	Map<Date,Trip> rides;//boleias
	Map<Date,Trip> trips;//deslocacoes
	String email,password,nomeCompleto;
	
	public UserClass(String email, String password, String nomeCompleto) {
		this.email = email;
		this.password = encrypt(password);
		this.nomeCompleto = nomeCompleto;
		rides = new MapWithJavaClass<Date, Trip>();
		trips = new MapWithJavaClass<Date, Trip>();
	}
	
	private String encrypt(String pwd) {
		String aux = "";
		for(int i = 0 ; i < pwd.length() ; i++) {
			aux += (char)(pwd.charAt(i)+1);
		}
		return aux;
	}

	private String decrypt(String pwd) {
		String aux = "";
		for(int i = 0 ; i < pwd.length() ; i++) {
			aux += (char)(pwd.charAt(i)-1);
		}
		return aux;
	}

	@Override
	public boolean checkPassword(String pwd) {
		if(decrypt(password).equals(pwd))return true;
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
