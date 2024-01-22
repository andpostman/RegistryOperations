package com.andpostman.processjobservice.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PackageOperationType {
    ACTIVE(10),
    FINISHED(20);

    private final int code;
}
