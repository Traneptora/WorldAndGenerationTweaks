package thebombzen.mods.worldandgenerationtweaks;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPotato extends WorldGenerator {
	@Override
	public boolean generate(World world, Random random, int x,
			int y, int z) {
		for (int l = 0; l < 64; l++) {
			int genX = x + random.nextInt(8) - random.nextInt(8);
			int genY = y + random.nextInt(4) - random.nextInt(4);
			int genZ = z + random.nextInt(8) - random.nextInt(8);
			if (world.isAirBlock(genX, genY, genZ)
					&& world.getBlock(genX, genY - 1, genZ) == Blocks.grass
					&& world.canBlockSeeTheSky(genX, genY, genZ)) {
				world.setBlock(genX, genY - 1, genZ,
						Blocks.farmland, 0, 2);
				world.setBlock(genX, genY, genZ, Blocks.potatoes, 7, 2);
			}
		}

		return true;
	}
}
