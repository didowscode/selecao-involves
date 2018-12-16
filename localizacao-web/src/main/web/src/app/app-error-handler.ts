import {ErrorHandler, Injectable, NgZone} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {NotificationService} from './shared/message/notification.service';

@Injectable()
export class ApplicationErrorHandler extends ErrorHandler {

  constructor(public ns: NotificationService, private zone: NgZone) {
    super();
  }

  handleError(errorResponse: Response | any) {
    if (errorResponse instanceof HttpErrorResponse) {
      const message = errorResponse.error.message;
      this.zone.run(() => {
        switch (errorResponse.status) {
          case 404:
            this.ns.notify(message || 'Recurso não encontrado');
            break;
          default:
            this.ns.notify(message || 'Ocorreu um erro inesperado');
            break;
        }
      });
    }

    super.handleError(errorResponse);

  }
}
