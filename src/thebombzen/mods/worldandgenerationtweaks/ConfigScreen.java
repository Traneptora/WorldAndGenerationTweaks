package thebombzen.mods.worldandgenerationtweaks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import thebombzen.mods.thebombzenapi.client.ThebombzenAPIConfigScreen;

@SideOnly(Side.CLIENT)
public class ConfigScreen extends ThebombzenAPIConfigScreen {

	public ConfigScreen(WorldAndGenerationTweaks mod, GuiScreen parentScreen,
			Configuration config) {
		super(mod, parentScreen, config);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		buttonList.remove(buttonList.size() - 1);
		this.buttonList.add(new GuiButton(4912, width / 2 + 1, height / 6 + 23
				* ((buttonList.size() - 1) >> 1) - 18, 205, 20, StatCollector
				.translateToLocal("gui.done")));
	}

}
