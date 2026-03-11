package com.example.ToDo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

}
