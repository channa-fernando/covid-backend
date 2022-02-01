package com.covid.covid.util;

public class Constant {
    public static final String ROUTING_KEY = "test_key";

    private Constant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String QUEUE_NAME = "test";
    public static final String EXCHANGE = "test_exchange";
    public static final String API_HEADER_AUTH = "Authorization";

}
