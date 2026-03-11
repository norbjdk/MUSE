package com.norbjdk.util;

import com.norbjdk.manager.JMXMLManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class DataBindingUtil {
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final JMXMLManager jmxmlManager;
    public DataBindingUtil(JMXMLManager jmxmlManager) {
        this.jmxmlManager = jmxmlManager;
    }

    public void bindInternetConnectionAvailability(Consumer<Boolean> onSuccess, Consumer<Exception> onError) {
        executor.submit(() -> {
            try {
                boolean isConnected = jmxmlManager.isInternetAvailable();
                onSuccess.accept(isConnected);
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    public void subscribeToMXMLFileOpened() {}

    public void subscribeToMXMLFileCreated() {}

    public void shutdown() {
        executor.shutdown();
    }

}
