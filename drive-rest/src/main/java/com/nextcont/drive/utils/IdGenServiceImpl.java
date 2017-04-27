package com.nextcont.drive.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
@Component("idGenService")
public class IdGenServiceImpl implements IdGenService {

    @Value("${idGenWorkerId}")
    private long idGenWorkerId;

    private long workerId;

    // 不用datacenter Id
//    private long datacenterId;

    private long sequence = 0L;

    private long twepoch = 1288834974657L; // 起始标记点，作为基准

//    private long workerIdBits = 5L;
//    private long datacenterIdBits = 5L;

    // 不需要datacenterId，只用workerId，扩展到10位
    private long workerIdBits = 10L; // 只允许workid的范围为：0-1023

    private long maxWorkerId = -1L ^ (-1L << workerIdBits);

//    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    private long sequenceBits = 12L;
    private long workerIdShift = sequenceBits;
//    private long datacenterIdShift = sequenceBits + workerIdBits;

//    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private long timestampLeftShift = sequenceBits + workerIdBits;

    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    private long lastTimestamp = -1L;

    public IdGenServiceImpl() {
        super();
        // sanity check for workerId
        // 只允许workId的范围为：0-1023
        if (idGenWorkerId > maxWorkerId || idGenWorkerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = idGenWorkerId;
    }



    /**
     *  下一个ID
     * */
    public synchronized long nextId(){
        long timestamp = timeGen();
        try {
            if (timestamp < lastTimestamp) {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            }

            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & sequenceMask;
                if (sequence == 0) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0;
            }

            lastTimestamp = timestamp;
        }catch (Exception e){
            e.printStackTrace();
        }

        return ((timestamp - twepoch) << timestampLeftShift) | (workerId << workerIdShift) | sequence;
    }

    /**
     * 保证返回的毫秒数在参数之后
     *
     * @param lastTimestamp
     * @return
     */
    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     *
     * @return
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }
}
