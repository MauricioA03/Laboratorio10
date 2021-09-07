package com.example.laboratorio10.service;

import com.example.laboratorio10.model.AccountAux;
import com.example.laboratorio10.repository.AccountAuxWithDelayRepositoryImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountAuxService {
    private final AccountAuxWithDelayRepositoryImpl accountAuxWithDelayRepository;

    public AccountAuxService(AccountAuxWithDelayRepositoryImpl accountAuxWithDelayRepository) {
        this.accountAuxWithDelayRepository = accountAuxWithDelayRepository;
    }

    public AccountAux getById(Long id) {
        return accountAuxWithDelayRepository.getById(id);
    }

    public AccountAux save(AccountAux accountAux) {
        return accountAuxWithDelayRepository.save(accountAux);
    }
    public AccountAux debit(Long accountAuxId, BigDecimal debitAmount) {
        AccountAux accountAux = accountAuxWithDelayRepository.getByIdWithDelay(accountAuxId, 10L);//10 sec delay
        if (accountAux.getBalance().compareTo(debitAmount) >= 0) {
            accountAux.setTotalDebit(accountAux.getTotalDebit().add(debitAmount));
            accountAux.setBalance(accountAux.getBalance().subtract(debitAmount));
        }
        return accountAuxWithDelayRepository.save(accountAux);
    }
}
