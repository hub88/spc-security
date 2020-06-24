package com.minglead.security.security.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j(topic = "AtomicStampedDemo3")
public class AtomicStampedDemo3 {

    private static AtomicStampedReference<String> asr = new AtomicStampedReference<>("A",1);

    public static void main(String[] args) throws InterruptedException {
           log.info("主线程开始执行代码");
           String prev = asr.getReference();
           atomicABA();
           log.info(" A -> B is {}",asr.compareAndSet(prev,"C",asr.getStamp(),asr.getStamp()+1));
           TimeUnit.SECONDS.sleep(1);
           log.info("最终结果为：{},版本号为：{}",asr.getReference(),asr.getStamp());
    }

    public static void atomicABA() throws InterruptedException {
        new Thread(()->{
            log.info(" A -> B is {}",asr.compareAndSet(asr.getReference(),"B",asr.getStamp(),asr.getStamp()+1));
        },"t1").start();
//        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            log.info(" B -> A is {}",asr.compareAndSet(asr.getReference(),"A",asr.getStamp(),asr.getStamp()+1));
        },"t2").start();

    }

}
