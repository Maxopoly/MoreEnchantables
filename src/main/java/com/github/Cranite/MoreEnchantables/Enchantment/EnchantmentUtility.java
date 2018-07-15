package com.github.Cranite.MoreEnchantables.Enchantment;

import com.github.Cranite.MoreEnchantables.ItemUtility;
import com.github.Cranite.MoreEnchantables.Misc.IntRange;
import com.github.Cranite.MoreEnchantables.Misc.Random;
import org.apache.commons.lang3.Validate;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentUtility {

    // Removes any enchantment in object that conflict with any within subject
    public static void removeConflictingEnchantments(List<EnchantmentVerbose> object, List<EnchantmentVerbose> subject) {
        Validate.notNull(object, "Object cannot be null");
        Validate.notNull(subject, "Subject cannot be null");
        for (EnchantmentVerbose objectEnchantment : object.toArray(new EnchantmentVerbose[0])) {
            for (EnchantmentVerbose subjectEnchantment : subject.toArray(new EnchantmentVerbose[0])) {
                if (objectEnchantment.conflictsWith(subjectEnchantment)) {
                    object.remove(objectEnchantment);
                }
            }
        }
    }

    // Removes any enchantment in object that are also present within subject
    public static void removeDuplicateEnchantments(List<EnchantmentVerbose> object, List<EnchantmentVerbose> subject) {
        Validate.notNull(object, "Object cannot be null");
        Validate.notNull(subject, "Subject cannot be null");
        for (EnchantmentVerbose objectEnchantment : object.toArray(new EnchantmentVerbose[0])) {
            if (subject.contains(objectEnchantment)) {
                object.remove(objectEnchantment);
            }
        }
    }

    // Calculate the enchantment cost of an enchantment based on the chosen
    // enchantment tier, and a bonus, which represents the amount of bookshelves
    // that surround the enchanting table.
    public static int calculateEnchantmentLevelCost(int tier, int bonus) {
        bonus = bonus < 0 ? 0 : bonus > 15 ? 15 : bonus;
        int baseLevel = bonus / 2;
        baseLevel += Random.randomIntRange(0, bonus);
        baseLevel += Random.randomIntRange(0, 8);
        switch (tier) {
            case 1:
                return Math.max(baseLevel / 3, 1);
            case 2:
                return (baseLevel * 2) / 3 + 1;
            case 3:
                return Math.max(baseLevel, bonus * 2);
            default:
                return 0;
        }
    }

    // Calculates a modified enchantment level based on an item's enchantability
    // and an the total level cost of that enchantment tier and bonus. Black magic.
    // Based on this pseudo-code: https://minecraft.gamepedia.com/Tutorials/Enchantment_mechanics
    public static int calculateModifiedEnchantmentLevel(int enchantability, int levelCost) {
        Integer modifiedLevel = levelCost;
        modifiedLevel += Random.randomIntRange(0, enchantability / 4);
        modifiedLevel += Random.randomIntRange(0, enchantability / 4);
        Float randomBonus = 1 + (Random.randomFloat() + Random.randomFloat() - 1) * 0.15f;
        modifiedLevel = (int) (modifiedLevel * randomBonus + 0.5f);
        if (modifiedLevel < 1) {
            modifiedLevel = 1;
        }
        return modifiedLevel;
    }

    // Generate a level/power for an enchantment based on a given modified level
    // Higher modifiedLevel values determine, for example, Power III over Power II.
    public static ConstLeveledEnchantment getEnchantmentLevelOffer(EnchantmentVerbose enchantment, int modifiedLevel) {
        Validate.notNull(enchantment, "Enchantment cannot be null.");
        IntRange[] levels = enchantment.getLevels();
        for (int i = levels.length; --i >= 0;) {
            if (levels[i].inRange(modifiedLevel)) {
                return new ConstLeveledEnchantment(enchantment, i + 1);
            }
        }
        return null;
    }

    // Return an random enchantment with a level determined by the provided modified level
    public static ConstLeveledEnchantment getWeightedRandomEnchantment(List<EnchantmentVerbose> enchantments, int modifiedLevel) {
        int totalWeight = 0;
        modifiedLevel = modifiedLevel < 1 ? 1 : modifiedLevel;
        // Loop through available enchantments and get a level for them based on
        // the given modified level. Also track how much weight in total gathered.
        List<ConstLeveledEnchantment> possibleEnchantments = new ArrayList<>();
        for (EnchantmentVerbose enchantment : enchantments) {
            ConstLeveledEnchantment offer = getEnchantmentLevelOffer(enchantment, modifiedLevel);
            if (offer != null) {
                possibleEnchantments.add(offer);
                totalWeight += enchantment.getWeight();
            }
        }
        //
        int currentWeight = Random.randomIntRange(0, totalWeight);
        for (ConstLeveledEnchantment enchantment : possibleEnchantments) {
            Integer enchantmentWeight = enchantment.enchantment.getWeight();
            if (currentWeight <= enchantmentWeight) {
                return enchantment;
            }
            currentWeight -= enchantmentWeight;
        }
        return null;
    }

    // Identical to getWeightedRandomEnchantment(enchantments, modifiedLevel) except that when
    // ensureAnEnchant is true, it will continue attempting to get an enchantment by checking
    // for enchantments with artificially higher and lower levels, preferring higher
    public static ConstLeveledEnchantment getWeightedRandomEnchantment(List<EnchantmentVerbose> enchantments, int modifiedLevel, boolean ensureAnEnchant) {
        int modifiedLevelOffset = 0;
        if (ensureAnEnchant && enchantments.size() > 0) {
            ConstLeveledEnchantment aboveOffer = getWeightedRandomEnchantment(enchantments, modifiedLevel);
            ConstLeveledEnchantment belowOffer = null;
            while (aboveOffer == null && belowOffer == null) {
                aboveOffer = getWeightedRandomEnchantment(enchantments, modifiedLevel + modifiedLevelOffset);
                belowOffer = getWeightedRandomEnchantment(enchantments, modifiedLevel - modifiedLevelOffset);
                modifiedLevelOffset++;
            }
            return aboveOffer != null ? aboveOffer : belowOffer;
        } else {
            return getWeightedRandomEnchantment(enchantments, modifiedLevel);
        }
    }

    public static List<ConstLeveledEnchantment> getEnchantmentOffers(ItemStack item, Integer modifiedLevel) {
        List<ConstLeveledEnchantment> generatedOffers = new ArrayList<>();
        // Get the list of available enchants for this kind of item,
        // or return empty if that list does not exist.
        List<EnchantmentVerbose> availableEnchants = ItemUtility.getAvailableEnchantments(item);
        if (availableEnchants.size() <= 0) {
            return generatedOffers;
        }
        // Get a list of enchantments already on this item, then loop
        // through any that are present within available enchantments
        List<EnchantmentVerbose> currentItemEnchantments = ItemUtility.getEnchantmentsOnItem(item);
        removeDuplicateEnchantments(availableEnchants, currentItemEnchantments);
        removeConflictingEnchantments(availableEnchants, currentItemEnchantments);
        Boolean primaryOfferMade = false;
        do {
            ConstLeveledEnchantment offer = getWeightedRandomEnchantment(availableEnchants, modifiedLevel, !primaryOfferMade);
            primaryOfferMade = true;
            if (offer != null) {
                availableEnchants.remove(offer.enchantment);
                generatedOffers.add(offer);
                modifiedLevel /= 2;
            } else break;
        }
        while (Random.executeProbability(modifiedLevel + 1, 50) && availableEnchants.size() > 0);
        return generatedOffers;
    }

    // Offers an enchantment to the player based off of the item, the enchantment tier,
    // and the amount of bookshelves that surround the enchanting table. An offer is not
    // guaranteed. Use makePrimaryOffer(item, tier, bonus) for more guarantee.
    public static ConstEnchantmentOffer[] makeOffers(ItemStack item, Integer tier, Integer bonus) {
        Integer levelCost = calculateEnchantmentLevelCost(tier, bonus);
        Integer enchantability = ItemUtility.getEnchantability(item);
        Integer modifiedLevel = calculateModifiedEnchantmentLevel(enchantability, levelCost);
        // Generate the offers and attach a level cost to them
        List<ConstEnchantmentOffer> offers = new ArrayList<>();
        for (ConstLeveledEnchantment offer : getEnchantmentOffers(item, modifiedLevel)) {
            offers.add(offer.toEnchantmentOffer(levelCost));
        }
        return offers.toArray(new ConstEnchantmentOffer[0]);
    }

}
