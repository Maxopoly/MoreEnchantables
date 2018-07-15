package com.github.Cranite.MoreEnchantables.Enchantment;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MoreEnchantablesConfigEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final ConfigItemEnchantments configuration;

    public MoreEnchantablesConfigEvent(ConfigItemEnchantments configuration) {
        this.configuration = configuration;
    }

    public ConfigItemEnchantments getConfig() {
        return this.configuration;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
