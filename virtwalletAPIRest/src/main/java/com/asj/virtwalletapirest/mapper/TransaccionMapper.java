package com.asj.virtwalletapirest.mapper;

import com.asj.virtwalletapirest.domain.Transaccion;
import com.asj.virtwalletapirest.domain.dto.TransaccionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {

    @Mapping(source = "id", target = "id")
    TransaccionDTO mapToDto(Transaccion transaccion);

    @Mapping(source = "id", target = "id")
    Transaccion mapToEntity(TransaccionDTO transaccionDTO);
}
