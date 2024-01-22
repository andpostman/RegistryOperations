package com.andpostman.packagerouter.service;

import com.andpostman.packagerouter.config.WebClientConfig;
import com.andpostman.packagerouter.repository.OrganizationPriorityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;

@DataR2dbcTest
@DirtiesContext
class PackageServiceTest {

    @Autowired
    private OrganizationPriorityRepository repository;
    @Mock
    private WebClientConfig client;
    private PackageServiceImpl service;

    @BeforeEach
    public void setup(){
        this.service = new PackageServiceImpl(repository,client);
    }

//    @Test
//    @DisplayName("Проверка занесения в базу данных пустого значения")
//    void whenOrganizationIsInvalid_thenThrowsException(){
//        Mono<OrganizationPriority> find = service.findPriority(anyString());
//        StepVerifier
//                .create(find)
//                .expectErrorMatches(throwable -> throwable instanceof NotFoundDBFieldException &&
//                        throwable.getMessage().equals("Not Found")
//                ).verify();
//
//    }





}
