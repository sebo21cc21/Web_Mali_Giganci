import { Injectable } from '@angular/core';
import { AngularFireDatabase } from '@angular/fire/compat/database';
import { map } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root',
})

export class FirebaseService {
  constructor(private db: AngularFireDatabase) {}
  getFlagValue(): Observable<boolean | null> {
    return this.db.object(`blockBaby/flag`).valueChanges().pipe(
      map(value => value as boolean | null) 
    );
  }
  updateFlag(value: boolean): Promise<void> {
    return this.db.object(`blockBaby/flag`).set(value);
  }
}