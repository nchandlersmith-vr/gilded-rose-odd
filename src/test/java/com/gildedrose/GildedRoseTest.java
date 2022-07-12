package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedRoseTest {
    String normalItemName = "normal item";
    String agedBrieName = "Aged Brie";
    String sulfurusName = "Sulfuras, Hand of Ragnaros";
    int sulfurusQuality = 80;
    String backStagePassesName = "Backstage passes to a TAFKAL80ETC concert";

    @ParameterizedTest(name = "startingSellIn = {0} | updatedSellIn = {1}")
    @MethodSource("sellInAlwaysDecrementsBy1")
    void updateQuality_normalItem_sellInAlwaysDecrementsBy1(int startingSellIn, int endingSellIn) {
        Item[] items = new Item[] { new Item(normalItemName, startingSellIn, 10)};
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
        Item[] items = new Item[] { new Item(normalItemName, sellIn, startingQuality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(endingQuality);
    }
    private static Stream<Arguments> updateQuality_normalItem_qualityDegrades() {
        Arguments notExpiredLoses1QualityPoint = Arguments.of(1, 10 , 9);
        Arguments expiresTodayLoses2QualityPoints = Arguments.of(0, 10, 8);
        Arguments expiredLoses2QualityPoints = Arguments.of(-1, 10, 8);
        return Stream.of(notExpiredLoses1QualityPoint, expiresTodayLoses2QualityPoints, expiredLoses2QualityPoints);
    }
    @ParameterizedTest(name = "sellIn = {0}")
    @ValueSource(ints = {1, 0, -1}) // not expired, expires today, expired
    void updateQuality_normalItem_qualityCannotBeNegative(int sellIn) {
        Item[] items = new Item[] { new Item(normalItemName, sellIn, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isZero();
    }
    @ParameterizedTest(name = "startingSellIn = {0} | updatedSellIn = {1}")
    @MethodSource("sellInAlwaysDecrementsBy1")
    void updateQuality_agedBrie_sellInAlwaysDecrementsBy1(int startingSellIn, int endingSellIn) {
        Item[] items = new Item[] { new Item(agedBrieName, startingSellIn, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(endingSellIn);
    }
    @ParameterizedTest(name = "sellIn = {0} | startingQuality = {1} | endingQuality = {2}")
    @MethodSource
    void updateQuality_agedBrie_qualityImproves(int sellIn, int startingQuality, int endingQuality) {
        Item[] items = new Item[] { new Item(agedBrieName, sellIn, startingQuality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(endingQuality);
    }
    private static Stream<Arguments> updateQuality_agedBrie_qualityImproves() {
        Arguments notExpiredAdds1QualityPoint = Arguments.of(1, 10, 11);
        Arguments expiresTodayAdds1QualityPoint = Arguments.of(0, 10, 12);
        Arguments expiredAdds2QualityPoints = Arguments.of(-1, 10, 12);
        return Stream.of(notExpiredAdds1QualityPoint, expiresTodayAdds1QualityPoint, expiredAdds2QualityPoints);
    }
    @ParameterizedTest(name = "sellIn = {0} | startingQuality = {1}")
    @MethodSource
    void updateQuality_agdBrie_qualityCannotBeAbove50(int sellIn, int startingQuality) {
        Item[] items = new Item[] { new Item(agedBrieName, sellIn, startingQuality)};
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
        Item[] items = new Item[] { new Item(sulfurusName, startingSellIn, sulfurusQuality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(startingSellIn);
    }
    @ParameterizedTest(name = "startingSellIn = {0}")
    @ValueSource(ints = {1, 0, -1})
    void updateQuality_sulfurus_qualityDoesNotChange(int startingSellIn) {
        Item[] items = new Item[] { new Item(sulfurusName, startingSellIn, sulfurusQuality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(sulfurusQuality);
    }
    @ParameterizedTest(name = "startingSellIn = {0}")
    @ValueSource(ints = {11, 10, 9, 6, 5, 4, 1, 0, -1})
    void updateQuality_backStagePasses_sellInAlwaysDecrements(int startingSellin) {
        int endingSellIn = startingSellin - 1;
        Item[] items = new Item[] { new Item(backStagePassesName, startingSellin, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(endingSellIn);
    }
    @Test
    void updateQuality_backstagePasses_qualityAdds1_whenSellInGreaterThan10() {
        Item[] items = new Item[] { new Item(backStagePassesName, 11, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(21);
    }
    @ParameterizedTest(name = "startingSellIn = {0}")
    @ValueSource(ints = {10, 6})
    void updateQuality_backstagePasses_qualityAdds2_whenSellInOnClosedInterval10_6(int sellin) {
        Item[] items = new Item[] { new Item(backStagePassesName, sellin, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(22);
    }
    @ParameterizedTest(name = "startingSellIn = {0}")
    @ValueSource(ints = {5, 1})
    void updateQuality_backstagePasses_qualityAdds3_whenSellInOnClosedInterval5_1(int sellIn) {
        Item[] items = new Item[] { new Item(backStagePassesName, sellIn, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(23);
    }
    @ParameterizedTest(name = "startingSellIn = {0}")
    @ValueSource(ints = {0, -1})
    void updateQuality_backstagePasses_qualityIsZero_whenSellInNotPositive(int sellIn) {
        Item[] items = new Item[] { new Item(backStagePassesName, sellIn, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isZero();
    }
    @ParameterizedTest(name = "startingSellIn = {0}")
    @ValueSource(ints = {11, 10, 9, 6, 5, 4, 1})
    void updateQuality_backstagePasses_qualityCannotBeGreaterThan50(int sellIn) {
        Item[] items = new Item[] { new Item(backStagePassesName, sellIn, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(50);
    }
}
