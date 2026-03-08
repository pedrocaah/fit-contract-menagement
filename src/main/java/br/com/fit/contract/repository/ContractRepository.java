package br.com.fit.contract.repository;

import br.com.fit.contract.entities.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, UUID> {

    Optional<ContractEntity> findByContractNumber(String contractNumber);

    boolean existsByNumberCnpjAndStatus(String numberCnpj, String status);

  @Query(value = "SELECT nextval('contract_sequence')", nativeQuery = true)
  Long getNextContractSequence();
}
