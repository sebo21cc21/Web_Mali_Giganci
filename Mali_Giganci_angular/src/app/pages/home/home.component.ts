import { Component, Injectable, OnDestroy, OnInit, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { FirebaseService } from '../../core/services/firebase.service';
import { CommonModule } from '@angular/common';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';
@Injectable({
  providedIn: 'root',
})

@Component({
  standalone: true,
  imports: [
    MatToolbarModule,
    MatButtonModule,
    CommonModule,
    MatSnackBarModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule, 
  ],
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
export default class HomeComponent implements OnInit, OnDestroy {
  email: string | null = null;
  name: string | null = null;
  imageUrl: string | null = null;
  private subscriptions: Subscription[] = [];
  
  private _snackBar = inject(MatSnackBar);
  private router = inject(Router);
  flagValue: boolean | null = true;

  constructor(private firebaseService: FirebaseService) {
  }
  ngOnInit(): void {
    const emailSub = this.firebaseService.getEmail().subscribe(email => {
      this.email = email;
    });
    this.subscriptions.push(emailSub);

    const nameSub = this.firebaseService.getName().subscribe(name => {
      this.name = name;
    });
    this.subscriptions.push(nameSub);
    const imageSub = this.firebaseService.getImageUrl().subscribe(url => {
      this.imageUrl = url;
    });
    this.subscriptions.push(imageSub);
  }

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

  async summary(): Promise<void> {
    try {
      this._router.navigateByUrl('/games-summary');
    } catch (error) {
      console.log(error);
    }
  }


  blockUser(): void {
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
  
  unblockUser(): void {
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

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }
}
