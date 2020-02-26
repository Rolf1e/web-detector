import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AnomalyComponent } from './component/anomaly/anomaly.component';

@NgModule({
  declarations: [
    AppComponent,
    AnomalyComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
