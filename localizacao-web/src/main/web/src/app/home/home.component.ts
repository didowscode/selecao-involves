import {Component, OnInit} from '@angular/core';
import {FuncionarioModel} from "../model/funcionario.model";
import {FuncionarioService} from "../service/funcionario.service";
import {ConfirmationModalComponent} from "../shared/confirmation-modal/confirmation-modal.component";
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  funcionarios: FuncionarioModel[];

  constructor(private funcionarioService: FuncionarioService,
              private modalService: NgbModal) { }

  ngOnInit() {
    this.onLoad();
  }

  onLoad(){
    this.funcionarioService.findAll().subscribe(funcionarios => {
      this.funcionarios = funcionarios;
    })
  }

  onRemover(funcionarioId: number){
    const modalRef = this.modalService.open(ConfirmationModalComponent, {});
    modalRef.componentInstance.title = 'Confirmar exclusão';
    modalRef.componentInstance.message = 'Você confirma a exclusão do representante?';

    modalRef.result.then((indice) => {
      this.funcionarioService.delete(funcionarioId).subscribe(result => {
        console.log(result);
        this.onLoad();
      })
    }, () => {
    });

  }
}
