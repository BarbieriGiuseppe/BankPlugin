package atm.plugin.objects;

import org.bukkit.OfflinePlayer;

public class CardInfo {
	private String pin;
	private OfflinePlayer owner;
	private String cardId;

	public CardInfo(String pin, OfflinePlayer owner, String cardId) {
		this.pin = pin;
		this.owner = owner;
		this.cardId = cardId;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setOwner(OfflinePlayer owner) {
		this.owner = owner;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPin() {
		return this.pin;
	}

	public OfflinePlayer getOwner() {
		return this.owner;
	}

	public String getCardId() {
		return this.cardId;
	}
}