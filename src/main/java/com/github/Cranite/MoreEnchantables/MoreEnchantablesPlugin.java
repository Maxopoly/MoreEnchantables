package com.github.Cranite.MoreEnchantables;

import com.github.Cranite.MoreEnchantables.Enchantment.EnchantmentEvents;
import vg.civcraft.mc.civmodcore.ACivMod;

public class MoreEnchantablesPlugin extends ACivMod {
    public static final String pluginName = "MoreEnchantables";
    public static MoreEnchantablesPlugin instance;

    @Override
    protected String getPluginName() {
        return pluginName;
    }

    @Override
    public void onEnable() {
        // Start the plugin
        super.onEnable();
        instance = this;
        // Load the configuration
        loadConfig();
        // Only continue if enabled
        if (Configuration.isEnabled()) {
            getServer().getPluginManager().registerEvents(new EnchantmentEvents(), this);
        }
    }

    private void loadConfig() {
        saveConfig();
        reloadConfig();
        Configuration.loadConfig(getConfig());
    }

    public static void consoleLog(String message) {
        if (Configuration.allowsDebugLogging()) {
            System.out.println("[" + pluginName + "]: " + message);
        }
    }


}
