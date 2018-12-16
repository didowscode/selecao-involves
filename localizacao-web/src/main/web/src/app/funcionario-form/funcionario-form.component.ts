import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {FuncionarioService} from "../service/funcionario.service";
import {NotificationService} from "../shared/message/notification.service";
import {Location} from "@angular/common";
import {MapsAPILoader} from "@agm/core";
import {FuncionarioModel} from "../model/funcionario.model";
declare let google: any;

@Component({
  selector: 'app-funcionario-form',
  templateUrl: './funcionario-form.component.html',
  styleUrls: ['./funcionario-form.component.css']
})
export class FuncionarioFormComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private funcionarioService: FuncionarioService,
              private ns: NotificationService,
              private location: Location,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone
              ) { }

  funcionarioForm: FormGroup;

  hasError(control: FormControl) {
    return control.invalid && (control.dirty || control.touched);
  }

  hasSuccess(control: FormControl) {
    return control.valid && (control.dirty || control.touched);
  }

  onSalvar(){
    const funcionario = new FuncionarioModel(this.funcionarioForm.value.name, this.latitude, this.longitude);
    this.funcionarioService.save(funcionario).subscribe(result => {
      this.ns.notify('Salvo com sucesso!');
      this.location.back();
    });
  }

  public latitude: number;
  public longitude: number;
  public searchControl: FormControl;
  public zoom: number;

  @ViewChild("search")
  public searchElementRef: ElementRef;

  ngOnInit() {
    this.funcionarioForm = this.formBuilder.group({
      name: this.formBuilder.control('', [Validators.required]),
      searchControl: this.formBuilder.control('', [Validators.required])
    });

    this.zoom = 13;
    this.searchControl = new FormControl();
    this.setCurrentPosition();

    this.mapsAPILoader.load().then(() => {
      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ["address"]
      });
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          let place = autocomplete.getPlace();
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }
          this.latitude = place.geometry.location.lat();
          this.longitude = place.geometry.location.lng();
          this.zoom = 13;
        });
      });
    });
  }

  private setCurrentPosition() {
    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        this.zoom = 13;
      });
    }
  }
}
