import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs/index';
import {LoaderService} from './loader.service';
import {LoaderState} from './loader.model';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.css']
})
export class LoaderComponent implements OnInit, OnDestroy {

  private subscription: Subscription;

  constructor(private loaderService: LoaderService, private spinner: NgxSpinnerService) {
  }

  ngOnInit() {
    this.subscription = this.loaderService.loaderState
      .subscribe((state: LoaderState) => {
        if (state.show) {
          this.spinner.show();
        } else {
          this.spinner.hide();
        }
      });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
