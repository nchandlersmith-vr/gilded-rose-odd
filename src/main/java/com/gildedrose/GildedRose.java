package com.gildedrose;

import com.gildedrose.evaluators.EvaluatorLookup;
import com.gildedrose.evaluators.ItemEvaluator;
import com.odd.Logger;
import com.odd.OddLogger;

public class GildedRose {
    Item[] items;
    EvaluatorLookup evaluatorLookup = new EvaluatorLookup();
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
            evaluator = evaluatorLookup.find(item);
            evaluator.evaluate(item);
        }
    }
}
