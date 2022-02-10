package bank.plugin.atm;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bank.plugin.main.Main;
import net.milkbowl.vault.economy.Economy;

public class InventoryEvents implements Listener{
	
	Atm atm = new Atm();
	Inventory inv;
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		int index = 0;
		
		Player player = (Player) e.getWhoClicked();
		
		final ItemStack clickedItem = e.getCurrentItem();
		String atmName = "Prelievo: " + player.getName();
		Economy econ = Main.getEconomy();
		double balance = econ.getBalance(player);
		
      /* 	*/
		
		
		if (clickedItem == null || clickedItem.getType().isAir()) {
        	
        	return;
        }
		
		if(e.getClickedInventory() == null) {
			return;
		}
		

		
		if(player.getOpenInventory().getTitle().equals(atmName) && player.getOpenInventory().getBottomInventory().equals(player.getInventory())) {
			
			e.setCancelled(true);
			System.out.println("trovato");
		}
		
		
		
		if(player.getOpenInventory().getTitle().equals(atmName) && balance <= 0) {
			
			player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
			e.setCancelled(true);
			player.closeInventory();
			
		}
		
		if(player.getOpenInventory().getTitle().equals(atmName) ) {
			
			if(e.getClick().isLeftClick() || e.getClick().isRightClick() || e.getClick().isKeyboardClick()) {
				e.setCancelled(true);
				
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.05€", 3))) {
				if(balance >= 0.05) {
					Main.getEconomy().withdrawPlayer(player, 0.05);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.05€", 3));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 0.05€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
				ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
				
				
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.10€", 4))) {
				if(balance >= 0.10) {
				Main.getEconomy().withdrawPlayer(player, 0.10);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.10€", 4));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 0.10€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.50€", 5))) {
				if(balance >= 0.50) {
				Main.getEconomy().withdrawPlayer(player, 0.50);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.50€", 5));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 0.50€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "1€", 6))) {
				if(balance >= 1) {
					Main.getEconomy().withdrawPlayer(player, 1);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "1€", 6));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 1€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
				
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "2€", 7))) {
				if(balance >= 2) {
				Main.getEconomy().withdrawPlayer(player, 2);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "2€", 7));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 2€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.AQUA + "5€", 8))) {
				if(balance >= 5) {
				Main.getEconomy().withdrawPlayer(player, 5);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.AQUA + "5€", 8));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 5€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
					
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.RED + "10€", 9))) {
				if(balance >=10) {
				Main.getEconomy().withdrawPlayer(player, 10);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.RED + "10€", 9));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 10€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.AQUA + "20€", 10))) {
				if(balance >= 20) {
				Main.getEconomy().withdrawPlayer(player, 20);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.AQUA + "20€", 10));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 20€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "50€", 11))) {
				if(balance >=50) {
				Main.getEconomy().withdrawPlayer(player, 50);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "50€", 11));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 50€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.GREEN + "100€", 12))) {
				if(balance >= 100) {
				Main.getEconomy().withdrawPlayer(player, 100);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.GREEN + "100€", 12));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 100€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "200€", 13))) {
				if(balance >= 200) {
				Main.getEconomy().withdrawPlayer(player, 200);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "200€", 13));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 200€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
			}
			
			if(clickedItem.equals(atm.createGuiItem(Material.STICK, ChatColor.LIGHT_PURPLE + "500€", 14))) {
				if(balance >= 500) {
				Main.getEconomy().withdrawPlayer(player, 500);
				player.getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.LIGHT_PURPLE + "500€", 14));
				player.sendMessage(ChatColor.GREEN + "Hai ritirato 500€");
				player.getOpenInventory().getTopInventory().setItem(31,atm.createBalItem(Material.GOLD_INGOT, 
						ChatColor.GOLD + "Bilancio di " + player.getName(),Main.getEconomy().format(Main.getEconomy().getBalance(player))));
				}else {
					player.sendMessage(ChatColor.RED + "Non hai abbastanza soldi sul conto per prelevare questa cifra!");
					e.setCancelled(true);
					player.closeInventory();
				}
			}
			
			
		}
		/*if(player.getOpenInventory().getTitle().equals("Prelievo: " + player.getName())) {
			System.out.println(inv);
			System.out.println(player.getOpenInventory().getTitle().toString());
			System.out.println("esistoqui");
			System.out.println(e.getClickedInventory().getItem(index).toString() + "qia");
			e.setCancelled(true);
			
			if(player.getOpenInventory()== atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.05€", 3 ) ) {
				System.out.println(e.getClickedInventory().getItem(index));
				
				player.getPlayer().getInventory().addItem(atm.createGuiItem(Material.STICK, ChatColor.YELLOW + "0.05€", 3));
				
			}
			
		}
		
		// verify current item is not null
        
        
		if(e.getClickedInventory() == null) {
			System.out.println("esistoquo");
			return;
			
		}
		
		/*if(e.getClickedInventory().equals(atm.inv)) {
		//	e.setCancelled(true);
			
			
			if(e.getCurrentItem() == null) {
				System.out.println("esisto");
				return;
			}*/
			
			
		
		
		}
	}
	

