package com.boris.learn.concurrent.part1.demo13;

import com.boris.learn.concurrent.part1.demo10.account.Account;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class TransferMoneyByLock {
    public boolean transferMoney(Account fromAcct,
                                 Account toAcct,
                                 BigDecimal amount,
                                 long timeout,
                                 TimeUnit unit) throws Exception {
        long stopTime = System.nanoTime() + unit.toNanos(timeout);

        while(true) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            if(fromAcct.getBalance().compareTo(amount)<0) {
                                throw new Exception("");
                            } else {
                                fromAcct.debit(amount);
                                toAcct.credit(amount);
                                return true;
                            }
                        } finally {
                            toAcct.lock.unlock();
                        }
                    }
                } finally {
                    fromAcct.lock.unlock();
                }
            }

            if (System.nanoTime() > stopTime) {
                return false;
            }
            NANOSECONDS.sleep(200);
        }
    }
}
