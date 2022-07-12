package com.gildedrose.evaluators;

import com.gildedrose.Item;
import com.odd.Logger;

public class AgedBrieEvaluator implements ItemEvaluator{
    Logger logger = new Logger();
    @Override
    public void evaluate(Item item) {
        String logLocation = "AgedBrieEvaluator.evaluate";
        logger.info(logLocation, "Starting quality update", item);
        item.quality += 1;
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            item.quality += 1;
            logger.info(logLocation, "Expired", item);
        }
        if (item.quality > 50) {
            item.quality = 50;
            logger.info(logLocation, "Quality cannot exceed 50", item);
        }
        logger.info(logLocation, "Finished quality update", item);
    }
}
