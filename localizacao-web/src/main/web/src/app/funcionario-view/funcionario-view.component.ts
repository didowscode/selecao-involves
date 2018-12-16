import { Component, OnInit } from '@angular/core';
import {LojaModel} from "../model/loja.model";
import {FuncionarioModel} from "../model/funcionario.model";
import {ActivatedRoute} from "@angular/router";
import {FuncionarioService} from "../service/funcionario.service";
import {LojaService} from "../service/loja.service";
import {MapsAPILoader} from "@agm/core";
declare let google: any;

@Component({
  selector: 'app-funcionario-view',
  templateUrl: './funcionario-view.component.html',
  styleUrls: ['./funcionario-view.component.css']
})
export class FuncionarioViewComponent implements OnInit {

  funcionario: FuncionarioModel;
  lojas: LojaModel[];

  constructor(private route: ActivatedRoute,
              private funcionarioService: FuncionarioService,
              private lojaService: LojaService,
              private mapsAPILoader: MapsAPILoader) { }

  ngOnInit() {
    const funcionarioId = this.route.snapshot.params.id;
    this.funcionarioService.get(funcionarioId).subscribe(data => {
      this.funcionario = data;

      this.lojaService.findAllByFuncionario(this.funcionario).subscribe(lojas => {
        this.lojas = lojas;
      });
    })
  }

  mapReady(){
    const geocoder = new google.maps.Geocoder();
    const latLng = new google.maps.LatLng(this.funcionario.latitude, this.funcionario.longitude);
    const request = { latLng: latLng };

    geocoder.geocode(request, (results, status) => {
      if (status === google.maps.GeocoderStatus.OK) {
        this.funcionario.endereco = results[0].formatted_address;
      }else{
        console.log(status);
      }
    });
  }

}
