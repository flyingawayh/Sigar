package com.qa.bo;

/**
 * Created by flyingway on 2018/7/23.
 */
public class MemInfoBo {

    public Long total;
    public Long use;
    public Long free;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getUse() {
        return use;
    }

    public void setUse(Long use) {
        this.use = use;
    }

    public Long getFree() {
        return free;
    }

    public void setFree(Long free) {
        this.free = free;
    }
}

