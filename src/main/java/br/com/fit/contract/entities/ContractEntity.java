package br.com.fit.contract.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_contract")
@NoArgsConstructor
@Getter
@Setter
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 14, nullable = false)
    private String numberCnpj;
    @Column(nullable = false)
    private String enterpriseName;
    @Column(nullable = false)
    private String enterpriseAdress;
    @Column(nullable = false)
    private String clientFullName;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant contractCreatedAt;
    @Column(nullable = false, unique = true)
    private BigDecimal contractNumber;

}
