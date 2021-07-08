package org.com.entity;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class Ingot {

    private Date Date;
    private Long MetalID;
    private Double Nominal;
    private Double NoCertificateDollars;
    private Double NoCertificateRubles;
    private Double CertificateDollars;
    private Double CertificateRubles;
    private Double BanksDollars;
    private Double BanksRubles;
    private Double EntitiesDollars;
    private Double EntitiesRubles;
}
