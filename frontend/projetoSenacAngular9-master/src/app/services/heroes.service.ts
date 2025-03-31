import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Hero } from '../interfaces/heroes.interface';


@Injectable({
  providedIn: 'root'
})
export class HeroesService {

  private apiUrl = 'http://localhost:3000/heroes';

  constructor(private http: HttpClient) { }

  getAllHeroes():Observable<Hero[]>{
    return this.http.get<Hero[]>(this.apiUrl);
  };

  getHeroById(id: string):Observable<Hero>{
    return this.http.get<Hero>(`${this.apiUrl}/${id}`);
  }

  createHero(hero: Hero):Observable<Hero>{
    return this.http.post<Hero>(this.apiUrl, hero);
  };

  updateHero(id:string, hero: Hero):Observable<Hero>{
    return this.http.patch<Hero>(`${this.apiUrl}/${id}`, hero);
  };

  deleteHero(id:string):Observable<any>{
    return this.http.delete<Hero>(`${this.apiUrl}/${id}`);
  };


}
