import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './pages/home/home.component';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { DashBoardComponent } from './pages/admin/dash-board/dash-board.component';
import { UserDashBoardComponent } from './pages/user/user-dash-board/user-dash-board.component';
import {MatListModule} from '@angular/material/list';
import { SidebarComponent } from './pages/admin/sidebar/sidebar.component';
import { RouterModule } from '@angular/router';
import { ViewCatComponent } from './pages/admin/view-cat/view-cat.component';
import { AddCatComponent } from './pages/admin/add-cat/add-cat.component';
import { ShowQuizComponent } from './pages/admin/show-quiz/show-quiz.component';
import { AddQuizComponent } from './pages/admin/add-quiz/add-quiz.component';
import {MatSelectModule} from '@angular/material/select';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { ViewQuesComponent } from './pages/admin/view-ques/view-ques.component';
import { UpdateQuizComponent } from './pages/admin/update-quiz/update-quiz.component';
import { AddQuesComponent } from './pages/admin/add-ques/add-ques.component';
import { UserSidebarComponent } from './pages/user/user-sidebar/user-sidebar.component';
import { AuthGuard } from './auth.guard';
import { TokenInterceptor } from './token-interceptor.service';
import { LoadQuizComponent } from './pages/user/load-quiz/load-quiz.component';
import { InstrnComponent } from './pages/user/instrn/instrn.component';
import { StartComponent } from './pages/user/start/start.component';
import {MatRadioModule} from '@angular/material/radio';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { AnsComponent } from './pages/user/ans/ans.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    DashBoardComponent,
    UserDashBoardComponent,
    SidebarComponent,
    ViewCatComponent,
    AddCatComponent,
    ShowQuizComponent,
    AddQuizComponent,
    ViewQuesComponent,
    UpdateQuizComponent,
    AddQuesComponent,
    UserSidebarComponent,
    LoadQuizComponent,
    InstrnComponent,
    StartComponent,
    AnsComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatTooltipModule,
    HttpClientModule,
    MatSnackBarModule,
    MatListModule,
    RouterModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatRadioModule,
    MatProgressSpinnerModule,


  
  ],
  providers: [AuthGuard,{
    provide:HTTP_INTERCEPTORS,
    useClass:TokenInterceptor,
    multi:true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
