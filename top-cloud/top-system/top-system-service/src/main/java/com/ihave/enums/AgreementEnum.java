package com.ihave.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgreementEnum {

    PRIVACY("PRIVACY"),
    USER("USER");

    private final String code;

    public static AgreementEnum getAgreement(String value){
        AgreementEnum[] values = AgreementEnum.values();
        for (AgreementEnum v : values) {
            if(v.getCode().equalsIgnoreCase(value)){
                return v ;
            }
        }
        return null ;
    }
}