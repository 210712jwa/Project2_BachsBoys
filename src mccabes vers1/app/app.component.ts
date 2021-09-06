import { Component } from '@angular/core';
import { Pokemon } from 'src/model/pokemon';
import { FriendpageService } from './friendpage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  pokemon: Pokemon[] = [];

  constructor(private pokeService: FriendpageService) {}

  onSearch(event: number){
    this.pokeService.getPokemonByID(event).subscribe((data) => {
      this.pokemon.push(data);
    });
  }
}
