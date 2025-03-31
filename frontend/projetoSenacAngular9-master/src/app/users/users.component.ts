import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  alunos: any[] = [];

  constructor(private loginService: LoginService) { }

ngOnInit(): void {

  this.getUsers();
}

getUsers() {

  this.loginService.getUsers().subscribe(alunos => {
    this.alunos = alunos;
    console.log(alunos)
  });

}

}
