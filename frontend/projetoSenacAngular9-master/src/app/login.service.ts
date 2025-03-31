import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl ='http://localhost:8080'

  constructor(private http: HttpClient) { }


  getHeaders(): HttpHeaders{
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + `${this.getToken()}`
    })
  }

getToken(){
  return sessionStorage.getItem('accessToken');
}

getUsers(): Observable<any[]>{
  return this.http.get<any[]>(`${this.apiUrl}/alunos/listar`, {headers: this.getHeaders()});
}

loginUser(email: string, senha: string): Observable<any> {

  const reqBody = {
    email,
    senha
  }
  return this.http.post(`${this.apiUrl}/auth/login`,reqBody)
}
}
