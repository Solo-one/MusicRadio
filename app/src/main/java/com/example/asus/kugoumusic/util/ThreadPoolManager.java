package com.example.asus.kugoumusic.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by asus on 2016/9/8.
 */
public class ThreadPoolManager {

    /** 线程执行器 **/
    private static ExecutorService executorService = null;
    /** 固定5个线程 **/
    private static int nThreads = 5;
    /** 单例模式 **/
    private static ThreadPoolManager taskExecutorPool = null;


    /** 初始化线程池 **/
    static {
        taskExecutorPool = new ThreadPoolManager(nThreads * getNumCores());//线程池线程总的个数
    }

    /** 构造函数 **/
    protected ThreadPoolManager(int threads) {
        //executorService = Executors.newFixedThreadPool(threads);
        executorService = Executors.newScheduledThreadPool(threads);
    }

    /**
     * 取得单例
     *
     * @return
     */
    public static ThreadPoolManager getInstance() {
        return taskExecutorPool;
    }

    /**
     * 取得线程执行器
     *
     * @return
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * 周期性执行
     * @return
     */
    public ScheduledExecutorService getScheduledExcutorService(){
        return (ScheduledExecutorService)executorService;
    }


    //获取硬件CPU核心数
    public static int getNumCores() {
        int threadCount = Runtime.getRuntime().availableProcessors();
        return threadCount;
    }
}
