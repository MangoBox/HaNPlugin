package mangobox.main;

import org.bukkit.entity.Player;

public class HaNAttributeController {

	HaNMain mainClass;
	public HaNAttributeController(HaNMain plugin) {
		mainClass = plugin;
	}
	
	//This class should be used for adding certain aspects ("attributes") to a player based on their overall health.
	
	//Adds to the players max health. Negative values can also be used here.
	public void playerSetMaxHealth(Player player, double amount) {
		//A switch operator to ensure health is not zero or below.
		double minHealth = mainClass.getConfig().getDouble("minHealth") <= 0 ? mainClass.getConfig().getDouble("minHealth") : mainClass.getConfig().getDouble("minHealth") + 1;
		double maxHealth = mainClass.getConfig().getDouble("maxHealth");
		double lerpedAmount = minHealth + ((maxHealth - minHealth) * amount);
		//Creates  a lerped amount.
		double clampedAmount = Math.min(maxHealth, Math.max(minHealth, lerpedAmount));
		//If the number is odd, adds another one to make a full heart, as long as the config permits it.
		int finalAmount = (int) clampedAmount;
		
		if(finalAmount % 2 != 0 && mainClass.getConfig().getBoolean("roundMaxHealthUp")) {
			finalAmount++;
		}

		player.setMaxHealth(finalAmount);
	}
	
	public void playerSetSpeed(Player player, double amount, boolean lerp) {
		double currentSpeed = player.getWalkSpeed();
		double minSpeed = mainClass.getConfig().getDouble("minSpeed");
		double maxSpeed = mainClass.getConfig().getDouble("maxSpeed");


		if(lerp) {
			double lerpedAmount = minSpeed + ((maxSpeed - minSpeed) * amount);
			double clampedAmount = Math.min(mainClass.getConfig().getDouble("maxSpeed"), Math.max(mainClass.getConfig().getDouble("minSpeed"), lerpedAmount));
			player.setWalkSpeed((float) lerpedAmount);
			player.sendMessage(String.valueOf(lerpedAmount));
		} else {
			double clampedAmount = Math.min(mainClass.getConfig().getDouble("maxSpeed"), Math.max(mainClass.getConfig().getDouble("minSpeed"), amount));
			player.setWalkSpeed((float) clampedAmount);
			player.sendMessage(String.valueOf(clampedAmount));
		}

	}
	

}
