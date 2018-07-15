package com.github.cranite.MoreEnchantables;

import com.github.cranite.MoreEnchantables.Enchantment.ConfigItemEnchantments;
import com.github.cranite.MoreEnchantables.Enchantment.EnchantmentVerbose;
import com.github.cranite.MoreEnchantables.Enchantment.MoreEnchantablesConfigEvent;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Configuration {

    private static boolean enabled;
    private static boolean allowDebugLogging;
    private static boolean randomiseEnchantments;
    private static boolean allowExternalConfigurations;
    private static ConfigItemEnchantments enchantments;

    public static boolean isEnabled() {
        return enabled;
    }

    public static boolean allowsDebugLogging() {
        return allowDebugLogging;
    }

    public static boolean allowsExternalConfigurations() {
        return allowExternalConfigurations;
    }

    public static boolean doRandomiseEnchants() {
        return randomiseEnchantments;
    }

    public static ConfigItemEnchantments getEnchantments() {
        return enchantments;
    }

    public static void loadConfig(FileConfiguration config) {
        // Parse the config
        enabled = config.getBoolean("enabled");
        allowDebugLogging = config.getBoolean("allowDebugLogging");
        randomiseEnchantments = config.getBoolean("randomiseEnchantments");
        allowExternalConfigurations = config.getBoolean("allowExternalConfigurations");
        enchantments = new ConfigItemEnchantments();
        for (Map<?, ?> setup : config.getMapList("makeEnchantable")) {
            // Ensure this instance is properly configured
            if (!setup.containsKey("items") || !setup.containsKey("enchants")) {
                continue;
            }
            // Get the item materials in this instance
            List<Material> setupMaterials = new ArrayList<>();
            try {
                for (String materialName : (List<String>) setup.get("items")) {
                    Material material = Material.getMaterial(materialName);
                    if (material != null) {
                        setupMaterials.add(material);
                    }
                }
            } catch (Exception error) {
                MoreEnchantablesPlugin.consoleLog("Could not complete materials extraction: " + error.getMessage());
                continue;
            }
            // Get the item enchantments in this instance
            List<EnchantmentVerbose> setupEnchantments = new ArrayList<>();
            try {
                for (String enchantName : (List<String>) setup.get("enchants")) {
                    Enchantment enchant = Enchantment.getByName(enchantName);
                    EnchantmentVerbose verbose = EnchantmentVerbose.fromEnchant(enchant);
                    if (verbose != null) {
                        setupEnchantments.add(verbose);
                    }
                }
            } catch (Exception error) {
                MoreEnchantablesPlugin.consoleLog("Could not complete enchantment extraction: " + error.getMessage());
                continue;
            }
            // Apply the materials and their enchantments
            for (Material itemMaterial : setupMaterials) {
                for (EnchantmentVerbose itemEnchantment : setupEnchantments) {
                    enchantments.addEnchantmentForItem(itemMaterial, itemEnchantment);
                }
            }
        }
        // Allow other plugins to modify the configuration
        if (allowExternalConfigurations) {
            MoreEnchantablesConfigEvent event = new MoreEnchantablesConfigEvent(enchantments);
            MoreEnchantablesPlugin.instance.getServer().getPluginManager().callEvent(event);
        }
        enchantments.lock();
    }


}
