package bank.plugin.main;

import java.io.IOException;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import bank.plugin.atm.Atm;
import bank.plugin.items.YamlUtils;
import bank.plugin.items.CardInfo;
import bank.plugin.items.GiveItems;
//import bank.plugin.items.InfoStorageUtil;


public class Main extends JavaPlugin implements Listener{ 
	
	private static Main plugin;
	


	public GiveItems player = new GiveItems();
	public Atm atm = new Atm();
	private CardInfo playerData;
	public YamlUtils carte;
		
		@Override
		public void onEnable() {
			//this.carte = new AbstractFileCarte(this);
			 plugin = this;
			 
			
			 
			// this.playerData = new CardInfo(this, null, null, null);
			 
			 System.out.println("\r\n"
			 		+ "___  ___          _       ______        ______     _____ _                     \r\n"
			 		+ "|  \\/  |         | |      | ___ \\       | ___ \\   /  ___| |                    \r\n"
			 		+ "| .  . | __ _  __| | ___  | |_/ /_   _  | |_/ /___\\ `--.| |_ __ _ _ __         \r\n"
			 		+ "| |\\/| |/ _` |/ _` |/ _ \\ | ___ \\ | | | |    // _ \\`--. \\ __/ _` | '__|        \r\n"
			 		+ "| |  | | (_| | (_| |  __/ | |_/ / |_| | | |\\ \\  __/\\__/ / || (_| | |           \r\n"
			 		+ "\\_|  |_/\\__,_|\\__,_|\\___| \\____/ \\__, | \\_| \\_\\___\\____/ \\__\\__,_|_|           \r\n"
			 		+ "                                  __/ |                          ______ ______ \r\n"
			 		+ "                                 |___/                          |______|______|\r\n"
			 		+ "");
					
			
			 PluginManager pm = getServer().getPluginManager();
			 pm.registerEvents(player, this);
			 pm.registerEvents(atm, this);
			 
			 
		}
		
		
	/*	private boolean setupEconomy() {
			if(getServer().getPluginManager().getPlugin("Vault") == null) {
				return false;
			}
			
			getServer().getServicesManager().register(Economy.class, arg1, arg2, arg3);
		}*/
		
		
		@Override
		public void onDisable() {
			
			System.out.println("Plugin Disabilitato");
		}


		public static Main getInstance() {
			
			return plugin;
		}
		
		
}