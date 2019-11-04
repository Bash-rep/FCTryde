import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import exception.InvalidPasswordException;
import exception.InvalidTripDateException;
import exception.TripHasRidesException;
import exception.TwoTripsOnSameDayException;
import ryde.*;

public class Main {

	// cts que definem os comandos
	private static final String EXIT = "termina";
	private static final String LOGOUT = "sai";
	private static final String LIST = "lista";
	private static final String SIGNUP = "regista";
	private static final String LOGIN = "entrada";
	private static final String HELP = "ajuda";
	private static final String NEW_TRIP = "nova";
	private static final String REMOVE_TRIP = "remove";
	private static final String NEW_RIDE = "boleia";
	private static final String REMOVE_RIDE = "retira";
	private static final String CONSULT = "consulta";

	// cts que definem as mensagens
	private static final String NEW_TRIP_SUCCESS = "Deslocacao %d registada. Obrigado %s.\n";
	private static final String INVALID_CMD = "Comando invalido.";
	private static final String SIGNUP_FAIL = "Registo nao realizado.";
	private static final String UTILIZADOR_JA_EXISTENTE = "Utilizador ja existente.";
	private static final String VALID_NAME = "nome (maximo 50 caracteres): ";
	private static final String VALID_PWD = "password (entre 4 e 6 caracteres - digitos e letras): ";
	private static final String SIGNUP_SUCCESS = "Registo %d efetuado.\n";
	private static final String FAREWELL = "Ate a proxima %s.\n";
	private static final String NO_SUCH_USER = "Utilizador nao existente.";
	private static final String SUCCESSFUL_LOGIN = "Visita %d efetuada.\n";
	private static final String TWO_TRIPS_SAME_DAY = "%s ja tem uma deslocacao ou boleia nesta data.\n";
	private static final String INVALID_DATA = "Dados invalidos.\n";
	private static final String NO_SUCH_TRIP = "%s nesta data nao tem registo de deslocacao.\n";
	private static final String CANNOT_REMOVE_TRIP = "%s ja nao pode eliminar esta deslocacao.";
	private static final String REMOVE_TRIP_SUCCESS = "Deslocacao removida.";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String cmd = " ";
		Ryde ryde;

		File persistence = new File("persistence.ser");

		if (!persistence.exists()) {
			ryde = new RydeClass();
		} else {
			ryde = load();
		}

		mainCycle: while (true != false) {
			printPrompt(ryde);
			cmd = readCmd(in);
			switch (cmd) {
			case LIST:
				break;
			case SIGNUP:
				processSignup(in, ryde);
				break;
			case LOGOUT:
				processLogout(ryde);
				break;
			case LOGIN:
				processLogin(in, ryde);
				break;
			case HELP:
				break;
			case NEW_TRIP:
				processNewTrip(in, ryde);
				break;
			case REMOVE_TRIP:
				processRemoveTrip(in, ryde);
				break;
			case REMOVE_RIDE:
				break;
			case NEW_RIDE:
				break;
			case CONSULT:
				break;
			case EXIT:
				if (processExit(ryde))
					break mainCycle;
				break;
			default:
				in.nextLine();
				System.out.println(INVALID_CMD);
			}
		}

