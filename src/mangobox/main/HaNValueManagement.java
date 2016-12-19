package mangobox.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class HaNValueManagement {

	HaNMain mainClass;
	public HaNValueManagement(HaNMain plugin) {
		mainClass = plugin;
	}
	
	
	public double getFoodFruitValue(Material itemMat) {
		Double foodValue = mainClass.getConfig().getDouble("foodTypes.fruit." + itemMat.toString());
		if(foodValue != null) {
			return foodValue;
		} else {
			return 0d;
		}
	}
	
	public double getFoodVegetableValue(Material itemMat) {
		Double foodValue = mainClass.getConfig().getDouble("foodTypes.vegetable." + itemMat);
		if(foodValue != null) {
			return foodValue;
		} else {
			return 0d;
		}
	}
	
	public double getFoodMeatValue(Material itemMat) {
		Double foodValue = mainClass.getConfig().getDouble("foodTypes.meat." + itemMat);
		if(foodValue != null) {
			return foodValue;
		} else {
			return 0d;
		}
	}
	
	public double getFoodFishValue(Material itemMat) {
		Double foodValue = mainClass.getConfig().getDouble("foodTypes.fish." + itemMat);
		if(foodValue != null) {
			return foodValue;
		} else {
			return 0d;
		}
	}
	
	public double getFoodGrainValue(Material itemMat) {
		Double foodValue = mainClass.getConfig().getDouble("foodTypes.grain." + itemMat);
		if(foodValue != null) {
			return foodValue;
		} else {
			return 0d;
		}
	}
	
	public double getTotalFoodLevel(Player player) {
		double fruitLevel = getPlayerFruitLevel(player);
		double vegetableLevel = getPlayerVegetableLevel(player);
		double meatLevel = getPlayerMeatLevel(player);
		double grainLevel = getPlayerGrainLevel(player);
		double fishLevel = getPlayerFishLevel(player);
		
		//Below calculations are as follows: Average - (Average - Lowest Set Value * 0.75), creating a heavy bias towards the lowest value.
		double totalAverage = (fruitLevel + vegetableLevel + meatLevel + grainLevel + fishLevel) / 5;
		//Extremely inefficient, but it nonetheless returns the lowest food value.
		double lowestLevel = Math.min(fruitLevel, Math.min(vegetableLevel, Math.min(meatLevel, Math.min(grainLevel, fishLevel))));
		return totalAverage - ((totalAverage - lowestLevel) * 0.75);
	
	}
	
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
		return playerConfig.getDouble("foodValues.fruitLevel");
	}
	
	public double getPlayerVegetableLevel(Player player) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		return playerConfig.getDouble("foodValues.vegetableLevel");
	}
	
	public double getPlayerMeatLevel(Player player) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		return playerConfig.getDouble("foodValues.meatLevel");
	}
	
	public double getPlayerGrainLevel(Player player) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		return playerConfig.getDouble("foodValues.grainLevel");
	}
	
	public double getPlayerFishLevel(Player player) {
		File playerFile = new File(mainClass.getDataFolder()+File.separator+"players"+File.separator+player.getUniqueId()+".yml");
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		return playerConfig.getDouble("foodValues.fishLevel");
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
		setPlayerFruitLevel(player, fruitLevel += toAdd);
	}
	
	public void addPlayerVegetableLevel(Player player, double toAdd) {
		double vegetableLevel = getPlayerVegetableLevel(player);
		setPlayerVegetableLevel(player, vegetableLevel += toAdd);
	}
	
	public void addPlayerMeatLevel(Player player, double toAdd) {
		double meatLevel = getPlayerMeatLevel(player);
		setPlayerMeatLevel(player, meatLevel += toAdd);
	}
	
	public void addPlayerFishLevel(Player player, double toAdd) {
		double fishLevel = getPlayerFishLevel(player);
		setPlayerFishLevel(player, fishLevel += toAdd);
	}
	
	public void addPlayerGrainLevel(Player player, double toAdd) {
		double grainLevel = getPlayerGrainLevel(player);
		setPlayerGrainLevel(player, grainLevel += toAdd);
	}
}
