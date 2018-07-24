package com.qa.sigar;

import com.alibaba.fastjson.JSONObject;
import com.qa.bo.CpuInfoBo;
import com.qa.bo.JvmInfoBo;
import com.qa.bo.MachineBo;
import com.qa.bo.MemInfoBo;
import com.qa.file.FileImpl;
import com.qa.kafka.KafkaHelper;
import org.hyperic.sigar.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyingway on 2018/7/23.
 */
public class SigarImpl {

    public MachineBo machineBo = new MachineBo();
    public List<CpuInfoBo> cpuInfoBoList = new ArrayList<>();
    public JvmInfoBo jvmInfoBo = new JvmInfoBo();
    public MemInfoBo memInfoBo = new MemInfoBo();

    //采集cpu信息
    public void cpu() throws SigarException {
        Sigar sigar = new Sigar();
        CpuInfo infos[] = sigar.getCpuInfoList();
        CpuPerc cpuList[] = sigar.getCpuPercList();

        for (int i = 0, len = infos.length; i < len; i++) {// 不管是单块CPU还是多CPU都适用
            CpuPerc cpu = cpuList[i];
            CpuInfoBo bo = new CpuInfoBo();
            bo.setUserUseRate(CpuPerc.format(cpu.getUser()));//CPU的用户使用率
            bo.setSystemUseRate(CpuPerc.format(cpu.getUser())); //CPU的用户使用率
            bo.setCpuWaitRate(CpuPerc.format(cpu.getWait())); //CPU的当前等待率
            bo.setErrorRate(CpuPerc.format(cpu.getNice())); //CPU的当前错误率
            bo.setFreeRate(CpuPerc.format(cpu.getIdle())); //CPU的当前空闲率
            bo.setTotalUseRate(CpuPerc.format(cpu.getCombined())); //CPU总的使用率
            cpuInfoBoList.add(bo);
        }
        machineBo.setCpu(cpuInfoBoList);
    }

    //采集jvm信息
    public void jvm() throws UnknownHostException {
        Runtime r = Runtime.getRuntime();
        jvmInfoBo.setTotal(r.totalMemory() / (1024 * 1024L)); //JVM可以使用的总内存
        jvmInfoBo.setFree(r.freeMemory() / (1024 * 1024L)); //JVM可以使用的剩余内存
        jvmInfoBo.setProcessorNum(r.availableProcessors()); //JVM可以使用的处理器个数
        machineBo.setJvm(jvmInfoBo);
    }

    //采集内存信息
    public void memory() throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        memInfoBo.setTotal( mem.getTotal() / (1024 * 1024L));// 内存总量
        memInfoBo.setFree(mem.getFree() / (1024 * 1024L));// 当前内存剩余量
        memInfoBo.setUse(mem.getUsed() / (1024 * 1024L));// 当前内存使用量
        machineBo.setMem(memInfoBo);
    }

    //获取IP
    public void getIP() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        String IP = addr.getHostAddress();
        machineBo.setIp(IP);
    }

    public void generateInfo(){
        try{
            cpu();
            jvm();
            memory();
            getIP();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        System.out.println(System.getProperty("java.library.path"));
        SigarImpl impl = new  SigarImpl();
        impl.generateInfo();
        String json = JSONObject.toJSONString(impl.machineBo);
        String needWrite = FileImpl.getValueByKey("needWrite");
        if(needWrite!=null){
            while (needWrite.equals("true")) {
                String kafkaBrokers = FileImpl.getValueByKey("kafkaBroker");
                String topic = FileImpl.getValueByKey("topic");
                KafkaHelper.sendMessage(json, kafkaBrokers, topic);
                String timeStr = FileImpl.getValueByKey("sleepTime");
                try{
                    Thread.sleep(Long.valueOf(timeStr));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("请检查相应目录下的配置文件设置是否正常");
        }
    }
}
