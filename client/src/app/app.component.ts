import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Booking';
  token: string | null;
  ngOnInit() {
    this.token = localStorage.getItem("token");
  }
}
