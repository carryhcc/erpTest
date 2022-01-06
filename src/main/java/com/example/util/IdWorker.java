package com.example.util;

import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2022/1/6 11:52
 */
@Configuration
public class IdWorker {
    private static final Sequence WORKER = new Sequence();

    public IdWorker() {
    }

    public static long getId() {
        return WORKER.nextId();
    }

    public static String getIdStr() {
        return String.valueOf(WORKER.nextId());
    }
}
