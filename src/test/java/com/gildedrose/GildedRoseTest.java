package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedRoseTest {
    @ParameterizedTest(name = "updateQuality_normalItem_decrementsBy1Always_startingSellInIs{0}_updatedSellInIs{1}")
    @MethodSource("updateQuality_normalItem_sellInAlwaysDecrementsBy1_argsOf_startingSellIn_endingSellIn")
    void updateQuality_normalItem_sellInAlwaysDecrementsBy1(int startingSellIn, int endingSellIn) {
        Item[] items = new Item[] { new Item("normal item", startingSellIn, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(endingSellIn);
    }
    private static Stream<Arguments> updateQuality_normalItem_sellInAlwaysDecrementsBy1_argsOf_startingSellIn_endingSellIn() {
        Arguments notExpired = Arguments.of(1, 0);
        Arguments expiresToday = Arguments.of(0, -1);
        Arguments expired = Arguments.of(-1, -2);
        return Stream.of(notExpired, expiresToday, expired);
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