		store(ryde);
	}

	private static boolean processExit(Ryde ryde) {
		if (ryde.getCurrentUserEmail() == null) {
			System.out.println("Obrigado. Ate a proxima.");
		} else {
			System.out.println(INVALID_CMD);
			return false;
		}
		return true;
	}

	private static void printPrompt(Ryde ryde) {
		String email;
		if ((email = ryde.getCurrentUserEmail()) == null)
			System.out.print("> ");
		else {
			System.out.print(email + " > ");
		}
	}

	private static void processSignup(Scanner in, Ryde ryde) {
		int i = 0;
		String email, name = "", pwd = "";

		email = in.nextLine().trim();

		if (ryde.hasUser(email))
			System.out.println(UTILIZADOR_JA_EXISTENTE);
		else if (ryde.getCurrentUserEmail() != null) {
			System.out.println(INVALID_CMD);
		} else {
			System.out.print(VALID_NAME);

			name = in.nextLine().trim();

			for (i = 0; i < 3; i++) {
				System.out.print(VALID_PWD);
				pwd = in.nextLine();
				if (pwdIsValid(pwd)) {
					System.out.printf(SIGNUP_SUCCESS, ryde.addUser(email, name, pwd));
					return;
				}
			}
			System.out.println(SIGNUP_FAIL);

		}
	}

	private static void processLogout(Ryde ryde) {
		if (ryde.getCurrentUserEmail() == null)
			System.out.println(INVALID_CMD);
		else
			System.out.printf(FAREWELL, ryde.logOut());
	}

	private static void processLogin(Scanner in, Ryde ryde) {
		String email = in.nextLine().trim();

		if (ryde.getCurrentUserEmail() != null) {
			System.out.println(INVALID_CMD);
		} else if (!ryde.hasUser(email)) {
			System.out.println(NO_SUCH_USER);
		} else {
			int logins = -1;
			for (int i = 0; i < 3 && logins == -1; i++) {
				System.out.print("password: ");
				String pwd = in.nextLine();
				if ((logins = logIn(email, pwd, ryde)) > -1) {
					System.out.printf(SUCCESSFUL_LOGIN, logins);
					return;
				}
			}
			System.out.println("Entrada nao realizada.");
		}
	}

	private static int logIn(String email, String pwd, Ryde ryde) {
		try {
			return ryde.logIn(email, pwd);
		} catch (InvalidPasswordException e) {
			return -1;
		}
	}

	private static void processNewTrip(Scanner in, Ryde ryde) {
		in.nextLine();
		if (ryde.getCurrentUserEmail() == null)
			System.out.println(INVALID_CMD);
		else {
			String start = in.nextLine().trim();
			String end = in.nextLine().trim();
			String dateStr = in.next().trim();
			String time = in.next().trim();
			int duration = in.nextInt();
			int seats = in.nextInt();
			in.nextLine();

			String[] splitDate = dateStr.split("-");
			String[] splitTime = time.split(":");

			Date date = new Date(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]),
					Integer.parseInt(splitDate[0]), Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));

			if (!dateIsValid(date) || duration < 1 || seats < 0 || seats > 10) {
				System.out.println(INVALID_DATA);
			} else {
				try {
					System.out.printf(NEW_TRIP_SUCCESS, ryde.addTrip(start, end, date, duration, seats),
							ryde.getCurrentUserName());
				} catch (TwoTripsOnSameDayException e) {
					System.out.printf(TWO_TRIPS_SAME_DAY, ryde.getCurrentUserName());
				}
			}
		}
	}

	private static void processRemoveTrip(Scanner in, Ryde ryde) {
		if (ryde.getCurrentUserEmail() == null) {
			System.out.println(INVALID_CMD);
		} else {
			String dateStr = in.next().trim();
			in.nextLine();
			String[] splitDate = dateStr.split("-");
			Date date = new Date(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]),
					Integer.parseInt(splitDate[0]), 0, 0);
			try {
				ryde.removeTrip(date);
				System.out.println(REMOVE_TRIP_SUCCESS);
			} catch (TripHasRidesException e) {
				System.out.printf(CANNOT_REMOVE_TRIP, ryde.getCurrentUserName());
			} catch (InvalidTripDateException e) {
				System.out.printf(NO_SUCH_TRIP, ryde.getCurrentUserName());
			}
		}
	}
	
	private static boolean dateIsValid(Date date) {
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		int hour = date.getHour();
		int minute = date.getMinute();
		
		if (year < 0 || month < 1 || month > 12 || day < 1 || day > 31) {
			return false;
		}
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
			return false;
		}
		if (month == 2) {
			if (day > 29) {
				return false;
			}
			if (day > 28) {
				if (year%4 != 0) {
					return false;
				} else if (year%100 == 0 && year%400 != 0) {
					return false;
				}
			}
		}
		if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
			return false;
		}
		return true;
	}

	private static boolean pwdIsValid(String pwd) {
		if (pwd.toCharArray().length > 3 && pwd.toCharArray().length < 7 && pwd.chars().anyMatch(Character::isLetter)
				&& pwd.chars().anyMatch(Character::isDigit) && pwd.chars().allMatch(Character::isLetterOrDigit)) {
			return true;
		}
		return false;
	}

	private static String readCmd(Scanner in) {
		String cmd = in.next().trim().toLowerCase();
		return cmd;
	}

	private static Ryde load() {

		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream("persistence.ser"));
			Ryde ryde = (Ryde) file.readObject();
			file.close();
			return ryde;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void store(Ryde ryde) {

		try {
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("persistence.ser"));
			file.writeObject(ryde);
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
