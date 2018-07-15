package com.github.cranite.MoreEnchantables.Enchantment;

import org.apache.commons.lang.Validate;
import org.bukkit.enchantments.Enchantment;

import java.io.Serializable;

public class ConstLeveledEnchantment implements Serializable {

    public final EnchantmentVerbose enchantment;
    public final int level;

    public ConstLeveledEnchantment(Enchantment enchantment, int level) {
        this(EnchantmentVerbose.fromEnchant(enchantment), level);
    }

    public ConstLeveledEnchantment(EnchantmentVerbose enchantment, int level) {
        Validate.notNull(enchantment, "Enchantment cannot be null.");
        this.enchantment = enchantment;
        this.level = level;
    }

    public ConstLeveledEnchantment(ConstEnchantmentOffer enchantmentOffer) {
        Validate.notNull(enchantmentOffer, "Enchantment offer cannot be null.");
        this.enchantment = enchantmentOffer.enchantment;
        this.level = enchantmentOffer.level;
    }

    public ConstEnchantmentOffer toEnchantmentOffer(int level) {
        return new ConstEnchantmentOffer(this, level);
    }

}
