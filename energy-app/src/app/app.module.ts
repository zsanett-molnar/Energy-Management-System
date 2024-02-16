import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AdminOperationsComponent } from './admin-operations/admin-operations.component';
import { ClientOperationsComponent } from './client-operations/client-operations.component';
import { CreateDeviceComponent } from './create-device/create-device.component';
import { AssociateDeviceComponent } from './associate-device/associate-device.component';
import { ChartComponent } from './chart/chart.component';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthInterceptor } from 'authconfig.interceptor';
import { ChatComponent } from './admin-chat/chat.component';
import { ClientChatComponent } from './client-chat/client-chat.component';
import { ClientListComponent } from './client-list/client-list.component';


@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    LoginComponent,
    RegisterComponent,
    AdminOperationsComponent,
    ClientOperationsComponent,
    CreateDeviceComponent,
    AssociateDeviceComponent,
    ChartComponent,
    ChatComponent,
    ClientChatComponent,
    ClientListComponent,   
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatDatepickerModule,
    MatInputModule,
    MatFormFieldModule,
    MatNativeDateModule,
    BrowserAnimationsModule
  ],
  providers: [{
    provide : HTTP_INTERCEPTORS,
    useClass : AuthInterceptor,
    multi : true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
