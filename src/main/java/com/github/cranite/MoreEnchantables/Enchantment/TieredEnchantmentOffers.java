package com.github.cranite.MoreEnchantables.Enchantment;

public class TieredEnchantmentOffers {

    private final ConstEnchantmentOffer[][] tieredOffers = new ConstEnchantmentOffer[3][];

    public TieredEnchantmentOffers(ConstEnchantmentOffer[] t1, ConstEnchantmentOffer[] t2, ConstEnchantmentOffer[] t3) {
        this.tieredOffers[0] = t1 == null ? new ConstEnchantmentOffer[0] : t1;
        this.tieredOffers[1] = t2 == null ? new ConstEnchantmentOffer[0] : t2;
        this.tieredOffers[2] = t3 == null ? new ConstEnchantmentOffer[0] : t3;
    }

    public ConstEnchantmentOffer[] getOffersFromTier(Integer tier) {
        return --tier >= 0 && tier <= 2 ? tieredOffers[tier].clone() : new ConstEnchantmentOffer[0];
    }

    public ConstEnchantmentOffer getPrimaryOfferFromTier(Integer tier) {
        ConstEnchantmentOffer[] offers = getOffersFromTier(tier);
        return offers.length > 0 ? offers[0] : null;
    }

}
