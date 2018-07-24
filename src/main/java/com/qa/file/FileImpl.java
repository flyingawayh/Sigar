package com.qa.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by flyingway on 2018/7/24.
 */
public class FileImpl {

    public static String getValueByKey(String key){
        String value = "";
        File file = new File(System.getProperty("user.dir")+File.separator+"file"+File.separator+"kafka.properties");
        try{
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), "UTF-8");// 考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;

            while ((lineTxt = bufferedReader.readLine()) != null)
            {
                if(lineTxt.startsWith(key)){
                    String[] array = lineTxt.split("=");
                    if (array[0].equals(key)){
                        value = array[1];
                        break;
                    }
                }
            }
            bufferedReader.close();
            read.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(key+": "+value);
        return value;
    }

    public static void main(String[] args){
        FileImpl.getValueByKey("needWrite");
        FileImpl.getValueByKey("topic");
        FileImpl.getValueByKey("kafkaBroker");
    }
}
