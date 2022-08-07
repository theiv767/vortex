package poker;

import java.util.ArrayList;
import java.util.Collections;

// FALTA fazer as combinações de dois pares pra baixo

public class Game {

	private static String[] naipes = { "C", "E", "O", "P" };
	private static String[] digits = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

	// verificando se existe royalFlush ------------------------------------
	public static boolean royalFlush(ArrayList<String> cards) {
		String[] value = cards.get(0).split("");
		String naipe = value[value.length - 1];
		String digit = cards.get(0).split(naipe)[0];
		if (cards.size() != 5) {
			return false;
		}

		for (int i = 0; i < cards.size(); i++) { // percorrer todas as posições
			int digitPosition = i + 8;
			value = cards.get(i).split("");
			String auxNaipe = value[value.length - 1];
			digit = cards.get(i).split(naipe)[0];

			if (!(auxNaipe.equals(naipe))) { // comparando naipe
				return false;

			}

			if (!(digit.equals(digits[digitPosition]))) { // verificando a sequencia numerica
				return false;

			}

		}

		return true;
	}

	// verificando straight flush
	// verificando se existe straightFlush ---------------------------------
	public static boolean straightFlush(ArrayList<String> cards) {
		String[] value = cards.get(0).split("");
		String naipe = value[value.length - 1];
		String digit = cards.get(0).split(naipe)[0];
		int startIndex = 0;
		if (cards.size() != 5) {
			return false;
		}
		for (int i = 0; i < digits.length; i++) { // descobrindo o valor de inicio da sequencia
			if (digit.equals(digits[i])) {
				if (i == 8) { // a sequencia não pode começar da posição 8 pois assim ela terminaria no "A"
					return false;
				}
				startIndex = i;
				break;
			}
		}

		for (int i = 0; i < cards.size(); i++) { // percorrer todas as posições do ArrayList de entrada
			int digitPosition = i + startIndex;
			value = cards.get(i).split("");
			String auxNaipe = value[value.length - 1];
			digit = cards.get(i).split(naipe)[0];

			if (!(auxNaipe.equals(naipe))) { // comparando naipe
				return false;

			}

			if (!(digit.equals(digits[digitPosition]))) { // verificando a sequencia numerica
				return false;

			}

		}

		return true;
	}

	// verificando se existe quadra

	// verificando se existe quadra ----------------------------------------
	public static boolean quadra(ArrayList<String> cards) {

		for (int i = 0; i < cards.size(); i++) {
			String[] value = cards.get(i).split("");
			String naipe = value[value.length - 1];
			String digit = cards.get(i).split(naipe)[0];
			int repeats = 0;

			for (int j = 0; j < cards.size(); j++) {

				String[] auxValue = cards.get(j).split("");
				String auxNaipe = auxValue[auxValue.length - 1];
				String auxDigit = cards.get(j).split(auxNaipe)[0];

				if (digit.equals(auxDigit)) {
					repeats++;
					if (repeats == 4) {
						return true;
					}

				}
			}
		}

		return false;

	}

	// verificando se existe fullHouse

