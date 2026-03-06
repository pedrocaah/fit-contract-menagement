import { Routes } from '@angular/router';
import { CreateContractComponent } from './pages/create-contract/create-contract.component';
import { SearchContractComponent } from './pages/search-contract/search-contract.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'contracts/create',
    pathMatch: 'full'
  },
  {
    path: 'contracts/create',
    component: CreateContractComponent
  },
  {
    path: 'contracts/search',
    component: SearchContractComponent
  },
  {
    path: '**',
    redirectTo: 'contracts/create'
  }
];
