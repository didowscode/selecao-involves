import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {FuncionarioModel} from "../model/funcionario.model";
import {environment} from "../../environments/environment";

const API_URL = environment.API_URL;

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {

  constructor(private  httpClient:  HttpClient) { }

  findAll():Observable<FuncionarioModel[]> {
    return this.httpClient.get<FuncionarioModel[]>(`${API_URL}/localizacao/funcionario/`);
  }

  get(funcionarioId: number):Observable<FuncionarioModel> {
    return this.httpClient.get<FuncionarioModel>(`${API_URL}/localizacao/funcionario/${funcionarioId}`);
  }

  save(funcionario: FuncionarioModel) {
    return this.httpClient.post(`${API_URL}/localizacao/funcionario/save`, funcionario, httpOptions);
  }

  delete(funcionarioId: number) :Observable<any> {
    return this.httpClient.delete(`${API_URL}/localizacao/funcionario/${funcionarioId}`);
  }
}
