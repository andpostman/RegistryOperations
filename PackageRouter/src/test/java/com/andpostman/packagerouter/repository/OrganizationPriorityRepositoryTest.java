package com.andpostman.packagerouter.repository;

import com.andpostman.packagerouter.model.OrganizationPriority;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@DataR2dbcTest
@DirtiesContext
class OrganizationPriorityRepositoryTest {

    @Autowired
    private OrganizationPriorityRepository repository;

    @Test
    @DisplayName("Проверка существоания репозитория")
    void testRepositoryExisted() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Проверка пустого запроса в БД")
    void whenOrganizationNotExist_returnEmptyResult(){
        Mono<OrganizationPriority> aPackage = repository.findById(anyString());
        StepVerifier
                .create(aPackage)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(organizationPriorities -> assertTrue(organizationPriorities.isEmpty()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Проверка занесения пустого значения в БД")
    void whenTryToSaveInDbOrganization_returnError(){
        OrganizationPriority organizationPriority = new OrganizationPriority(true);
        Mono<OrganizationPriority> aPackage = repository.save(organizationPriority);
        StepVerifier
                .create(aPackage)
                .verifyError();
    }


}
