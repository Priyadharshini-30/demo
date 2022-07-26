import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AddCatComponent } from './pages/admin/add-cat/add-cat.component';
import { AddQuesComponent } from './pages/admin/add-ques/add-ques.component';
import { AddQuizComponent } from './pages/admin/add-quiz/add-quiz.component';
import { DashBoardComponent } from './pages/admin/dash-board/dash-board.component';
import { ShowQuizComponent } from './pages/admin/show-quiz/show-quiz.component';
import { UpdateQuizComponent } from './pages/admin/update-quiz/update-quiz.component';
import { ViewCatComponent } from './pages/admin/view-cat/view-cat.component';
import { ViewQuesComponent } from './pages/admin/view-ques/view-ques.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { AnsComponent } from './pages/user/ans/ans.component';
import { InstrnComponent } from './pages/user/instrn/instrn.component';
import { LoadQuizComponent } from './pages/user/load-quiz/load-quiz.component';
import { StartComponent } from './pages/user/start/start.component';
import { UserDashBoardComponent } from './pages/user/user-dash-board/user-dash-board.component';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent,
    pathMatch:'full',
  },
  {
    path:'signup',
    component:SignupComponent,
    pathMatch:'full',
  },
  {
    path:'login',
    component:LoginComponent,
    pathMatch:'full'
  },
  {
    path:'admin',
    component:DashBoardComponent,
    canActivate:[AuthGuard],
    children:[
    {
      path:'view-questions/:qid/:title',
      component:ViewQuesComponent
    },
    {
      path:'categories',
      component:ViewCatComponent
    },
    {
      path:'add',
      component:AddCatComponent
    },
    {
      path:'quizzes',
      component:ShowQuizComponent,
    },
    {
      path:'add-quiz',
      component:AddQuizComponent,
    },
    {
      path:'quiz/:qid',
      component:UpdateQuizComponent,
    },
    {
      path:'add-question/:qid/:title',
      component:AddQuesComponent
    }
   ],

  },
  {
    path:'user',
    component:UserDashBoardComponent,
    canActivate:[AuthGuard],
    children:[
      {
        path:'quiz/:cid',
        component:LoadQuizComponent,
      },
      {
        path:'instructions/:qid',
        component:InstrnComponent,
      },
    ]
  },
  {
    path:'user/start/:qid',
    component:StartComponent,
    canActivate:[AuthGuard],
  },
  {
    path:'user/answer/:qid',
    component:AnsComponent,
    canActivate:[AuthGuard],
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  
})
export class AppRoutingModule { }
