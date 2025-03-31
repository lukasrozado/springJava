import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {


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
  return this.http.get<any[]>(`${this.apiUrl}/user`, {headers: this.getHeaders()});
}

registerUser(email: string, password: string, username: string ): Observable<any> {

  const role = "ROLE_USER"
  const reqBody = {
    email,
    password,
    role,
    username
  }
  return this.http.post(`${this.apiUrl}/auth/register`,reqBody)
}
}
