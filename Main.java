import java.util.Scanner;

import exception.InvalidPasswordException;
import exception.MultipleLogInException;
import exception.NoSuchUserException;
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
	private static final String INVALID_CMD = "Comando invalido.";
	private static final String SIGNUP_FAIL = "Registo nao realizado.";
	private static final String UTILIZADOR_JA_EXISTENTE = "Utilizador ja existente.";
	private static final String VALID_NAME = "nome (maximo 50 caracteres): ";
	private static final String VALID_PWD = "password (entre 4 e 6 caracteres - digitos e letras): ";
	private static final String SIGNUP_SUCCESS = "Registo %d efetuado.\n";
	private static final String FAREWELL = "Ate a proxima %s.\n";
	private static final String NO_SUCH_USER = "Utilizador nao existente.\n";
	private static final String SUCCESSFUL_LOGIN = "Visita %d efetuada.\n";
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String cmd = " ";
		Ryde ryde = new RydeClass();
		while (!cmd.equalsIgnoreCase(EXIT)) {
			cmd = readCmd(in);
			switch (cmd) {
			case LIST:break;
			case SIGNUP:signup(in, ryde);break;
			case LOGOUT:logout(ryde);break;
			case LOGIN:login(in, ryde);break;
			case HELP:break;
			case NEW_TRIP:break;
			case REMOVE_TRIP:break;
			case REMOVE_RIDE:break;
			case NEW_RIDE:break;
			case CONSULT:break;
			case EXIT:break;
			default:
				in.nextLine();
				System.out.println(INVALID_CMD);
			}
		}
	}

	private static void signup(Scanner in, Ryde ryde) {
		int i = 0;
		String email, name = "", pwd = "";
		
		email = in.nextLine();
		if (ryde.hasUser(email))
			System.out.println(UTILIZADOR_JA_EXISTENTE);
		else if(ryde.getCurrentUser() != null) {
			System.out.println(INVALID_CMD);
		}
		else {
			System.out.print(VALID_NAME);
			name = in.nextLine().trim();

			while (i > -1 && i < 3) {
				System.out.print(VALID_PWD);
				pwd = in.nextLine();
				if (isValid(pwd)) {
					i = -2;
				}
				i++;
			}
			if (i > 0) {
				System.out.println(SIGNUP_FAIL);
			} else {
				System.out.printf(SIGNUP_SUCCESS,ryde.addUser(email, name, pwd));
			}
		}
	}
	
	private static void logout(Ryde ryde) {
		if (ryde.getCurrentUser() == null)
			System.out.println(INVALID_CMD);
		else
			System.out.printf(FAREWELL, ryde.logOut());
	}
	
	private static void login(Scanner in, Ryde ryde) {
		String email = in.nextLine();
		
		if (!ryde.hasUser(email))
			System.out.println(NO_SUCH_USER);
		else if (ryde.getCurrentUser() != null)
			System.out.println(INVALID_CMD);
		else {
			String pwd = in.nextLine();
			for (int i = 0; i < 3; i++) {
				try {
					int numLogins = ryde.logIn(email, pwd);
					System.out.printf(SUCCESSFUL_LOGIN, numLogins);
					i = 3;
				} catch (InvalidPasswordException e) {}
			}
		}
	}
	
	private static void newTrip(Scanner in, Ryde ryde) {
		String start = in.nextLine();
		String end = in.nextLine();
		String dateStr = in.next().trim();
		String time = in.next().trim();
		String duration = in.next();
		int seats = in.nextInt();
		in.nextLine();
		
		String[] splitDate = dateStr.split("-");
		String[] splitTime = time.split(":");
		
		Date date = new Date(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]),
				Integer.parseInt(splitDate[0]), Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
	}
	
	private static void removeTrip(Scanner in, Ryde ryde) {
		String dateStr = in.next().trim();
		String time = in.nextLine();
		
		String[] splitDate = dateStr.split("-");
		String[] splitTime = time.split(":");
		
		Date date = new Date(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]),
				Integer.parseInt(splitDate[0]), Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
	}
	
	private static boolean isValid(String pwd) {
		if (pwd.toCharArray().length > 3 && pwd.toCharArray().length < 7
				&& pwd.chars().anyMatch(Character::isLetter) && pwd.chars().anyMatch(Character::isDigit)
				&& pwd.chars().allMatch(Character::isLetterOrDigit)) {
			return true;
		}
		return false;
	}
	

	private static String readCmd(Scanner in) {
		String cmd = in.next().trim().toLowerCase();
		return cmd;
	}

}
