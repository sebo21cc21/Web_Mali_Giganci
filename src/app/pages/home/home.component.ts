import { Component, Injectable, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { FirebaseService } from '../../core/services/firebase.service';
import { CommonModule } from '@angular/common';

@Injectable({
  providedIn: 'root'
})

@Component({
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, CommonModule],
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  styles: [
    `
      mat-toolbar {
        display: flex;
        justify-content: space-between;
      }
    `,
  ],
})
export default class HomeComponent {
  flagValue: boolean | null = null;
  constructor(private firebaseService: FirebaseService) {}
  private _router = inject(Router);

  private authservice = inject(AuthService);

  async logOut(): Promise<void> {
    try {
      await this.authservice.logOut();
      this._router.navigateByUrl('/auth/log-in');
    } catch (error) {
      console.log(error);
    }
  }
  toggleFlag(): void {
    this.firebaseService.updateFlag(true).then(() => {
      console.log(`lag updated`);
      this.firebaseService.getFlagValue().subscribe((value: boolean | null) => {
        this.flagValue = value;
      });
    }).catch(error => {
      console.error('Error updating flag: ', error);
    });
  }

  getFlag(): void{
    this.firebaseService.getFlagValue().subscribe((value: boolean | null) => {
      this.flagValue = value;
    });
  }
  
}