	// verificando se existe fullHouse -------------------------------------
	public static boolean fullHouse(ArrayList<String> cards) {

		/*
		 * vetor dos indicies que formam o trio e que serão removidos para restar apenas
		 * o teste da dupla
		 */
		ArrayList<Integer> finalToRemove = new ArrayList<Integer>();

		/*
		 * finalCards -> será um vetor de duas posições onde vai ser testado se as duas
		 * cartas que não formam trio formam dupla
		 */
		ArrayList<String> finalCards = new ArrayList<String>();

		int[] indexToRemove;
		boolean trio = false;
		boolean par = false;

		for (int i = 0; i < cards.size(); i++) {
			String[] value = cards.get(i).split("");
			String naipe = value[value.length - 1];
			String digit = cards.get(i).split(naipe)[0]; // digito da carta (valor)
			int repeats = 0;
			ArrayList<Integer> toRemove = new ArrayList<>();

			for (int j = 0; j < cards.size(); j++) {

				String[] auxValue = cards.get(j).split("");
				String auxNaipe = auxValue[auxValue.length - 1];
				String auxDigit = cards.get(j).split(auxNaipe)[0];

				if (digit.equals(auxDigit)) {
					repeats++;
					toRemove.add(j);
					if (repeats == 3) {
						trio = true;
						break;
					}
				}
			}
			if (trio) {
				finalToRemove = toRemove;
				break;
			}
		}
		for (int i = 0; i < cards.size(); i++) {
			boolean add = true;
			for (int j : finalToRemove) {
				if (i == (int) j) {
					add = false;
				}
			}
			if (add) {
				finalCards.add(cards.get(i));
			}

		}

		for (int i = 0; i < finalCards.size(); i++) {
			String[] value = finalCards.get(i).split("");
			String naipe = value[value.length - 1];
			String digit = finalCards.get(i).split(naipe)[0];
			int repeats = 0;

			for (int j = 0; j < finalCards.size(); j++) {

				String[] auxValue = finalCards.get(j).split("");
				String auxNaipe = auxValue[auxValue.length - 1];
				String auxDigit = finalCards.get(j).split(auxNaipe)[0];

				if (digit.equals(auxDigit)) {
					repeats++;
					if (repeats == 2) {
						par = true;
						break;
					}
				}
			}
			if (par) {
				break;
			}
		}

		if (trio && par) {
			return true;
		} else {
			return false;

		}

	}

	// verificando se existe flush

	// verificando se existe flush -----------------------------------------
	public static boolean flush(ArrayList<String> cards) {
		boolean sequence = true;

		String[] value = cards.get(0).split("");
		String naipe = value[value.length - 1];
		String digit = cards.get(0).split(naipe)[0];
		int startIndex = 0;

		for (int i = 0; i < digits.length; i++) { // descobrindo o valor de inicio da sequencia
			if (digit.equals(digits[i])) {
				startIndex = i;
				break;
			}
		}

		for (int i = 0; i < cards.size(); i++) {
			value = cards.get(i).split("");
			String auxNaipe = value[value.length - 1];
			digit = cards.get(i).split(naipe)[0];

			if (!(naipe.equals(auxNaipe))) {
				return false;

			}

			if (!(digit.equals(digits[startIndex + i]))) {
				sequence = false;

			}

		}

		if (!sequence) {
			return true;
		} else {
			return false;
		}
	}

	// verificando se existe straight

	// verificando se existe straight --------------------------------------
	public static boolean straight(ArrayList<String> cards) {
		boolean repeat = true;
		String[] value = cards.get(0).split("");
		String naipe = value[value.length - 1];
		String digit = cards.get(0).split(naipe)[0];
		int startIndex = 0;

		for (int i = 0; i < digits.length; i++) { // descobrindo o valor de inicio da sequencia
			if (digit.equals(digits[i])) {
				startIndex = i;
				break;
			}
		}

		for (int i = 0; i < cards.size(); i++) { // verificação de naipe igual e sequencia de digitos
			String[] auxValue = cards.get(i).split("");
			String aux = auxValue[auxValue.length - 1];
			digit = cards.get(i).split(aux)[0];

			if (!(aux.equals(naipe))) { // se existir algum naipe diferente repeat = false
				repeat = false;
			}

			if (!(digit.equals(digits[startIndex + i]))) { // verifica numeros fora da sequencia
				return false;
			}

			naipe = aux;
		}

		if (repeat) { // se todos os naipes forem iguais retorna erro
			return false;

		} else { // se chegou aqui é pq os naipes são diferentes e está em sequencia
			return true;

		}

	}

	// verificando se existe trio

	// verificando se existe trio -------------------------------------------
	public static boolean trio(ArrayList<String> cards) {

		for (int i = 0; i < cards.size(); i++) {
			String[] value = cards.get(i).split("");
			String naipe = value[value.length - 1];
			String digit = cards.get(i).split(naipe)[0];
			int repeats = 0;

			for (int j = 0; j < cards.size(); j++) {

				String[] auxValue = cards.get(j).split("");
				String auxNaipe = auxValue[auxValue.length - 1];
				String auxDigit = cards.get(j).split(auxNaipe)[0];

				if (digit.equals(auxDigit)) {
					repeats++;
					if (repeats == 3) {
						return true;
					}

				}
			}
		}

		return false;

	}

