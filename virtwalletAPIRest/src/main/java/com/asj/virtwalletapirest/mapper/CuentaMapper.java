package com.asj.virtwalletapirest.mapper;

import com.asj.virtwalletapirest.domain.Cuenta;
import com.asj.virtwalletapirest.domain.dto.CuentaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    @Mapping(source = "id", target = "id")
    CuentaDTO mapToDto(Cuenta cuenta);

    @Mapping(source = "id", target = "id")
    Cuenta mapToEntity(CuentaDTO cuentaDTO);
}
