package com.andpostman.rowprocessor.service;

import com.andpostman.rowprocessor.command.PackageRowOperationType;
import com.andpostman.rowprocessor.model.RowResult;
import com.andpostman.rowprocessor.service.impl.RowResultServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import com.andpostman.rowprocessor.property.JmsRowMessage;
import com.andpostman.rowprocessor.repository.RowResultRepository;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@DataR2dbcTest
@DirtiesContext
class RowResultServiceTest {

    @Autowired
    private RowResultRepository rowRepository;

    private RowResultServiceImpl service;

    @BeforeEach
    public void setup(){
        this.service = new RowResultServiceImpl(rowRepository);
    }

    @Test
    @DisplayName("Проверка занесения в сервис пустое сообщение")
    void whenInsertEmptyRowResult_thenError(){
        Mono<RowResult> find = service.insertResultData(new JmsRowMessage(anyInt(),anyInt(),anyString(),anyString(), BigDecimal.ZERO, PackageRowOperationType.PROCESSING),BigDecimal.TEN);
        StepVerifier
                .create(find)
                .verifyError();

    }

}
