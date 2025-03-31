import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../login.service';
import Swal from 'sweetalert2';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm: FormGroup;
  
  constructor(private fb: FormBuilder, private loginService: LoginService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required]],
      senha: ['', [Validators.required, Validators.minLength(6)]]
    });
   }

   onSubmit(){
    if(this.loginForm.valid){
 
      this.loginService.loginUser(this.loginForm.get('email')?.value, this.loginForm.get('senha')?.value).subscribe(data => {
      

        console.log(data.token)
        const token = data.token;
        sessionStorage.setItem('accessToken', token);
        Swal.fire({
          title: 'Login realizado com sucesso!',
          text: 'Você será redirecionado para a página inicial.',
          icon: 'success',
          customClass: { 
            popup: 'swal-custom',
            title: 'swal-custom-title',
            confirmButton: 'swal-custom-confirm',         
            
          },
          heightAuto: false,
          confirmButtonText: 'Ok'
        }).then((result) =>{
          if (result.isConfirmed) {
            this.redirectUser();
          }  else if (result.isDismissed) {
            this.redirectUser();
          }
        })
      },
      error => {
        Swal.fire({
          title: 'Login inválido!',
          text: 'Email ou senha inválidos.',
          icon: 'error',
          customClass: { 
            popup: 'swal-custom',
            title: 'swal-custom-title',
            confirmButton: 'swal-custom-confirm',         
            
          },
          heightAuto: false,
          confirmButtonText: 'Ok'
        })
    }  );

    }else{
     console.log("Formulário inválido")
   }
  }


  redirectUser() {
    window.location.href = '/usuarios';
  }
}
