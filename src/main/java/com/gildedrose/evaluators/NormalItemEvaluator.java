package com.gildedrose.evaluators;

import com.gildedrose.Item;
import com.odd.Logger;
import com.odd.OddLogger;

public class NormalItemEvaluator implements ItemEvaluator{
    Logger logger = new OddLogger();
    @Override
    public void evaluate(Item item) {
        String logLocation = "NormalItemEvaluator.evaluate";
        item.sellIn -= 1;
        item.quality -= 1;
        if (item.sellIn < 0) {
            item.quality -= 1;
            logger.info(logLocation, "Expired", item);
        }
        if (item.quality < 0) {
            item.quality = 0;
            logger.info(logLocation, "Quality < 0", item);
        }
    }
}
