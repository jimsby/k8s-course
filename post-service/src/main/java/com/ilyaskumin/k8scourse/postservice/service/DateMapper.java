package com.ilyaskumin.k8scourse.postservice.service;

import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface DateMapper {
    default LocalDate toDate(Instant instant) {
        return LocalDate.ofInstant(instant, ZoneId.of("Europe/Minsk"));
    }
}
