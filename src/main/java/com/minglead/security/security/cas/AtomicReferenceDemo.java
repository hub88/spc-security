package com.minglead.security.security.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j(topic = "AtomicReferenceDemo")
public class AtomicReferenceDemo {

    private AtomicReference<Integer> balance;

    private AtomicReferenceDemo(Integer balance){
        this.balance = new AtomicReference<>(balance);
    }

    private void withdraw(Integer mount){
        while (true){
            Integer pre = balance.get();
            Integer next = pre - mount;
            if(balance.compareAndSet(pre,next)){
                log.info("next is {}",next);
                break;
            }
        }
    }

    public static void main(String[] args) {
        AtomicReferenceDemo atomicReferenceDemo = new AtomicReferenceDemo(1000);
        for (int i=0;i<5; i++){
            new Thread(()->{
                 atomicReferenceDemo.withdraw(100);
            },"t"+i).start();
        }
    }

}
