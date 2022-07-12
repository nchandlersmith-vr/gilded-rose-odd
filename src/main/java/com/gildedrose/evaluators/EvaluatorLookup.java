package com.gildedrose.evaluators;

import com.gildedrose.Item;
import com.odd.Logger;

public class EvaluatorLookup {
    Logger logger = new Logger();
    public ItemEvaluator find(Item item) {
        String logLocation = "EvaluatorLookup.find";
        switch (item.name) {
            case "Aged Brie":
                logger.info(logLocation,"found Aged Brie", item);
                return new AgedBrieEvaluator();
            default:
                logger.info(logLocation, "using default", item);
                return new SulfurusEvaluator();
        }
    }
}
