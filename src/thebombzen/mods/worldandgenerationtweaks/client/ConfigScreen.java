package thebombzen.mods.worldandgenerationtweaks.client;

import net.minecraft.client.gui.GuiScreen;
import thebombzen.mods.thebombzenapi.client.ThebombzenAPIConfigScreen;
import thebombzen.mods.worldandgenerationtweaks.WorldAndGenerationTweaks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ConfigScreen extends ThebombzenAPIConfigScreen {

	public ConfigScreen(GuiScreen parentScreen) {
		super(WorldAndGenerationTweaks.instance, parentScreen, WorldAndGenerationTweaks.instance.getConfiguration());
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		//buttonList.remove(buttonList.size() - 1);
		this.buttonList.add(new GuiButton(4912, width / 2 + 1, height / 6 + 23
				* ((buttonList.size() - 1) >> 1) - 18, 205, 20, StatCollector
				.translateToLocal("gui.done")));
	}*/

}
