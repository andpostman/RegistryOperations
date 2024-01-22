package com.andpostman.rowprocessor.service.impl;

import com.andpostman.rowprocessor.command.PackageRowOperation;
import com.andpostman.rowprocessor.command.PackageRowOperationType;
import com.andpostman.rowprocessor.service.CommandProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.property.JmsRowMessage;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandProcessServiceImpl implements CommandProcessService {

    private final Map<PackageRowOperationType, PackageRowOperation> rowOperationMap;

    public CommandProcessServiceImpl(List<PackageRowOperation> list) {
        this.rowOperationMap = list.stream().collect(Collectors.toMap(PackageRowOperation::getType, Function.identity()));
    }

    @Override
    public Mono<Void> executeCommand(JmsRowMessage rowInfo) {
        log.info("get message '{}'",rowInfo);
        PackageRowOperation rowPackageRowOperation = rowOperationMap.get(rowInfo.getType());
        return rowPackageRowOperation.send(rowInfo);
    }
}