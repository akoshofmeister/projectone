import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
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

export const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'room/global',
    pathMatch: 'full'
  },
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
    NotFoundComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule {

}
