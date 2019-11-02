import java.util.Scanner;

import ryde.*;

public class Main {
	//cts que definem os comandos
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
	
	
	
	//cts que definem as mensagens
	private static final String INVALID_CMD = "Comando invalido.";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String cmd = "";
		Ryde ride = new RydeClass();
		while(!cmd.equalsIgnoreCase(EXIT)) {
			cmd = readCmd(in);
			switch(cmd) {
			case LIST:break;
			case REGIST:break;
			case SWITCH_USER:break;
			case LOGIN:break;
			case HELP:break;
			case NEW_TRIP:break;
			case REMOVE_TRIP:break;
			case REMOVE_RIDE:break;
			case NEW_RIDE:break;
			case CONSULT:break;
			case EXIT:break;
			default:in.nextLine();System.out.println(INVALID_CMD);
			}
		}
	}

	private static String readCmd(Scanner in) {
		String cmd = in.next().trim().toLowerCase();
		return cmd;
	}

}
