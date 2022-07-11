package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedRoseTest {
    @ParameterizedTest(name = "updateQuality_normalItem_decrementsBy1Always_startingSellInIs{0}_updatedSellInIs{1}")
    @MethodSource
    void updateQuality_normalItem_sellInAlwaysDecrementsBy1(int sellIn, int expected) {
        Item[] items = new Item[] { new Item("normal item", sellIn, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(expected);
    }
    private static Stream<Arguments> updateQuality_normalItem_sellInAlwaysDecrementsBy1() {
        return Stream.of(
                Arguments.of(1, 0), // not expired
                Arguments.of(0, -1), // expires today
                Arguments.of(-1, -2) // expired
        );
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
