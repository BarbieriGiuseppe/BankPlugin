package atm.plugin.objects;

import atm.plugin.main.Main;
import java.io.File;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class YamlUtils {
	private String pin;
	private String cardId;
	private OfflinePlayer owner;
	CardInfo ci;
	private File dataFolder;
	private File configFile;
	private FileConfiguration config;
	private HashMap<UUID, YamlConfiguration> usersFiles;

	public YamlUtils() {
		this.ci = new CardInfo(this.pin, this.owner, this.cardId);
		this.usersFiles = new HashMap();
	}

	public void create(OfflinePlayer p) {
		this.dataFolder = Main.getInstance().getDataFolder();
		this.configFile = new File(this.dataFolder, "carta di -" + p.getUniqueId() + ".yml");
		if (!this.dataFolder.exists()) {
			this.dataFolder.mkdir();
		}

		if (!this.configFile.exists()) {
			try {
				this.configFile.createNewFile();
			} catch (Exception var3) {
				Bukkit.getConsoleSender()
						.sendMessage(ChatColor.RED + "Error creating " + this.configFile.getName() + "!");
			}
		}

		this.config = YamlConfiguration.loadConfiguration(this.configFile);
	}

	public File getfile() {
		return this.configFile;
	}

	public void load(OfflinePlayer p) {
		this.configFile = new File(this.dataFolder, "carta di -" + p.getUniqueId() + ".yml");
		this.config = YamlConfiguration.loadConfiguration(this.configFile);
	}

	public void save() {
		try {
			this.config.save(this.configFile);
		} catch (Exception var2) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + this.configFile.getName() + "!");
		}

	}

	public FileConfiguration get() {
		return this.config;
	}
}