package com.github.Cranite.MoreEnchantables.Enchantment;

import org.apache.commons.lang3.Validate;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigItemEnchantments extends HashMap<Material, ArrayList<EnchantmentVerbose>> {

    private boolean isLocked = false;

    /**
     * Locks this instances, preventing modification.
     * */
    public void lock() {
        isLocked = true;
    }

    public boolean isLocked() {
        return isLocked;
    }

    /**
     * A more appropriately named version of containsKey()
     *
     * @param itemMaterial The type/material of the item
     * @return boolean
     * */
    public boolean hasEnchantmentsForItem(Material itemMaterial) {
        return containsKey(itemMaterial);
    }

    /**
     * Returns any and all enchantments listed for a
     * particular item material. Will never return null.
     * Modifications to the returned list will only be
     * reflected if this instance of ConfigItemEnchantments
     * remains unlocked.
     *
     * @param itemMaterial The type/material of the item.
     * @return List Returns a instance of ArrayList.
     * */
    public List<EnchantmentVerbose> getEnchantmentsForItem(Material itemMaterial) {
        ArrayList<EnchantmentVerbose> enchantments = null;
        if (hasEnchantmentsForItem(itemMaterial)) {
            enchantments = get(itemMaterial);
        }
        if (enchantments == null) {
            enchantments = new ArrayList<>();
            put(itemMaterial, enchantments);
            return enchantments;
        }
        else if (isLocked) {
            return new ArrayList<>(enchantments);
        }
        else {
            return enchantments;
        }
    }

    /**
     * Checks whether an item can receive a particular
     * enchantment offer from an enchanting table. If
     * this returns false, that does not mean that this
     * plugin will prevent that enchantment from being
     * applied to that item type, but rather that this
     * plugin will not offer that item that enchantment
     * at an enchanting table.
     *
     * @param itemMaterial The type/material of the item.
     * @param enchant The enchantment to check against.
     * @return boolean
     * */
    public boolean doesItemAllowEnchant(Material itemMaterial, EnchantmentVerbose enchant) {
        return getEnchantmentsForItem(itemMaterial).contains(enchant);
    }

    /**
     * Will add an enchantment to the list of enchantments
     * that an item will be offered by this plugin at an
     * enchanting table. Will error if this instance of
     * ConfigItemEnchantments is locked.
     *
     * @param itemMaterial The type/material of the item.
     * @param enchant The enchantment to check against.
     * */
    public void addEnchantmentForItem(Material itemMaterial, EnchantmentVerbose enchant) {
        Validate.isTrue(!isLocked, "Cannot add when config is locked");
        Validate.notNull(itemMaterial, "Item cannot be null.");
        Validate.notNull(enchant, "Enchantment cannot be null.");
        getEnchantmentsForItem(itemMaterial).add(enchant);
    }

    /**
     * Will remove a particular enchantment from the list
     * of enchantments that an item will be offered by this
     * plugin at an enchanting table. Will error if this
     * instance of ConfigItemEnchantments is locked.
     *
     * @param itemMaterial The type/material of the item.
     * @param enchant The enchantment to check against.
     * */
    public void removeEnchantmentForItem(Material itemMaterial, EnchantmentVerbose enchant) {
        Validate.isTrue(!isLocked, "Cannot add when config is locked");
        Validate.notNull(itemMaterial, "Item cannot be null.");
        Validate.notNull(enchant, "Enchantment cannot be null.");
        getEnchantmentsForItem(itemMaterial).remove(enchant);
    }

    /**
     * Will remove all enchantments from the list of
     * enchantments that an item will be offered by this
     * plugin at an enchanting table. Will error if this
     * instance of ConfigItemEnchantments is locked.
     *
     * @param itemMaterial The type/material of the item.
     * */
    public void flushEnchantmentsForItem(Material itemMaterial) {
        Validate.isTrue(!isLocked, "Cannot add when config is locked");
        Validate.notNull(itemMaterial, "Item cannot be null.");
        put(itemMaterial, null);
        remove(itemMaterial);
    }

}
