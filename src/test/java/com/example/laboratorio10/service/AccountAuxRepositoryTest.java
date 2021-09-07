package com.example.laboratorio10.service;

import com.example.laboratorio10.model.AccountAux;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;


@SpringBootTest
public class AccountAuxRepositoryTest {
    @Autowired
    private AccountAuxService accountService;

    @Test
    public void givenBalance100WhenDebit100TwiceShouldFail() {

        assertThrows(ExecutionException.class, () -> {
            AccountAux accountAux = new AccountAux();
            accountAux.setTotalCredit(new BigDecimal("100"));
            accountAux.setTotalDebit(new BigDecimal("0"));
            accountAux.setBalance(new BigDecimal("100"));
            accountService.save(accountAux);

            CompletableFuture<AccountAux> completableFutureFirstOperation =
                    CompletableFuture.supplyAsync(() -> accountService.debit(accountAux.getId(),
                            new BigDecimal("100"))).orTimeout(60, TimeUnit.SECONDS);

            CompletableFuture<AccountAux> completableFutureSecondOperation =
                    CompletableFuture.supplyAsync(() -> accountService.debit(accountAux.getId(),
                            new BigDecimal("100"))).orTimeout(60, TimeUnit.SECONDS);

            AccountAux firstfinalAccount = completableFutureFirstOperation.get();
            AccountAux secondfinalAccount2 = completableFutureSecondOperation.get();

            AccountAux accountAuxAfterOperations = accountService.getById(secondfinalAccount2.getId());

            System.out.println("id: " + accountAuxAfterOperations.getId() + ", balance: " + accountAuxAfterOperations.getBalance() + ", debit: "+ accountAuxAfterOperations.getTotalDebit() + ", credit: " + accountAuxAfterOperations.getTotalCredit());

            assertEquals(accountAuxAfterOperations.getBalance(), 0);
        });
    }
}
