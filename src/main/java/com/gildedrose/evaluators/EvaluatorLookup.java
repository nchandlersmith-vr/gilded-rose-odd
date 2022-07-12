package com.gildedrose.evaluators;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import com.odd.Logger;
import com.odd.OddLogger;

public class EvaluatorLookup {
    Logger logger = new OddLogger();
    public ItemEvaluator find(Item item) {
        String logLocation = "EvaluatorLookup.find";
        switch (item.name) {
            case GildedRose.AGED_BRIE:
                logger.info(logLocation,"found Aged Brie", item);
                return new AgedBrieEvaluator();
            default:
                logger.info(logLocation, "using default", item);
                return new SulfurusEvaluator();
        }
    }
}
