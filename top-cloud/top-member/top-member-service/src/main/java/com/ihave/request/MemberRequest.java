package com.ihave.request;

import com.ihave.entity.Member;
import lombok.Data;

import java.io.Serializable;

@Data
public class MemberRequest extends Member implements Serializable  {

    private static final long serialVersionUID = 1L;


}