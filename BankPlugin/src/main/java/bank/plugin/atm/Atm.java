package bank.plugin.atm;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import bank.plugin.items.YamlUtils;
import bank.plugin.items.CardInfo;
import bank.plugin.main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import net.wesjd.anvilgui.AnvilGUI;
import net.wesjd.anvilgui.AnvilGUI.Builder;




public class Atm implements Listener {
	public Main plugin;
		String pin;
		String cardId;
		String playerName;
	CardInfo ci = new CardInfo(pin, cardId, playerName);
	String message = ChatColor.RED + "Devi avere una carta di credito in mano per prelevare!";
	FileConfiguration config;
	private static Economy econ;
	 Inventory inv;
	//InventoryEvents inve = new InventoryEvents();
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

				} else {
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
				    				 
				    				 player.openInventory(atmGui(event));
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
				    .text("§a")                              //sets the text the GUI should start with
				    .title("Inserisci il PIN")                                    //set the title of the GUI (only works in 1.14+)
				    .plugin(Main.getInstance())                                          //set the plugin instance
				    .open(player);
				    
	}
	
	public Inventory atmGui(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		
		inv = Bukkit.createInventory(player, 36, "Prelievo: " + player.getName());

		inv.setItem(11,createGuiItem(Material.STICK, ChatColor.YELLOW + "0.05€", 3));
		inv.setItem(12,createGuiItem(Material.STICK, ChatColor.YELLOW + "0.10€", 4));
		inv.setItem(13,createGuiItem(Material.STICK, ChatColor.YELLOW + "0.50€", 5));
		inv.setItem(14,createGuiItem(Material.STICK, ChatColor.YELLOW + "1€", 6));
		inv.setItem(15,createGuiItem(Material.STICK, ChatColor.YELLOW + "2€", 7));
		inv.setItem(19,createGuiItem(Material.STICK, ChatColor.AQUA + "5€", 8));
		inv.setItem(20,createGuiItem(Material.STICK, ChatColor.RED + "10€", 9));
		inv.setItem(21,createGuiItem(Material.STICK, ChatColor.AQUA + "20€", 10));
		inv.setItem(22,createGuiItem(Material.STICK, ChatColor.YELLOW + "50€", 11));
		inv.setItem(23,createGuiItem(Material.STICK, ChatColor.GREEN + "100€", 12));
		inv.setItem(24,createGuiItem(Material.STICK, ChatColor.YELLOW + "200€", 13));
		inv.setItem(25,createGuiItem(Material.STICK, ChatColor.LIGHT_PURPLE + "500€", 14));
		
		inv.setItem(31,createBalItem(Material.GOLD_INGOT, 
				ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
		
		return inv;
	}
	 
	 
	 protected ItemStack createGuiItem(final Material material, final String name, final int modelData) {
	        final ItemStack item = new ItemStack(material, 1);
	        final ItemMeta meta = item.getItemMeta();

	        // Set the name of the item
	        meta.setDisplayName(name);
	        meta.setCustomModelData(modelData);

	        item.setItemMeta(meta);

	        return item;
	    }
	 
	 protected ItemStack createBalItem(final Material material, final String name, final String bal) {
	        final ItemStack item = new ItemStack(material, 1);
	        final ItemMeta meta = item.getItemMeta();

	        // Set the name of the item
	        meta.setDisplayName(name);
	        meta.setLore(Arrays.asList(bal));

	        item.setItemMeta(meta);

	        return item;
	    }
}
