import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';

export const dashboardRoutes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    children: [
      { path: 'permissions', loadComponent: () => import('./permissions/permissions.component').then(m => m.PermissionsComponent) },
      { path: 'sales/new', loadComponent: () => import('./sales/new-sale.component').then(m => m.NewSaleComponent) },
      { path: 'sales/funnel', loadComponent: () => import('./sales/funnel.component').then(m => m.FunnelComponent) },
      { path: 'sales/data', loadComponent: () => import('./sales/data.component').then(m => m.SalesDataComponent) },
      { path: 'sales/calendar', loadComponent: () => import('./sales/calendar.component').then(m => m.SalesCalendarComponent) },
      { path: 'sales/finished', loadComponent: () => import('./sales/finished.component').then(m => m.SalesFinishedComponent) },
      { path: 'clients', loadComponent: () => import('./clients/manage-clients.component').then(m => m.ManageClientsComponent) },
      { path: 'users', loadComponent: () => import('./users/manage-users.component').then(m => m.ManageUsersComponent) },
      { path: '', redirectTo: 'permissions', pathMatch: 'full' }
    ]
  }
];
