package thebombzen.mods.worldandgenerationtweaks.loader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.jar.JarFile;


import cpw.mods.fml.common.FMLModContainer;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions(value = "thebombzen.mods.worldandgenerationtweaks.loader")
public class WAGTCorePlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { ASMClassTransformer.class.getCanonicalName() };
	}

	@Override
	@Deprecated
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
	}
}
