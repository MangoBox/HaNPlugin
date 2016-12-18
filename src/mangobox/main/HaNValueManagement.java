package mangobox.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class HaNValueManagement {

	HaNMain mainClass;
	
	//Returns the minimum maximum health possible
	public Double getMinHealth() {
		return mainClass.getConfig().getDouble("minHealth");
	}
	
	//Returns the maximum maximum health possible.
	public Double getMaxHealth() {
		return mainClass.getConfig().getDouble("maxHealth");
	}
	
	//Returns the minimum player walking speed possible.
	public float getMinSpeed() {
		return Float.parseFloat(mainClass.getConfig().getString("minSpeed"));
	}
	
	//Returns the maximum player walking speed possible.
	public float getMaxSpeed() {
		return Float.parseFloat(mainClass.getConfig().getString("maxSpeed"));
	}
	
	//Gets the players nutrition levels.
	public double getPlayerFruitLevel(Player player) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		return playerConfig.getDouble("fruitLevel");
	}
	
	public double getPlayerVegetableLevel(Player player) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		return playerConfig.getDouble("vegetableLevel");
	}
	
	public double getPlayerMeatLevel(Player player) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		return playerConfig.getDouble("meatLevel");
	}
	
	public double getPlayerGrainLevel(Player player) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		return playerConfig.getDouble("grainLevel");
	}
	
	public double getPlayerFishLevel(Player player) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		return playerConfig.getDouble("fishLevel");
	}
	
	//The below methods set the players level for each nutrition.
	public void setPlayerFruitLevel(Player player, double value) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		playerConfig.set("foodValues.fruitLevel", value);
		try {
			playerConfig.save(playerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPlayerVegetableLevel(Player player, double value) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		playerConfig.set("foodValues.vegetableLevel", value);
		try {
			playerConfig.save(playerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPlayerMeatLevel(Player player, double value) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		playerConfig.set("foodValues.meatLevel", value);
		try {
			playerConfig.save(playerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPlayerFishLevel(Player player, double value) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		playerConfig.set("foodValues.fishLevel", value);
		try {
			playerConfig.save(playerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPlayerGrainLevel(Player player, double value) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		playerConfig.set("foodValues.grainLevel", value);
		try {
			playerConfig.save(playerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//The following methods add food values to the player, in conjunction with the methods above.
	public void addPlayerFruitLevel(Player player, double toAdd) {
		double fruitLevel = getPlayerFruitLevel(player);
		setPlayerFruitLevel(player, fruitLevel + toAdd);
	}
	
	public void addPlayerVegetableLevel(Player player, double toAdd) {
		double vegetableLevel = getPlayerVegetableLevel(player);
		setPlayerVegetableLevel(player, vegetableLevel + toAdd);
	}
	
	public void addPlayerMeatLevel(Player player, double toAdd) {
		double meatLevel = getPlayerMeatLevel(player);
		setPlayerMeatLevel(player, meatLevel + toAdd);
	}
	
	public void addPlayerFishLevel(Player player, double toAdd) {
		double fishLevel = getPlayerFishLevel(player);
		setPlayerFishLevel(player, fishLevel + toAdd);
	}
	
	public void addPlayerGrainLevel(Player player, double toAdd) {
		double grainLevel = getPlayerGrainLevel(player);
		setPlayerGrainLevel(player, grainLevel + toAdd);
	}
}
