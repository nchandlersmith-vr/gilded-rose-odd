package com.gildedrose;

class GildedRose {
    Item[] items;
    String sulfurus = "Sulfuras, Hand of Ragnaros";
    String agedBrie = "Aged Brie";
    String backstagePasses = "Backstage passes to a TAFKAL80ETC concert";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            System.out.println();
            oddLog("Starting", item);
            updateItem(item);
            decrementSellIn(item);
            enforceExpirationRules(item);
            oddLog("Finished", item);
        }
    }

    private void updateItem(Item item) {
        if (isItemDegrading(item)) {
            oddLog("Item does not degrade", item);
            if (item.quality > 0) {
                oddLog("quality > 0", item);
                if (!item.name.equals(sulfurus)) {
                    oddLog("Found normal item", item);
                    item.quality = item.quality - 1;
                    oddLog("quality - 1", item);
                }
            }
        } else {
            if (item.quality < 50) {
                oddLog("quality < 50", item);
                item.quality = item.quality + 1;
                oddLog("quality + 1", item);
                if (item.name.equals(backstagePasses)) {
                    oddLog("Found backstage pass", item);
                    if (item.sellIn < 11) {
                        oddLog("sellIn < 11", item);
                        incrementQuality(item);
                    }

                    if (item.sellIn < 6) {
                        incrementQuality(item);
                    }
                }
            }
        }
    }

    private void enforceExpirationRules(Item item) {
        if (item.sellIn < 0) {
            oddLog("sellIn < 0", item);
            if (!item.name.equals(agedBrie)) {
                oddLog("Item not Aged Brie", item);
                if (!item.name.equals(backstagePasses)) {
                    oddLog("Item not backstage pass", item);
                    if (item.quality > 0) {
                        oddLog("quality > 0", item);
                        if (!item.name.equals(sulfurus)) {
                            oddLog("Item not sulfurus", item);
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = 0;
                    oddLog("quality = 0", item);
                }
            } else {
                incrementQuality(item);
            }
        }
    }

    private void incrementQuality(Item item) {
        if (item.quality < 50) {
            oddLog("quality < 50", item);
            item.quality = item.quality + 1;
            oddLog("quality + 1", item);
        }
    }

    private void decrementSellIn(Item item) {
        if (!item.name.equals(sulfurus)) {
            oddLog("Item is not sulfurus", item);
            item.sellIn = item.sellIn - 1;
            oddLog("sellIn - 1", item);
        }
    }
    private void oddLog(String location, Item item) {
        System.out.printf("%s: name: %s, sellIn: %d, quality: %d%n", location, item.name, item.sellIn, item.quality);
    }

    private boolean isItemDegrading(Item item) {
        return !(item.name.equals(sulfurus) || item.name.equals(agedBrie) || item.name.equals(backstagePasses));
    }
}
