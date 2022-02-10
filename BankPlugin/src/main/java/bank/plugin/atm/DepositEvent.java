package bank.plugin.atm;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import bank.plugin.main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class DepositEvent implements Listener{
	Atm atm = new Atm();
	
	@EventHandler
	public void onPlayerRightClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
	
		
		double amount= 0;
		if ( event.getAction() == Action.RIGHT_CLICK_BLOCK) { 
				
			if(event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.INFESTED_STONE) {
					
				 if (player.getItemInHand().getType() == Material.STICK && player.getItemInHand().hasItemMeta() 
						 && player.getItemInHand().getItemMeta().hasDisplayName() && player.getItemInHand().getItemMeta().getDisplayName().contains("€")) {
					
	                  try {
	                	  
	                      Double.parseDouble(player.getItemInHand().getItemMeta().getDisplayName().replaceAll("[^\\w][a-z]", "").split("€")[0]);
	                   } catch (Exception var10) {
	                	  
	                	   
	                	   System.out.println(player.getItemInHand().getItemMeta().getDisplayName().replaceAll("[^\\w][a-z]", "").split("€")[0]);
	                	   System.out.println("errore");
	                      return;
	                   }
	                  
	                    amount = Double.parseDouble(player.getItemInHand().getItemMeta().getDisplayName().replaceAll("[^\\w][a-z]", "").split("€")[0]);
	                    
	                   Main.getEconomy().depositPlayer(player, amount);
	                   player.sendMessage((ChatColor.GREEN + "Hai depositato " + String.format("%.2f", amount) + "€" + " nel tuo conto."));
	                   player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
	                }
				
			}
		}		
	}
}
