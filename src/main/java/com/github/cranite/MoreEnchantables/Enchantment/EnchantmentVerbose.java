package com.github.cranite.MoreEnchantables.Enchantment;

import com.github.cranite.MoreEnchantables.Configuration;
import com.github.cranite.MoreEnchantables.Misc.IntRange;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum EnchantmentVerbose {

    ARROW_DAMAGE(
        Enchantment.ARROW_DAMAGE,
        "Power",
        10,
        new IntRange(1, 16),
        new IntRange(11, 26),
        new IntRange(21, 36),
        new IntRange(31, 46),
        new IntRange(41, 56)),
    ARROW_FIRE(
        Enchantment.ARROW_FIRE,
        "Flame",
        2,
        new IntRange(20, 50)),
    ARROW_INFINITE(
        Enchantment.ARROW_INFINITE,
        "Infinity",
        1,
        new IntRange(20, 50)),
    ARROW_KNOCKBACK(
        Enchantment.ARROW_KNOCKBACK,
        "Punch",
        2,
        new IntRange(12, 37),
        new IntRange(32, 57)),
    BINDING_CURSE(
        Enchantment.BINDING_CURSE,
        "Curse of Binding",
        1,
        new IntRange(25, 50)),
    DAMAGE_ALL(
        Enchantment.DAMAGE_ALL,
        "Sharpness",
        10,
        new IntRange(1, 21),
        new IntRange(12, 32),
        new IntRange(23, 43),
        new IntRange(34, 54),
        new IntRange(45, 65)),
    DAMAGE_ARTHROPODS(
        Enchantment.DAMAGE_ARTHROPODS,
        "Bane of Arthropods",
        5,
        new IntRange(5, 25),
        new IntRange(13, 33),
        new IntRange(21, 41),
        new IntRange(29, 49),
        new IntRange(37, 57)),
    DAMAGE_UNDEAD(
        Enchantment.DAMAGE_UNDEAD,
        "Smite",
        5,
        new IntRange(5, 25),
        new IntRange(13, 33),
        new IntRange(21, 41),
        new IntRange(29, 49),
        new IntRange(37, 57)),
    DEPTH_STRIDER(
        Enchantment.DEPTH_STRIDER,
        "Depth Strider",
        2,
        new IntRange(10, 25),
        new IntRange(20, 35),
        new IntRange(30, 45)),
    DIG_SPEED(
        Enchantment.DIG_SPEED,
        "Efficiency",
        10,
        new IntRange(1, 51),
        new IntRange(11, 61),
        new IntRange(21, 71),
        new IntRange(31, 81),
        new IntRange(41, 91)),
    DURABILITY(
        Enchantment.DURABILITY,
        "Unbreaking",
        5,
        new IntRange(5, 55),
        new IntRange(13, 63),
        new IntRange(21, 71)),
    FIRE_ASPECT(
        Enchantment.FIRE_ASPECT,
        "Fire Aspect",
        2,
        new IntRange(10, 60),
        new IntRange(30, 80)),
    FROST_WALKER(
        Enchantment.FROST_WALKER,
        "Frost Walker",
        2,
        new IntRange(10, 25),
        new IntRange(20, 35)),
    KNOCKBACK(
        Enchantment.KNOCKBACK,
        "Knockback",
        5,
        new IntRange(5, 55),
        new IntRange(25, 75)),
    LOOT_BONUS_BLOCKS(
        Enchantment.LOOT_BONUS_BLOCKS,
        "Fortune",
        2,
        new IntRange(15, 65),
        new IntRange(24, 74),
        new IntRange(33, 83)),
    LOOT_BONUS_MOBS(
        Enchantment.LOOT_BONUS_MOBS,
        "Looting",
        2,
        new IntRange(15, 65),
        new IntRange(24, 74),
        new IntRange(33, 83)),
    LUCK(
        Enchantment.LUCK,
        "Luck of the Sea",
        2,
        new IntRange(15, 65),
        new IntRange(24, 74),
        new IntRange(33, 83)),
    LURE(
        Enchantment.LURE,
        "Lure",
        2,
        new IntRange(15, 65),
        new IntRange(24, 74),
        new IntRange(33, 83)),
    MENDING(
        Enchantment.MENDING,
        "Mending",
        2,
        new IntRange(25, 75)),
    OXYGEN(
        Enchantment.OXYGEN,
        "Respiration",
        2,
        new IntRange(10, 40),
        new IntRange(20, 50),
        new IntRange(30, 60)),
    PROTECTION_ENVIRONMENTAL(
        Enchantment.PROTECTION_ENVIRONMENTAL,
        "Protection",
        10,
        new IntRange(1, 21),
        new IntRange(12, 32),
        new IntRange(23, 43),
        new IntRange(34, 54)),
    PROTECTION_EXPLOSIONS(
        Enchantment.PROTECTION_EXPLOSIONS,
        "Blast Protection",
        2,
        new IntRange(5, 17),
        new IntRange(13, 25),
        new IntRange(21, 33),
        new IntRange(29, 41)),
    PROTECTION_FALL(
        Enchantment.PROTECTION_FALL,
        "Feather Falling",
        5,
        new IntRange(5, 15),
        new IntRange(11, 21),
        new IntRange(17, 27),
        new IntRange(23, 33)),
    PROTECTION_FIRE(
        Enchantment.PROTECTION_FIRE,
        "Fire Protection",
        5,
        new IntRange(10, 22),
        new IntRange(18, 30),
        new IntRange(26, 38),
        new IntRange(34, 46)),
    PROTECTION_PROJECTILE(
        Enchantment.PROTECTION_PROJECTILE,
        "Projectile Protection",
        5,
        new IntRange(3, 18),
        new IntRange(9, 24),
        new IntRange(15, 30),
        new IntRange(21, 36)),
    SILK_TOUCH(
        Enchantment.SILK_TOUCH,
        "Silk Touch",
        1,
        new IntRange(15, 65)),
    SWEEPING_EDGE(
        Enchantment.SWEEPING_EDGE,
        "Sweeping Edge",
        2,
        new IntRange(5, 20),
        new IntRange(14, 29),
        new IntRange(23, 38)),
    THORNS(
        Enchantment.THORNS,
        "Thorns",
        1,
        new IntRange(20, 50)),
    VANISHING_CURSE(
        Enchantment.VANISHING_CURSE,
        "Curse of Vanishing",
        1,
        new IntRange(10, 60),
        new IntRange(30, 80),
        new IntRange(50, 100)),
    WATER_WORKER(
        Enchantment.WATER_WORKER,
        "Aqua Affinity",
        2,
        new IntRange(1, 41));

    private final Enchantment enchant;
    private final String name;
    private final Integer weight;
    private final IntRange[] levels;

    EnchantmentVerbose(Enchantment enchant, String name, Integer weight, IntRange... levels) {
        this.enchant = enchant;
        this.name = name;
        this.weight = weight;
        this.levels = levels;
    }

    public String getName() {
        return this.name;
    }

    public String getSlug() {
        return this.enchant.getName();
    }

    public Enchantment getEnchant() {
        return this.enchant;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public IntRange[] getLevels() {
        return this.levels.clone();
    }

    public Integer getMaxLevel() {
        return this.levels.length;
    }

    public Boolean canEnchantItem(ItemStack item) {
        return this.enchant.canEnchantItem(item) || Configuration.getEnchantments().doesItemAllowEnchant(item.getType(), this);
    }

    public Boolean conflictsWith(EnchantmentVerbose enchant) {
        return conflictsWith(enchant.enchant);
    }

    public Boolean conflictsWith(Enchantment enchant) {
        return this.enchant.conflictsWith(enchant);
    }

    public Boolean isTreasure() {
        return this.enchant.isTreasure();
    }

    public Boolean isCursed() {
        return this.enchant.isCursed();
    }

    public static EnchantmentVerbose fromEnchant(Enchantment enchant) {
        if (enchant != null) {
            for (EnchantmentVerbose instance : values()) {
                if (instance.enchant.equals(enchant)) {
                    return instance;
                }
            }
        }
        return null;
    }

    public static List<EnchantmentVerbose> fromEnchants(List<Enchantment> enchants) {
        List<EnchantmentVerbose> verboseEnchantments = new ArrayList<>();
        for (Enchantment enchant : enchants) {
            EnchantmentVerbose verboseEnchant = fromEnchant(enchant);
            if (verboseEnchant != null) {
                verboseEnchantments.add(verboseEnchant);
            }
        }
        return verboseEnchantments;
    }

    public static List<Enchantment> toEnchants(List<EnchantmentVerbose> verboseEnchantments) {
        List<Enchantment> enchantments = new ArrayList<>();
        for (EnchantmentVerbose verboseEnchantment : verboseEnchantments) {
            enchantments.add(verboseEnchantment.enchant);
        }
        return enchantments;
    }

    @Override
    @SuppressWarnings("deprecation")
    public String toString() {
        return "EnchantmentVerbose[" + this.enchant.getId() + ", " + this.enchant.toString() + "]";
    }

}
