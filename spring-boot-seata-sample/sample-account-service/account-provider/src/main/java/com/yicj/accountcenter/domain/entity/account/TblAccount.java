package com.yicj.accountcenter.domain.entity.account;

import lombok.ToString;

import javax.persistence.*;

@ToString
@Table(name = "tbl_account")
public class TblAccount {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    private Integer balance;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return balance
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * @param balance
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}