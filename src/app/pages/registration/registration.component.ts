import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  hide: boolean = true;

  registerForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    newpassword: new FormControl('', [Validators.required])
  });

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  registerWithEmailAndPassword(){
    console.log(this.registerForm.value);
    const userData = Object.assign(this.registerForm.value, {email: this.registerForm.value.username});
    console.log(userData);
    this.authService.registerWithEmailAndPassword(userData).then((res: any) => {
      this.router.navigateByUrl('home');
      }).catch((error: any) => {
        console.log(error);
      }); 
    }
}
