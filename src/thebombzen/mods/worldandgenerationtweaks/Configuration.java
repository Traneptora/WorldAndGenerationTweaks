package thebombzen.mods.worldandgenerationtweaks;

import java.io.IOException;

import thebombzen.mods.thebombzenapi.ThebombzenAPIConfiguration;

public class Configuration extends ThebombzenAPIConfiguration<ConfigOption> {

	public Configuration(WorldAndGenerationTweaks mod) {
		super(mod, ConfigOption.class);
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
