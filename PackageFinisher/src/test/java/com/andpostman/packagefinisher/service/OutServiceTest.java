package com.andpostman.packagefinisher.service;

import com.andpostman.packagefinisher.repository.PackageReportRepository;
import com.andpostman.packagefinisher.repository.PackageRepository;
import com.andpostman.packagefinisher.repository.PackageRowRepository;
import com.andpostman.packagefinisher.repository.StatusRepository;
import com.andpostman.packagefinisher.service.impl.OutputServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataR2dbcTest
@DirtiesContext
class OutServiceTest {

    @Autowired
    private PackageRowRepository rowRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private PackageReportRepository packageReportRepository;

    private OutputServiceImpl service;

    @BeforeEach
    public void setup(){
        this.service = new OutputServiceImpl(rowRepository,statusRepository,packageRepository, packageReportRepository);
    }

    @Test
    @DisplayName("Проверка вывода сервиса не существующего сообщения")
    void whenInsertRow_thenOk(){
//        when(rowRepository.findByReqId(anyInt())).thenReturn(Flux.just(new PackageRow(1,"s","s",BigDecimal.TEN, false, 40)));
//        when(statusRepository.update(anyInt(),anyInt())).thenReturn(Mono.empty());
//        when(packageRepository.getPackageById(anyInt())).thenReturn(Mono.just(new Package("111","SV",BigDecimal.ONE,false)));
        Mono<Void> find = service.checkToPrint(1);
        StepVerifier
                .create(find)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(organizationPriorities -> assertTrue(organizationPriorities.isEmpty()))
                .verifyComplete();

    }

}
