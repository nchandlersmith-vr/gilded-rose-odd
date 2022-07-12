package com.odd;

import com.gildedrose.Item;

public interface Logger {
    void comment(String comment);
    void info(String logLocation, String comment, Item item);
    void prefixedInfo(String prefix, Item item);
}
