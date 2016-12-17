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
}
