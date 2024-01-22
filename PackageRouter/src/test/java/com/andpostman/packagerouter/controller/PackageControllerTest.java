package com.andpostman.packagerouter.controller;

import com.andpostman.packagerouter.service.PackageServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import com.andpostman.packagerouter.dto.Package;
import com.andpostman.packagerouter.dto.Worker;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PackageController.class)
class PackageControllerTest {

    @MockBean
    private PackageServiceImpl service;

    @Autowired
    private WebTestClient client;

//    @Test
//    @DisplayName("Проверка запроса JSON")
//    void testSendJsonPackage(){
//        Package aPackage = initPackage();
//
//        client.post()
//                .uri("/sendPackage")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(aPackage)
//                .exchange()
//                .expectStatus().isOk();
//
//    }

//    @Test
//    @DisplayName("Проверка запроса XML")
//    void testSendXmlPackage(){
//        Package aPackage = initPackage();
//
//        client.post()
//                .uri("/sendPackageXml")
//                .contentType(MediaType.APPLICATION_XML)
//                .bodyValue(aPackage)
//                .exchange()
//                .expectStatus().isOk();
//
//    }

    private Package initPackage(){
        Package aPackage = new Package("SBT","112323",new BigDecimal(200000)
                , List.of(new Worker("Vlasov Ivan Ivanovich", "2313 1313 1232 6544",new BigDecimal(50000))
                , new Worker("Grigoriev Artem Romanovich", "4838 3448 3434 3434", new BigDecimal(90000))
                , new Worker("Luar Kolin Pert","3222 5433 5666 3331", new BigDecimal(60000))));
        Mockito.when(service.sendPackage(aPackage)).thenReturn(Mono.just("Пакет успешно создан!"));
        return aPackage;
    }
}
