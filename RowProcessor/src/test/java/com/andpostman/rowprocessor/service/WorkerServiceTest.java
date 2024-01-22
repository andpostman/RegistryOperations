package com.andpostman.rowprocessor.service;

import com.andpostman.rowprocessor.model.Worker;
import com.andpostman.rowprocessor.service.impl.WorkerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import com.andpostman.rowprocessor.repository.WorkerRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@DataR2dbcTest
@DirtiesContext
class WorkerServiceTest {

    @Autowired
    private WorkerRepository rowRepository;

    private WorkerServiceImpl service;

    @BeforeEach
    public void setup(){
        this.service = new WorkerServiceImpl(rowRepository);
    }

    @Test
    @DisplayName("Проверка занесения в базу данных пакета")
    void whenInsertPackage_thenOk(){
        Mono<Worker> find = service.getWorkerByFio(anyString());
        StepVerifier
                .create(find)
                .assertNext(aPackage -> assertThat(aPackage).isEqualTo(new Worker()))
                .verifyComplete();

    }

}
