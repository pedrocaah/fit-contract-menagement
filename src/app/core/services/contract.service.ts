import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { Contract } from '../../models/contract.model';
import { CreateContractRequest } from '../../models/create-contract-request.model';
import { AmendmentContractRequest } from '../../models/amendment-contract-request.model';

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/contracts`;

  create(payload: CreateContractRequest): Observable<Contract> {
    return this.http.post<Contract>(this.baseUrl, payload);
  }

  findByContractNumber(contractNumber: string): Observable<Contract> {
    return this.http.get<Contract>(`${this.baseUrl}/${contractNumber}`);
  }

  amend(contractNumber: string, payload: AmendmentContractRequest): Observable<Contract> {
    return this.http.put<Contract>(`${this.baseUrl}/${contractNumber}`, payload);
  }

  inactivate(contractNumber: string): Observable<void> {
    return this.http.patch<void>(`${this.baseUrl}/${contractNumber}`, {});
  }
}
