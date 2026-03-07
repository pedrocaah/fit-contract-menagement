import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { ContractService } from '../../core/services/contract.service';
import { ErrorResponse } from '../../models/error-response.model';

@Component({
  selector: 'app-create-contract',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './create-contract.component.html',
  styleUrl: './create-contract.component.css'
})
export class CreateContractComponent {
  private readonly fb = inject(FormBuilder);
  private readonly contractService = inject(ContractService);

  loading = false;
  successMessage = '';
  errorMessage = '';

  form = this.fb.nonNullable.group({
    numberCnpj: ['', [Validators.required, Validators.minLength(14), Validators.maxLength(14)]],
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

    this.loading = true;

    this.contractService.create(this.form.getRawValue()).subscribe({
      next: (response) => {
        this.successMessage = `Contrato criado com sucesso. Número: ${response.contractNumber}`;
        this.form.reset({
          numberCnpj: '',
          enterpriseName: '',
          legalName: '',
          enterpriseAdress: '',
          nameCEO: ''
        });
      },
      error: (error: HttpErrorResponse) => {
        const apiError = error.error as ErrorResponse;
        this.errorMessage = apiError?.errorCause || 'Falha ao criar contrato.';
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
