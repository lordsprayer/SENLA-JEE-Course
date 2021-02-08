package com.senla.courses.util;

public class IdGenerator {

    private static Long bookId = 1L;
    private static Long orderId = 1L;
    private static Long requestId = 1L;

    public static Long GenerateBookId() {
        return bookId ++;
    }

    public static Long GenerateOrderId() {

        return orderId ++;
    }

    public static Long GenerateRequestId() {
        return requestId ++;
    }
}
