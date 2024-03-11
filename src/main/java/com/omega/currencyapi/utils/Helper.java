package com.omega.currencyapi.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

public class Helper {

    @Value("spring.application.name")
    private static String applicationName;

    public static String generatePath(String... args) {
        StringBuffer buffer = new StringBuffer();
        for (String args2 : args) {
            buffer.append(args2);
            buffer.append("/");
        }
        return buffer.substring(buffer.length() - 1);
    }

    public static String generateTracePath() {
        String traceId = UUID.randomUUID().toString();
        return Helper.generatePath(applicationName, LocalDateTime.now().toString(), traceId);
    }

}
