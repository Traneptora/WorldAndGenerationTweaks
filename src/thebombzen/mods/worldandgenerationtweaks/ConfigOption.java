package thebombzen.mods.worldandgenerationtweaks;

import thebombzen.mods.thebombzenapi.ThebombzenAPIConfigOption;

public enum ConfigOption implements ThebombzenAPIConfigOption {
	
	FLAT_BEDROCK(BOOLEAN, "true", "Flat bedrock", "Generate bedrock as a single, flat layer."),
	MORE_RED_MUSHROOMS(BOOLEAN, "true", "More red mushrooms", "Generate red mushrooms just as often as brown."),
	MORE_SUGARCANE(BOOLEAN, "true", "More natural sugarcane", "Generate more natural sugarcane."),
	NATURAL_MELONS(BOOLEAN, "true", "Naturally-spawning melons", "Generate naturally-spawning melons", "just like pumpkins"),
	NATURAL_CARROTS_POTATOES(BOOLEAN, "true", "Natural carrots and potatoes", "Generate naturally-spawning carrots", "and potatoes just like pumpkins."),
	MORE_SPAWNED_CROPS(BOOLEAN, "true", "More generated crops", "Generate more natural pumpkins, melons (if enabled)","and carrots/potatoes (if enabled)."),
	LESS_UNDERGROUND_DIRT(BOOLEAN, "true", "Less underground dirt", "Generate less underground dirt.", "Requires Minecraft restart to change."),
	LESS_UNDERGROUND_GRAVEL(BOOLEAN, "true", "Less underground gravel", "Generate less underground gravel.", "Requires Minecraft restart to change."),
	MORE_MUSHROOM_BIOMES(BOOLEAN, "true", "More mushroom biomes", "Generate more mushroom biomes."),
	MORE_STRONGHOLDS(BOOLEAN, "true", "More strongholds", "Generate twice as many strongholds", "(6 per world instead of 3)", "and half the distance from spawn."),
	MORE_VILLAGES(BOOLEAN, "true", "More villages", "Generate more NPC villages."),
	EMERALD_SPAWNS_EVERYWHERE(BOOLEAN, "true", "Emerald ore spawns everywhere", "Generate emerald ore in every biome", "and twice as often in the extreme hills."),
	TALLER_MOUNTAINS(BOOLEAN, "true", "Taller mountains", "Generate taller and more sky-like mountains.", "Requires Minecraft restart to change."),
	DEEPER_OCEANS(BOOLEAN, "true", "Deeper oceans", "Generate deeper oceans.", "Requires Minecraft restart to change."),
	FLAT_DESERT(BOOLEAN, "true", "Flat desert", "Generate very flat deserts", "and no desert hills.", "Requires Minecraft restart to change."),
	FLAT_PLAINS(BOOLEAN, "true", "Flat plains", "Generate very flat plains.", "Requires Minecraft restart to change."),
	FEWER_SNOW_PLAINS(BOOLEAN, "true", "Fewer snow plains", "Generate far fewer snow plains biomes.");

	private int optionType;

	private String defaultValue;
	private String shortInfo;
	private String[] info;
	private ConfigOption(int optionType, String defaultValue,
			String shortInfo, String... info) {
		this.optionType = optionType;
		this.defaultValue = defaultValue;
		this.shortInfo = shortInfo;
		this.info = info;
	}
	

	@Override
	public int getDefaultToggleIndex() {
		return -1;
	}

	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

	@Override
	public String[] getFiniteStringOptions() {
		throw new UnsupportedOperationException("Only supported for finite strings!");
	}

	@Override
	public String[] getInfo() {
		return info;
	}

	@Override
	public int getOptionType() {
		return optionType;
	}

	@Override
	public String getShortInfo() {
		return shortInfo;
	}
	
}
