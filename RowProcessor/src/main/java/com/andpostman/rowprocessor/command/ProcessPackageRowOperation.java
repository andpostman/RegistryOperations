package com.andpostman.rowprocessor.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import com.andpostman.rowprocessor.property.JmsRowMessage;
import com.andpostman.rowprocessor.service.impl.PackageRowServiceImpl;
import com.andpostman.rowprocessor.service.impl.RowErrorServiceImpl;
import com.andpostman.rowprocessor.service.impl.RowResultServiceImpl;
import com.andpostman.rowprocessor.service.impl.WorkerServiceImpl;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProcessPackageRowOperation implements PackageRowOperation {

    private final RowResultServiceImpl resultService;
    private final RowErrorServiceImpl errorService;
    private final PackageRowServiceImpl packageRowService;
    private final WorkerServiceImpl workerService;

    @Override
    public Mono<Void> send(JmsRowMessage row) {
        return Flux.zip(
                workerService.getWorkerByAccount(row.getAccount()),
                workerService.getWorkerByFio(row.getFio()),
                (workerByAccount, workerByFio) -> {
                    if (workerByFio.getFio() == null && workerByAccount.getAccount() == null) {
                        log.info("Работник c ФИО {} и счетом {} не найден", row.getFio(),row.getAccount());
                        packageRowService.updateState(row, PackageRowOperationType.ERROR).then(errorService.insertErrorData(row, PackageRowOperationType.ERROR.getDescription())).subscribe();
                    }
                    else if(workerByFio.getFio() == null){
                        log.info("ФИО {} не найдено", row.getFio());
                        packageRowService.updateState(row, PackageRowOperationType.ERROR).then(errorService.insertErrorData(row, PackageRowOperationType.ERROR.getDescription())).subscribe();
                    }
                    else if (workerByAccount.getAccount() == null){
                        log.info("Счет {} не найден", row.getAccount());
                        packageRowService.updateState(row, PackageRowOperationType.ERROR).then(errorService.insertErrorData(row, PackageRowOperationType.ERROR.getDescription())).subscribe();
                    }
                    else {
                        log.info("Платеж успешно поступил на счет " + row.getFio());
                        packageRowService.updateState(row, PackageRowOperationType.DONE).then(resultService.insertResultData(row, row.getAmount())).subscribe();
                    }
                    return Mono.empty();

        }).subscribeOn(Schedulers.parallel()).then(Mono.empty());
    }

    @Override
    public PackageRowOperationType getType() {
        return PackageRowOperationType.PROCESSING;
    }


}