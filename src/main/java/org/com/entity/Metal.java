package org.com.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Metal {

    private Long Id;
    private String Name;
    private String NameEng;
    private String NameBel;
}
