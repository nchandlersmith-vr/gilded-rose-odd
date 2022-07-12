package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedRoseTest {
    String sulfurus = "Sulfuras, Hand of Ragnaros";
    int sulfurusQuality = 80;

    @ParameterizedTest(name = "startingSellIn = {0} | updatedSellIn = {1}")
    @MethodSource("sellInAlwaysDecrementsBy1")
    void updateQuality_normalItem_sellInAlwaysDecrementsBy1(int startingSellIn, int endingSellIn) {
        Item[] items = new Item[] { new Item("normal item", startingSellIn, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(endingSellIn);
    }
    private static Stream<Arguments> sellInAlwaysDecrementsBy1() {
        Arguments notExpired = Arguments.of(1, 0);
        Arguments expiresToday = Arguments.of(0, -1);
        Arguments expired = Arguments.of(-1, -2);
        return Stream.of(notExpired, expiresToday, expired);
    }
    @ParameterizedTest(name = "sellIn = {0} | startingQuality = {1} | endingQuality = {2}")
    @MethodSource
    void updateQuality_normalItem_qualityDegrades(int sellIn, int startingQuality, int endingQuality) {
        Item[] items = new Item[] { new Item("normal item", sellIn, startingQuality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(endingQuality);
    }
    private static Stream<Arguments> updateQuality_normalItem_qualityDegrades() {
        Arguments notExpiredLoses1QualityPoint = Arguments.of(1, 1 , 0);
        Arguments expiresTodayLoses1QualityPoint = Arguments.of(0, 1 , 0);
        Arguments expiredLoses2QualityPoints = Arguments.of(-1, 2 , 0);
        return Stream.of(notExpiredLoses1QualityPoint, expiresTodayLoses1QualityPoint, expiredLoses2QualityPoints);
    }
    @ParameterizedTest(name = "sellIn = {0}")
    @ValueSource(ints = {1, 0, -1}) // not expired, expires today, expired
    void updateQuality_normalItem_qualityCannotBeNegative(int sellIn) {
        Item[] items = new Item[] { new Item("normal item", sellIn, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isZero();
    }
    @ParameterizedTest(name = "startingSellIn = {0} | updatedSellIn = {1}")
    @MethodSource("sellInAlwaysDecrementsBy1")
    void updateQuality_agedBrie_sellInAlwaysDecrementsBy1(int startingSellIn, int endingSellIn) {
        Item[] items = new Item[] { new Item("Aged Brie", startingSellIn, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(endingSellIn);
    }
    @ParameterizedTest(name = "sellIn = {0} | startingQuality = {1} | endingQuality = {2}")
    @MethodSource
    void updateQuality_agedBrie_qualityImproves(int sellIn, int startingQuality, int endingQuality) {
        Item[] items = new Item[] { new Item("Aged Brie", sellIn, startingQuality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(endingQuality);
    }
    private static Stream<Arguments> updateQuality_agedBrie_qualityImproves() {
        Arguments notExpiredAdds1QualityPoint = Arguments.of(1, 1 , 2);
        Arguments expiresTodayAdds1QualityPoint = Arguments.of(0, 1 , 3);
        Arguments expiredAdds2QualityPoints = Arguments.of(-1, 2 , 4);
        return Stream.of(notExpiredAdds1QualityPoint, expiresTodayAdds1QualityPoint, expiredAdds2QualityPoints);
    }
    @ParameterizedTest(name = "sellIn = {0} | startingQuality = {1}")
    @MethodSource
    void updateQuality_agdBrie_qualityCannotBeAbove50(int sellIn, int startingQuality) {
        Item[] items = new Item[] { new Item("Aged Brie", sellIn, startingQuality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(50);
    }
    private static Stream<Arguments> updateQuality_agdBrie_qualityCannotBeAbove50() {
        return Stream.of(
                Arguments.of(1, 50),
                Arguments.of(0, 50),
                Arguments.of(-1, 50),
                Arguments.of(0, 49),
                Arguments.of(-1, 49)
        );
    }
    @ParameterizedTest(name = "startingSellIn = {0}")
    @ValueSource(ints = {1, 0, -1})
    void updateQuality_sulfurus_sellInDoesNotDecrement(int startingSellIn) {
        Item[] items = new Item[] { new Item(sulfurus, startingSellIn, sulfurusQuality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(startingSellIn);
    }
    @ParameterizedTest(name = "startingSellIn = {0}")
    @ValueSource(ints = {1, 0, -1})
    void updateQuality_sulfurus_qualityDoesNotChange(int startingSellIn) {
        Item[] items = new Item[] { new Item(sulfurus, startingSellIn, sulfurusQuality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(sulfurusQuality);
    }
}
