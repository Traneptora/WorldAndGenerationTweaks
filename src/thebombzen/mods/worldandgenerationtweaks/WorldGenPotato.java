package thebombzen.mods.worldandgenerationtweaks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPotato extends WorldGenerator {
	@Override
	public boolean generate(World par1World, Random par2Random, int par3,
			int par4, int par5) {
		for (int var6 = 0; var6 < 64; ++var6) {
			int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(var7, var8, var9)
					&& par1World.getBlockId(var7, var8 - 1, var9) == Block.grass.blockID
					&& par1World.canBlockSeeTheSky(var7, var8, var9)) {
				par1World.setBlock(var7, var8 - 1, var9,
						Block.tilledField.blockID);
				par1World
						.setBlock(var7, var8, var9, Block.potato.blockID, 7, 3);
			}
		}

		return true;
	}
}