	// verificando se existe dois pares

	// verificando se existe dois pares
	public static boolean doisPares(ArrayList<String> cards) {
		boolean par1 = false;
		boolean par2 = false;
		ArrayList<String> finalPar = new ArrayList<String>();
		ArrayList<Integer> toRemove = new ArrayList<Integer>();

		for (int i = 0; i < cards.size(); i++) { // virificação de par
			ArrayList<Integer> auxRemove = new ArrayList<Integer>();
			String[] value = cards.get(i).split("");
			String naipe = value[value.length - 1];
			String digit = cards.get(i).split(naipe)[0];
			int repeats = 0;

			for (int j = 0; j < cards.size(); j++) {

				String[] auxValue = cards.get(j).split("");
				String auxNaipe = auxValue[auxValue.length - 1];
				String auxDigit = cards.get(j).split(auxNaipe)[0];

				if (digit.equals(auxDigit)) {
					auxRemove.add(j);
					repeats++;
					if (repeats == 2) {
						toRemove = auxRemove;
						par1 = true; // par1 true significa que já foi encontrado um par
					}

				}
			}
		}
		if (par1) {
			for (int i = 0; i < cards.size(); i++) {
				boolean add = true;
				for (int j : toRemove) {
					if (i == (int) j) {
						add = false;
					}
				}
				if (add) {
					finalPar.add(cards.get(i));
				}
			}

			for (int i = 0; i < finalPar.size(); i++) { // repetindo a verificação de par
				String[] value = finalPar.get(i).split("");
				String naipe = value[value.length - 1];
				String digit = finalPar.get(i).split(naipe)[0];
				int repeats = 0;

				for (int j = 0; j < finalPar.size(); j++) {

					String[] auxValue = finalPar.get(j).split("");
					String auxNaipe = auxValue[auxValue.length - 1];
					String auxDigit = finalPar.get(j).split(auxNaipe)[0];

					if (digit.equals(auxDigit)) {
						repeats++;
						if (repeats == 2) {
							return true;
						}

					}
				}
			}

		}

		return false;
	}

	// verificando se existe par

	// verificando se existe par
	public static boolean par(ArrayList<String> cards) {
		for (int i = 0; i < cards.size(); i++) {
			String[] value = cards.get(i).split("");
			String naipe = value[value.length - 1];
			String digit = cards.get(i).split(naipe)[0];
			int repeats = 0;

			for (int j = 0; j < cards.size(); j++) {

				String[] auxValue = cards.get(j).split("");
				String auxNaipe = auxValue[auxValue.length - 1];
				String auxDigit = cards.get(j).split(auxNaipe)[0];

				if (digit.equals(auxDigit)) {
					repeats++;
					if (repeats == 2) {
						return true;
					}

				}
			}
		}

		return false;

	}

	// verificando qual é a carta mais alta, retorna int; -1 significa erro;
	public static int cartaAlta(ArrayList<String> cards) {
		String[] value = cards.get(cards.size() - 1).split("");
		String naipe = value[value.length - 1];
		String digit = cards.get(cards.size() - 1).split(naipe)[0];

		for (int i = 0; i < digits.length; i++) {
			if (digit.equals(digits[i])) {
				return i;
			}
		}

		return -1;
	}

	public static String calcPoints(ArrayList<String> c) {
		ArrayList<String> cards = c;
		Collections.sort(cards, new ComparatorCards());
		String textCards ="";
		for(int i=0; i<cards.size(); i++) {
			textCards+=cards.get(i);
			if(i+1 == cards.size()) {
				textCards +="-";	// separador padrão			
			}else {
				textCards +=", ";
			}
		}
		
		// return [melhor sequencia] [nome da melhor sequencia] [pontuação]
		if (royalFlush(cards)) { 
			return textCards+"Royal Flush-"+21; 

		} else if (straightFlush(cards)) {
			return textCards+"Straight Flush-"+20;

		} else if (quadra(cards)) {
			return textCards+"Quadra-"+ 19;

		} else if (fullHouse(cards)) {
			return textCards+"Full House-"+18;

		} else if (flush(cards)) {
			return textCards+"Flush-"+17;

		} else if (straight(cards)) {
			return textCards+"Straight-"+16;

		} else if (trio(cards)) {
			return textCards+"Trio-"+15;

		} else if (doisPares(cards)) {
			return textCards+"Dois Pares-"+14;

		} else if (par(cards)) {
			return textCards+"Par-"+13;

		} else {
			return textCards+"Carta Alta-"+cartaAlta(cards);

		}
	}


