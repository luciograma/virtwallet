import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Cuenta} from "../../entities/cuenta";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DataCuentaService} from "../../services/data-cuenta.service";
import {Transaccion} from "../../entities/transaccion";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  cuenta={"dni":"","nombre":"","apellido":"","password":""};
  registerForm: FormGroup;

  constructor(private router: Router, private formBuilder: FormBuilder, private cuentaService: DataCuentaService) {
    this.registerForm = formBuilder.group({
      dni: ['', Validators.compose([
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(8)])],
      nombre: ['', Validators.compose([
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20)])],
      apellido: ['', Validators.compose([
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20)])],
      password: ['', Validators.compose([
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(10)])],
      checkPassword: ['', Validators.compose([
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(10)
      ])]
    });
  }

  get password() {
    return this.registerForm.value.password;
  }

  ngOnInit(): void {
  }

  irLogin() {
    this.router.navigate(['']);
  }

  registrar() {
      this.cuenta.dni = this.registerForm.value.dni;
      this.cuenta.nombre = this.registerForm.value.nombre;
      this.cuenta.apellido = this.registerForm.value.apellido;
      this.cuenta.password = this.registerForm.value.password;
      this.guardarCuenta();
      console.log("Se ha creado un usuario de manera válida" + this.registerForm.value);
      console.log(this.cuenta.toString());
  }

  private guardarCuenta() {
    this.cuentaService.registrarCuenta(this.cuenta)
      .subscribe(dato => {
          console.log(dato);
        },
        error => {
          console.log(error);
        });
  }

  submitForm() {
    if (this.registerForm.invalid) {
      return;
    }
    this.registrar();
    this.irLogin();
  }
}
