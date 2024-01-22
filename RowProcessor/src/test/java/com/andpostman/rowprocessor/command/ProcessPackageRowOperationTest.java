package com.andpostman.rowprocessor.command;

import com.andpostman.rowprocessor.service.impl.PackageRowServiceImpl;
import com.andpostman.rowprocessor.service.impl.RowErrorServiceImpl;
import com.andpostman.rowprocessor.service.impl.RowResultServiceImpl;
import com.andpostman.rowprocessor.service.impl.WorkerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

@DataR2dbcTest
@DirtiesContext
class ProcessPackageRowOperationTest {

    @MockBean
    private RowResultServiceImpl resultService;
    @MockBean
    private RowErrorServiceImpl errorService;
    @MockBean
    private PackageRowServiceImpl packageRowService;
    @MockBean
    private WorkerServiceImpl workerService;

    private ProcessPackageRowOperation service;

    @BeforeEach
    public void setup(){
        this.service = new ProcessPackageRowOperation(resultService,errorService,packageRowService,workerService);
    }

//    @Test
//    @DisplayName("Проверка обновления базу данных")
//    void whenSendMessage_thenOk(){
////        when(workerService.getWorkerByAccount(anyString())).thenReturn(Mono.just(new Worker()));
////        when(workerService.getWorkerByFio(anyString())).thenReturn(Mono.just(new Worker()));
////        when(packageRowService.updateState(any(JmsRowMessage.class),any(PackageRowOperationType.class))).thenReturn(Mono.just(new PackageRow()));
////        when(errorService.insertErrorData(any(JmsRowMessage.class),anyString())).thenReturn(Mono.just(new RowError()));
////        when(resultService.insertResultData(any(JmsRowMessage.class),BigDecimal.TEN)).thenReturn(Mono.just(new RowResult()));
//        JmsRowMessage message = new JmsRowMessage(
//                1,
//                2,
//                "CC",
//                "DD",
//                BigDecimal.TEN,
//                PackageRowOperationType.PROCESSING
//        );
//        Mono<Void> find = service.send(message);
//        StepVerifier
//                .create(find)
//                .verifyComplete();
//
//    }

}
