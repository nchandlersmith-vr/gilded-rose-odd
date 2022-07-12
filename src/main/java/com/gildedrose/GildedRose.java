package com.gildedrose;

import com.gildedrose.evaluators.ItemEvaluatorFactory;
import com.gildedrose.evaluators.ItemEvaluator;
import com.odd.Logger;
import com.odd.OddLogger;

public class GildedRose {
    Item[] items;
    ItemEvaluatorFactory itemEvaluatorFactory = new ItemEvaluatorFactory();
    ItemEvaluator evaluator;
    Logger logger = new OddLogger();

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        String logLocation = "GildedRose.updateQuality";
        for (Item item : items) {
            logger.comment("");
            logger.info(logLocation, "Starting", item);
            evaluator = itemEvaluatorFactory.create(item);
            evaluator.evaluate(item);
        }
    }
}
