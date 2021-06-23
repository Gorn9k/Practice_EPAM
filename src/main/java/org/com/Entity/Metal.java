package org.com.Entity;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Metal {

    private Long Id;
    private String Name;
    private String NameEng;
    private String NameBel;
}
