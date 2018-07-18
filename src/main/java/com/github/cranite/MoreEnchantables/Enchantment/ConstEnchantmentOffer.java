package com.github.cranite.MoreEnchantables.Enchantment;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;

public class ConstEnchantmentOffer extends ConstLeveledEnchantment {

    public final int cost;

    public ConstEnchantmentOffer(Enchantment enchantment, int level, int cost) {
        super(enchantment, level);
        this.cost = cost;
    }

    public ConstEnchantmentOffer(EnchantmentVerbose enchantment, int level, int cost) {
        super(enchantment, level);
        this.cost = cost;
    }

    public ConstEnchantmentOffer(ConstLeveledEnchantment leveledEnchantment, int cost) {
        this(leveledEnchantment.enchantment, leveledEnchantment.level, cost);
    }

    public ConstEnchantmentOffer(EnchantmentOffer enchantmentOffer) {
        this(enchantmentOffer.getEnchantment(), enchantmentOffer.getEnchantmentLevel(), enchantmentOffer.getCost());
    }

    public ConstLeveledEnchantment getLeveledEnchantment() {
        return new ConstLeveledEnchantment(this);
    }

    public EnchantmentOffer toEnchantmentOffer() {
        return new EnchantmentOffer(this.enchantment.getEnchant(), this.level, this.cost);
    }

}
