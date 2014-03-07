package thebombzen.mods.worldandgenerationtweaks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import thebombzen.mods.thebombzenapi.ThebombzenAPIBaseMod;
import thebombzen.mods.thebombzenapi.ThebombzenAPIConfiguration;
import thebombzen.mods.thebombzenapi.client.ThebombzenAPIConfigScreen;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "WorldAndGenerationTweaks", name = "WorldAndGenerationTweaks", version = "1.6.0", dependencies = "required-after:ThebombzenAPI")
public class WorldAndGenerationTweaks extends ThebombzenAPIBaseMod implements
		IWorldGenerator {

	private Configuration configuration;

	@Instance(value = "WorldAndGenerationTweaks")
	public static WorldAndGenerationTweaks instance;

	public WorldAndGenerationTweaks() {
		configuration = new Configuration(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void activeKeyPressed(int keyCode) {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public ThebombzenAPIConfigScreen createConfigScreen(GuiScreen base) {
		return new ConfigScreen(this, base, configuration);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (chunkGenerator instanceof ChunkProviderGenerate) {
			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator,
					chunkProvider);
		} else if (chunkGenerator instanceof ChunkProviderHell) {
			generateNether(random, chunkX, chunkZ, world, chunkGenerator,
					chunkProvider);
		}
	}

	public void generateNether(Random random, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		if (configuration.getPropertyBoolean(ConfigOption.FLAT_BEDROCK)) {
			for (int x = 8; x < 24; x++) {
				for (int z = 8; z < 24; z++) {
					for (int y = 4; y > 0; y--) {
						int id = world.getBlockId((chunkX << 4) + x, y,
								(chunkZ << 4) + z);
						if (id == Block.bedrock.blockID) {
							int setID;
							int idabove = world.getBlockId((chunkX << 4) + x,
									y + 1, (chunkZ << 4) + z);
							int idbelow = world.getBlockId((chunkX << 4) + x,
									y - 1, (chunkZ << 4) + z);
							if (idabove == 0 || idbelow == 0) {
								setID = 0;
							} else {
								setID = Block.netherrack.blockID;
							}
							world.setBlock((chunkX << 4) + x, y, (chunkZ << 4)
									+ z, setID);
						}
					}
					for (int y = 123; y < 127; y++) {
						int id = world.getBlockId((chunkX << 4) + x, y,
								(chunkZ << 4) + z);
						if (id == Block.bedrock.blockID) {
							int setID;
							int idabove = world.getBlockId((chunkX << 4) + x,
									y + 1, (chunkZ << 4) + z);
							int idbelow = world.getBlockId((chunkX << 4) + x,
									y - 1, (chunkZ << 4) + z);
							if (idabove == 0 || idbelow == 0) {
								setID = 0;
							} else {
								setID = Block.netherrack.blockID;
							}
							world.setBlock((chunkX << 4) + x, y, (chunkZ << 4)
									+ z, setID);
						}
					}
				}
			}
		}
	}

	public void generateOverworld(Random random, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		if (configuration.getPropertyBoolean(ConfigOption.FLAT_BEDROCK)) {
			for (int x = 0; x < 16; x++) {
				for (int y = 1; y < 5; y++) {
					for (int z = 0; z < 16; z++) {
						int id = world.getBlockId((chunkX << 4) + x + 8, y,
								(chunkZ << 4) + z + 8);
						if (id == Block.bedrock.blockID) {
							world.setBlock((chunkX << 4) + x + 8, y,
									(chunkZ << 4) + z + 8, Block.stone.blockID);
						}
					}
				}
			}
		}

		if (configuration
				.getPropertyBoolean(ConfigOption.EMERALD_SPAWNS_EVERYWHERE)) {
			int numberPerChunk = 3 + random.nextInt(6);
			for (int i = 0; i < numberPerChunk; i++) {
				int x = (chunkX << 4) + random.nextInt(16);
				int y = random.nextInt(28) + 4;
				int z = (chunkZ << 4) + random.nextInt(16);
				int id = world.getBlockId(x, y, z);
				if (id == Block.stone.blockID) {
					world.setBlock(x, y, z, Block.oreEmerald.blockID);
				}
			}
		}

		if (configuration.getPropertyBoolean(ConfigOption.MORE_RED_MUSHROOMS)) {
			for (int i = 0; i < world.getBiomeGenForCoords(chunkX << 4,
					chunkZ << 4).theBiomeDecorator.mushroomsPerChunk; i++) {
				if (random.nextInt(4) == 0) { // this is 4 and not 7 b/c in
												// BiomeDecorator.java it's
												// random.nextInt(128) not
												// world.getHeightValue(x, z)
					int x = (chunkX << 4) + random.nextInt(16) + 8;
					int z = (chunkZ << 4) + random.nextInt(16) + 8;
					int y = world.getHeightValue(x, z);
					world.getBiomeGenForCoords(x, z).theBiomeDecorator.mushroomRedGen
							.generate(world, random, x, y, z);
				}
			}
			if (random.nextInt(7) == 0) { // (1 - ((1-a)*(1-b))) = 1/4, a= 1/8,
											// b=1/7, so 1/8 previously or 1/7
											// now is 1/4 prob of it gen at all
											// (with 1/56 prob of twice)
				int x = (chunkX << 4) + random.nextInt(16) + 8;
				int z = (chunkZ << 4) + random.nextInt(16) + 8;
				int y = random.nextInt(128);
				world.getBiomeGenForCoords(x, z).theBiomeDecorator.mushroomRedGen
						.generate(world, random, x, y, z);
			}
		}

		if (configuration.getPropertyBoolean(ConfigOption.MORE_SUGARCANE)) {
			for (int i = 0; i < world.getBiomeGenForCoords((chunkX << 4),
					(chunkZ << 4)).theBiomeDecorator.reedsPerChunk + 10; i++) {
				int x = (chunkX << 4) + random.nextInt(16) + 8;
				int y = random.nextInt(128);
				int z = (chunkZ << 4) + random.nextInt(16) + 8;
				world.getBiomeGenForCoords(x, z).theBiomeDecorator.reedGen
						.generate(world, random, x, y, z);
			}
		}

		if (configuration.getPropertyBoolean(ConfigOption.MORE_SPAWNED_CROPS)
				&& random.nextInt(62) == 0) { // 1 - (1-(1/32))*(1-(1/62)) = 3/2
												// * 1/32
			int x = (chunkX << 4) + random.nextInt(16) + 8;
			int y = random.nextInt(128);
			int z = (chunkZ << 4) + random.nextInt(16) + 8;
			new WorldGenPumpkin().generate(world, random, x, y, z);
		}

		if (configuration.getPropertyBoolean(ConfigOption.NATURAL_MELONS)
				&& (random.nextInt(32) == 0 || configuration
						.getPropertyBoolean(ConfigOption.MORE_SPAWNED_CROPS)
						&& random.nextInt(62) == 0)) { // 1 -
														// (1-(1/32))*(1-(1/62))
														// = 3/2 * 1/32
			int x = (chunkX << 4) + random.nextInt(16) + 8;
			int y = random.nextInt(128);
			int z = (chunkZ << 4) + random.nextInt(16) + 8;
			new WorldGenMelon().generate(world, random, x, y, z);
		}

		if (configuration
				.getPropertyBoolean(ConfigOption.NATURAL_CARROTS_POTATOES)
				&& (random.nextInt(32) == 0 || configuration
						.getPropertyBoolean(ConfigOption.MORE_SPAWNED_CROPS)
						&& random.nextInt(62) == 0)) {
			int x = (chunkX << 4) + random.nextInt(16) + 8;
			int y = random.nextInt(128);
			int z = (chunkZ << 4) + random.nextInt(16) + 8;
			new WorldGenCarrot().generate(world, random, x, y, z);
		}

		if (configuration
				.getPropertyBoolean(ConfigOption.NATURAL_CARROTS_POTATOES)
				&& (random.nextInt(32) == 0 || configuration
						.getPropertyBoolean(ConfigOption.MORE_SPAWNED_CROPS)
						&& random.nextInt(62) == 0)) {
			int x = (chunkX << 4) + random.nextInt(16) + 8;
			int y = random.nextInt(128);
			int z = (chunkZ << 4) + random.nextInt(16) + 8;
			new WorldGenPotato().generate(world, random, x, y, z);
		}
	}

	@Override
	public ThebombzenAPIConfiguration<?> getConfiguration() {
		return configuration;
	}

	@Override
	public String getLongName() {
		return "WorldAndGenerationTweaks";
	}

	@Override
	public String getLongVersionString() {
		return "WorldAndGenerationTweaks v1.6.0 for Minecraft 1.6.4";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getNumActiveKeys() {
		return 0;
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
		return "https://dl.dropboxusercontent.com/u/51080973/WorldAndGenerationTweaks/WAGTVersion.txt";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasConfigScreen() {
		return true;
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(this);
		for (BiomeGenBase biome : BiomeGenBase.biomeList) {
			if (biome == null) {
				continue;
			}
			if (configuration
					.getPropertyBoolean(ConfigOption.LESS_UNDERGROUND_DIRT)) {
				biome.theBiomeDecorator.dirtGen = new WorldGenMinable(
						Block.dirt.blockID, 8);
			}
			if (configuration
					.getPropertyBoolean(ConfigOption.LESS_UNDERGROUND_GRAVEL)) {
				biome.theBiomeDecorator.gravelGen = new WorldGenMinable(
						Block.gravel.blockID, 8);
			}
		}
		if (configuration.getPropertyBoolean(ConfigOption.TALLER_MOUNTAINS)) {
			float oldMaxHeight = BiomeGenBase.extremeHills.maxHeight;
			BiomeGenBase.extremeHills.maxHeight *= 3.6F / oldMaxHeight;
			BiomeGenBase.extremeHillsEdge.maxHeight *= 3.6F / oldMaxHeight;
		}
		if (configuration.getPropertyBoolean(ConfigOption.DEEPER_OCEANS)) {
			BiomeGenBase.ocean.minHeight = -1.5F;
		}
		if (configuration.getPropertyBoolean(ConfigOption.FLAT_PLAINS)) {
			BiomeGenBase.plains.minHeight = 0.05F;
			BiomeGenBase.plains.maxHeight = 0.1F;
		}
		if (configuration.getPropertyBoolean(ConfigOption.FLAT_DESERT)) {
			BiomeGenBase.desert.minHeight = 0.05F;
			BiomeGenBase.desert.maxHeight = 0.1F;
			BiomeGenBase.desertHills.minHeight = 0.05F;
			BiomeGenBase.desertHills.maxHeight = 0.1F;
		}
	}

	@Override
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}
}
