import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  navVisible = true;
  ngAfterContentInit() {
    document.getElementById("sideNav").addEventListener("transitionend", (event) => {
      if (this.navVisible) {
        document.getElementById("togglerSecond").classList.remove("hidden");
      } else {
        document.getElementById("togglerFirst").classList.remove("hidden");
      }
    }, false);
  }

  toggle() {
    if (!this.navVisible) {
      document.getElementById("togglerFirst").classList.add("hidden");
    } else {
      document.getElementById("togglerSecond").classList.add("hidden");
    }
    this.navVisible = !this.navVisible;
  }
}
