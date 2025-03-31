import { Component, OnInit } from '@angular/core';
import { HeroesService } from '../services/heroes.service';
import { Hero } from '../interfaces/heroes.interface';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.scss'],
})
export class HeroesComponent implements OnInit {
  constructor(private heroesService: HeroesService) {}

  heroes: Hero[] = []; //array de heroes
  hero: Hero = {

    "name": '',
    "power": '',
    "age": 0,
  }; // objeto hero inicia vazio

  newHero: Hero = {

    "name": '',
    "power": '',
    "age": 0,
  };

  selectedHero?: Hero;

  ngOnInit(): void {
   this.getAllHeroes();
  }

  getAllHeroes(): void {
    this.heroesService.getAllHeroes().subscribe(
      (response) => (this.heroes = response),
      (error) => this.generateError('Error ao obtener los heroes', error)
    );
  }

  getheroById(id: string): void {
    this.heroesService.getHeroById(id).subscribe(
      (response) => (this.hero = response),
      (error) => this.generateError('Error ao obtener los heroes', error)
    );
  }

  createHero(): void {
    this.heroesService.createHero(this.newHero).subscribe(
      (hero) => {
        this.heroes.push(hero);
        this.newHero = {
          "name": '',
          "power": '',
          "age": 0,
        };
      },
      (error) => this.generateError('Error ao criar heroes', error)
    );
  }

  selectHero(hero: Hero): void {
    this.selectedHero = {...hero};
  }


    updateHero():void {


    if(this.selectedHero){
      this.heroesService.updateHero(this.selectedHero?._id!, this.selectedHero).subscribe(

        (updatedHero) =>{
          const index = this.heroes.findIndex(hero => hero._id === updatedHero._id);
          window.location.reload();
        }

      )

}
}

  deleteHero(id: string): void {
    this.heroesService.deleteHero(id).subscribe(
      () => (this.heroes = this.heroes.filter((hero) => hero._id === id)),
      (error) => this.generateError('Error ao deletar hero', error)
    )
  };


generateError(message: string, error: any) {
  console.log(message, error);
}

}
