package com.andpostman.packageprocessor.controller;

import com.andpostman.packageprocessor.service.impl.PackageProcessorServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(PackageProcessorController.class)
class PackageProcessorControllerTest {

    @MockBean
    private PackageProcessorServiceImpl service;

    @Autowired
    private WebTestClient client;

//    @Test
//    @DisplayName("Проверка запроса JSON")
//    void testPackageToSplit(){
//        when(service.processPackage(any(PackageDto.class), any(Boolean.class))).thenReturn(Mono.just("успешно"));
//
//        client.post()
//                .uri("/sendPackageToSplit")
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("green_field", String.valueOf(true))
//                .bodyValue(new PackageDto("SBT","1111", BigDecimal.ONE, List.of(new WorkerDto("SS ss ss","111",BigDecimal.TEN),new WorkerDto("AA aa aa","1111",BigDecimal.TEN))))
//                .exchange()
//                .expectStatus().isOk();
//
//    }
}
