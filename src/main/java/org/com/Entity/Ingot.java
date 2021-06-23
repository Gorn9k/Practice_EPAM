package org.com.Entity;

import lombok.Builder;
import lombok.ToString;

import java.util.Date;

@Builder
@ToString
public class Ingot {

    private Date Date;
    private Long MetalID;
    private Double NoCertificateDollars;
    private Double NoCertificateRubles;
    private Double CertificateDollars;
    private Double CertificateRubles;
    private Double BanksDollars;
    private Double BanksRubles;
    private Double EntitiesDollars;
    private Double EntitiesRubles;
}
