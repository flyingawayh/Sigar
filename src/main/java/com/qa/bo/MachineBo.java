package com.qa.bo;

import java.util.List;

/**
 * Created by flyingway on 2018/7/23.
 */
public class MachineBo {

    public String ip;
    public List<CpuInfoBo> cpu;
    public JvmInfoBo jvm;
    public MemInfoBo mem;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<CpuInfoBo> getCpu() {
        return cpu;
    }

    public void setCpu(List<CpuInfoBo> cpu) {
        this.cpu = cpu;
    }

    public JvmInfoBo getJvm() {
        return jvm;
    }

    public void setJvm(JvmInfoBo jvm) {
        this.jvm = jvm;
    }

    public MemInfoBo getMem() {
        return mem;
    }

    public void setMem(MemInfoBo mem) {
        this.mem = mem;
    }
}
