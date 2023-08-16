package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        assertEquals(2_000 + 3_000, account.getBalance());
    }
    @Test // Пополнение при нулевом балансе
    public void shouldAddThanMaxBalanceWithMinimum() {
        SavingAccount account = new SavingAccount(
                0,
                0,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test // Пополнение на ноль
    public void shouldAddToZeroBalance() {
        SavingAccount account = new SavingAccount(
                1000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test //Пополнение больше максимального баланса
    public void shouldAddIsGreaterThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                14_000,
                1_000,
                15_000,
                5
        );

        account.add(3_000);
        Assertions.assertEquals(14_000, account.getBalance());
    }

    @Test //Пополнение для максимального баланса
    public void shouldAddLessForMaxBalance() {
        SavingAccount account = new SavingAccount(
                14_000,
                1_000,
                15_000,
                5
        );

        account.add(1000);
        Assertions.assertEquals(15_000, account.getMaxBalance());
    }

    @Test //Покупка ниже минимального баланса
    public void shouldAddBelowMinBalance() {
        SavingAccount account = new SavingAccount(
                1000,
                1_000,
                15_000,
                5
        );

        account.pay(500);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test // Исключение для начального баланса
    public void ThrowExceptionForInitialBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -1000,
                    1_000,
                    15_000,
                    5
            );
            System.out.println(account.getBalance());
        });
    }

    @Test // Исключение для ставки
    public void ThrowExceptionForRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1000,
                    1_000,
                    15_000,
                    -3
            );
            System.out.println(account.getBalance());
        });
    }

    @Test // Исключение для минимального баланса
    public void ThrowExceptionForMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1000,
                    -100,
                    15_000,
                    5
            );
            System.out.println(account.getBalance());
        });
    }

    @Test // Исключение для максимального баланса
    public void ThrowExceptionForMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1000,
                    1_000,
                    1_000,
                    5
            );
            System.out.println(account.getBalance());
        });
    }

    @Test // Расчет процентов за год
    public void shouldInterestCalculation() {
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertEquals(400, account.yearChange());
    }

    @Test // Расчет итоговой суммы с процентами
    public void shouldAmountWithInterest() {
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertEquals(8400, account.getBalance() + account.yearChange());
    }

    @Test //Снятие суммы
    public void shouldWithdrawal() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                15_000,
                5
        );

        account.pay(1000);
        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test // Расчет процентов за год
    public void shouldInterestCalculationWithAdd() {
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        account.add(1000);


        Assertions.assertEquals(450, account.yearChange());
    }

    @Test // Исключение для баланса больше максимального
    public void ThrowExceptionForBalanceMore() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    3000,
                    1_000,
                    2_000,
                    5
            );
            System.out.println(account.getBalance());
        });
    }

    @Test // Исключение для баланса меньше минимального
    public void ThrowExceptionForBalanceLess() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    0,
                    1_000,
                    2_000,
                    5
            );
            System.out.println(account.getBalance());
        });
    }

    @Test // Расчет процентов за год при покупках
    public void shouldInterestCalculationWithPay() {
        SavingAccount account = new SavingAccount(
                10_000,
                1_000,
                10_000,
                5
        );
        account.pay(1000);

        Assertions.assertEquals(450, account.yearChange());
    }

    @Test //Снятие суммы
    public void shouldWithdrawalWithMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                15_000,
                5
        );

        account.pay(1000);
        Assertions.assertEquals(1_000, account.getBalance());
    }

}