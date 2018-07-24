package com.qa.bo;

/**
 * Created by flyingway on 2018/7/23.
 */
public class JvmInfoBo {

    public Long total;
    public Long free;
    public int processorNum;

    public int getProcessorNum() {
        return processorNum;
    }

    public void setProcessorNum(int processorNum) {
        this.processorNum = processorNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getFree() {
        return free;
    }

    public void setFree(Long free) {
        this.free = free;
    }
}
