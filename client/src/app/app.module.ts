import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router'
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {RoomComponent} from './room/room.component';

export const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'room/global',
    pathMatch: 'full'
  },
  { path: 'room/:id', component: RoomComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    RoomComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

}
