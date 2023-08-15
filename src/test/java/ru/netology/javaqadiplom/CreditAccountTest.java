package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldThrowExceptionWhenInitialBalanceNegative() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CreditAccount(-1_000, 5_000, 15);
                });
    }

    @Test
    public void shouldThrowExceptionWhenCreditLimitNegative() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CreditAccount(1_000, -5_000, 15);
                });
    }

    @Test
    public void shouldThrowExceptionWhenInterestRateNegative() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CreditAccount(0, 5_000, -15);
                });
    }

    @Test
    public void shouldNotChangeBalanceIfPayAmountNegative() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.pay(-1_000);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldNotChangeBalanceIfPayAmountZero() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        account.pay(0);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldReduceBalanceByPayAmount() {
        CreditAccount account = new CreditAccount(500, 5_000, 15);
        account.pay(499);
        Assertions.assertEquals(1, account.getBalance());
    }


    @Test
    public void shouldNotChangeInitBalanceIfPayAboveCreditLimit() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.pay(5_001);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldReturnNegativeBalanceIfPayAmountAboveInitBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.pay(1_000);

        Assertions.assertEquals(-1_000, account.getBalance());
    }

    @Test
    public void shouldReturnZeroBalanceIfPayAmountEqualSumInitBalanceAndCreditLimit() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        account.pay(6_000);

        Assertions.assertEquals(-5000, account.getBalance());
    }


    @Test
    public void shouldAddToZeroInitBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveInitBalance() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void shouldKeepOriginalInitBalanceIfAmountNegative() {
        CreditAccount account = new CreditAccount(1000, 5_000, 15);
        account.add(-3_000);

        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldKeepOriginalInitBalanceIfAmountZero() {
        CreditAccount account = new CreditAccount(1000, 5_000, 15);
        account.add(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldDoYearChangeWhenBalanceNegative() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.pay(200);
        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test
    public void shouldNotDoYearChangeWhenBalancePositive() {
        CreditAccount account = new CreditAccount(200, 5_000, 15);
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldNotDoYearChangeWhenBalanceZero() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        Assertions.assertEquals(0, account.yearChange());
    }


}
