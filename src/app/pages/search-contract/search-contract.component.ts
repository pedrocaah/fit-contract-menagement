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
  foundContract: Contract | null = null;
  errorMessage = '';

  searchForm = this.fb.nonNullable.group({
    contractNumber: ['', [Validators.required]]
  });

  search(): void {
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

  hasSearchError(): boolean {
    const control = this.searchForm.controls.contractNumber;
    return control.invalid && (control.dirty || control.touched);
  }
}
