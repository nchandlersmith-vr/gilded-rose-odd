package com.gildedrose.evaluators;

import com.gildedrose.Item;
import com.odd.Logger;
import com.odd.OddLogger;

public class BackstagePassEvaluator implements ItemEvaluator {
    Logger logger = new OddLogger();
    @Override
    public void evaluate(Item item) {
        String logLocation = "BackstagePassEvaluator.evaluate";
        logger.info(logLocation, "Incrementing quality", item);
        item.quality += 1;
        if (item.sellIn <= 10) {
            item.quality += 1;
            logger.info(logLocation, "sellIn <= 10, incrementing quality.", item);
        }
        if (item.sellIn <= 5) {
            item.quality += 1;
            logger.info(logLocation, "sellIn <= 5, Incrementing quality.", item);
        }
        if (item.sellIn <= 0) {
            item.quality = 0;
            logger.info(logLocation, "Expired quality set to 0", item);
        }
        if (item.quality > 50) {
            item.quality = 50;
            logger.info(logLocation, "Quality cannot exceed 50", item);
        }
        item.sellIn -= 1;
    }
}
