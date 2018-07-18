package com.github.cranite.MoreEnchantables.Enchantment;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;

import java.util.HashMap;

public class ItemEnchantmentOffers extends HashMap<Material, TieredEnchantmentOffers> {

    public Boolean hasItemOffers(Material itemMaterial) {
        return containsKey(itemMaterial);
    }

    public TieredEnchantmentOffers getItemOffers(Material itemMaterial) {
        return hasItemOffers(itemMaterial) ? get(itemMaterial) : null;
    }

    public void addItemOffers(Material itemMaterial, TieredEnchantmentOffers enchantmentOffers) {
        Validate.notNull(itemMaterial, "Item cannot be null.");
        Validate.notNull(enchantmentOffers, "Enchantment offers cannot be null.");
        put(itemMaterial, enchantmentOffers);
    }

    public void removeItem(Material itemMaterial) {
        Validate.notNull(itemMaterial, "Item cannot be null.");
        put(itemMaterial, null);
        remove(itemMaterial);
    }

}
