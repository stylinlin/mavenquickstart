package com.jonlin.model;

import lombok.Data;
import lombok.Synchronized;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * description 用户
 * Author Jonlin
 * Date 2019/6/25 11:01
 **/
@Data
public class User implements Serializable {
    private String name; // 姓名
    private BigDecimal balance; // 余额

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
