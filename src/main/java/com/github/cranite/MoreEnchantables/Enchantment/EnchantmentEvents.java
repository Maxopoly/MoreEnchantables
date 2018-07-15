package com.github.cranite.MoreEnchantables.Enchantment;

import com.github.cranite.MoreEnchantables.Configuration;
import com.github.cranite.MoreEnchantables.MoreEnchantablesPlugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class EnchantmentEvents implements Listener {
    public EnchantmentEvents() {
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        // If the inventory has no item to enchant, skip
        Player player = (Player)event.getWhoClicked();
        Inventory inventory = null;
        ItemStack itemMoved = null;
        switch (event.getAction()) {
            case PLACE_ALL:
            case PLACE_ONE:
            case PLACE_SOME:
            case SWAP_WITH_CURSOR:
                inventory = event.getClickedInventory();
                itemMoved = event.getCursor();
                break;
            case MOVE_TO_OTHER_INVENTORY:
                inventory = event.getInventory();
                itemMoved = event.getCurrentItem();
                break;
            case HOTBAR_SWAP:
                inventory = event.getClickedInventory();
                itemMoved = player.getInventory().getItem(event.getHotbarButton());
                break;
            default:
                return;
        }
        if (itemMoved == null || inventory == null) {
            return;
        }
        // If not an enchantment table, skip
        if (!(inventory.getType() == InventoryType.ENCHANTING)) {
            return;
        }
        Block table = inventory.getLocation().getBlock();
        if (!(table.getType() == Material.ENCHANTMENT_TABLE)) {
            return;
        }
        // If player's table offers does not exist, create them
        PlayerEnchantmentOffers playerOffers;
        if (!PlayerEnchantmentOffers.has(player)) {
            playerOffers = new PlayerEnchantmentOffers(MoreEnchantablesPlugin.instance);
            PlayerEnchantmentOffers.set(player, playerOffers);
            MoreEnchantablesPlugin.consoleLog("Created offer storage for " + player.getName() + ".");
        }
        else {
            playerOffers = PlayerEnchantmentOffers.get(player);
        }
        if (playerOffers == null) {
            MoreEnchantablesPlugin.consoleLog(player.getName() + "'s offer storage was still not set, weird.");
            return;
        }
        // If item offers via bonus does not exist, create them
        int bonus = EnchantingTableUtility.getEnchantmentBonus(table);
        ItemEnchantmentOffers itemOffers;
        if (!playerOffers.hasItemOffersAtBonus(bonus)) {
            itemOffers = new ItemEnchantmentOffers();
            playerOffers.setItemOffersAtBonus(bonus, itemOffers);
            MoreEnchantablesPlugin.consoleLog("Created bonuses storage " + player.getName() + ".");
        }
        else {
            itemOffers = playerOffers.getItemOffersAtBonus(bonus);
        }
        if (itemOffers == null) {
            MoreEnchantablesPlugin.consoleLog(player.getName() + "'s bonuses storage was still not set, weird.");
            return;
        }
        // If tiered enchantment offers does not exist, create them
        Material itemMaterial = itemMoved.getType();
        if (!itemOffers.hasItemOffers(itemMaterial) || Configuration.doRandomiseEnchants()) {
            itemOffers.addItemOffers(itemMaterial, new TieredEnchantmentOffers(
                    EnchantmentUtility.makeOffers(itemMoved, 1, bonus),
                    EnchantmentUtility.makeOffers(itemMoved, 2, bonus),
                    EnchantmentUtility.makeOffers(itemMoved, 3, bonus)));
            MoreEnchantablesPlugin.consoleLog("Generated enchantment offers for " + player.getName() + ".");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareItemEnchant(PrepareItemEnchantEvent event) {
        // If the item is not present, do nothing
        ItemStack itemStack = event.getItem();
        Material itemMaterial = itemStack.getType();
        if (!Configuration.getEnchantments().hasEnchantmentsForItem(itemMaterial)) {
            return;
        }
        // If player's table offers does not exist, do nothing
        Player player = event.getEnchanter();
        PlayerEnchantmentOffers playerOffers = PlayerEnchantmentOffers.get(player);
        if (playerOffers == null) {
            return;
        }
        // If item offers do not exist, do nothing
        int bonus = EnchantingTableUtility.getEnchantmentBonus(event.getEnchantBlock());
        ItemEnchantmentOffers itemOffers = playerOffers.getItemOffersAtBonus(bonus);
        if (itemOffers == null) {
            return;
        }
        // If tiered offers do not exist, do nothing
        TieredEnchantmentOffers tieredOffers = itemOffers.getItemOffers(itemMaterial);
        if (tieredOffers == null) {
            return;
        }
        // Apply the enchantment offers to the table
        EnchantmentOffer[] rawTableOffers = event.getOffers();
        ConstEnchantmentOffer tier1Offer = tieredOffers.getPrimaryOfferFromTier(1);
        ConstEnchantmentOffer tier2Offer = tieredOffers.getPrimaryOfferFromTier(2);
        ConstEnchantmentOffer tier3Offer = tieredOffers.getPrimaryOfferFromTier(3);
        if (tier1Offer != null) {
            rawTableOffers[0] = tier1Offer.toEnchantmentOffer();
        }
        if (tier2Offer != null) {
            rawTableOffers[1] = tier2Offer.toEnchantmentOffer();
        }
        if (tier3Offer != null) {
            rawTableOffers[2] = tier3Offer.toEnchantmentOffer();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEnchantItem(EnchantItemEvent event) {
        // If the item is not present, do nothing
        ItemStack itemStack = event.getItem();
        Material itemMaterial = itemStack.getType();
        if (!Configuration.getEnchantments().hasEnchantmentsForItem(itemMaterial)) {
            return;
        }
        // If player's table offers does not exist, do nothing
        Player player = event.getEnchanter();
        PlayerEnchantmentOffers playerOffers = PlayerEnchantmentOffers.get(player);
        if (playerOffers == null) {
            return;
        }
        // If item offers do not exist, do nothing
        int bonus = EnchantingTableUtility.getEnchantmentBonus(event.getEnchantBlock());
        ItemEnchantmentOffers itemOffers = playerOffers.getItemOffersAtBonus(bonus);
        if (itemOffers == null) {
            return;
        }
        // If tiered offers do not exist, do nothing
        TieredEnchantmentOffers tieredOffers = itemOffers.getItemOffers(itemMaterial);
        if (tieredOffers == null) {
            return;
        }
        // Give enchantment offers to the enchanting table
        // which shall apply those enchantments to the item
        int tier = event.whichButton() + 1;
        ConstEnchantmentOffer[] enchantmentOffers = tieredOffers.getOffersFromTier(tier);
        Map<Enchantment, Integer> enchantmentsToAdd = event.getEnchantsToAdd();
        enchantmentsToAdd.clear();
        for (ConstEnchantmentOffer currentOffer : enchantmentOffers) {
            enchantmentsToAdd.put(currentOffer.enchantment.getEnchant(), currentOffer.level);
        }
        PlayerEnchantmentOffers.remove(player, MoreEnchantablesPlugin.instance);
    }

}
