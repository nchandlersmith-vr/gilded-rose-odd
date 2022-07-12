package com.gildedrose;

import com.gildedrose.evaluators.EvaluatorLookup;
import com.gildedrose.evaluators.ItemEvaluator;
import com.odd.Logger;

public class GildedRose {
    Item[] items;
    EvaluatorLookup evaluatorLookup = new EvaluatorLookup();
    ItemEvaluator evaluator;
    Logger logger = new Logger();
    public static final String SULFURUS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    String backstagePasses = "Backstage passes to a TAFKAL80ETC concert";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        String logLocation = "GildedRose.updateQuality";
        for (Item item : items) {
            if (item.name.equals(SULFURUS) || item.name.equals(AGED_BRIE)) {
                logger.comment("");
                logger.info(logLocation, "Starting", item);
                evaluator = evaluatorLookup.find(item);
                evaluator.evaluate(item);
                return ;
            }
            logger.comment("");
            logger.prefixedInfo("Starting", item);
            updateDegradingItem(item);
            updateImprovingItem(item);
            decrementSellIn(item);
            enforceExpirationRules(item);
            logger.prefixedInfo("Finished", item);
        }
    }

    private void enforceExpirationRules(Item item) {
        if (item.sellIn < 0) {
            logger.prefixedInfo("sellIn < 0", item);
            if (!item.name.equals(AGED_BRIE)) {
                logger.prefixedInfo("Item not Aged Brie", item);
                if (!item.name.equals(backstagePasses)) {
                    logger.prefixedInfo("Item not backstage pass", item);
                    if (item.quality > 0) {
                        logger.prefixedInfo("quality > 0", item);
                        if (!item.name.equals(SULFURUS)) {
                            logger.prefixedInfo("Item not sulfurus", item);
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = 0;
                    logger.prefixedInfo("quality = 0", item);
                }
            } else {
                incrementQuality(item);
            }
        }
    }

    private void incrementQuality(Item item) {
        if (item.quality < 50) {
            logger.prefixedInfo("quality < 50", item);
            item.quality = item.quality + 1;
            logger.prefixedInfo("quality + 1", item);
        }
    }

    private void decrementSellIn(Item item) {
        if (!item.name.equals(SULFURUS)) {
            logger.prefixedInfo("Item is not sulfurus", item);
            item.sellIn = item.sellIn - 1;
            logger.prefixedInfo("sellIn - 1", item);
        }
    }

    private boolean isItemDegrading(Item item) {
        return !(item.name.equals(SULFURUS) || item.name.equals(AGED_BRIE) || item.name.equals(backstagePasses));
    }

    private boolean isItemImprove(Item item) {
        return !item.name.equals(SULFURUS) && !isItemDegrading(item);
    }

    private void updateDegradingItem(Item item) {
        if (isItemDegrading(item)) {
            logger.prefixedInfo("Item degrades", item);
            if (item.quality > 0) {
                logger.prefixedInfo("quality > 0", item);
                if (!item.name.equals(SULFURUS)) {
                    logger.prefixedInfo("Found normal item", item);
                    item.quality = item.quality - 1;
                    logger.prefixedInfo("quality - 1", item);
                }
            }
        }
    }

    private void updateImprovingItem(Item item) {
        if (isItemImprove(item)) {
            logger.prefixedInfo("Item improves", item);
            incrementQuality(item);
            logger.prefixedInfo("quality + 1", item);
            if (item.name.equals(backstagePasses)) {
                logger.prefixedInfo("Found backstage pass", item);
                if (item.sellIn < 11) {
                    logger.prefixedInfo("sellIn < 11", item);
                    incrementQuality(item);
                }
                if (item.sellIn < 6) {
                    incrementQuality(item);
                }
            }
        }
    }
}
