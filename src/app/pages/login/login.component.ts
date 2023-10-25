import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{

  hide: boolean = true;
  passwordControl: FormControl = new FormControl('', [Validators.required]);
  
  loginForm : FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])  
  });
  constructor(private authService: AuthService, private router: Router) { }
   
  ngOnInit(): void {

  }

  loginWithGoogle(){
    this.authService.signInWithGoogle().then((res: any) => {
      this.router.navigateByUrl('home');
      }).catch((error: any) => {
        console.log(error);
      });
  }

  
  loginWithFacebook(){
    this.authService.signInWithFacebook().then((res: any) => {
      this.router.navigateByUrl('home');
      }).catch((error: any) => {
        console.log(error);
      });
  }

  loginWithEmailAndPassword(){
    console.log(this.loginForm.value);
    const userData = Object.assign(this.loginForm.value, {email: this.loginForm.value.username});
    console.log(userData);
    this.authService.signWithEmailAndPassword(userData).then((res: any) => {
      this.router.navigateByUrl('home');
      }).catch((error: any) => {
        console.log(error);
      }); 
    }
}

