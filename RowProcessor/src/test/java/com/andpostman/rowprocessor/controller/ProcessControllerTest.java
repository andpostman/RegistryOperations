package com.andpostman.rowprocessor.controller;

import com.andpostman.rowprocessor.service.impl.CommandProcessServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProcessController.class)
class ProcessControllerTest {

    @MockBean
    private CommandProcessServiceImpl service;

    @Autowired
    private WebTestClient client;

//    @Test
//    @DisplayName("Проверка запроса JSON")
//    void testProcessCommand(){
//        Mockito.when(service.executeCommand(Mockito.any(JmsRowMessage.class))).thenReturn(Mono.empty());
//
//        client.post()
//                .uri("/processCommand")
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("green_field", String.valueOf(true))
//                .bodyValue(new JmsRowMessage())
//                .exchange()
//                .expectStatus().isOk();
//
//    }

}