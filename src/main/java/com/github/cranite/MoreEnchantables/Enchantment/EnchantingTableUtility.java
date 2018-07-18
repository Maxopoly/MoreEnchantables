package com.github.cranite.MoreEnchantables.Enchantment;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class EnchantingTableUtility {

    // Gets the exact amount of lapis in an item stack
    public static int getFuelAmount(ItemStack itemStack) {
        if (itemStack != null && itemStack.getType() == Material.INK_SACK && itemStack.getDurability() == 4) {
            return itemStack.getAmount();
        }
        return 0;
    }

    // Gets the amount of surrounding bookshelves
    public static int getEnchantmentBonus(Block table) {
        int bonus = 0;
        if (table != null) {
            for (int z = -1; z <= 1; z++) {
                for (int x = -1; x <= 1; x++) {
                    if ((x != 0 || z != 0) && table.getRelative(x, 0, z).isEmpty() && table.getRelative(x, 1, z).isEmpty()) {
                        if (table.getRelative(x * 2, 0, z * 2).getType() == Material.BOOKSHELF) {
                            bonus++;
                        }
                        if (table.getRelative(x * 2, 1, z * 2).getType() == Material.BOOKSHELF) {
                            bonus++;
                        }
                        if (x != 0 && z != 0) {
                            if (table.getRelative(x * 2, 0, z).getType() == Material.BOOKSHELF) {
                                bonus++;
                            }
                            if (table.getRelative(x * 2, 1, z).getType() == Material.BOOKSHELF) {
                                bonus++;
                            }
                            if (table.getRelative(x, 0, z * 2).getType() == Material.BOOKSHELF) {
                                bonus++;
                            }
                            if (table.getRelative(x, 1, z * 2).getType() == Material.BOOKSHELF) {
                                bonus++;
                            }
                        }
                    }
                }
            }
        }
        return bonus;
    }

}
