import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isCollapsed= false;
  togglerFirstHidden= false;
  togglerSecondHidden= true;

  ngOnInit() {
    document.getElementById("sideNav").addEventListener("transitionend", (event) => {
      this.togglerFirstHidden = this.isCollapsed;
      this.togglerSecondHidden = !this.isCollapsed;
    }, false);
  }

  toggle() {
    this.isCollapsed = !this.isCollapsed;
  }
}
