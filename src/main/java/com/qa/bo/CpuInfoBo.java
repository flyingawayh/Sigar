package com.qa.bo;

/**
 * Created by flyingway on 2018/7/23.
 */
public class CpuInfoBo {
    public String userUseRate;
    public String systemUseRate;
    public String cpuWaitRate;
    public String errorRate;
    public String freeRate;
    public String totalUseRate;

    public String getUserUseRate() {
        return userUseRate;
    }

    public void setUserUseRate(String userUseRate) {
        this.userUseRate = userUseRate;
    }

    public String getSystemUseRate() {
        return systemUseRate;
    }

    public void setSystemUseRate(String systemUseRate) {
        this.systemUseRate = systemUseRate;
    }

    public String getCpuWaitRate() {
        return cpuWaitRate;
    }

    public void setCpuWaitRate(String cpuWaitRate) {
        this.cpuWaitRate = cpuWaitRate;
    }

    public String getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(String errorRate) {
        this.errorRate = errorRate;
    }

    public String getFreeRate() {
        return freeRate;
    }

    public void setFreeRate(String freeRate) {
        this.freeRate = freeRate;
    }

    public String getTotalUseRate() {
        return totalUseRate;
    }

    public void setTotalUseRate(String totalUseRate) {
        this.totalUseRate = totalUseRate;
    }
}
