package com.andpostman.packageprocessor.service.impl;

import com.andpostman.packageprocessor.property.JmsRowMessage;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.andpostman.packageprocessor.command.PackageRowOperation;
import com.andpostman.packageprocessor.command.PackageRowOperationType;
import com.andpostman.packageprocessor.service.SendQueueMessageService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SendQueueMessageServiceImpl implements SendQueueMessageService {

    private final Map<PackageRowOperationType, PackageRowOperation> rowOperationMap;

    public SendQueueMessageServiceImpl(List<PackageRowOperation> list) {
        this.rowOperationMap = list.stream().collect(Collectors.toMap(PackageRowOperation::getType, Function.identity()));
    }

    @Override
    public Mono<Void> sendMessage(JmsRowMessage jmsRowMessage, boolean greenField) {
        PackageRowOperation rowPackageRowOperation = rowOperationMap.get(jmsRowMessage.getType());
        return rowPackageRowOperation.send(jmsRowMessage, greenField);
    }

}
