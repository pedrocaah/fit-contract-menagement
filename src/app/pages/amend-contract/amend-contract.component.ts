import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { ContractService } from '../../core/services/contract.service';
import { ErrorResponse } from '../../models/error-response.model';

@Component({
  selector: 'app-amend-contract',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './amend-contract.component.html',
  styleUrl: './amend-contract.component.css'
})
export class AmendContractComponent {
  private readonly fb = inject(FormBuilder);
  private readonly contractService = inject(ContractService);

  loading = false;
  successMessage = '';
  errorMessage = '';

  form = this.fb.nonNullable.group({
    contractNumber: ['', [Validators.required]],
    enterpriseName: ['', [Validators.required]],
    legalName: ['', [Validators.required]],
    enterpriseAdress: ['', [Validators.required]],
    nameCEO: ['', [Validators.required]]
  });

  submit(): void {
    this.successMessage = '';
    this.errorMessage = '';

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const { contractNumber, ...payload } = this.form.getRawValue();
    this.loading = true;

    this.contractService.amend(contractNumber, payload).subscribe({
      next: (response) => {
        this.successMessage = `Contrato ${response.contractNumber} alterado com sucesso.`;
      },
      error: (error: HttpErrorResponse) => {
        const apiError = error.error as ErrorResponse;
        this.errorMessage = apiError?.errorCause || 'Falha ao aditar contrato.';
      },
      complete: () => {
        this.loading = false;
      }
    });
  }

  hasError(controlName: keyof typeof this.form.controls): boolean {
    const control = this.form.controls[controlName];
    return control.invalid && (control.dirty || control.touched);
  }
}
