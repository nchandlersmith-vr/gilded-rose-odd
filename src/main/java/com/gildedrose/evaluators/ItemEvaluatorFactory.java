package com.gildedrose.evaluators;

import com.gildedrose.Item;
import com.odd.Logger;
import com.odd.OddLogger;

public class ItemEvaluatorFactory {
    Logger logger = new OddLogger();
    private static final String SULFURUS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public ItemEvaluator create(Item item) {
        String logLocation = "ItemEvaluatorFactory.create";
        if (item.name.equals(AGED_BRIE)) {
            logger.info(logLocation,"found " + AGED_BRIE, item);
            return new AgedBrieEvaluator();
        }
        if (item.name.equals(BACKSTAGE_PASS)) {
            logger.info(logLocation, "found " + BACKSTAGE_PASS, item);
            return new BackstagePassEvaluator();
        }
        if (item.name.equals(SULFURUS)) {
            logger.info(logLocation, "found " + SULFURUS, item);
            return new SulfurusEvaluator();
        }
        if (item.name.toLowerCase().contains("conjured")) {
            logger.info(logLocation, "found a conjured item", item);
            return new ConjuredItemEvaluator();
        }
        logger.info(logLocation, "using normal item", item);
        return new NormalItemEvaluator();

    }
}
