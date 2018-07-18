# MoreEnchantables

Allows admins to decide which items can be enchanted with what enchantments, at an enchanting table.

Uuuuuuuh... *why?* I hear you cry. Well, the Minecraft server that I currently play on has disabled enchanting books, deeming them too overpowered. While you may still find various enchanted books through exploration, it has by extension neutered any possibility for items that can only be enchanted via anvil combinations, such as shears, hoes, and shields. It's not that I think that making this plugin would revive an otherwise supressed booming market for enchanted hoes, it's more that if someone wants to spend 30 levels enchanting a hoe... why shouldn't they?

## The config

The configuration file, located within `[server-folder]/plugins/MoreEnchantables/config.yml`, is where you can change the behaviour of the plugin.

Setting the following to false will disable the plugin on the next restart without needing to remove the plugin completely.
```yaml
enabled: true
```

Allowing the plugin to output debug information is useful for admins if any issues with the plugin occurs, although if you wish to reduce spam, set this to false.
```yaml
allowDebugLogging: true
```

Disabling randomise enchants means that previous offers for that item type and amount of surrounding bookshelves are kept until the player enchants an item, or a server restart.
```yaml
randomiseEnchantments: true
```

Below is an example of how to format giving items various enchantments. Please note that this is an exclusive list. Items that are never specified within the config will not be handled, and enchantments not specified will not be recommended. If you only list Unbreaking (DURABILITY) under enchants, then only Unbreaking will be offered when you want to enchant any of the relevant items, which as defined below, is all the different kinds of hoe. You may specify any arbitrary item by specifying its material, you could allow the enchantment of cobblestone if you wish. Could allow for the biggest meme currency of Fortune 3 diamonds?
```yaml
makeEnchantable:
  - items:
      - WOOD_HOE
      - STONE_HOE
      - IRON_HOE
      - GOLD_HOE
      - DIAMOND_HOE
    enchants:
      - DURABILITY
```