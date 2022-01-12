package com.ihave.request;

import com.ihave.entity.VersionUpgrade;
import lombok.Data;

import java.io.Serializable;

@Data
public class VersionUpgradeRequest extends VersionUpgrade implements Serializable  {

    private static final long serialVersionUID = 1L;


}