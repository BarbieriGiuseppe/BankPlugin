package atm.plugin.atm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import atm.plugin.main.Main;
import net.milkbowl.vault.economy.Economy;

public class WithdrawEvents implements Listener{

		Atm atm = new Atm();
		Inventory inv;
		
		
		@EventHandler
		public void onClick(InventoryClickEvent e) {
			int index = 0;
			Player player = (Player) e.getWhoClicked();
			
			

			final ItemStack clickedItem = e.getCurrentItem();
			String atmName = "Prelievo: " + player.getName();
			Economy econ = Main.getEconomy();
			String message = ChatColor.RED + "Non ci sono abbastanza soldi sul conto per prelevare questa cifra!";
			
			
			
			
			if (clickedItem == null || clickedItem.getType().isAir()) {
	        	
	        	return;
	        }
			
			if(e.getClickedInventory() == null) {
				return;
			}
			

			
			if(player.getOpenInventory().getTitle().equals(atmName) && player.getOpenInventory().getBottomInventory().equals(player.getInventory())) {
				
				e.setCancelled(true);
				
			}
			
			
			
			if(player.getOpenInventory().getTitle().equals(atmName)) {
				OfflinePlayer ofp;
				ofp = getOfflinePlayerBalance(player);
				double balance = econ.getBalance(ofp);
				if(balance <= 0) {
				player.sendMessage(message);
				e.setCancelled(true);
				player.closeInventory();
				}
				
			}
			
			if(player.getOpenInventory().getTitle().equals(atmName) ) {
				OfflinePlayer ofp;
				ofp = getOfflinePlayerBalance(player);
				double balance = econ.getBalance(ofp);
				
				if(e.getClick().isLeftClick() || e.getClick().isRightClick() || e.getClick().isKeyboardClick()) {
					e.setCancelled(true);
					
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.05€", 3))) {
					if(balance >= 0.05) {
						Main.getEconomy().withdrawPlayer(ofp, 0.05);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.05€", 3));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 0.05€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
					ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
					
					
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.10€", 4))) {
					if(balance >= 0.10) {
					Main.getEconomy().withdrawPlayer(ofp, 0.10);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.10€", 4));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 0.10€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.50€", 5))) {
					if(balance >= 0.50) {
					Main.getEconomy().withdrawPlayer(ofp, 0.50);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.50€", 5));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 0.50€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "1€", 6))) {
					if(balance >= 1) {
						Main.getEconomy().withdrawPlayer(ofp, 1);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "1€", 6));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 1€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
					
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "2€", 7))) {
					if(balance >= 2) {
					Main.getEconomy().withdrawPlayer(ofp, 2);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "2€", 7));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 2€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.AQUA + "5€", 8))) {
					if(balance >= 5) {
					Main.getEconomy().withdrawPlayer(ofp, 5);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.AQUA + "5€", 8));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 5€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
						
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.RED + "10€", 9))) {
					if(balance >=10) {
					Main.getEconomy().withdrawPlayer(ofp, 10);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.RED + "10€", 9));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 10€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.AQUA + "20€", 10))) {
					if(balance >= 20) {
					Main.getEconomy().withdrawPlayer(ofp, 20);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.AQUA + "20€", 10));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 20€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "50€", 11))) {
					if(balance >=50) {
					Main.getEconomy().withdrawPlayer(ofp, 50);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "50€", 11));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 50€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.GREEN + "100€", 12))) {
					if(balance >= 100) {
					Main.getEconomy().withdrawPlayer(ofp, 100);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.GREEN + "100€", 12));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 100€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "200€", 13))) {
					if(balance >= 200) {
					Main.getEconomy().withdrawPlayer(ofp, 200);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "200€", 13));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 200€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
					}
				}
				
				if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.LIGHT_PURPLE + "500€", 14))) {
					if(balance >= 500) {
					Main.getEconomy().withdrawPlayer(ofp, 500);
					player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.LIGHT_PURPLE + "500€", 14));
					player.sendMessage(ChatColor.GREEN + "Hai ritirato 500€");
					player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
							ChatColor.GOLD + "Bilancio di " + ofp.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(ofp))));
					}else {
						player.sendMessage(message);
						e.setCancelled(true);
						player.closeInventory();
						}
					}
				
				
				}
			}
		
		protected OfflinePlayer getOfflinePlayerBalance(OfflinePlayer ofp) {
			List<String> LoreList = new ArrayList<String>();
			
			LoreList = ofp.getPlayer().getItemInHand().getLore();
	    	String lore = LoreList.get(1); // prende la stringa corrispondente al proprietario della carta
	    	String result[] = lore.split("[ :]+"); //elimina tutto il contenuto 

	    	String returnValue = result[result.length - 1]; //ritorna il valore dopo i : senza spazi
	    	
	    	ofp = Bukkit.getOfflinePlayer(returnValue); //trasforma il nome utente in uuid per poter trovare il file
	    	//UUID playerUUID = ofp.getUniqueId(); // variabile contenente l'uuid dell'utente offline
			
			
			return ofp;
			
		}
		
		
	}
		



