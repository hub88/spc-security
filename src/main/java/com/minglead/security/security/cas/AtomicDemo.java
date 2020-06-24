package com.minglead.security.security.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicDemo{

    private AtomicInteger balance;

    public AtomicDemo(Integer startNum){
        this.balance = new AtomicInteger(startNum);
    }

    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo(100);
        for (int i=0; i<5; i++){
            new Thread(()->{
                atomicDemo.draw(10);
            },"t"+i).start();
        }

    }

    public void draw(Integer num){
        while (true){
            Integer pre = balance.get();
            Integer bas = pre - num;
            if(balance.compareAndSet(pre,bas)){
                log.info("当前线程号{},数字为{}",Thread.currentThread().getName(),pre);
                break;
            }
        }
    }
}
