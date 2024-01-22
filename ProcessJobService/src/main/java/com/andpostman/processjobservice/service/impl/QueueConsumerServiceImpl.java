package com.andpostman.processjobservice.service.impl;

import com.andpostman.processjobservice.command.PackageOperation;
import com.andpostman.processjobservice.command.PackageOperationType;
import com.andpostman.processjobservice.property.JmsReceiveMessage;
import com.andpostman.processjobservice.property.JmsSendMessage;
import com.andpostman.processjobservice.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import com.andpostman.processjobservice.service.QueueConsumerService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QueueConsumerServiceImpl implements QueueConsumerService {

    private final StatusRepository statusRepository;
    private final Map<PackageOperationType, PackageOperation> rowOperationMap;

    public QueueConsumerServiceImpl(List<PackageOperation> list, StatusRepository statusRepository) {
        this.rowOperationMap = list.stream().collect(Collectors.toMap(PackageOperation::getType, Function.identity()));
        this.statusRepository = statusRepository;
    }

    @Override
    public Mono<Void> check(JmsReceiveMessage message) {
        log.info("get message '{}'",message);
        PackageOperation packageOperation = rowOperationMap.get(PackageOperationType.ACTIVE);
        statusRepository.getStatusByState(PackageOperationType.ACTIVE.getCode())
                .flatMap(status -> {
                    JmsSendMessage validateMessage = new JmsSendMessage(status.getPackageId());
                    return packageOperation.send(validateMessage);
                })
                .subscribeOn(Schedulers.parallel()).subscribe();
        return Mono.empty();
    }

}

