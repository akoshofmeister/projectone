import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router'
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {RoomComponent} from './room/room.component';
import {LoginFormComponent} from './login-form/login-form.component';
import {UserService} from "./services/user.service";
import {MenuComponent} from './menu/menu.component';
import 'rxjs';
import {NotFoundComponent} from './not-found/not-found.component';
import { NewRoomComponent } from './new-room/new-room.component';
import {RoomService} from "./services/room.service";
import {MessagesService} from "./services/messages.service";

export const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'room/0',
    pathMatch: 'full'
  },
  {path: 'newroom', component: NewRoomComponent},
  {path: 'room/:id', component: RoomComponent},
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  declarations: [
    AppComponent,
    RoomComponent,
    LoginFormComponent,
    MenuComponent,
    NotFoundComponent,
    NewRoomComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserService, RoomService, MessagesService],
  bootstrap: [AppComponent]
})
export class AppModule {

}
