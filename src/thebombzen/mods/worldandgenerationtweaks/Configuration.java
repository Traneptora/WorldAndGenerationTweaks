package thebombzen.mods.worldandgenerationtweaks;

import java.io.IOException;

import thebombzen.mods.thebombzenapi.configuration.ConfigOption;
import thebombzen.mods.thebombzenapi.configuration.ThebombzenAPIConfiguration;

public class Configuration extends ThebombzenAPIConfiguration {

	public static final ConfigOption FLAT_BEDROCK = new ConfigOption(true, "FLAT_BEDROCK", "Flat bedrock", "Generate bedrock as a single, flat layer.");
	public static final ConfigOption MORE_RED_MUSHROOMS = new ConfigOption(true, "MORE_RED_MUSHROOMS", "More Red Mushrooms", "Generate red mushrooms just as often as brown.");
	public static final ConfigOption MORE_SUGARCANE = new ConfigOption(true, "MORE_SUGARCANE", "More natural sugarcane", "Generate more natural sugarcane.");
	public static final ConfigOption NATURAL_MELONS = new ConfigOption(true, "NATURAL_MELONS", "Naturally-spawning melons", "Generate naturally-spawning melons", "just like pumpkins");
	public static final ConfigOption NATURAL_CARROTS_POTATOES = new ConfigOption(true, "NATURAL_CARROTS_POTATOES", "Natural carrots and potatoes", "Generate naturally-spawning carrots", "and potatoes just like pumpkins.");
	public static final ConfigOption MORE_SPAWNED_CROPS = new ConfigOption(true, "MORE_SPAWNED_CROPS", "More generated crops", "Generate more natural pumpkins, melons (if enabled)","and carrots/potatoes (if enabled).");
	public static final ConfigOption LESS_UNDERGROUND_DIRT = new ConfigOption(true, "LESS_UNDERGROUND_DIRT", "Less underground dirt", "Generate smaller underground dirt patches.");
	public static final ConfigOption LESS_UNDERGROUND_GRAVEL = new ConfigOption(true, "LESS_UNDERGROUND_GRAVEL", "Less underground gravel", "Generate smaller underground gravel patches.");
	public static final ConfigOption EMERALD_SPAWNS_EVERYWHERE = new ConfigOption(true, "EMERALD_SPAWNS_EVERYWHERE", "Emerald ore spawns everywhere", "Generate emerald ore in every biome", "and twice as often in the extreme hills.");
	public static final ConfigOption TALLER_MOUNTAINS = new ConfigOption(true, "TALLER_MOUNTAINS", "Taller mountains", "Generate taller and more sky-like mountains.");
	public static final ConfigOption DEEPER_OCEANS = new ConfigOption(true, "DEEPER_OCEANS", "Deeper oceans", "Generate deeper oceans.");
	public static final ConfigOption FLAT_DESERT = new ConfigOption(true, "FLAT_DESERT", "Generate flatter deserts", "and no desert hills.");
	public static final ConfigOption FLAT_PLAINS = new ConfigOption(true, "FLAT_PLAINS", "Flat plains", "Generate flatter plains.");
	public static final ConfigOption MORE_VILLAGES = new ConfigOption(true, "MORE_VILLAGES", "More villages", "Generate more villages.");
	public static final ConfigOption MORE_STRONGHOLDS = new ConfigOption(true, "MORE_STRONGHOLDS", "Generate twice as many strongholds", "(6 per map instead of 3)", "and generate them twice as close to home.");
	
	public Configuration(WorldAndGenerationTweaks mod) {
		super(mod);
	}
	
	@Override
	public ConfigOption[] getAllOptions() {
		return new ConfigOption[]{FLAT_BEDROCK, MORE_RED_MUSHROOMS, MORE_SUGARCANE, NATURAL_MELONS, NATURAL_CARROTS_POTATOES, MORE_SPAWNED_CROPS, LESS_UNDERGROUND_DIRT, LESS_UNDERGROUND_GRAVEL, EMERALD_SPAWNS_EVERYWHERE, TALLER_MOUNTAINS, DEEPER_OCEANS, FLAT_DESERT, FLAT_PLAINS, MORE_VILLAGES, MORE_STRONGHOLDS};
	}
	
	public void saveProperties(){
		super.saveProperties();
		WorldAndGenerationTweaks.instance.setBiomeChanges();
	}
	
	public void loadProperties() throws IOException {
		super.loadProperties();
		WorldAndGenerationTweaks.instance.setBiomeChanges();
	}

}
