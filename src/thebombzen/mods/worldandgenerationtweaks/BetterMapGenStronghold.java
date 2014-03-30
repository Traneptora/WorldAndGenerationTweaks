package thebombzen.mods.worldandgenerationtweaks;

import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.gen.structure.MapGenStronghold;
import thebombzen.mods.thebombzenapi.ThebombzenAPI;

public class BetterMapGenStronghold extends MapGenStronghold {
	 
	public static final String[] iNames = {"field_82672_i", "i"};
	public static final String[] hNames = {"field_82671_h", "h"};
	public static final String[] scNames = {"field_75057_g", "g", "structureCoords"};
	public static final String[] rbcNames = {"ranBiomeCheck", "field_75056_f", "f"};
	
	public BetterMapGenStronghold(MapGenStronghold stronghold) {
		ThebombzenAPI.setPrivateField(this, MapGenStronghold.class, ThebombzenAPI.getPrivateField(stronghold, MapGenStronghold.class, iNames), iNames);
		ThebombzenAPI.setPrivateField(this, MapGenStronghold.class, 0.5D * (Double)ThebombzenAPI.getPrivateField(stronghold, MapGenStronghold.class, hNames), hNames);
		ThebombzenAPI.setPrivateField(this, MapGenStronghold.class, false, rbcNames);
		ThebombzenAPI.setPrivateField(this, MapGenStronghold.class, new ChunkCoordIntPair[6], scNames);
	}
}
