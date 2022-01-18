package com.ihave.aspect;

import lombok.Getter;

@Getter
public enum LogEnum {

    INFO(1),DEBUG(2),WARING(3),ERROR(4);

    final int code;

    LogEnum(int code){
        this.code = code;
    }

}
