package bank.plugin.main;

import java.io.IOException;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import bank.plugin.atm.Atm;
import bank.plugin.atm.DepositEvent;
import bank.plugin.atm.InventoryEvents;
import bank.plugin.items.YamlUtils;
import net.milkbowl.vault.economy.Economy;
import bank.plugin.items.CardInfo;
import bank.plugin.items.GiveItems;
//import bank.plugin.items.InfoStorageUtil;


public class Main extends JavaPlugin implements Listener{ 
	
	private static Main plugin;
	


	public GiveItems player = new GiveItems();
	public Atm atm = new Atm();
	private CardInfo playerData;
	public YamlUtils carte;
	public InventoryEvents inve = new InventoryEvents();
	private static Economy econ;
	public DepositEvent deposit = new DepositEvent();
		@Override
		public void onEnable() {
			//this.carte = new AbstractFileCarte(this);
			 plugin = this;
			 
			if(!setupEconomy()) {
				System.out.println("Nessun economy plugin trovato. Disabilitando BankPlugin");
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
			 
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
			 pm.registerEvents(inve, this);
			 pm.registerEvents(atm, this);
			 pm.registerEvents(deposit, this);
			 
		}
		
		
		private boolean setupEconomy() {
			if(getServer().getPluginManager().getPlugin("Vault") == null) {
				return false;
			}
			
			RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
			if(rsp == null) {
				
				return false;
			}
			
			econ = rsp.getProvider();
			return  econ != null;
			
		}
		
		public static Economy getEconomy() {
			
			return econ;
			
		}
		
		
		@Override
		public void onDisable() {
			
			System.out.println("Plugin Disabilitato");
		}


		public static Main getInstance() {
			
			return plugin;
		}
		
		
}