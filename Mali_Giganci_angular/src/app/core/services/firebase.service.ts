import { Injectable} from '@angular/core';
import { AngularFireDatabase } from '@angular/fire/compat/database';
import { map } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root',
})

export class FirebaseService {
  constructor(private db: AngularFireDatabase) {}

getMemoryValues(difficulty: 'Easy' | 'Hard'): Observable<any> {
  return this.db.object(`blockBaby/Memory/${difficulty}`).valueChanges();
}

getQuizValues(topic: 'Angielski' | 'Flagi' | 'Liczby'): Observable<any> {
  return this.db.object(`blockBaby/Quiz/${topic}`).valueChanges();
}

getTicTacToeValues(difficulty: 'Easy' | 'Hard' | 'Medium' | 'TwoPlayer'): Observable<any> {
  return this.db.object(`blockBaby/TicTacToe/${difficulty}`).valueChanges();
}

getEmail(): Observable<string> {
  return this.db.object(`blockBaby/email`).valueChanges().pipe(
    map(value => value as string)
  );
}

getImageUrl(): Observable<string> {
  return this.db.object(`blockBaby/image`).valueChanges().pipe(
    map(value => value as string)
  );
}

getName(): Observable<string> {
  return this.db.object(`blockBaby/name`).valueChanges().pipe(
    map(value => value as string)
  );
}
getFlagValue(): Observable<boolean | null> {
  return this.db.object(`blockBaby/flag`).valueChanges().pipe(
    map(value => value as boolean | null) 
  );
}
updateFlag(value: boolean): Promise<void> {
  return this.db.object(`blockBaby/flag`).set(value);
}
}
