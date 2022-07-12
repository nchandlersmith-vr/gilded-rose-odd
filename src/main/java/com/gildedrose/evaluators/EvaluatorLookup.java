package com.gildedrose.evaluators;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import com.odd.Logger;
import com.odd.OddLogger;

public class EvaluatorLookup {
    Logger logger = new OddLogger();
    private static final String SULFURUS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public ItemEvaluator find(Item item) {
        String logLocation = "EvaluatorLookup.find";
        switch (item.name) { // can I get rid of this switch-case ?
            case AGED_BRIE:
                logger.info(logLocation,"found Aged Brie", item);
                return new AgedBrieEvaluator();
            case BACKSTAGE_PASS:
                logger.info(logLocation, "found Backstage Pass", item);
                return new BackstagePassEvaluator();
            case SULFURUS:
                logger.info(logLocation, "found Sulfurus", item);
                return new SulfurusEvaluator();
            default:
                if (item.name.toLowerCase().contains("conjured")) {
                    logger.info(logLocation, "found a conjured item", item);
                    return new ConjuredItemEvaluator();
            }
                logger.info(logLocation, "using normal item evaluator", item);
                return new NormalItemEvaluator();
        }
    }
}
