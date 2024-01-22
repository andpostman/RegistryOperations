package com.andpostman.processjobservice.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import com.andpostman.processjobservice.model.Status;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

@DataR2dbcTest
@DirtiesContext
class StatusRepositoryTest {

    @Autowired
    private StatusRepository repository;

    @Test
    @DisplayName("Проверка существоания репозитория")
    void testRepositoryExisted() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Проверка пустого запроса в БД")
    void whenStatusNotExist_returnEmptyResult(){
        Mono<Status> aPackage = repository.findById(anyInt());
        StepVerifier
                .create(aPackage)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(organizationPriorities -> assertTrue(organizationPriorities.isEmpty()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Проверка занесения пустого значения в БД")
    void whenTryToSaveInDbStatus_returnError(){
        Status status = new Status();
        Mono<Status> newStatus = repository.save(status);
        StepVerifier
                .create(newStatus)
                .verifyError();
    }

    @Test
    @DisplayName("Проверка обновления таблицы в БД пустым значением")
    void whenTryToUpdateInDbStatus_returnEmptyResult(){
        Flux<Status> newStatus = repository.getStatusByState(anyInt());
        StepVerifier
                .create(newStatus)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(organizationPriorities -> assertTrue(organizationPriorities.isEmpty()))
                .verifyComplete();
    }

}
