package com.boris.learn.concurrent.part1.demo10.account;

import java.math.BigDecimal;

/*
  动态的锁顺序死锁
 */
public class TransferMoneyService {
    //获得相同的哈希值时 加时赛锁
    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAcct,
                              final Account toAcct,
                              final BigDecimal amount) throws Exception {
        class TransferMoneyHelper {
            public void transfer() throws Exception {
                if (fromAcct.getBalance().compareTo(amount) < 0) {
                    throw new Exception("账户余额不足");
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }

        //根据两个账户的哈希值来固定锁的顺序
        //如果两个账户的哈希值相同 需要先获取加时赛锁
        //如果直接使用加时赛锁 可以保证安全性 但是效率极低 所有转账操作只能顺序执行
        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);

        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new TransferMoneyHelper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new TransferMoneyHelper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new TransferMoneyHelper().transfer();
                    }
                }
            }
        }
    }
}
