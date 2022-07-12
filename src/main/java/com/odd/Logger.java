package com.odd;

import com.gildedrose.Item;

public class Logger {

    public void prefixedInfo(String prefix, Item item) {
        System.out.printf("%s: name: %s, sellIn: %d, quality: %d%n", prefix, item.name, item.sellIn, item.quality);
    }

    public void info(String location, String comment, Item item) {
        System.out.printf("location: %s, comment: %s,  name: %s, sellIn: %d, quality: %d%n", location, comment, item.name, item.sellIn, item.quality);
    }

    public void comment(String string) {
        System.out.println(string);
    }
}
