package com.andpostman.rowprocessor.repository;

import com.andpostman.rowprocessor.model.Worker;
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
import static org.mockito.ArgumentMatchers.anyString;

@DirtiesContext
@DataR2dbcTest
class WorkerRepositoryTest {

    @Autowired
    private WorkerRepository repository;

    @Test
    @DisplayName("Проверка существоания репозитория")
    void testRepositoryExisted() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Проверка пустого запроса в БД")
    void whenWorkerNotExist_returnEmptyResult(){
        Mono<Worker> aPackage = repository.findById(anyInt());
        StepVerifier
                .create(aPackage)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(organizationPriorities -> assertTrue(organizationPriorities.isEmpty()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Проверка занесения пустого значения в БД")
    void whenTryToSaveInDbEmptyWorker_returnError(){
        Worker worker = new Worker();
        Mono<Worker> aNewPackage = repository.save(worker);
        StepVerifier
                .create(aNewPackage)
                .verifyError();
    }

    @Test
    @DisplayName("Проверка поиска пустого worker в БД")
    void whenTryToFindInDbEmptyWorker_returnEmptyResults(){
        Mono<Worker> aNewPackage = repository.findWorkerByFio(anyString());
        Mono<Worker> aSecondNew = repository.findWorkerByAccount(anyString());
        StepVerifier
                .create(aNewPackage)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(organizationPriorities -> assertTrue(organizationPriorities.isEmpty()))
                .verifyComplete();

        StepVerifier
                .create(aSecondNew)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(organizationPriorities -> assertTrue(organizationPriorities.isEmpty()))
                .verifyComplete();
    }

}
