package com.andpostman.packageprocessor.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PackageRowOperationType {
    PROCESSING(1, "Обработка"),
    DONE(2, "Выполнено"),
    ERROR(3, "Работник не найден");

    private final int code;
    private final String description;
}
