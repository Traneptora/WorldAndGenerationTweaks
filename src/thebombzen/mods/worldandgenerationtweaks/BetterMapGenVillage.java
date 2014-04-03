package thebombzen.mods.worldandgenerationtweaks;

import java.util.Random;

import net.minecraft.world.gen.structure.MapGenVillage;
import thebombzen.mods.thebombzenapi.ThebombzenAPI;

public class BetterMapGenVillage extends MapGenVillage {
	
	public static final String[] terrainTypeNames = {"field_75054_f", "terrainType", "f"};
	public static final String[] gNames = {"field_82665_g", "g"};
	public static final String[] hNames = {"field_82666_h", "h"};
	
	public BetterMapGenVillage(MapGenVillage village){
		ThebombzenAPI.setPrivateField(this, MapGenVillage.class, ThebombzenAPI.getPrivateField(village, MapGenVillage.class, terrainTypeNames), terrainTypeNames);
		ThebombzenAPI.setPrivateField(this, MapGenVillage.class, ThebombzenAPI.getPrivateField(village, MapGenVillage.class, gNames), gNames);
		ThebombzenAPI.setPrivateField(this, MapGenVillage.class, ThebombzenAPI.getPrivateField(village, MapGenVillage.class, hNames), hNames);
	}
	
	public int getFieldG(){
		return ThebombzenAPI.getPrivateField(this, MapGenVillage.class, gNames);
	}
	
	public int getFieldH(){
		return ThebombzenAPI.getPrivateField(this, MapGenVillage.class, hNames);
	}
	
	public boolean canStructureSpawnAtCoords0(Random random, int par1, int par2){
		int k = par1;
        int l = par2;

        if (par1 < 0)
        {
            par1 -= this.getFieldG() - 1;
        }

        if (par2 < 0)
        {
            par2 -= this.getFieldG() - 1;
        }

        int i1 = par1 / this.getFieldG();
        int j1 = par2 / this.getFieldG();
        i1 *= this.getFieldG();
        j1 *= this.getFieldG();
        i1 += random.nextInt(this.getFieldG() - this.getFieldH());
        j1 += random.nextInt(this.getFieldG() - this.getFieldH());

        if (k == i1 && l == j1)
        {
            boolean flag = this.worldObj.getWorldChunkManager().areBiomesViable(k * 16 + 8, l * 16 + 8, 0, villageSpawnBiomes);

            if (flag)
            {
                return true;
            }
        }

        return false;
	}
	
	@Override
	public boolean canSpawnStructureAtCoords(int par1, int par2){
		if (!WorldAndGenerationTweaks.instance.getConfiguration().getBooleanProperty(Configuration.MORE_VILLAGES)){
			return super.canSpawnStructureAtCoords(par1, par2);
		}
		Random random = this.worldObj.setRandomSeed(par1, par2, 10387312);
		for (int i = 0; i < 4; i++){
			if (this.canStructureSpawnAtCoords0(random, par1, par2)){
				return true;
			}
		}
		return false;
	}
	
}
