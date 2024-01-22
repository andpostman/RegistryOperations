package com.andpostman.processjobservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import com.andpostman.processjobservice.repository.StatusRepository;
import com.andpostman.processjobservice.command.PackageOperation;
import com.andpostman.processjobservice.service.impl.QueueConsumerServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataR2dbcTest
@DirtiesContext
class QueueConsumerServiceTest {

    @Autowired
    private StatusRepository statusRepository;

    @Mock
    private List<PackageOperation> list;

    private QueueConsumerServiceImpl service;

    @BeforeEach
    public void setup(){
        this.service = new QueueConsumerServiceImpl(list,statusRepository);
    }


//    @Test
//    @DisplayName("Проверка отправки сообщения когда никто не ожидает сообщения")
//    void whenCheck_AndDoesNotNeedToSendMessage_thenReturnEmptyResult(){
////        when(rowRepository.findByReqId(anyInt())).thenReturn(Flux.just(new PackageRow(1,"s","s",BigDecimal.TEN, false, 40)));
////        when(statusRepository.update(anyInt(),anyInt())).thenReturn(Mono.empty());
////        when(packageRepository.getPackageById(anyInt())).thenReturn(Mono.just(new Package("111","SV",BigDecimal.ONE,false)));
//        Mono<Void> find = service.check(new JmsReceiveMessage("Check"));
//        StepVerifier
//                .create(find)
//                .recordWith(ArrayList::new)
//                .thenConsumeWhile(x -> true)
//                .consumeRecordedWith(organizationPriorities -> assertTrue(organizationPriorities.isEmpty()))
//                .verifyComplete();
//
//    }

}
