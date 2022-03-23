package atm.plugin.creditcard;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import atm.plugin.objects.CardInfo;
import atm.plugin.objects.YamlUtils;

public class GiveCard implements Listener {
	
	
	private String pin;
	private OfflinePlayer owner;
	private String cardId;
	CardInfo ci = new CardInfo(pin, owner, cardId);
	YamlUtils fileCarte = new YamlUtils();

	private  HashMap<UUID, YamlConfiguration> usersFiles = new HashMap<>();
	
	public ItemStack giveItems(OfflinePlayer utente) {
		
		 cardId = randomId();
		 ci.setCardId(cardId); 
		ItemStack baseCard = new ItemStack(Material.STICK,1);
		ItemMeta baseCardMeta= baseCard.getItemMeta();
		baseCardMeta.setCustomModelData(16);
		baseCardMeta.setDisplayName(ChatColor.GRAY + "Conto Base");
		 ArrayList<String> LoreList = new ArrayList<String>();
	     LoreList.add(ChatColor.GRAY + "ID : "+ cardId);
	     LoreList.add(ChatColor.GRAY + "Proprietario: " + owner.getName() );
	     
	     baseCardMeta.setLore(LoreList);
		 baseCard.setItemMeta(baseCardMeta);
		return baseCard;
		
	}
	
	public ItemStack giveBook(OfflinePlayer utente) {
		owner = utente.getPlayer();
		ItemStack libroPin = new ItemStack(Material.WRITTEN_BOOK,1);
		 BookMeta libroMeta = (BookMeta) libroPin.getItemMeta();
		String titolo = ChatColor.BLUE + "CARTA DI CREDITO";
		String rilasciata = "Rilasciata a : ";
		
		 
		//String playerName = owner.getName();
		 pin = randomPin();
		 ci.setPin(pin);
		String pinStringa = ChatColor.BLACK + "Pin :" + ChatColor.LIGHT_PURPLE +  ChatColor.BOLD + " " + pin;

		
		
		
		libroMeta.setAuthor(owner.getName());
		libroMeta.setTitle(ChatColor.BLUE + "Pin di " + owner.getName());
		libroMeta.addPage("-------------------" + "\n" +  titolo +  "\n" +  "\n" 
				+  rilasciata + "\n" + owner.getName() + "\n"  + "\n" + pinStringa );
		
		libroPin.setItemMeta(libroMeta);
		return libroPin;
	}
	
	
	
	public String randomId() {
		 Random rnd = new Random();
		 int number = rnd.nextInt(999999);
		 String cardId = String.format("%06d", number);
		 
		    return cardId;
		
	}
	
	public String randomPin() {
		
		 Random rnd = new Random();
		 int number = rnd.nextInt(9999);
		 String pin = String.format("%04d", number);
		 
		  return pin;
		
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	    if(!event.getPlayer().hasPlayedBefore()) {   	
	    	owner = event.getPlayer();
	    	 event.getPlayer().getInventory().addItem(giveItems(owner));
		     event.getPlayer().getInventory().addItem(giveBook(owner));
		     fileCarte.create(owner);
		     	fileCarte.get().createSection("UUID");
		     	fileCarte.get().set("UUID", owner.getUniqueId().toString());
		     	fileCarte.get().createSection("Nome");
		     	fileCarte.get().set("Nome", owner.getName());
		     	fileCarte.get().createSection("Pin");
		     	fileCarte.get().set("Pin", ci.getPin());
		     	fileCarte.get().createSection("ID Carta");
		     	fileCarte.get().set("ID Carta", ci.getCardId());
		     fileCarte.save();

	    }
	}
	
}
