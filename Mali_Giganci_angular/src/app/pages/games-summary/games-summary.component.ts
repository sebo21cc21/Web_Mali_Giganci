import { Component, OnInit } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { FirebaseService } from '../../core/services/firebase.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { Router } from '@angular/router';

interface GameData {
  game: string;
  difficulty: string;
  numberOfGames: number;
  points: number;
}

@Component({
  selector: 'app-games-summary',
  templateUrl: './games-summary.component.html',
  styleUrls: ['./games-summary.component.scss']
})
export class GamesSummaryComponent implements OnInit {
  memoryEasy: any = {}; 
  memoryHard: any = {}; 
  ticTacToe: any = {};
  quiz: any = {};
  dataSource: GameData[] = [];
  dataSourceMemory: GameData[] = [];
  dataSourceTicTacToe: GameData[] = [];
  dataSourceQuiz: GameData[] = [];
  displayedColumns = ['game', 'difficulty', 'numberOfGames', 'points'];

  constructor(private firebaseService: FirebaseService, private authService: AuthService, private router: Router) { }

  async ngOnInit(): Promise<void> {
    const memoryEasy = await firstValueFrom(this.firebaseService.getMemoryValues('Easy'));
    const memoryHard = await firstValueFrom(this.firebaseService.getMemoryValues('Hard'));
    const ticTacToeEasy = await firstValueFrom(this.firebaseService.getTicTacToeValues('Easy'));
    const ticTacToeHard = await firstValueFrom(this.firebaseService.getTicTacToeValues('Hard'));
    const ticTacToeMedium = await firstValueFrom(this.firebaseService.getTicTacToeValues('Medium'));
    const ticTacToeTwoPlayer = await firstValueFrom(this.firebaseService.getTicTacToeValues('TwoPlayer'));
    const quizEnglish = await firstValueFrom(this.firebaseService.getQuizValues('Angielski'));
    const quizFlags = await firstValueFrom(this.firebaseService.getQuizValues('Flagi'));
    const quizNumbers = await firstValueFrom(this.firebaseService.getQuizValues('Liczby'));

    this.memoryEasy = memoryEasy;
    this.memoryHard = memoryHard;
    this.ticTacToe = { easy: ticTacToeEasy, hard: ticTacToeHard, medium: ticTacToeMedium, twoPlayer: ticTacToeTwoPlayer };
    this.quiz = { english: quizEnglish, flags: quizFlags, numbers: quizNumbers };

    this.updateDataSource();
  }

  updateDataSource() {
    this.dataSource = [
      // Memory
      { game: 'Memory', difficulty: 'Easy', numberOfGames: this.memoryEasy.numberOfGames, points: this.memoryEasy.point },
      { game: 'Memory', difficulty: 'Hard', numberOfGames: this.memoryHard.numberOfGames, points: this.memoryHard.point },
  
      // TicTacToe
      { game: 'TicTacToe', difficulty: 'Easy', numberOfGames: this.ticTacToe.easy.youEasyTicTacToe, points: this.ticTacToe.easy.computerEasyTicTacToe },
      { game: 'TicTacToe', difficulty: 'Hard', numberOfGames: this.ticTacToe.hard.youHardTicTacToe, points: this.ticTacToe.hard.computerHardTicTacToe },
      { game: 'TicTacToe', difficulty: 'Medium', numberOfGames: this.ticTacToe.medium.youMediumTicTacToe, points: this.ticTacToe.medium.computerMediumTicTacToe },
      { game: 'TicTacToe', difficulty: 'TwoPlayer', numberOfGames: this.ticTacToe.twoPlayer.circleTwoPlayerTicTacToe, points: this.ticTacToe.twoPlayer.crossTwoPlayerTicTacToe },
  
      // Quiz
      { game: 'Quiz', difficulty: 'Angielski', numberOfGames: this.quiz.english.angielskiTotalQuestions, points: this.quiz.english.angielskiCorrectAnswers },
      { game: 'Quiz', difficulty: 'Flagi', numberOfGames: this.quiz.flags.flagiTotalQuestions, points: this.quiz.flags.flagiCorrectAnswers },
      { game: 'Quiz', difficulty: 'Liczby', numberOfGames: this.quiz.numbers.liczbyTotalQuestions, points: this.quiz.numbers.liczbyCorrectAnswers }
    ];
    this.dataSourceMemory = this.dataSource.filter(game => game.game === 'Memory');
    this.dataSourceTicTacToe = this.dataSource.filter(game => game.game === 'TicTacToe');
    this.dataSourceQuiz = this.dataSource.filter(game => game.game === 'Quiz');
  }

  async logOut(): Promise<void> {
    try {
      await this.authService.logOut();
      this.router.navigateByUrl('/auth/log-in');
    } catch (error) {
      console.error(error);
    }
  }

  async profil(): Promise<void> {
    try {
      this.router.navigateByUrl('/');
    } catch (error) {
      console.error(error);
    }
  }
}
