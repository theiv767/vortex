package poker;

import java.util.ArrayList;

public class Player {
	private String name;
	private int points = 0;
	private String bestCards = "";
	private ArrayList<String> cards = new ArrayList<String>();

	public Player(String name, String card1, String card2) {
		this.name = name;
		this.points = 0;
		this.cards.add(card1);
		this.cards.add(card2);
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getCards() {
		return cards;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String getBestCards() {
		return bestCards;
	}
	
	public void setBestCards(String bestCards) {
		this.bestCards = bestCards;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

}
