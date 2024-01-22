package com.andpostman.rowprocessor.service;

import com.andpostman.rowprocessor.command.PackageRowOperationType;
import com.andpostman.rowprocessor.model.PackageRow;
import com.andpostman.rowprocessor.service.impl.PackageRowServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import com.andpostman.rowprocessor.property.JmsRowMessage;
import com.andpostman.rowprocessor.repository.PackageRowRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;

@DataR2dbcTest
@DirtiesContext
class PackageRowServiceTest {

    @Autowired
    private PackageRowRepository rowRepository;

    private PackageRowServiceImpl service;

    @BeforeEach
    public void setup(){
        this.service = new PackageRowServiceImpl(rowRepository);
    }

    @Test
    @DisplayName("Проверка обновления сервиса")
    void whenUpdatePackageRow_thenReturnEmptyResult(){
        Mono<PackageRow> find = service.updateState(new JmsRowMessage(anyInt(),anyInt(),anyString(),anyString(),BigDecimal.ZERO, PackageRowOperationType.PROCESSING),PackageRowOperationType.DONE);
        StepVerifier
                .create(find)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(packageRows -> assertThat(packageRows).isEmpty())
                .verifyComplete();

    }
}
