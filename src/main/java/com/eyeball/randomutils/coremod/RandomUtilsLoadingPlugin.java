package com.eyeball.randomutils.coremod;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Map;

public class RandomUtilsLoadingPlugin implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
                RandomUtilsTransformer.class.getName()
        };
    }

    @Override
    public String getModContainerClass() {
        return DummyCoremod.class.getName();
    }

    @Override
    public String getSetupClass() {
        return "com.eyeball.randomutils.coremod.DummyCallHook";
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
