import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable} from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from './services/user.service';

@Injectable({
  providedIn: 'root'
})

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private loginservice: UserService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


        const token = this.loginservice.getToken();

        console.log("interceptor", token);

        if (token != null) {
            console.log("hello");

            req = req.clone(
                {
                    setHeaders: {
                        'Content-Type': 'application/json; charset=utf-8',
                        'Accept': 'application/json',
                        'Authorization': `Bearer ${token}`
                    }
                }
            )
        }
        return next.handle(req);

    }
}



