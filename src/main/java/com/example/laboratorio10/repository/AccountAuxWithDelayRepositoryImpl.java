package com.example.laboratorio10.repository;

import com.example.laboratorio10.model.AccountAux;

import java.util.concurrent.TimeUnit;

public interface AccountAuxWithDelayRepositoryImpl extends AccountAuxRepository {
    default AccountAux getByIdWithDelay(Long id, Long delay) {
        AccountAux accountAux = getById(id);
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return accountAux;
    }
}
