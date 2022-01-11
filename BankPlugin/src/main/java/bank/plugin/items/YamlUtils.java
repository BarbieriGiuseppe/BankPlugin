package bank.plugin.items;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.google.gson.Gson;

import bank.plugin.main.Main;

public class YamlUtils  {

	
	




	 private static String pin;
	private static String cardId;
	private static String playerName;
	CardInfo ci = new CardInfo(pin, cardId, playerName);
	 
	
	private static HashMap<UUID, YamlConfiguration> usersConfig = new HashMap<>();
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
	        //get().addDefault("UUID", p.getUniqueId().toString());
	       // get().addDefault("Nome", p.getName());
	       // get().addDefault("Pin","");
	       // get().addDefault("ID Carta","");
			//get().options().copyDefaults(true);
			//save();
	        //usersConfig.clear();
	        
	         
	        
	    }
	 
	    public static File getfolder() {
	        return folder;
	    }
	 
	    public static File getfile() {
	        return configFile;
	    }
	 
	    public static void load(Player p) {
	        configFile =  new File(dataFolder,"carta di " + "-" + p.getUniqueId() + ".yml");
	        config = YamlConfiguration.loadConfiguration(configFile);
	    }
	 
	    public static FileConfiguration get() {
	        return config;
	    }
	 
	    public static void save() {
	        try {
	            config.save(configFile);
	        } catch(Exception e) {
	            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + configFile.getName() + "!");
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
