package bank.plugin.items;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import bank.plugin.main.Main;







public class GiveItems implements Listener{
	
	private Player utente;
	 String title;
	 String currentPage = "";
	 int numPages = 0;
	 int numLines = 0;
	 
	 String cardId;
	 String pin;
	 String playerName;
	 CardInfo ci = new CardInfo(pin, cardId, playerName);
	 public YamlUtils carte;
	
	
	
	
	//}
	
	public ItemStack giveItems(Player utente) {
		
		 cardId = randomId();
		 ci.setCardId(cardId); 
		ItemStack baseCard = new ItemStack(Material.STICK,1);
		ItemMeta baseCardMeta= baseCard.getItemMeta();
		baseCardMeta.setCustomModelData(16);
		baseCardMeta.setDisplayName(ChatColor.GRAY + "Conto Base");
		 ArrayList<String> LoreList = new ArrayList<String>();
	     LoreList.add(ChatColor.GRAY + "ID : "+ cardId);
	     LoreList.add(ChatColor.GRAY + "Proprietario: " + utente.getName() );
	     
	     baseCardMeta.setLore(LoreList);
		 baseCard.setItemMeta(baseCardMeta);
		return baseCard;
		
	}
	
	public ItemStack giveBook(Player utente) {
		ItemStack libroPin = new ItemStack(Material.WRITTEN_BOOK,1);
		 BookMeta libroMeta = (BookMeta) libroPin.getItemMeta();
		String titolo = ChatColor.BLUE + "CARTA DI CREDITO";
		String rilasciata = "Rilasciata a : ";
		
		 playerName = utente.getName();
		 pin = randomPin();
		ci.setPin(pin);
		 ci.setPlayerName(playerName);
		String pinStringa = ChatColor.BLACK + "Pin :" + ChatColor.LIGHT_PURPLE +  ChatColor.BOLD + " " + pin;

		
		
		
		libroMeta.setAuthor(utente.getName());
		libroMeta.setTitle(ChatColor.BLUE + "Pin di " + utente.getName());
		libroMeta.addPage("-------------------" + "\n" +  titolo +  "\n" +  "\n" 
				+  rilasciata + "\n" + utente.getName() + "\n"  + "\n" + pinStringa );
		
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
	    	utente = event.getPlayer();
	    	
	        event.getPlayer().getInventory().addItem(giveItems(utente));
	        event.getPlayer().getInventory().addItem(giveBook(utente));
	        YamlUtils.create(utente);
	        	YamlUtils.get().createSection("UUID");
	    		YamlUtils.get().set("UUID", utente.getUniqueId().toString());
	    		YamlUtils.get().createSection("Nome");
	 	        YamlUtils.get().set("Nome", utente.getName());
	 	       YamlUtils.get().createSection("Pin");
	    		YamlUtils.get().set("Pin", ci.getPin());
	    		YamlUtils.get().createSection("ID Carta");
	    		YamlUtils.get().set("ID Carta", ci.getCardId());
	    	YamlUtils.save();
	        
	    }
	    
	}
	/*private static HashMap<UUID, YamlConfiguration> usersConfig = new HashMap<>();
	static File configFile;
    static FileConfiguration config;
    static File folder = new File(Main.getInstance().getDataFolder(), "player data" + File.separator);
    static File dataFolder = Main.getInstance().getDataFolder();
	 
	    public static void create(Player p) {
	        configFile = new File(dataFolder,"carta di " + "-" + p.getUniqueId() + ".yml");
	        if (!dataFolder.exists()) dataFolder.mkdir();
	        if (!configFile.exists()) {
	            try {
	                configFile.createNewFile();
	            } catch(Exception e) {
	                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + configFile.getName() + "!");
	            }
	        }
	        config = YamlConfiguration.loadConfiguration(configFile);
	        usersConfig.put(p.getUniqueId(), YamlConfiguration.loadConfiguration(configFile));
	    }
	 
	 */
	
	
}
