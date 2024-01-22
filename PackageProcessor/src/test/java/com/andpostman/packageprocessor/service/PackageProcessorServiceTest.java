package com.andpostman.packageprocessor.service;

import com.andpostman.packageprocessor.repository.PackageRepository;
import com.andpostman.packageprocessor.repository.PackageRowRepository;
import com.andpostman.packageprocessor.repository.StatusRepository;
import com.andpostman.packageprocessor.service.impl.PackageProcessorServiceImpl;
import com.andpostman.packageprocessor.service.impl.SendQueueMessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataR2dbcTest
@DirtiesContext
class PackageProcessorServiceTest {

    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private PackageRowRepository rowRepository;
    @Mock
    private SendQueueMessageServiceImpl sendQueueMessageService;
    @Autowired
    private StatusRepository statusRepository;

    private PackageProcessorServiceImpl service;

    @BeforeEach
    public void setup(){
        this.service = new PackageProcessorServiceImpl(packageRepository,rowRepository,sendQueueMessageService,statusRepository);
    }

//    @Test
//    @DisplayName("Проверка занесения в базу данных пакета")
//    void whenInsertPackage_thenReturnPackage(){
//        Mono<Package> find = service.insertPackage(new PackageDto("SBT","1111", BigDecimal.ONE, List.of(new WorkerDto("SS ss ss","111",BigDecimal.TEN),new WorkerDto("AA aa aa","1111",BigDecimal.TEN))),anyBoolean());
//        StepVerifier
//                .create(find)
//                .assertNext(aPackage -> assertThat(aPackage.getContractNumber())
//                        .isEqualTo("1111"))
//                .verifyComplete();
//
//    }

//    @Test
//    @DisplayName("Проверка занесения в базу данных полей пакета")
//    void whenInsertRow_thenReturnRows(){
//        Flux<PackageRow> find = service.insertRow(List.of(new WorkerDto("SS ss ss","111",BigDecimal.TEN),new WorkerDto("AA aa aa","1111",BigDecimal.TEN)),106,true);
//        StepVerifier
//                .create(find)
//                .recordWith(ArrayList::new)
//                .thenConsumeWhile(x -> true)
//                .consumeRecordedWith(packageRows -> assertThat(packageRows).hasSize(2))
//                .verifyComplete();
//
//    }

//    @Test
//    @DisplayName("Проверка отправки пустого статуса пакета")
//    void whenSendStatus_thenError(){
//        Mono<Status> find = service.insertStatus(anyInt());
//        StepVerifier
//                .create(find)
//                .verifyError();
//
//    }
}
