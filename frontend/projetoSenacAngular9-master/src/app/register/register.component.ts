import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { RegisterService } from '../register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerForm: FormGroup;
  
  constructor(private fb: FormBuilder, private registerService: RegisterService) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, Validators.minLength(6)]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
   }

   onSubmit(){
    if(this.registerForm.valid){
 
      this.registerService.registerUser(this.registerForm.get('email')?.value, this.registerForm.get('password')?.value, this.registerForm.get('username')?.value).subscribe(data => {
      

        console.log(data.accessToken)
        const token = data.accessToken;
        sessionStorage.setItem('accessToken', token);
        Swal.fire({
          title: 'Registro realizado com sucesso!',
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
          title: 'Registro inválido!',
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
    window.location.href = '/login';
  }
}
