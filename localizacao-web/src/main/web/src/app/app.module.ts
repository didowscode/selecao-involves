import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {PreloadAllModules, RouterModule} from "@angular/router";
import {ROUTES} from "./app.routes";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HomeComponent} from './home/home.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FuncionarioViewComponent} from './funcionario-view/funcionario-view.component';
import {AgmCoreModule} from "@agm/core";
import {LoaderComponent} from "./shared/loader/loader.component";
import {NgxSpinnerModule} from 'ngx-spinner';
import {LoaderInterceptorService} from "./shared/loader/loader-interceptor.service";
import { FuncionarioFormComponent } from './funcionario-form/funcionario-form.component';
import {SnackbarComponent} from "./shared/message/snackbar/snackbar.component";
import {NotificationService} from "./shared/message/notification.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ApplicationErrorHandler} from "./app-error-handler";
import {ConfirmationModalComponent} from "./shared/confirmation-modal/confirmation-modal.component";
import {ModalComponent} from "./shared/modal/modal.component";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    FuncionarioViewComponent,
    LoaderComponent,
    FuncionarioFormComponent,
    SnackbarComponent,
    ConfirmationModalComponent,
    ModalComponent
      ],
  imports: [
    NgbModule,
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES, {preloadingStrategy: PreloadAllModules}),
    NgSelectModule,
    FormsModule,
    ReactiveFormsModule,
    NgxSpinnerModule,
    BrowserAnimationsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCsKGYpRREXLNjKr4K3dI4WSIJoe0qWBEM',
      libraries: ["places"]
    })
  ],
  providers: [
    NotificationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoaderInterceptorService,
      multi: true
    },
    {provide: ErrorHandler, useClass: ApplicationErrorHandler},
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    ModalComponent,
    ConfirmationModalComponent
  ]
})
export class AppModule { }
