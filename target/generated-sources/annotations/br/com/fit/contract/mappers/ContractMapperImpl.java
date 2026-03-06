package br.com.fit.contract.mappers;

import br.com.fit.contract.dtos.AmendmentContractRequest;
import br.com.fit.contract.dtos.AmendmentContractResponse;
import br.com.fit.contract.dtos.CreateContractRequest;
import br.com.fit.contract.dtos.CreateContractResponse;
import br.com.fit.contract.dtos.GetContractResponse;
import br.com.fit.contract.entities.ContractEntity;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-06T00:06:13-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class ContractMapperImpl implements ContractMapper {

    @Override
    public ContractEntity toContractEntity(CreateContractRequest request) {
        if ( request == null ) {
            return null;
        }

        ContractEntity contractEntity = new ContractEntity();

        contractEntity.setNumberCnpj( request.numberCnpj() );
        contractEntity.setEnterpriseName( request.enterpriseName() );
        contractEntity.setLegalName( request.legalName() );
        contractEntity.setEnterpriseAdress( request.enterpriseAdress() );
        contractEntity.setNameCEO( request.nameCEO() );

        contractEntity.setContractNumber( request.numberCnpj().substring(0, 12) );

        return contractEntity;
    }

    @Override
    public CreateContractResponse toCreateContractResponse(ContractEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String numberCnpj = null;
        String enterpriseName = null;
        String enterpriseAdress = null;
        String legalName = null;
        String nameCEO = null;
        Instant contractCreatedAt = null;
        String contractNumber = null;
        String status = null;

        numberCnpj = maskCnpj( entity.getNumberCnpj() );
        enterpriseName = entity.getEnterpriseName();
        enterpriseAdress = entity.getEnterpriseAdress();
        legalName = entity.getLegalName();
        nameCEO = entity.getNameCEO();
        contractCreatedAt = entity.getContractCreatedAt();
        contractNumber = entity.getContractNumber();
        status = entity.getStatus();

        CreateContractResponse createContractResponse = new CreateContractResponse( numberCnpj, enterpriseName, enterpriseAdress, legalName, nameCEO, contractCreatedAt, contractNumber, status );

        return createContractResponse;
    }

    @Override
    public AmendmentContractResponse toAmendmentContractResponse(ContractEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String numberCnpj = null;
        String enterpriseName = null;
        String enterpriseAdress = null;
        String legalName = null;
        String nameCEO = null;
        Instant contractCreatedAt = null;
        String contractNumber = null;
        String status = null;

        numberCnpj = maskCnpj( entity.getNumberCnpj() );
        enterpriseName = entity.getEnterpriseName();
        enterpriseAdress = entity.getEnterpriseAdress();
        legalName = entity.getLegalName();
        nameCEO = entity.getNameCEO();
        contractCreatedAt = entity.getContractCreatedAt();
        contractNumber = entity.getContractNumber();
        status = entity.getStatus();

        AmendmentContractResponse amendmentContractResponse = new AmendmentContractResponse( numberCnpj, enterpriseName, enterpriseAdress, legalName, nameCEO, contractCreatedAt, contractNumber, status );

        return amendmentContractResponse;
    }

    @Override
    public void updateEntity(AmendmentContractRequest request, ContractEntity entity) {
        if ( request == null ) {
            return;
        }

        if ( request.enterpriseName() != null ) {
            entity.setEnterpriseName( request.enterpriseName() );
        }
        if ( request.legalName() != null ) {
            entity.setLegalName( request.legalName() );
        }
        if ( request.enterpriseAdress() != null ) {
            entity.setEnterpriseAdress( request.enterpriseAdress() );
        }
        if ( request.nameCEO() != null ) {
            entity.setNameCEO( request.nameCEO() );
        }
    }

    @Override
    public GetContractResponse toGetContractResponse(ContractEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String numberCnpj = null;
        String enterpriseName = null;
        String enterpriseAdress = null;
        String legalName = null;
        String nameCEO = null;
        Instant contractCreatedAt = null;
        String contractNumber = null;
        String status = null;

        numberCnpj = maskCnpj( entity.getNumberCnpj() );
        enterpriseName = entity.getEnterpriseName();
        enterpriseAdress = entity.getEnterpriseAdress();
        legalName = entity.getLegalName();
        nameCEO = entity.getNameCEO();
        contractCreatedAt = entity.getContractCreatedAt();
        contractNumber = entity.getContractNumber();
        status = entity.getStatus();

        GetContractResponse getContractResponse = new GetContractResponse( numberCnpj, enterpriseName, enterpriseAdress, legalName, nameCEO, contractCreatedAt, contractNumber, status );

        return getContractResponse;
    }
}
