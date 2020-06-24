package com.minglead.security.security.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

@Slf4j(topic = "AtomicMarkableDemo4")
public class AtomicMarkableDemo4 {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Gable gable = new Gable("装满垃圾");
        AtomicMarkableReference<Gable> amr = new AtomicMarkableReference<>(gable, true);
        new Thread(() -> {
            while ((System.currentTimeMillis()-startTime)<5000L) {
                try {
                    log.info("清理垃圾,垃圾箱状态：{}",gable.getGable());
                    gable.putGable("空垃圾袋");
                    amr.compareAndSet(gable, gable, true, false);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        log.info("过一秒后，主线程丢进去香蕉皮");
        gable.putGable("香蕉皮");
        amr.compareAndSet(gable, gable, false, true);
    }

}
