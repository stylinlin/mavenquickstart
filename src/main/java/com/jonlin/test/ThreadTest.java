package com.jonlin.test;

import com.jonlin.model.User;

import java.math.BigDecimal;

/**
 * description 线程测试
 * Author Jonlin
 * Date 2019/6/25 11:04
 **/
public class ThreadTest {
    /**
     * 功能描述 汇款
     * 多个线程调用时，需要需要 synchronized 同步
     * author Jonlin
     * date 2019/6/25 11:07
     * param remitter 汇款人
     * param payee 收款人
     * param money 收款金额
     * return void
     */
    public static void remittance(final User remitter, final User payee, final BigDecimal money){
//        synchronized (ThreadTest.class) {
            remitter.setBalance(remitter.getBalance().subtract(money));
            payee.setBalance(payee.getBalance().add(money));
//        }
    }



}
