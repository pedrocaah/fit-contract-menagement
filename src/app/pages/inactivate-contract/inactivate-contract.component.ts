import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { ContractService } from '../../core/services/contract.service';
import { ErrorResponse } from '../../models/error-response.model';

@Component({
  selector: 'app-inactivate-contract',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './inactivate-contract.component.html',
  styleUrl: './inactivate-contract.component.css'
})
export class InactivateContractComponent {
  private readonly fb = inject(FormBuilder);
  private readonly contractService = inject(ContractService);

  loading = false;
  successMessage = '';
  errorMessage = '';

  form = this.fb.nonNullable.group({
    contractNumber: ['', [Validators.required]]
  });

  submit(): void {
    this.successMessage = '';
    this.errorMessage = '';

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const contractNumber = this.form.getRawValue().contractNumber;
    this.loading = true;

    this.contractService.inactivate(contractNumber).subscribe({
      next: () => {
        this.successMessage = `Contrato ${contractNumber} inativado com sucesso.`;
      },
      error: (error: HttpErrorResponse) => {
        const apiError = error.error as ErrorResponse;
        this.errorMessage = apiError?.errorCause || 'Falha ao inativar contrato.';
      },
      complete: () => {
        this.loading = false;
      }
    });
  }

  hasError(): boolean {
    const control = this.form.controls.contractNumber;
    return control.invalid && (control.dirty || control.touched);
  }
}
