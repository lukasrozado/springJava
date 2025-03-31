import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsersComponent } from './users.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [UsersComponent],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,

  ],
  exports: [UsersComponent]
})
export class UsersModule { }
