package me.elendrial.cardGameBase.decks;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import me.elendrial.cardGameBase.Controller;
import me.elendrial.cardGameBase.cards.StandardCard;
import me.elendrial.cardGameBase.helpers.TextureHelper;

public class StandardDeck {
	
	public ArrayList<StandardCard> cards;
	public BufferedImage cardBack;
	public Point position, size;
	public int maxDeckSize = 100;
	
	public StandardDeck(int maxDeckSize){
		this.maxDeckSize = maxDeckSize;
	}
	
	public StandardDeck(int maxDeckSize, ArrayList<StandardCard> cards){
		this.maxDeckSize = maxDeckSize;
		this.cards = cards;
	}
	
	public StandardDeck(int maxDeckSize, ArrayList<StandardCard> cards, Point position, Point size) {
		this.cards = cards;
		this.position = position;
		this.size = size;
		this.maxDeckSize = maxDeckSize;
		
		if(cards.get(0).backTextureName.contains("textures/cards/")) cardBack = TextureHelper.loadTexture(cards.get(0).backTextureName.substring(0, cards.get(0).backTextureName.lastIndexOf("/")+1), cards.get(0).backTextureName.substring(cards.get(0).backTextureName.lastIndexOf("/")+1), this);
		else cardBack = TextureHelper.loadTexture("textures/cards/", cards.get(0).backTextureName, this);
	}
	
	public StandardDeck(int maxDeckSize, ArrayList<StandardCard> cards, Point position, Point size, String cardBackTextureName) {
		this.cards = cards;
		this.position = position;
		this.size = size;
		this.maxDeckSize = maxDeckSize;
		
		cardBack = TextureHelper.getImage(cardBackTextureName.contains("textures/cards/") ? cardBackTextureName : "textures/cards/" + cardBackTextureName);
	}

	
	public void addCard(StandardCard card){
		if(cards.size() < maxDeckSize) cards.add(card);
	}
	
	public void addCards(StandardCard[] cards){
		for(StandardCard c : cards) this.cards.add(c);
	}
	
	public void addCards(ArrayList<StandardCard> cards){
		this.cards.addAll(cards);
	}
	
	public StandardCard drawCard(){
		int toDraw = Controller.rand.nextInt(cards.size());
		StandardCard c = cards.get(toDraw);
		cards.remove(toDraw);
		return c;
	}

	public int getCardsLeft() {
		return cards.size();
	}

	public void render(Graphics g) {
		g.drawImage(cardBack, position.x, position.y, size.x, size.y, null);
	}
}
