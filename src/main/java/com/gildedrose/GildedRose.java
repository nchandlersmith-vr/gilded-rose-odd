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
        System.out.println("Starting update quality.");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("----- Item %d -----%n", i);
            System.out.printf("Updating item: %s.%n", items[i].toString());
            updateItem(items[i]);
            decrementSellIn(items[i]);
            enforceExpirationRules(items[i]);
            System.out.printf("Resulting item: %s.%n", items[i].toString());
        }
    }

    private void updateItem(Item item) {
        if (!item.name.equals(agedBrie)
                && !item.name.equals(backstagePasses)) {
            if (item.quality > 0) {
                if (!item.name.equals(sulfurus)) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals(backstagePasses)) {
                    if (item.sellIn < 11) {
                        enforceQualityMaximum(item);
                    }

                    if (item.sellIn < 6) {
                        enforceQualityMaximum(item);
                    }
                }
            }
        }
    }

    private void enforceExpirationRules(Item item) {
        if (item.sellIn < 0) {
            if (!item.name.equals(agedBrie)) {
                if (!item.name.equals(backstagePasses)) {
                    if (item.quality > 0) {
                        if (!item.name.equals(sulfurus)) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                enforceQualityMaximum(item);
            }
        }
    }

    private void enforceQualityMaximum(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decrementSellIn(Item item) {
        if (!item.name.equals(sulfurus)) {
            item.sellIn = item.sellIn - 1;
        }
    }
}
