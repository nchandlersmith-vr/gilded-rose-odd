package com.gildedrose;

import com.odd.Logger;

class GildedRose {
    Item[] items;
    Logger logger = new Logger();
    String sulfurus = "Sulfuras, Hand of Ragnaros";
    String agedBrie = "Aged Brie";
    String backstagePasses = "Backstage passes to a TAFKAL80ETC concert";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            logger.comment("");
            logger.info("Starting", item);
            updateDegradingItem(item);
            updateImprovingItem(item);
            decrementSellIn(item);
            enforceExpirationRules(item);
            logger.info("Finished", item);
        }
    }

    private void enforceExpirationRules(Item item) {
        if (item.sellIn < 0) {
            logger.info("sellIn < 0", item);
            if (!item.name.equals(agedBrie)) {
                logger.info("Item not Aged Brie", item);
                if (!item.name.equals(backstagePasses)) {
                    logger.info("Item not backstage pass", item);
                    if (item.quality > 0) {
                        logger.info("quality > 0", item);
                        if (!item.name.equals(sulfurus)) {
                            logger.info("Item not sulfurus", item);
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = 0;
                    logger.info("quality = 0", item);
                }
            } else {
                incrementQuality(item);
            }
        }
    }

    private void incrementQuality(Item item) {
        if (item.quality < 50) {
            logger.info("quality < 50", item);
            item.quality = item.quality + 1;
            logger.info("quality + 1", item);
        }
    }

    private void decrementSellIn(Item item) {
        if (!item.name.equals(sulfurus)) {
            logger.info("Item is not sulfurus", item);
            item.sellIn = item.sellIn - 1;
            logger.info("sellIn - 1", item);
        }
    }

    private boolean isItemDegrading(Item item) {
        return !(item.name.equals(sulfurus) || item.name.equals(agedBrie) || item.name.equals(backstagePasses));
    }

    private boolean isItemImprove(Item item) {
        return !item.name.equals(sulfurus) && !isItemDegrading(item);
    }

    private void updateDegradingItem(Item item) {
        if (isItemDegrading(item)) {
            logger.info("Item degrades", item);
            if (item.quality > 0) {
                logger.info("quality > 0", item);
                if (!item.name.equals(sulfurus)) {
                    logger.info("Found normal item", item);
                    item.quality = item.quality - 1;
                    logger.info("quality - 1", item);
                }
            }
        }
    }

    private void updateImprovingItem(Item item) {
        if (isItemImprove(item)) {
            logger.info("Item improves", item);
            if (item.quality < 50) {
                logger.info("quality < 50", item);
                item.quality = item.quality + 1;
                logger.info("quality + 1", item);
                if (item.name.equals(backstagePasses)) {
                    logger.info("Found backstage pass", item);
                    if (item.sellIn < 11) {
                        logger.info("sellIn < 11", item);
                        incrementQuality(item);
                    }

                    if (item.sellIn < 6) {
                        incrementQuality(item);
                    }
                }
            }
        }
    }
}
