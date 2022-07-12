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
            updateItem(i);
            decrementSellIn(items[i]);
            enforceExpirationRules(i);
            System.out.printf("Resulting item: %s.%n", items[i].toString());
        }
    }

    private void updateItem(int i) {
        if (!items[i].name.equals(agedBrie)
                && !items[i].name.equals(backstagePasses)) {
            if (items[i].quality > 0) {
                if (!items[i].name.equals(sulfurus)) {
                    items[i].quality = items[i].quality - 1;
                }
            }
        } else {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;

                if (items[i].name.equals(backstagePasses)) {
                    if (items[i].sellIn < 11) {
                        enforceQualityMaximum(items[i]);
                    }

                    if (items[i].sellIn < 6) {
                        enforceQualityMaximum(items[i]);
                    }
                }
            }
        }
    }

    private void enforceExpirationRules(int i) {
        if (items[i].sellIn < 0) {
            if (!items[i].name.equals(agedBrie)) {
                if (!items[i].name.equals(backstagePasses)) {
                    if (items[i].quality > 0) {
                        if (!items[i].name.equals(sulfurus)) {
                            items[i].quality = items[i].quality - 1;
                        }
                    }
                } else {
                    items[i].quality = items[i].quality - items[i].quality;
                }
            } else {
                enforceQualityMaximum(items[i]);
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
