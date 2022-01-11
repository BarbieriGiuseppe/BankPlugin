package bank.plugin.atm;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import bank.plugin.items.YamlUtils;
import bank.plugin.items.CardInfo;
import bank.plugin.main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.wesjd.anvilgui.AnvilGUI;
import net.wesjd.anvilgui.AnvilGUI.Builder;




public class Atm implements Listener {
	public Main plugin;
		String pin;
		String cardId;
		String playerName;
	CardInfo ci = new CardInfo(pin, cardId, playerName);
	String message = ChatColor.RED + "Devi avere una carta di credito in mano!";
	
	//File configFile = new File(YamlUtils.get(), "config.yml");
	//FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
	//YamlUtils dati = new YamlUtils();
	FileConfiguration config;
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event) throws IOException {
		String contoBase = "Conto Base";
		Player player = event.getPlayer();
		Action action = event.getAction();
		
		ItemStack baseCard = new ItemStack(Material.STICK,1);
	    ItemMeta meta = baseCard.getItemMeta();
	  

		if ( event.getAction() == Action.RIGHT_CLICK_BLOCK){ 
			if(event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.INFESTED_STONE) {
				if(player.getItemInHand().getType() == Material.STICK && 
						player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Conto Base")){
				
							openAnvilGui(event);

				}else {
					player.spigot().sendMessage(ChatMessageType.ACTION_BAR,TextComponent.fromLegacyText(message));
				}		
			}
	   }      

       
		
		
		
	}
	
	public void openAnvilGui(PlayerInteractEvent event) throws IOException {
		
		
		Player player = event.getPlayer();
		
		ItemStack paper = new ItemStack(Material.PAPER);
		//Builder gui = new AnvilGUI.Builder();
					new AnvilGUI.Builder()
				    .onClose(p -> {                                               //called when the inventory is closing
				    	AnvilGUI.Response.close();
				    })
				    .onComplete((p, text) -> {                                    //called when the inventory output slot is clicked
				    File f=new File(Main.getInstance().getDataFolder(),"carta di " + "-" + p.getUniqueId() + ".yml");
				    	if(f.exists()){ 
				    		config=YamlConfiguration.loadConfiguration(f);
				    		
				    		
				    			 if(config.getString("Pin").equalsIgnoreCase(text)) {
				    				 player.sendMessage("Corretto");
				    				 return AnvilGUI.Response.close();
					            
				    			 	}else {
				    			 		player.sendMessage(ChatColor.RED + "PIN errato, riprova!");
				    			 		return AnvilGUI.Response.close();
				    			 	}
					      
				    		 } else {
								  
				    			Bukkit.getConsoleSender().sendMessage("il file non esiste");
								config=YamlConfiguration.loadConfiguration(f);
				    		
				    			}
				    
				          return AnvilGUI.Response.close();
				    })		                                                       //prevents the inventory from being closed
				    .text("Pin")                              //sets the text the GUI should start with
				    .title("Inserisci il PIN")                                    //set the title of the GUI (only works in 1.14+)
				    .plugin(Main.getInstance())                                          //set the plugin instance
				    .open(player);
				    
	}
	
	
	 
	
}
