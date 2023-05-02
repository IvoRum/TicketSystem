package com.diploma.ticket.system.config;

import org.springframework.integration.transaction.TransactionSynchronizationFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;

@Component
public class TranscationalConfig implements TransactionSynchronizationFactory {
    @Override
    public TransactionSynchronization create(Object key) {
        return null;
    }
}
