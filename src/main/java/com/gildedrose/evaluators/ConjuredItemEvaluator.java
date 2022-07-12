package com.gildedrose.evaluators;

import com.gildedrose.Item;
import com.odd.Logger;
import com.odd.OddLogger;

public class ConjuredItemEvaluator implements ItemEvaluator {
    Logger logger = new OddLogger();
    @Override
    public void evaluate(Item item) {
        String logLocation = "ConjuredItemEvaluator.evaluate";
        logger.info(logLocation, "Starting quality update", item);
        item.sellIn -= 1;
        logger.info(logLocation, "Finished quality update", item);
    }
}
