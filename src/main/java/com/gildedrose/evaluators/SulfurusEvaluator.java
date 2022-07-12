package com.gildedrose.evaluators;

import com.gildedrose.Item;

public class SulfurusEvaluator implements ItemEvaluator{
    @Override
    public void evaluate(Item item) {
        new Item(item.name, item.sellIn, item.quality);
    }
}
