import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../app/login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AdminOperationsComponent } from './admin-operations/admin-operations.component';
import { ClientOperationsComponent } from './client-operations/client-operations.component';
import { CreateDeviceComponent } from './create-device/create-device.component';
import { AssociateDeviceComponent } from './associate-device/associate-device.component';
import { ChartComponent } from './chart/chart.component';
import { ChatComponent } from './admin-chat/chat.component';
import { ClientChatComponent } from './client-chat/client-chat.component';
import { ClientListComponent } from './client-list/client-list.component';

const routes: Routes = [
  {path : '', component: HomepageComponent},
  {path : 'Homepage', component: HomepageComponent},
  {path: 'Login', component: LoginComponent},
  {path : 'Register', component : RegisterComponent},
  {path : 'Admin-Operations', component : AdminOperationsComponent},
  {path : 'Client-Operations', component : ClientOperationsComponent},
  {path : 'Create-Device', component : CreateDeviceComponent}, 
  {path : 'Associate-Device', component : AssociateDeviceComponent},
  {path : 'Chart', component : ChartComponent},
  {path : 'Chat' , component : ChatComponent},
  {path : 'Client-Chat' , component : ClientChatComponent},
  {path : 'Client-List', component : ClientListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
