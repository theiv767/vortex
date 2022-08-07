package poker;

import java.util.ArrayList;

public class Deck {
	private String value;
	private ArrayList<String> cards = new ArrayList<>(); // será todas as cartas do baralho(sem repetições) após processar o construtor
	private int cardsLength; 

	public Deck(String[] naipes, String[] digits) {
		
		cardsLength = naipes.length * digits.length; // tamanho do ArrayList "cards"
		for (int i = 0; i < naipes.length; i++) { // i --> indicie dos naipes
			for (int j = 0; j < digits.length; j++) { // j --> indicie dos digitos
				this.cards.add( digits[j] + naipes[i]);
			}
		}
	}

	public String dealCard() { // entregar uma carta
		int index = randomInt(this.cardsLength--);
		String aux = cards.get(index);
		cards.remove(index);
		return aux;
	}
	
	public ArrayList<String> getAllCards(){ //retorna o ArrayList "cards"
		return this.cards;
	}

	private int randomInt(int n) { // utilidade interna
		return (int) Math.floor(Math.random() * (n));
	}

}