	public static String getBotPoints(Player bot, String[] table){
		ArrayList<String> finalCards = new ArrayList<String>();
		ArrayList<String> arranjos = new ArrayList<String>(); // todas as possibilidades de arranjo entre das cartas da mesa
		arranjos.add("0 1 2");
		arranjos.add("0 1 3");
		arranjos.add("0 1 4");
		arranjos.add("0 2 3");
		arranjos.add("0 2 4");
		arranjos.add("0 3 4");
		arranjos.add("1 2 3");
		arranjos.add("1 2 4");
		arranjos.add("1 3 4");
		arranjos.add("2 3 4");
		int pointsBot = 0;
		String textBot = "";
		for(String i: arranjos) { // percorrendo as possibilidades de arranjo de 3 em 3
			String[] values = i.split(" ");
			ArrayList<Integer> positions;
			
			ArrayList<String> test = new ArrayList<String>();
			test.add(bot.getCards().get(0));
			test.add(bot.getCards().get(1));
			
			for(String j: values) {
				test.add(table[(int) Integer.valueOf(j)]);	
			}
			
			int aux = Integer.valueOf(calcPoints(test).split("-")[2]);
			if(aux > pointsBot) {
				finalCards.clear();
				finalCards = test;
				pointsBot = aux;
				textBot = calcPoints(test);
			}
			
		}
		return textBot;
	}
	
	
	// -----------------------------------------------------------------
	// execução ---------------------------
	public static void main(String[] args) {
		
		
		
		int numCardsTable = 5; // número de cartas reservadas para a mesa
		String[] table = new String[5]; // cartas da mesa
		Deck deck = new Deck(naipes, digits); // baralho de cartas
		
		for (int i = 0; i < numCardsTable; i++) { // distribuindo as cartas da mesa
			table[i] = deck.dealCard();
		}
		Player bot1 = new Player("Robô 1", deck.dealCard(), deck.dealCard()); // robo 1
		Player bot2 = new Player("Robô 2", deck.dealCard(), deck.dealCard()); // robo 2
	
		
		
		String bestCards = getBotPoints(bot1, table).split("-")[1]+"("; // obtendo o nome da melhor sequencia do robo 1
		bestCards += getBotPoints(bot1, table).split("-")[0]+")";	// obtendo a melhor sequencia do robo 1
		int botPoints = (int) Integer.valueOf(getBotPoints(bot1, table).split("-")[2]); // obtendo a pontuação do robo 1
		bot1.setBestCards(bestCards);
		bot1.setPoints(botPoints);
		
		bestCards = getBotPoints(bot2, table).split("-")[1]+"("; // obtendo o nome da melhor sequencia do robo 2
		bestCards += getBotPoints(bot2, table).split("-")[0]+")"; // obtendo a melhor sequencia do robo 2
		botPoints = (int) Integer.valueOf(getBotPoints(bot2, table).split("-")[2]); // obtendo a pontuação do robo 2
		bot2.setBestCards(bestCards);
		bot2.setPoints(botPoints);
		
		System.out.print("{Robô 1:"+ bot1.getBestCards()+","+"Robô 2:"+bot2.getBestCards()+",");
		if(bot1.getPoints() > bot2.getPoints()) {
			System.out.println("Robô 1 venceu!}");
			
		}else if(bot1.getPoints() < bot2.getPoints()) {
			System.out.println("Robô 2 venceu!}");
			
		}else {
			System.out.println("empate!}");
		}
		
		

	}

}