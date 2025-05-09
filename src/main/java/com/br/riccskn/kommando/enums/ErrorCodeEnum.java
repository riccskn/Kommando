package com.br.riccskn.kommando.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    SUBCOMMAND_NOT_FOUND("SUBCOMMAND_NOT_FOUND");

    public final String errorCode;

    ErrorCodeEnum(String errorCode) {
        this.errorCode = errorCode;
    }

}
