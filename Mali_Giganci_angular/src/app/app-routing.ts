import { RouterModule, Routes } from '@angular/router';

import { authGuard, publicGuard } from './core/guards';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GamesSummaryComponent } from './pages/games-summary/games-summary.component';


const routes: Routes = [
  {
    path: '',
    canActivate: [authGuard],
    loadComponent: () => import('./pages/home/home.component'),
  },
  {
    path: 'games-summary',
    component: GamesSummaryComponent,
    canActivate: [authGuard] 
  },
  {
    path: 'auth',
    canActivate: [publicGuard],
    children: [
      {
        path: 'sign-up',
        loadComponent: () => import('./pages/auth/sign-up/sign-up.component'),
      },
      {
        path: 'log-in',
        loadComponent: () => import('./pages/auth/log-in/log-in.component'),
      },
    ],
  },
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule {
}