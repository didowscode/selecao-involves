import {Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {FuncionarioViewComponent} from "./funcionario-view/funcionario-view.component";
import {FuncionarioFormComponent} from "./funcionario-form/funcionario-form.component";

export const ROUTES: Routes = [
  {path: '', component: HomeComponent},
  {path: 'funcionario-view/:id', component: FuncionarioViewComponent},
  {path: 'funcionario-form', component: FuncionarioFormComponent}
];
