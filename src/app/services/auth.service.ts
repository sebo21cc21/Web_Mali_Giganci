import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { GoogleAuthProvider, FacebookAuthProvider } from 'firebase/auth'; // Correct imports


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private afs: AngularFireAuth) { }

  isLoggedIn(): boolean {
    return this.afs.authState !== null && this.afs.authState !== undefined;
  }

  signInWithGoogle() {
    const provider = new GoogleAuthProvider();
    return this.afs.signInWithPopup(provider);
  }

  signInWithFacebook() {
    const provider = new FacebookAuthProvider();
    return this.afs.signInWithPopup(provider);
  }
  registerWithEmailAndPassword(user: { email: string, password: string }) {
    return this.afs.createUserWithEmailAndPassword(user.email, user.password);
  }

  signWithEmailAndPassword(user: { email: string, password: string }) {
    return this.afs.signInWithEmailAndPassword(user.email, user.password);
  }
}
