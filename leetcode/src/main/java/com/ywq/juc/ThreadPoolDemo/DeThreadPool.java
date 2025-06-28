package com.ywq.juc.ThreadPoolDemo;

import java.util.List;

public interface DeThreadPool {
    void execute(Runnable task);
    void shutdown();
    List<Runnable> shutdownNow();
}
