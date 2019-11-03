import java.util.Scanner;

import ryde.*;

public class Main {

	
	// cts que definem os comandos
	private static final String EXIT = "termina";
	private static final String SWITCH_USER = "sai";
	private static final String LIST = "lista";
	private static final String REGIST = "regista";
	private static final String LOGIN = "entrada";
	private static final String HELP = "ajuda";
	private static final String NEW_TRIP = "nova";
	private static final String REMOVE_TRIP = "remove";
	private static final String NEW_RIDE = "boleia";
	private static final String REMOVE_RIDE = "retira";
	private static final String CONSULT = "consulta";

	// cts que definem as mensagens
	private static final String INVALID_CMD = "Comando invalido.";
	private static final String REGIST_FAIL = "Registo nao realizado.";
	private static final String UTILIZADOR_JA_EXISTENTE = "Utilizador ja existente.";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String cmd = " ";
		Ryde ride = new RydeClass();
		while (!cmd.equalsIgnoreCase(EXIT)) {
			cmd = readCmd(in);
			switch (cmd) {
			case LIST:break;
			case REGIST:processRegist(in, ride);break;
			case SWITCH_USER:break;
			case LOGIN:break;
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

	private static void processRegist(Scanner in, Ryde ride) {
		int i = 0;
		String email, name = "", pwd = "";
		
		email = in.nextLine();
		if (ride.hasUser(email))
			System.out.println(UTILIZADOR_JA_EXISTENTE);
		else if(ride.getCurrentUser() != null) {
			System.out.println(INVALID_CMD);
		}
		else {
			System.out.print("nome (maximo 50 caracteres): ");
			name = in.nextLine().trim();

			while (i > -1 && i < 3) {
				System.out.print("password (entre 4 e 6 caracteres - digitos e letras): ");
				pwd = in.nextLine();
				if (isValid(pwd)) {
					i = -2;
				}
				i++;
			}
			if (i > 0) {
				System.out.println(REGIST_FAIL);
			} else {
				System.out.printf("Registo %d efetuado.\n",ride.addUser(email, name, pwd));
			}
		}
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
