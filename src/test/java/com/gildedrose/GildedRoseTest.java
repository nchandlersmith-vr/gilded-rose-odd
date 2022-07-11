package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedRoseTest {
    @Test
    void updateQuality_normalItem_sellInDecrementsBy1_whenPositive() {
        Item[] items = new Item[] { new Item("normal item", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isZero();
    }
    @Test
    void updateQuality_normalItem_sellInDecrementsBy1_when0() {
        Item[] items = new Item[] { new Item("normal item", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(-1);
    }
    @Test
    void updateQuality_normalItem_sellInDecrementsBy1_whenNegative() {
        Item[] items = new Item[] { new Item("normal item", -1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(-2);
    }
    @Test
    void updateQuality_normalItem_qualityDecrementsBy1_whenSellInIsPositive() {
        Item[] items = new Item[] { new Item("normal item", 1, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isZero();
    }
    @Test
    void updateQuality_normalItem_qualityDecrementsBy1_whenSellInIsZero() {
        Item[] items = new Item[] { new Item("normal item", 0, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isZero();
    }
    @Test
    void updateQuality_normalItem_qualityDecrementsBy2_whenSellInIsNegative() {
        Item[] items = new Item[] { new Item("normal item", -1, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isZero();
    }
    @Test
    void updateQuality_normalItem_qualityCannotBeLessThanZero_whenSellInIsPositive() {
        Item[] items = new Item[] { new Item("normal item", 1, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isZero();
    }
    @Test
    void updateQuality_normalItem_qualityCannotBeLessThanZero_whenSellInIsZero() {
        Item[] items = new Item[] { new Item("normal item", 0,0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isZero();
    }
    @Test
    void updateQality_normalItem_qualityCannotBeLessThanZero_whenSellInIsNegative() {
        Item[] items = new Item[] { new Item("normal item", -1, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isZero();
    }
}
