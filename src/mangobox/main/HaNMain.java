package mangobox.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HaNMain extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getLogger().info("HaNMain v0.1 by MangoBox has been loaded!");
		
		//Min and max values for possible max health.
		getConfig().addDefault("minHealth", 1d);
		getConfig().addDefault("maxHealth", 40d);

		//Min and max values for possible speed.
		getConfig().addDefault("minSpeed", 0.1f);
		getConfig().addDefault("maxSpeed", 0.3f);
		
		//When true, rounds the maxHealth attribute to the nearest whole heart.
		getConfig().addDefault("roundMaxHealthUp", true);
		
		//Vegetable
		getConfig().addDefault("foodTypes.vegetable.CARROT", 0.25f);
		getConfig().addDefault("foodTypes.vegetable.POTATO", 0.1f);
		getConfig().addDefault("foodTypes.vegetable.BAKED_POTATO", 0.3f);
		getConfig().addDefault("foodTypes.vegetable.POISONOUS_POTATO", 0.3f);
		getConfig().addDefault("foodTypes.vegetable.MUSHROOM_SOUP", 0.5f);
		getConfig().addDefault("foodTypes.vegetable.PUMPKIN_PIE", 0.5f);
		getConfig().addDefault("foodTypes.vegetable.RABBIT_STEW", 0.5f);
		getConfig().addDefault("foodTypes.vegetable.BEETROOT", 0.15f);
		getConfig().addDefault("foodTypes.vegetable.BEETROOT_SOUP", 0.4f);
		getConfig().addDefault("foodTypes.vegetable.GOLDEN_CARROT", 0.5f);
		
		//Fruit
		getConfig().addDefault("foodTypes.fruit.APPLE", 0.35f);
		getConfig().addDefault("foodTypes.fruit.GOLDEN_APPLE", 0.7f);
		getConfig().addDefault("foodTypes.fruit.MELON", 0.1f);
		getConfig().addDefault("foodTypes.fruit.CHORUS", 0.5f);
		
		//Meat
		getConfig().addDefault("foodTypes.meat.BEEF", 0.05f);
		getConfig().addDefault("foodTypes.meat.COOKED_BEEF", 0.4f);
		getConfig().addDefault("foodTypes.meat.MUTTON", 0.05f);
		getConfig().addDefault("foodTypes.meat.COOKED_MUTTON", 0.45f);
		getConfig().addDefault("foodTypes.meat.RAW_CHICKEN", 0.04f);
		getConfig().addDefault("foodTypes.meat.RABBIT", 0.04f);
		getConfig().addDefault("foodTypes.meat.COOKED_RABBIT", 0.35f);
		getConfig().addDefault("foodTypes.meat.PORK", 0.04f);
		getConfig().addDefault("foodTypes.meat.GRILLED_PORK", 0.05f);
		getConfig().addDefault("foodTypes.meat.SPIDER_EYE", 0.05f);
		
		//Fish
		getConfig().addDefault("foodTypes.fish.RAW_FISH", 0.05f);
		getConfig().addDefault("foodTypes.fish.COOKED_FISH", 0.6f);
		
		//Grain
		getConfig().addDefault("foodTypes.grain.BREAD", 0.4f);
		getConfig().addDefault("foodTypes.grain.COOKIE", 0.1f);
		
		
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		if(cmd.getName().equalsIgnoreCase("setmaxhealth") && sender instanceof Player) {
			Player player = (Player) sender;
			player.setMaxHealth(Double.parseDouble(args[0]));
			return false;
		} else if(cmd.getName().equalsIgnoreCase("setspeed") && sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(Float.toString(player.getWalkSpeed()));
			player.setWalkSpeed(Float.parseFloat(args[0]));
			return false;
		} else if (cmd.getName().equalsIgnoreCase("getitemmat") && sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(player.getItemInHand().getType().toString());
		} else if (cmd.getName().equalsIgnoreCase("showpercbar") && sender instanceof Player) {
			float floatPercentage = Float.parseFloat(args[0]);
			Player player = (Player) sender;
			player .sendMessage("§a" + args[0] + "% " + new HaNMsgFormatting().returnPercentageBar(floatPercentage, 10, true, "§a", "§b", "§2"));
		}
		return false;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		File playerFile = new File(this.getDataFolder()+File.separator+"players"+File.separator+event.getPlayer().getUniqueId()+".yml");
		
		if(!playerFile.exists()) {
			try {
			playerFile.createNewFile();
			FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
			playerConfig.set("fruitLevel", );
			playerConfig.set("xpInfo.rank", 1);
			playerConfig.save(playerFile);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}

}
