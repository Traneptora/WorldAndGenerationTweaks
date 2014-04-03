package thebombzen.mods.worldandgenerationtweaks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import thebombzen.mods.thebombzenapi.ThebombzenAPIBaseMod;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "worldandgenerationtweaks", name = "WorldAndGenerationTweaks", version = "1.7.0pre2", dependencies = "required-after:thebombzenapi", guiFactory="thebombzen.mods.worldandgenerationtweaks.client.ConfigGuiFactory")
public class WorldAndGenerationTweaks extends ThebombzenAPIBaseMod {

	private Configuration configuration;
	private Map<BiomeGenBase, Float> oldVariations = new HashMap<BiomeGenBase, Float>();
	private Map<BiomeGenBase, Float> oldRootHeights = new HashMap<BiomeGenBase, Float>();
	private Map<BiomeGenBase, WorldGenerator> oldWorldGenDirt = new HashMap<BiomeGenBase, WorldGenerator>();
	private Map<BiomeGenBase, WorldGenerator> oldWorldGenGravel = new HashMap<BiomeGenBase, WorldGenerator>();
	

	@Instance("worldandgenerationtweaks")
	public static WorldAndGenerationTweaks instance;

	public void replaceOverworldBedrock(World world, int worldX, int worldZ, Random random){
		if (configuration.getBooleanProperty(Configuration.FLAT_BEDROCK)) {
			for (int x = 0; x < 16; x++) {
				for (int y = 1; y < 5; y++) {
					for (int z = 0; z < 16; z++) {
						Block setBlock;
						boolean above = world.isAirBlock(worldX + x,
								y + 1, worldZ + z);
						boolean below = world.isAirBlock(worldX + x,
								y - 1, worldZ + z);
						if (above || below){
							setBlock = Blocks.air;
						} else {
							setBlock = Blocks.stone;
						}
						world.setBlock(worldX + x, y, worldZ
								+ z, setBlock, 0, 2);
					}
				}
			}
		}
	}
	
