package com.github.cranite.MoreEnchantables;

import com.github.cranite.MoreEnchantables.Enchantment.EnchantmentVerbose;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ItemUtility {

    // Returns the level of enchantability that a particular item has
    // higher enchantability scores improve chances of high level enchants
    public static int getEnchantability(ItemStack itemStack) {
        Validate.notNull(itemStack, "Item cannot be null.");
        return getEnchantability(itemStack.getType());
    }
    public static int getEnchantability(Material itemMaterial) {
        switch (itemMaterial) {
            // Tools
            case WOOD_SWORD:
            case WOOD_PICKAXE:
            case WOOD_AXE:
            case WOOD_SPADE:
            case WOOD_HOE:
                return 15;
            case STONE_SWORD:
            case STONE_PICKAXE:
            case STONE_AXE:
            case STONE_SPADE:
            case STONE_HOE:
                return 5;
            case IRON_SWORD:
            case IRON_PICKAXE:
            case IRON_AXE:
            case IRON_SPADE:
            case IRON_HOE:
                return 14;
            case GOLD_SWORD:
            case GOLD_PICKAXE:
            case GOLD_AXE:
            case GOLD_SPADE:
            case GOLD_HOE:
                return 22;
            case DIAMOND_SWORD:
            case DIAMOND_PICKAXE:
            case DIAMOND_AXE:
            case DIAMOND_SPADE:
            case DIAMOND_HOE:
                return 10;
            // Armour
            case LEATHER_HELMET:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
                return 15;
            case CHAINMAIL_HELMET:
            case CHAINMAIL_CHESTPLATE:
            case CHAINMAIL_LEGGINGS:
            case CHAINMAIL_BOOTS:
                return 12;
            case IRON_HELMET:
            case IRON_CHESTPLATE:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
                return 9;
            case GOLD_HELMET:
            case GOLD_CHESTPLATE:
            case GOLD_LEGGINGS:
            case GOLD_BOOTS:
                return 25;
            case DIAMOND_HELMET:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_LEGGINGS:
            case DIAMOND_BOOTS:
                return 10;
        }
        return 1;
    }

    // Returns all available enchantments for that particular item based on
    // the plugin's configuration. Modifications to this list will not be
    // reflected within the plugin's configuration.
    public static ArrayList<EnchantmentVerbose> getAvailableEnchantments(ItemStack itemStack) {
        Validate.notNull(itemStack, "Item cannot be null.");
        return getAvailableEnchantments(itemStack.getType());
    }
    public static ArrayList<EnchantmentVerbose> getAvailableEnchantments(Material itemMaterial) {
        return new ArrayList<>(Configuration.getEnchantments().getEnchantmentsForItem(itemMaterial));
    }

    // Returns a list of current enchantments on the item. Modifications to
    // this list or the enchantments in it will not be reflected on the item.
    public static ArrayList<EnchantmentVerbose> getEnchantmentsOnItem(ItemStack itemStack) {
        Validate.notNull(itemStack, "Item cannot be null.");
        return itemStack.getEnchantments().keySet().stream()
            .map(enchant -> EnchantmentVerbose.fromEnchant(enchant))
            .filter(enchant -> enchant != null)
            .collect(Collectors.toCollection(ArrayList::new));
    }



}
