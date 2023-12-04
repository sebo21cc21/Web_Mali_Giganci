import { Component, Injectable, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { FirebaseService } from '../../core/services/firebase.service';
import { CommonModule } from '@angular/common';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})

@Component({
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, CommonModule, MatSnackBarModule],
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
  private _snackBar = inject(MatSnackBar);
  private router = inject(Router);
  flagValue: boolean | null = true;
  flagToggled: boolean = false; // Dodaj zmienną flagToggled

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

  block(): void {
    this.firebaseService.updateFlag(false).then(() => {
      console.log(`Użytkownik został zablokowany`);
      this.flagValue = false;
      const snackBarRef = this.openSnackBarBlock();
      snackBarRef.afterDismissed().subscribe(() => {
        this.router.navigateByUrl('/');
      });
    }).catch(error => {
      console.error('Error updating flag: ', error);
    });
  }
  
  unblock(): void {
    this.firebaseService.updateFlag(true).then(() => {
      console.log(`Użytkownik został odblokowany`);
      this.flagValue = true;
      const snackBarRef = this.openSnackBarUnblock();
      snackBarRef.afterDismissed().subscribe(() => {
        this.router.navigateByUrl('/');
      });
    }).catch(error => {
      console.error('Error updating flag: ', error);
    });
  }
  
  getFlag(): void {
    this.firebaseService.getFlagValue().subscribe((value: boolean | null) => {
      this.flagValue = value;
    });
  }
  openSnackBarBlock() {
    return this._snackBar.open('Użytkownik zablokowany poprawnie 😀', 'Zamknij', {
      duration: 2500,
      verticalPosition: 'top',
      horizontalPosition: 'end',
    });
  }

  openSnackBarUnblock() {
    return this._snackBar.open('Użytkownik odblokowany poprawnie 😀', 'Zamknij', {
      duration: 2500,
      verticalPosition: 'top',
      horizontalPosition: 'end',
    });
  }
}
