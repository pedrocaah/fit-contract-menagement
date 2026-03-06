import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { ContractService } from '../../core/services/contract.service';
import { Contract } from '../../models/contract.model';
import { ErrorResponse } from '../../models/error-response.model';

@Component({
  selector: 'app-search-contract',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './search-contract.component.html',
  styleUrl: './search-contract.component.css'
})
export class SearchContractComponent {
  private readonly fb = inject(FormBuilder);
  private readonly contractService = inject(ContractService);

  loading = false;
  saving = false;
  inactivating = false;

  foundContract: Contract | null = null;
  feedbackMessage = '';
  errorMessage = '';

  searchForm = this.fb.nonNullable.group({
    contractNumber: ['', [Validators.required]]
  });

  amendmentForm = this.fb.nonNullable.group({
    enterpriseName: ['', [Validators.required]],
    legalName: ['', [Validators.required]],
    enterpriseAdress: ['', [Validators.required]],
    nameCEO: ['', [Validators.required]]
  });

  search(): void {
    this.feedbackMessage = '';
    this.errorMessage = '';
    this.foundContract = null;

    if (this.searchForm.invalid) {
      this.searchForm.markAllAsTouched();
      return;
    }

    this.loading = true;

    this.contractService.findByContractNumber(this.searchForm.getRawValue().contractNumber).subscribe({
      next: (response) => {
        this.foundContract = response;
        this.amendmentForm.setValue({
          enterpriseName: response.enterpriseName,
          legalName: response.legalName,
          enterpriseAdress: response.enterpriseAdress,
          nameCEO: response.nameCEO
        });
      },
      error: (error: HttpErrorResponse) => {
        const apiError = error.error as ErrorResponse;
        this.errorMessage = apiError?.errorCause || 'Contrato não encontrado.';
      },
      complete: () => {
        this.loading = false;
      }
    });
  }

  saveAmendment(): void {
    if (!this.foundContract) {
      return;
    }

    this.feedbackMessage = '';
    this.errorMessage = '';

    if (this.amendmentForm.invalid) {
      this.amendmentForm.markAllAsTouched();
      return;
    }

    this.saving = true;

    this.contractService.amend(this.foundContract.contractNumber, this.amendmentForm.getRawValue()).subscribe({
      next: (response) => {
        this.foundContract = response;
        this.feedbackMessage = 'Contrato alterado com sucesso.';
      },
      error: (error: HttpErrorResponse) => {
        const apiError = error.error as ErrorResponse;
        this.errorMessage = apiError?.errorCause || 'Falha ao alterar contrato.';
      },
      complete: () => {
        this.saving = false;
      }
    });
  }

  inactivate(): void {
    if (!this.foundContract) {
      return;
    }

    this.feedbackMessage = '';
    this.errorMessage = '';
    this.inactivating = true;

    this.contractService.inactivate(this.foundContract.contractNumber).subscribe({
      next: () => {
        if (this.foundContract) {
          this.foundContract = {
            ...this.foundContract,
            status: 'INATIVO'
          };
        }
        this.feedbackMessage = 'Contrato inativado com sucesso.';
      },
      error: (error: HttpErrorResponse) => {
        const apiError = error.error as ErrorResponse;
        this.errorMessage = apiError?.errorCause || 'Falha ao inativar contrato.';
      },
      complete: () => {
        this.inactivating = false;
      }
    });
  }

  hasSearchError(): boolean {
    const control = this.searchForm.controls.contractNumber;
    return control.invalid && (control.dirty || control.touched);
  }

  hasAmendmentError(controlName: keyof typeof this.amendmentForm.controls): boolean {
    const control = this.amendmentForm.controls[controlName];
    return control.invalid && (control.dirty || control.touched);
  }
}
