package com.jonlin;

import com.jonlin.model.User;
import com.jonlin.test.IOTest;
import com.jonlin.test.ThreadTest;

import java.math.BigDecimal;
import java.util.zip.ZipFile;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws Exception
    {
          // 压缩文件
        IOTest.zipFile("first");
//        // copy文件
//        IOTest.readAndWhite("test.txt");
//        // 读取文件
//        IOTest.readFile("/test.txt");
    }

    /**
     * 测试线程
     */
    public void oiThread() throws Exception{
        final User userA = new User();
        userA.setName("userA");
        userA.setBalance(new BigDecimal(100));
        final User userB = new User();
        userB.setName("userB");
        userB.setBalance(new BigDecimal(100));
        final User userC = new User();
        userC.setName("userC");
        userC.setBalance(new BigDecimal(100));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("ua start!");
                    ThreadTest.remittance(userA,userB, new BigDecimal(10));
                    System.out.println("ua end!");
                }catch (Exception e){

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("uc start!");
                    ThreadTest.remittance(userC,userB, new BigDecimal(20));
                    System.out.println("uc end!");
                }catch (Exception e){

                }
            }
        }).start();

        Thread.currentThread().sleep(2000);
        System.out.println("userA的余额：" + userA.getBalance());
        System.out.println("userB的余额：" + userB.getBalance());
        System.out.println("userC的余额：" + userC.getBalance());
    }
}
