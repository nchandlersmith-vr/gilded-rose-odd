package com.gildedrose.evaluators;

import com.gildedrose.Item;
import com.odd.Logger;

public class EvaluatorLookup {
    Logger logger = new Logger();
    public ItemEvaluator find(Item item) {
        switch (item.name) {
            default:
                logger.prefixedInfo("EvaluatorCreator: returning SulfurusEvaluator", item);
                return new SulfurusEvaluator();
        }
    }
}
