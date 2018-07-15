package com.github.Cranite.MoreEnchantables.Enchantment;

import com.github.Cranite.MoreEnchantables.MoreEnchantablesPlugin;
import org.apache.commons.lang3.Validate;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.Plugin;

import java.io.Serializable;

public class PlayerEnchantmentOffers extends MetadataValueAdapter implements Serializable {

    private static final String metadataKey = MoreEnchantablesPlugin.pluginName + "Offers";
    private final ItemEnchantmentOffers[] offersByBonus = new ItemEnchantmentOffers[16];

    public PlayerEnchantmentOffers(Plugin owningPlugin) {
        super(owningPlugin);
    }

    public boolean hasItemOffersAtBonus(int bonus) {
        return offersByBonus[bonus < 0 ? 0 : bonus > 15 ? 15 : bonus] != null;
    }

    public ItemEnchantmentOffers getItemOffersAtBonus(int bonus) {
        return offersByBonus[bonus < 0 ? 0 : bonus > 15 ? 15 : bonus];
    }

    public void setItemOffersAtBonus(int bonus, ItemEnchantmentOffers offers) {
        offersByBonus[bonus < 0 ? 0 : bonus > 15 ? 15 : bonus] = offers;
    }

    public void flushItemOffers() {
        for (int i = 0; i < offersByBonus.length; i++) {
            offersByBonus[i] = null;
        }
    }

    @Override
    public Object value() {
        return this;
    }

    @Override
    public void invalidate() {
        flushItemOffers();
    }

    public static boolean has(Player player) {
        Validate.notNull(player, "Player cannot be null.");
        return player.hasMetadata(metadataKey);
    }

    public static PlayerEnchantmentOffers get(Player player) {
        Validate.notNull(player, "Player cannot be null.");
        if (player.hasMetadata(metadataKey)) {
            for (MetadataValue value : player.getMetadata(metadataKey)) {
                if (value != null) {
                    if (value.getOwningPlugin() == MoreEnchantablesPlugin.instance) {
                        return (PlayerEnchantmentOffers)value;
                    }
                }
            }
        }
        return null;
    }

    public static void set(Player player, PlayerEnchantmentOffers offers) {
        Validate.notNull(player, "Player cannot be null.");
        Validate.notNull(player, "Offers cannot be null");
        player.setMetadata(metadataKey, offers);
    }

    public static void remove(Player player, Plugin plugin) {
        Validate.notNull(player, "Player cannot be null.");
        Validate.notNull(plugin, "Plugin cannot be null.");
        player.removeMetadata(metadataKey, plugin);
    }

}