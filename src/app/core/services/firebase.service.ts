import { Injectable } from '@angular/core';
import { AngularFireDatabase } from '@angular/fire/compat/database';

@Injectable({
  providedIn: 'root',
})
export class FirebaseService {
  constructor(private db: AngularFireDatabase) {}

  updateFlag(value: boolean, childPath: string): Promise<void> {
    return this.db.object(`blockBaby/flag/${childPath}`).set(value);
  }
}