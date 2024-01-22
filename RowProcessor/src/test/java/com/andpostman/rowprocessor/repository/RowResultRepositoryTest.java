package com.andpostman.rowprocessor.repository;

import com.andpostman.rowprocessor.model.RowResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

@DataR2dbcTest
@DirtiesContext
class RowResultRepositoryTest {

    @Autowired
    private RowResultRepository repository;

    @Test
    @DisplayName("Проверка существоания репозитория")
    void testRepositoryExisted() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Проверка пустого запроса в БД")
    void whenRowResultNotExist_returnEmptyResult(){
        Mono<RowResult> aPackage = repository.findById(anyInt());
        StepVerifier
                .create(aPackage)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(organizationPriorities -> assertTrue(organizationPriorities.isEmpty()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Проверка занесения пустого значения в БД")
    void whenTryToSaveInDbRowResult_returnError(){
        RowResult aPackage = new RowResult();
        Mono<RowResult> aNewPackage = repository.save(aPackage);
        StepVerifier
                .create(aNewPackage)
                .verifyError();
    }
}
