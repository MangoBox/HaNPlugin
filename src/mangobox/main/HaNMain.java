package mangobox.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class HaNMain extends JavaPlugin implements Listener {
	
	
    HaNMain mainClass = this;
	@Override
	public void onEnable() {
		getLogger().info("HaNMain v0.1 by MangoBox has been loaded!");
		
		//Creates a directory for player data.
		File folder = new File(this.getDataFolder()+File.separator+"players");
		if(!folder.exists()) folder.mkdirs();
		this.getServer().getPluginManager().registerEvents(this, this);
		HaNFoodIntakeController foodController = new HaNFoodIntakeController(this);
		this.getServer().getPluginManager().registerEvents(foodController, this);
		
		//Min and max values for possible max health.
		getConfig().addDefault("minHealth", 1d);
		getConfig().addDefault("maxHealth", 40d);
		
		getConfig().addDefault("minHealthLevel", 0d);
		getConfig().addDefault("maxHealthLevel", 90d);

		//Min and max values for possible speed.
		getConfig().addDefault("minSpeed", 0.1f);
		getConfig().addDefault("maxSpeed", 0.3f);
		
		getConfig().addDefault("minSpeedLevel", 0d);
		getConfig().addDefault("maxSpeedLevel", 80d);
		
		//When true, rounds the maxHealth attribute to the nearest whole heart.
		getConfig().addDefault("roundMaxHealthUp", true);
		
		//The number of ticks before subtracting the subtractPercValue
		getConfig().addDefault("ticksPerDeduction", 1200L);
		getConfig().addDefault("deductionPercAmount", -0.3);
		
		getConfig().addDefault("useRandomSpawnValues", true);
		getConfig().addDefault("additionalRandomAdvantage", 25);
		
		getConfig().addDefault("fruitDefaultValue", 50d);
		getConfig().addDefault("vegetableDefaultValue", 50d);
		getConfig().addDefault("meatDefaultValue", 50d);
		getConfig().addDefault("fishDefaultValue", 50d);
		getConfig().addDefault("grainDefaultValue", 50d);
		
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
		
		//Creates a scheduler for dwindling away the health levels of players
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
              for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            	  	HaNValueManagement valueManagement = new HaNValueManagement(mainClass);
					valueManagement.addPlayerFishLevel(player, getConfig().getDouble("deductionPercAmount"));
					valueManagement.addPlayerFruitLevel(player, getConfig().getDouble("deductionPercAmount"));
					valueManagement.addPlayerVegetableLevel(player, getConfig().getDouble("deductionPercAmount"));
					valueManagement.addPlayerMeatLevel(player, getConfig().getDouble("deductionPercAmount"));
					valueManagement.addPlayerGrainLevel(player, getConfig().getDouble("deductionPercAmount"));
              }
            }
        }, 200L, getConfig().getLong("ticksPerDeduction"));
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		//The debugging commands. Use if necessary.
		if(cmd.getName().equalsIgnoreCase("setmaxhealth") && sender instanceof Player) {
			Player player = (Player) sender;
			player.setMaxHealth(Double.parseDouble(args[0]));
			return false;
		} else if(cmd.getName().equalsIgnoreCase("setspeed") && sender instanceof Player) {
			Player player = (Player) sender;
			new HaNAttributeController(this).playerSetSpeed(player, Double.parseDouble(args[0]), true);
			return false;
		} else if (cmd.getName().equalsIgnoreCase("getitemmat") && sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(player.getItemInHand().getType().toString());
		} else if (cmd.getName().equalsIgnoreCase("showpercbar") && sender instanceof Player) {
			float floatPercentage = Float.parseFloat(args[0]);
			Player player = (Player) sender;
			player .sendMessage("�a" + args[0] + "% " + new HaNMsgFormatting().returnPercentageBar(floatPercentage, 10, true, "�a", "�b", "�2"));
		}
		if(cmd.getName().equalsIgnoreCase("health") && sender instanceof Player) {
			//Casting the Sender to a player.
			Player player = (Player) sender;
			Player target;
			
			//Setting the target to the sender if they leave the last argument out.
			if(args.length == 3) {
				target = Bukkit.getPlayerExact(args[2]);
			} else {
				target = player;
			}
			
			//Breaks the method if /health <type> AMOUNT is not a parseable double.
			//Also breaks the method afterwards if the value is more than 100 or less than 0.
			if(args.length >= 2) {
				try {
				Double.parseDouble(args[1]);
				} catch (NumberFormatException exception) {
					player.sendMessage("�cThe amount you entered was not valid.");
					return true;
				}
				
				if(Double.parseDouble(args[1]) > 100 || Double.parseDouble(args[1]) < 0) {
					player.sendMessage("�cThe amount you entered must be between 0 and 100.");
					return true;
				}
			}
			
			//Breaks the method if the player was not found.
			if(target == null) {
				player.sendMessage("�cThe target was not found.");
				return true;
			}
			
			HaNValueManagement valueManagement = new HaNValueManagement(this);
			if (args.length == 0) {
				//Making the standard health message
				HaNMsgFormatting msgFormatting = new HaNMsgFormatting();
				msgFormatting.sendPlayerNutritionMessage(player, this);
			} else if (args.length >= 1) {
				//Command for setting health values. Admin permission should be added to the statement above.
				if(args.length == 1) {
					player.sendMessage("�cThat command was not used correctly. Usage: /health <type> <amount> [player]");
					player.sendMessage("�cTypes are: fruit, vegetable, fish, meant, grain, all");
				} else if (args.length == 2 || args.length == 3) {
					//Setting the individual values.
					switch (args[0]) {
						case "all":
							valueManagement.setPlayerFishLevel(target, Double.parseDouble(args[1]));
							valueManagement.setPlayerFruitLevel(target, Double.parseDouble(args[1]));
							valueManagement.setPlayerMeatLevel(target, Double.parseDouble(args[1]));
							valueManagement.setPlayerVegetableLevel(target, Double.parseDouble(args[1]));
							valueManagement.setPlayerGrainLevel(target, Double.parseDouble(args[1]));
							player.sendMessage("�aSet all values to " + args[1] + " for player " + target.getDisplayName());
							return true;
					case "fruit":
							valueManagement.setPlayerFruitLevel(target, Double.parseDouble(args[1]));
							player.sendMessage("�aSet fruit value to " + args[1] + " for player " + target.getDisplayName());
							return true;
						case "vegetable":
							valueManagement.setPlayerVegetableLevel(target, Double.parseDouble(args[1]));
							player.sendMessage("�aSet vegetable value to " + args[1] + " for player " + target.getDisplayName());
							return true;
						case "meat":
							valueManagement.setPlayerMeatLevel(target, Double.parseDouble(args[1]));
							player.sendMessage("�aSet meat value to " + args[1] + " for player " + target.getDisplayName());
							return true;
						case "grain":
							valueManagement.setPlayerGrainLevel(target, Double.parseDouble(args[1]));
							player.sendMessage("�aSet grain value to " + args[1] + " for player " + target.getDisplayName());
							return true;
						case "fish":
							valueManagement.setPlayerFishLevel(target, Double.parseDouble(args[1]));
							player.sendMessage("�aSet fish value to " + args[1] + " for player " + target.getDisplayName());
							return true;
						default:
							//If the type of food is invalid, the player is sent an error message.
							player.sendMessage("�cThat type of food value was not found.");
							return true;
					}
				} else if (args.length >= 4) {
					player.sendMessage("�cToo many arguments. Usage: /health <type> <amount> [player]");
					player.sendMessage("�cTypes are: fruit, vegetable, fish, meant, grain, all");
				}
			}
		} else if (cmd.getName().equalsIgnoreCase("sethunger") && sender instanceof Player) {
			Player player = (Player) sender;
			player.setFoodLevel(Integer.parseInt(args[0]));
		}
		return true;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
		File playerFile = new File(this.getDataFolder()+File.separator+"players"+File.separator+event.getPlayer().getUniqueId()+".yml");
		
		if(!playerFile.exists()) {
			playerFile.createNewFile();
			FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
			if(getConfig().getBoolean("useRandomSpawnValues")) {
				//If config permits it, random health values will be used upon spawn.
				HaNValueManagement valueManagement = new HaNValueManagement(this);
				valueManagement.setRandomPlayerValues(event.getPlayer(), getConfig().getDouble("additionalRandomAdvantage"));
			} else {
				//If not, default values will be used.
				playerConfig.set("foodValues.fruitLevel", getConfig().getDouble("fruitDefaultValue"));
				playerConfig.set("foodValues.meatLevel", getConfig().getDouble("meatDefaultValue"));
				playerConfig.set("foodValues.vegetableLevel", getConfig().getDouble("vegetableDefaultValue"));
				playerConfig.set("foodValues.grainLevel", getConfig().getDouble("grainDefaultValue"));
				playerConfig.set("foodValues.fishLevel", getConfig().getDouble("fishDefaultValue"));
				playerConfig.save(playerFile);
			}
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		HaNValueManagement valueManagement = new HaNValueManagement(this);
		valueManagement.setRandomPlayerValues(event.getPlayer(), getConfig().getDouble("additionalRandomAdvantage"));
	}

}
