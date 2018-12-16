import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {LojaModel} from "../model/loja.model";
import {FuncionarioModel} from "../model/funcionario.model";
import {environment} from "../../environments/environment";

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class LojaService {

  constructor(private  httpClient:  HttpClient) { }

  findAllByFuncionario(funcionario: FuncionarioModel):Observable<LojaModel[]> {
    const params: any = {
      latitude: funcionario.latitude,
      longitude: funcionario.longitude
    };
    return this.httpClient.get<LojaModel[]>(`${API_URL}/localizacao/loja/`, {params: params});
  }
}
