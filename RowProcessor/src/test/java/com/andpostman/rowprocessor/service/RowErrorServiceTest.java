package com.andpostman.rowprocessor.service;

import com.andpostman.rowprocessor.command.PackageRowOperationType;
import com.andpostman.rowprocessor.model.RowError;
import com.andpostman.rowprocessor.service.impl.RowErrorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import com.andpostman.rowprocessor.property.JmsRowMessage;
import com.andpostman.rowprocessor.repository.RowErrorRepository;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@DataR2dbcTest
@DirtiesContext
class RowErrorServiceTest {

    @Autowired
    private RowErrorRepository rowRepository;

    private RowErrorServiceImpl service;

    @BeforeEach
    public void setup(){
        this.service = new RowErrorServiceImpl(rowRepository);
    }

    @Test
    @DisplayName("Проверка занесения в сервис пустого сообщения")
    void whenInsertEmptyRowError_thenError(){
        Mono<RowError> find = service.insertErrorData(new JmsRowMessage(anyInt(),anyInt(),anyString(),anyString(), BigDecimal.ZERO, PackageRowOperationType.PROCESSING),anyString());
        StepVerifier
                .create(find)
                .verifyError();

    }

}
