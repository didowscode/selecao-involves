import {Component, Input} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal',
  template: `
      <div class="modal-header">
        <h6 class="modal-title" id="modal-basic-title"><small class="text-muted">{{title}}</small></h6>
        <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss()">
          <span aria-hidden="true"><small class="text-muted">&times;</small></span>
        </button>
      </div>
      <div class="modal-body">
        <ng-content></ng-content>
      </div>
      <div class="modal-footer" *ngIf="showConfirmPanel">
        <button type="button" class="btn btn-outline-secondary" (click)="activeModal.dismiss('')">CANCELAR</button>
        <button type="button" ngbAutofocus class="btn btn-danger" (click)="activeModal.close('')">CONFIRMAR</button>
      </div>
      <div class="modal-footer" *ngIf="showSavePanel">
        <button type="button" class="btn btn-outline-secondary" (click)="activeModal.dismiss('')">CANCELAR</button>
        <button type="button" ngbAutofocus class="btn btn-danger" (click)="activeModal.close('')">SALVAR</button>
      </div>
    `
})
export class ModalComponent {

  @Input() title: string;
  @Input() showConfirmPanel: boolean;
  @Input() showSavePanel: boolean;

  constructor(public activeModal: NgbActiveModal) {
  }
}
