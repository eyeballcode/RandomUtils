package com.eyeball.randomutils.coremod;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.util.Collections;

public class DummyCoremod extends DummyModContainer {

    public DummyCoremod() {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "RUCoreMod";
        meta.name = "Random Utils Core Mod";
        meta.version = "1.0.0";
        meta.credits = "Eyeballcode";
        meta.authorList = Collections.singletonList("Eyeballcode");
        meta.description = "Coremod for Random Utils";
        meta.url = "";
        meta.updateUrl = "";
        meta.screenshots = new String[0];
        meta.logoFile = "";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CoremodStatic.LOGGER = event.getModLog();
    }
}
