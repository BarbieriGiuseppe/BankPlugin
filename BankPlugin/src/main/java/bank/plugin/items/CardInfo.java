package bank.plugin.items;

import java.util.Random;

import org.bukkit.entity.Player;

import bank.plugin.main.Main;

public class CardInfo /*extends AbstractFileCarte */{
	 
	 String cardId;
	 String pin;
	 String playerName;
	 String id;
	 
	 public CardInfo(String cardId, String pin, String playerName) {
		//super();
		this.cardId = cardId;
		this.pin = pin;
		this.playerName = playerName;
		
	}
	 
	 /*public CardInfo(Main main) {
		 super(main,"Carta-.yml");
		
		
	}
	 
	public void newPlayer(Player player,String cardId,String pin) { 
		config.set(player.)
	}*/
	 
	 public String getId() {
			return id;
		} 
	 
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		
		this.cardId = cardId;
		
	}

	

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		
		this.pin = pin;
		
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
	
}
