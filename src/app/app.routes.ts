import { Routes } from '@angular/router';
import { CreateContractComponent } from './pages/create-contract/create-contract.component';
import { SearchContractComponent } from './pages/search-contract/search-contract.component';
import { AmendContractComponent } from './pages/amend-contract/amend-contract.component';
import { InactivateContractComponent } from './pages/inactivate-contract/inactivate-contract.component';

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
    path: 'contracts/get',
    component: SearchContractComponent
  },
  {
    path: 'contracts/amend',
    component: AmendContractComponent
  },
  {
    path: 'contracts/inactivate',
    component: InactivateContractComponent
  },
  {
    path: '**',
    redirectTo: 'contracts/create'
  }
];
