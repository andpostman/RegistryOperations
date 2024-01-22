package com.andpostman.packageprocessor.repository;

import com.andpostman.packageprocessor.model.Package;
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
class PackageRepositoryTest {

    @Autowired
    private PackageRepository repository;

    @Test
    @DisplayName("Проверка существоания репозитория")
    void testRepositoryExisted() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Проверка пустого запроса в БД")
    void whenPackageNotExist_returnEmptyResult(){
        Mono<Package> aPackage = repository.findById(anyInt());
        StepVerifier
                .create(aPackage)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(organizationPriorities -> assertTrue(organizationPriorities.isEmpty()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Проверка занесения пустого значения в БД")
    void whenTryToSaveInDbPackage_returnError(){
        Package aPackage = new Package();
        Mono<Package> aNewPackage = repository.save(aPackage);
        StepVerifier
                .create(aNewPackage)
                .verifyError();
    }
}
