import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
// import { FirebaseService } from '../../core/services/firebase.service';

@Component({
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule],
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
  // constructor(private firebaseService: FirebaseService) {}
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
  // toggleFlag(childName: string): void {
  //   this.firebaseService.updateFlag(true, childName).then(() => {
  //     console.log(`${childName} flag updated`);
  //   }).catch(error => {
  //     console.error('Error updating flag: ', error);
  //   });
  // }
}
