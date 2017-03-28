package com.fanfandou.common.util;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdWorker {
    private final static Logger logger = LoggerFactory.getLogger(IdWorker.class);

    private final long workerId;
    private final long epoch = 1403854494756L;   // 时间起始标记点，作为基准，一般取系统的最近时间
    private final long workerIdBits = 10L;      // 机器标识位数
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;// 机器ID最大值: 1023
    private long sequence = 0L;                   // 0，并发控制
    private final long sequenceBits = 12L;      //毫秒内自增位

    private final long workerIdShift = this.sequenceBits;                             // 12
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;// 22
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;                 // 4095,111111111111,12位
    private long lastTimestamp = -1L;

    private IdWorker(long workerId) {
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            logger.error(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
            try {
				throw new Exception(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        this.lastTimestamp = timestamp;
        return timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
    }

 /*   private static IdWorker flowIdWorker = new IdWorker(2);
    public static IdWorker getFlowIdWorkerInstance() {
        return flowIdWorker;
    }*/



    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    static class IdWorkThread implements Runnable {
        private Set<Long> set;
        private IdWorker idWorker;
 
        public IdWorkThread(Set<Long> set, IdWorker idWorker) {
            this.set = set;
            this.idWorker = idWorker;
        }
 
        @Override
        public void run() {
            	try{
            		Thread.sleep(1000);
            		long id = idWorker.nextId();
                    if (!set.add(id)) {
                        System.out.println("duplicate:" + id);
                    }
                    else{
                    	System.out.println("create:" + id);
                    }
            	}catch(InterruptedException e)
            	{
            		
            	}    
        }
    }
 
    public static void main(String[] args) {
    Set<Long> set = new HashSet<Long>();
    final IdWorker idWorker1 = new IdWorker(2);
      ExecutorService exec = Executors.newFixedThreadPool(3);
      for(int i = 0;i<100000;i++){
    	  exec.execute(new IdWorkThread(set, idWorker1 ));
      }
      exec.shutdown();
    }	
//	public static void main(String[] args) throws Exception{
//		Map<Long,Long> map = new HashMap();
//		int count =0;
//		for(int i=0;i<1000000;i++){
//			 IdWorker idWorker = IdWorker.getFlowIdWorkerInstance();
//		long a = idWorker.nextId();
//		if(map.containsKey(a))
//		{
//		   count++;
//		}
//		map.put(a, a);
//	    
//		}
//
//		System.out.println(count);
//	}

}

	    			
