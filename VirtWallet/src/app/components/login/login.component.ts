import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {validarEspacios} from "../../validators/espacios.validator";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private router: Router, private formBuilder: FormBuilder) {
  }

  get dni() {
    return this.loginForm.get('dni')
  }

  get password() {
    return this.loginForm.get('password')
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      dni: ['', Validators.compose([
        Validators.required,
        Validators.minLength(2),
        validarEspacios,
        Validators.maxLength(8)])],
      password: ['', Validators.compose([
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(10)])],
    });
  }

  login() {
    if (!this.loginForm.invalid) {
      console.log(this.loginForm)
      this.router.navigate(["home/" + this.loginForm.value.dni]);
    }
  }

  irRegister() {
    this.router.navigate(['register']);
  }

  //TODO borrar cuando todo funcione
  verCuentas() {
    this.router.navigate(['cuentas']);
  }

  verTransacciones() {
    this.router.navigate(['transacciones']);
  }

}
