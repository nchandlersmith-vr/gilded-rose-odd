package com.gildedrose.evaluators;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import com.odd.Logger;
import com.odd.OddLogger;

public class EvaluatorLookup {
    Logger logger = new OddLogger();
    public ItemEvaluator find(Item item) {
        String logLocation = "EvaluatorLookup.find";
        switch (item.name) { // can I get rid of this switch-case ?
            case GildedRose.AGED_BRIE:
                logger.info(logLocation,"found Aged Brie", item);
                return new AgedBrieEvaluator();
            case GildedRose.BACKSTAGE_PASS:
                logger.info(logLocation, "found Backstage Pass", item);
                return new BackstagePassEvaluator();
            case GildedRose.SULFURUS:
                logger.info(logLocation, "found Sulfurus", item);
                return new SulfurusEvaluator();
            default:
                logger.info(logLocation, "using normal item evaluator", item);
                return new NormalItemEvaluator();
        }
    }
}
