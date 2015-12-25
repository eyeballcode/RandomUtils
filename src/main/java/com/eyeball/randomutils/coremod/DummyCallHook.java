package com.eyeball.randomutils.coremod;

import cpw.mods.fml.relauncher.IFMLCallHook;

import java.util.Map;

public class DummyCallHook implements IFMLCallHook {
    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public Void call() throws Exception {
        return null;
    }
}
