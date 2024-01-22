package com.andpostman.packagefinisher.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.andpostman.packagefinisher.model.PackageReport;
import com.andpostman.packagefinisher.property.PackageOperationType;
import com.andpostman.packagefinisher.property.PackageRowOperationType;
import com.andpostman.packagefinisher.property.output.RowErrorOutput;
import com.andpostman.packagefinisher.repository.PackageRepository;
import com.andpostman.packagefinisher.repository.PackageRowRepository;
import com.andpostman.packagefinisher.repository.PackageReportRepository;
import com.andpostman.packagefinisher.repository.StatusRepository;
import com.andpostman.packagefinisher.service.OutputService;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
@Service
public class OutputServiceImpl implements OutputService {

    private final PackageRowRepository rowRepository;
    private final StatusRepository statusRepository;
    private final PackageRepository packageRepository;
    private final PackageReportRepository packageReportRepository;


    @Override
    public Mono<Void> checkToPrint(int message) {
        log.info("Работа с пакетом {}", message);
        List<RowErrorOutput> logError = new ArrayList<>();
        AtomicReference<BigDecimal> sumOfResult = new AtomicReference<>();
        AtomicReference<BigDecimal> sumOfError = new AtomicReference<>();
        sumOfResult.set(BigDecimal.ZERO);
        sumOfError.set(BigDecimal.ZERO);
        return rowRepository.findByReqId(message)
                .doOnNext(row -> {
                    if (row.getState() == 2) {
                        sumOfResult.updateAndGet(bigDecimal -> bigDecimal.add(row.getAmount()));
                    }
                    else if (row.getState() == 3) {
                        logError.add(new RowErrorOutput(row.getAccount(), PackageRowOperationType.ERROR.getDescription(), row.getAmount()));
                        sumOfError.updateAndGet(bigDecimal -> bigDecimal.add(row.getAmount()));
                    }
                })
                .then(makeResult(message,sumOfResult,sumOfError,logError));
//                .flatMap(row -> {
//                    if (row.getState() == 2) {
//                        sumOfResult.updateAndGet(bigDecimal -> bigDecimal.add(row.getAmount()));
//                    }
//                    else if (row.getState() == 3) {
//                        logError.add(new RowErrorOutput(row.getAccount(), PackageRowOperationType.ERROR.getDescription(), row.getAmount()));
//                        sumOfError.updateAndGet(bigDecimal -> bigDecimal.add(row.getAmount()));
//                    }
//                    return Flux.just(row);
//                }).then(makeResult(message,sumOfResult,sumOfError,logError));
    }

    private Mono<Void> makeResult(int id, AtomicReference<BigDecimal> sumOfResult, AtomicReference<BigDecimal> sumOfError, List<RowErrorOutput> logError){
        return statusRepository.update(id, PackageOperationType.FINISHED.getCode())
                .then(packageRepository.getPackageById(id)
//                        .doOnSuccess(aPackage -> {
//                            log.info("Отправлено: {}",aPackage.getTotalAmount());
//                            log.info("Зачислено: {}", sumOfResult.get());
//                            log.info("Не зачислено: {}", sumOfError.get());
//                            logError.forEach(el -> log.info(el.toString()));
//                        }))
                .flatMap(aPackage -> {
                    PackageReport packageReport = new PackageReport(aPackage.getTotalAmount(),sumOfResult.get(),sumOfError.get(), LocalTime.now());
                    return packageReportRepository.save(packageReport)
                            .doOnSuccess(el -> log.info(el.toString()))
                            .thenMany(Flux.fromIterable(logError)).map(el -> {
                                log.info(el.toString());
                                return el;
                            }).then(Mono.just(aPackage));
                }))
                .then(Mono.empty());
    }

}
