import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './shared/guard/auth.guard';

const routes: Routes = [
  {
  path: '',
  redirectTo: 'login',
  pathMatch: 'full' 
  },
  {
    path: 'login',
  component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent,  
    canActivate: [AuthGuard]
  },
  {
    path: 'register',
    component: RegistrationComponent
  }
];

@NgModule({
  declarations: [], 
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
