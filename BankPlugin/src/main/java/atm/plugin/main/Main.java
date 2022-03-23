package atm.plugin.main;

import atm.plugin.atm.Atm;
import atm.plugin.atm.DepositEvent;
import atm.plugin.atm.WithdrawEvents;
import atm.plugin.creditcard.GiveCard;
import java.util.HashMap;
import java.util.UUID;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	private static Main plugin;
	private static Economy econ;
	public GiveCard cards = new GiveCard();
	public Atm atm = new Atm();
	public WithdrawEvents wde = new WithdrawEvents();
	public DepositEvent deposit = new DepositEvent();
	private HashMap<UUID, YamlConfiguration> usersFiles = new HashMap();

	public void onEnable() {
		plugin = this;
		if (!this.setupEconomy()) {
			Bukkit.getConsoleSender()
					.sendMessage("Nessun plugin economico trovato. Controlla la presenza di Vault e Essentials ");
			this.getServer().getPluginManager().disablePlugin(this);
		} else {
			Bukkit.getConsoleSender().sendMessage(
					"\r\n___  ___          _       ______        ______     _____ _                     \r\n|  \\/  |         | |      | ___ \\       | ___ \\   /  ___| |                    \r\n| .  . | __ _  __| | ___  | |_/ /_   _  | |_/ /___\\ `--.| |_ __ _ _ __         \r\n| |\\/| |/ _` |/ _` |/ _ \\ | ___ \\ | | | |    // _ \\`--. \\ __/ _` | '__|        \r\n| |  | | (_| | (_| |  __/ | |_/ / |_| | | |\\ \\  __/\\__/ / || (_| | |           \r\n\\_|  |_/\\__,_|\\__,_|\\___| \\____/ \\__, | \\_| \\_\\___\\____/ \\__\\__,_|_|           \r\n                                  __/ |                          ______ ______ \r\n                                 |___/                          |______|______|\r\n");
			PluginManager pm = this.getServer().getPluginManager();
			pm.registerEvents(this.cards, this);
			pm.registerEvents(this.atm, this);
			pm.registerEvents(this.wde, this);
			pm.registerEvents(this.deposit, this);
		}
	}

	private boolean setupEconomy() {
		if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		} else {
			RegisteredServiceProvider<Economy> rsp = this.getServer().getServicesManager()
					.getRegistration(Economy.class);
			if (rsp == null) {
				return false;
			} else {
				econ = (Economy) rsp.getProvider();
				return econ != null;
			}
		}
	}

	public static Economy getEconomy() {
		return econ;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("AtmPlugin Disabilitato");
	}

	public static Main getInstance() {
		return plugin;
	}
}