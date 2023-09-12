package za.co.demo.bookstore.util;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static long random() {
        long smallest = 1000_0000;
        long biggest =  9999_9999;
        return ThreadLocalRandom.current().nextLong(smallest, biggest+1);
    }
}