	public void replaceNetherBedrock(World world, int worldX, int worldZ, Random random){
		if (configuration.getBooleanProperty(Configuration.FLAT_BEDROCK)) {
			for (int x = 8; x < 24; x++) {
				for (int z = 8; z < 24; z++) {
					for (int y = 4; y > 0; y--) {
						Block block = world.getBlock(worldX + x, y,
								worldZ + z);
						if (block == Blocks.bedrock) {
							Block setBlock;
							boolean above = world.isAirBlock(worldX + x,
									y + 1, worldZ + z);
							boolean below = world.isAirBlock(worldX + x,
									y - 1, worldZ + z);
							if (above || below){
								setBlock = Blocks.air;
							} else {
								setBlock = Blocks.netherrack;
							}
							world.setBlock(worldX + x, y, worldZ
									+ z, setBlock, 0, 3);
						}
					}
					for (int y = 123; y < 127; y++) {
						Block block = world.getBlock(worldX + x, y,
								worldZ + z);
						if (block == Blocks.bedrock) {
							Block setBlock;
							boolean above = world.isAirBlock(worldX + x,
									y + 1, worldZ + z);
							boolean below = world.isAirBlock(worldX + x,
									y - 1, worldZ + z);
							if (above || below){
								setBlock = Blocks.air;
							} else {
								setBlock = Blocks.netherrack;
							}
							world.setBlock(worldX + x, y, worldZ
									+ z, setBlock, 0, 3);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void postOreGen(OreGenEvent.Post event){
		try {
			World world = event.world;
			int worldX = event.worldX;
			int worldZ = event.worldZ;
			Random random = event.rand;
			if (configuration
					.getBooleanProperty(Configuration.EMERALD_SPAWNS_EVERYWHERE)) {
				int numberPerChunk = 3 + random.nextInt(6);
				for (int i = 0; i < numberPerChunk; i++) {
					int x = worldX + random.nextInt(16);
					int y = random.nextInt(28) + 4;
					int z = worldZ + random.nextInt(16);
					Block block = world.getBlock(x, y, z);
					if (block == Blocks.stone) {
						world.setBlock(x, y, z, Blocks.emerald_ore, 0, 2);
					}
				}
			}
		} catch (Throwable e){
			throwException("postOreGen", e, true);
		}
		
	}
	
	@SubscribeEvent
	public void postDecorate(DecorateBiomeEvent.Post event){
		try {
			World world = event.world;
			int worldX = event.chunkX;
			int worldZ = event.chunkZ;
			Random random = event.rand;
			BiomeGenBase biome = world.getBiomeGenForCoords(worldX, worldZ);
			if (BiomeGenBase.sky.isEqualTo(biome)) {
				return;
			}
			if (BiomeGenBase.hell.isEqualTo(biome)) {
				replaceNetherBedrock(world, worldX, worldZ, random);
				return;
			} else {
				replaceOverworldBedrock(world, worldX, worldZ, random);
			}
			if (configuration
					.getBooleanProperty(Configuration.MORE_RED_MUSHROOMS)) {
				for (int i = 0; i < biome.theBiomeDecorator.mushroomsPerChunk; i++) {
					if (random.nextInt(4) == 0) { // this is 4 and not 7 b/c in
													// BiomeDecorator.java it's
													// random.nextInt(128) not
													// world.getHeightValue(x,
													// z)
						int x = worldX + random.nextInt(16) + 8;
						int z = worldZ + random.nextInt(16) + 8;
						int y = world.getHeightValue(x, z);
						biome.theBiomeDecorator.mushroomRedGen.generate(world,
								random, x, y, z);
					}
				}
				if (random.nextInt(7) == 0) { // (1 - ((1-a)*(1-b))) = 1/4, a=
												// 1/8,
												// b=1/7, so 1/8 previously or
												// 1/7
												// now is 1/4 prob of it gen at
												// all
												// (with 1/56 prob of twice)
					int x = worldX + random.nextInt(16) + 8;
					int z = worldZ + random.nextInt(16) + 8;
					int y = random.nextInt(128);
					biome.theBiomeDecorator.mushroomRedGen.generate(world,
							random, x, y, z);
				}
			}

			if (configuration.getBooleanProperty(Configuration.MORE_SUGARCANE)) {
				for (int i = 0; i < biome.theBiomeDecorator.reedsPerChunk + 10; i++) {
					int x = worldX + random.nextInt(16) + 8;
					int y = random.nextInt(128);
					int z = worldZ + random.nextInt(16) + 8;
					biome.theBiomeDecorator.reedGen.generate(world, random, x,
							y, z);
				}
			}

			if (configuration
					.getBooleanProperty(Configuration.MORE_SPAWNED_CROPS)
					&& random.nextInt(62) == 0) { // 1 - (1-(1/32))*(1-(1/62)) =
													// 3/2
													// * 1/32
				int x = worldX + random.nextInt(16) + 8;
				int y = random.nextInt(128);
				int z = worldZ + random.nextInt(16) + 8;
				new WorldGenPumpkin().generate(world, random, x, y, z);
			}

			if (configuration.getBooleanProperty(Configuration.NATURAL_MELONS)
					&& (random.nextInt(32) == 0 || configuration
							.getBooleanProperty(Configuration.MORE_SPAWNED_CROPS)
							&& random.nextInt(62) == 0)) { // 1 -
															// (1-(1/32))*(1-(1/62))
															// = 3/2 * 1/32
				int x = worldX + random.nextInt(16) + 8;
				int y = random.nextInt(128);
				int z = worldZ + random.nextInt(16) + 8;
				new WorldGenMelon().generate(world, random, x, y, z);
			}

			if (configuration
					.getBooleanProperty(Configuration.NATURAL_CARROTS_POTATOES)
					&& (random.nextInt(32) == 0 || configuration
							.getBooleanProperty(Configuration.MORE_SPAWNED_CROPS)
							&& random.nextInt(62) == 0)) {
				int x = worldX + random.nextInt(16) + 8;
				int y = random.nextInt(128);
				int z = worldZ + random.nextInt(16) + 8;
				new WorldGenCarrot().generate(world, random, x, y, z);
			}

			if (configuration
					.getBooleanProperty(Configuration.NATURAL_CARROTS_POTATOES)
					&& (random.nextInt(32) == 0 || configuration
							.getBooleanProperty(Configuration.MORE_SPAWNED_CROPS)
							&& random.nextInt(62) == 0)) {
				int x = worldX + random.nextInt(16) + 8;
				int y = random.nextInt(128);
				int z = worldZ + random.nextInt(16) + 8;
				new WorldGenPotato().generate(world, random, x, y, z);
			}
		} catch (Throwable e) {
			throwException("postDecorate", e, true);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public String getLongName() {
		return "WorldAndGenerationTweaks";
	}

	@Override
	public String getLongVersionString() {
		return "WorldAndGenerationTweaks, version 1.7.0pre2, Minecraft 1.7.2";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getNumToggleKeys() {
		return 0;
	}

	@Override
	public String getShortName() {
		return "WAGT";
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected String getToggleMessageString(int index, boolean enabled) {
		return null;
	}

	@Override
	protected String getVersionFileURLString() {
		//return "https://dl.dropboxusercontent.com/u/51080973/Mods/WorldAndGenerationTweaks/WAGTVersion.txt";
		return "";
	}
	
	@SubscribeEvent
	public void initializeMapGens(InitMapGenEvent event){
		try {
			if (event.originalGen instanceof MapGenVillage && !(event.originalGen instanceof BetterMapGenVillage)){
				event.newGen = new BetterMapGenVillage((MapGenVillage)event.originalGen);
			}
			if (configuration.getBooleanProperty(Configuration.MORE_STRONGHOLDS)){
				if (event.originalGen instanceof MapGenStronghold && !(event.originalGen instanceof BetterMapGenStronghold)){
					event.newGen = new BetterMapGenStronghold((MapGenStronghold)event.originalGen);
				}
			}
		} catch (Throwable t){
			throwException("initializeMapGens", t, true);
		}
		
	}
	
	public void setBiomeChanges(){
		try {
			for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()){
				if (biome == null){
					continue;
				}
				if (!oldVariations.containsKey(biome)){
					oldVariations.put(biome, biome.heightVariation);
					oldRootHeights.put(biome, biome.rootHeight);
					oldWorldGenDirt.put(biome, biome.theBiomeDecorator.dirtGen);
					oldWorldGenGravel.put(biome, biome.theBiomeDecorator.gravelGen);
				}
			}
			if (configuration.getBooleanProperty(Configuration.TALLER_MOUNTAINS)) {
				BiomeGenBase.extremeHills.heightVariation = 2.5F;
				BiomeGenBase.extremeHillsEdge.heightVariation *= 2.5F / oldVariations.get(BiomeGenBase.extremeHills);
				BiomeGenBase.extremeHillsPlus.heightVariation *= 2.5F / oldVariations.get(BiomeGenBase.extremeHills);
			} else {
				BiomeGenBase.extremeHills.heightVariation = oldVariations.get(BiomeGenBase.extremeHills);
				BiomeGenBase.extremeHillsEdge.heightVariation = oldVariations.get(BiomeGenBase.extremeHillsEdge);
				BiomeGenBase.extremeHillsPlus.heightVariation = oldVariations.get(BiomeGenBase.extremeHillsPlus);
			}
			if (configuration.getBooleanProperty(Configuration.DEEPER_OCEANS)) {
				BiomeGenBase.ocean.rootHeight = -1.5F;
			} else {
				BiomeGenBase.ocean.rootHeight = oldRootHeights.get(BiomeGenBase.ocean);
			}
			if (configuration.getBooleanProperty(Configuration.FLAT_PLAINS)) {
				BiomeGenBase.plains.heightVariation = 0.05F;
				BiomeGenBase.plains.rootHeight = 0.075F;
			} else {
				BiomeGenBase.plains.heightVariation = oldVariations.get(BiomeGenBase.plains);
				BiomeGenBase.plains.rootHeight = oldRootHeights.get(BiomeGenBase.plains);
			}
			if (configuration.getBooleanProperty(Configuration.FLAT_DESERT)) {
				BiomeGenBase.desert.heightVariation = 0.05F;
				BiomeGenBase.desert.rootHeight = 0.075F;
				BiomeGenBase.desertHills.heightVariation = 0.05F;
				BiomeGenBase.desertHills.rootHeight = 0.075F;
			} else {
				BiomeGenBase.desert.heightVariation = oldVariations.get(BiomeGenBase.desert);
				BiomeGenBase.desert.rootHeight = oldRootHeights.get(BiomeGenBase.desert);
				BiomeGenBase.desertHills.heightVariation = oldVariations.get(BiomeGenBase.desertHills);
				BiomeGenBase.desertHills.rootHeight = oldRootHeights.get(BiomeGenBase.desertHills);
			}
			for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
				if (biome == null){
					continue;
				}
				if (configuration.getBooleanProperty(Configuration.LESS_UNDERGROUND_DIRT)){
					biome.theBiomeDecorator.dirtGen = new WorldGenMinable(Blocks.dirt, 8);
				} else {
					biome.theBiomeDecorator.dirtGen = oldWorldGenDirt.get(biome);
				}
				if (configuration.getBooleanProperty(Configuration.LESS_UNDERGROUND_GRAVEL)){
					biome.theBiomeDecorator.gravelGen = new WorldGenMinable(Blocks.gravel, 8);
				} else {
					biome.theBiomeDecorator.gravelGen = oldWorldGenGravel.get(biome);
				}
			}
		} catch (Throwable t){
			throwException("setBiomeChanges", t, true);
		}
	}

	@Override
	public void init1(FMLPreInitializationEvent event) {
		configuration = new Configuration(this);
		FMLCommonHandler.instance().findContainerFor(this).getMetadata().authorList = Arrays.asList("Thebombzen");
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.TERRAIN_GEN_BUS.register(this);
		MinecraftForge.ORE_GEN_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}


	@Override
	public String getDownloadLocationURLString() {
		return "http://is.gd/ThebombzensMods#WorldAndGenerationTweaks";
	}

}
