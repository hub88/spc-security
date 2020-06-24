package com.minglead.security.security.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j(topic = "AtomicReferenceDemo2")
public class AtomicReferenceDemo2 {

    private static AtomicReference<String> aba = new AtomicReference<>("A");

    public static void main(String[] args) throws InterruptedException {
        log.info("主程序运行开始");
        referenceABA();
        TimeUnit.SECONDS.sleep(2);
        log.info(" A -> B {}",aba.compareAndSet(aba.get(),"C"));
    }

    private static void referenceABA() throws InterruptedException {
        new Thread(()->{
            log.info(" A -> B  is  {}", aba.compareAndSet(aba.get(),"B"));
        },"t1|").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            log.info(" B -> A is {}",aba.compareAndSet(aba.get(),"A"));
        },"t2").start();
    }
}
