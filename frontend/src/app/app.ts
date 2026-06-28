import { Component, signal,} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('virtual-banking-ui');
  showLayout = false;

  constructor(private router: Router) {

  this.router.events
    .pipe(
      filter(event => event instanceof NavigationEnd)
    )
    .subscribe(() => {

      this.showLayout =
        this.router.url !== '/login';

    });

}
}
