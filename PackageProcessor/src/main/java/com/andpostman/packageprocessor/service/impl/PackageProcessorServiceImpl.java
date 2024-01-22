package com.andpostman.packageprocessor.service.impl;

import com.andpostman.packageprocessor.property.JmsRowMessage;
import com.andpostman.packageprocessor.property.PackageOperationType;
import com.andpostman.packageprocessor.repository.PackageRepository;
import com.andpostman.packageprocessor.repository.PackageRowRepository;
import com.andpostman.packageprocessor.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import com.andpostman.packageprocessor.command.PackageRowOperationType;
import com.andpostman.packageprocessor.dto.PackageDto;
import com.andpostman.packageprocessor.dto.WorkerDto;
import com.andpostman.packageprocessor.model.Package;
import com.andpostman.packageprocessor.model.PackageRow;
import com.andpostman.packageprocessor.model.Status;
import com.andpostman.packageprocessor.service.PackageProcessorService;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageProcessorServiceImpl implements PackageProcessorService {

    private final PackageRepository packageRepository;
    private final PackageRowRepository rowRepository;
    private final SendQueueMessageServiceImpl messageService;
    private final StatusRepository statusRepository;

    @Override
    public Mono<String> processPackage(PackageDto packageDto, boolean greenField) {
        return insertPackage(packageDto,greenField).map(aPackage ->
                insertRow(packageDto.getWorkers(),aPackage.getId(), greenField).map(packageRow ->
                     sendRow(packageRow,greenField).subscribeOn(Schedulers.parallel()))
                        .then(insertStatus(aPackage.getId())).subscribe())
                .then(Mono.just("Пакет успешно создан!")).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Package> insertPackage(PackageDto packageDto, boolean greenField){
        return packageRepository.save(new Package(packageDto.getContractNumber(), packageDto.getOrganization(),packageDto.getTotalAmount(), greenField));
    }

    @Override
    public Flux<PackageRow> insertRow(List<WorkerDto> workers, int packageId, boolean greenField) {
        return Flux.fromIterable(workers)
                .flatMap(workerDto ->
                        rowRepository.save(new PackageRow(packageId, workerDto.getAccount(), workerDto.getFio(), workerDto.getAmount(), greenField, PackageRowOperationType.PROCESSING.getCode()))
                )
                .subscribeOn(Schedulers.parallel());
    }

    @Override
    public Mono<Void> sendRow(PackageRow packageRow, boolean greenField) {
        JmsRowMessage jmsRowMessage = new JmsRowMessage(packageRow.getId(), packageRow.getReqId(),packageRow.getAccount(),packageRow.getFio(),packageRow.getAmount(), PackageRowOperationType.PROCESSING);
        return messageService.sendMessage(jmsRowMessage, greenField);
    }

    @Override
    public Mono<Status> insertStatus(int packageId) {
        Status status = new Status(packageId, LocalTime.now(), PackageOperationType.ACTIVE.getCode());
        return statusRepository.save(status);
    }

}
