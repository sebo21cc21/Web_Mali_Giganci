import { Component, OnInit, inject } from '@angular/core';
import { FirebaseService } from '../../core/services/firebase.service';
import { ChartDataset, ChartOptions } from 'chart.js';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-games-summary',
  templateUrl: './games-summary.component.html',
  styleUrls: ['./games-summary.component.scss']
})
export class GamesSummaryComponent implements OnInit {
  // Define properties for chart data
  memoryChartData: ChartDataset[] = [];
  quizChartData: ChartDataset[] = [];
  tictactoeChartData: ChartDataset[] = [];
  // ...other properties...

  constructor(private firebaseService: FirebaseService) { }

  ngOnInit(): void {
    // Fetch and process data for charts
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

  async profil(): Promise<void> {
    try {
      this._router.navigateByUrl('/');
    } catch (error) {
      console.log(error);
    }
  }

}