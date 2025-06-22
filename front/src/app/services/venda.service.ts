import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environmet/environment';
import { Observable } from 'rxjs';
import { Venda } from '../models/venda.model';

@Injectable({ providedIn: 'root' })
export class VendaService {
  private apiUrl = `${environment.apiUrl}/vendas`;
  constructor(private http: HttpClient) {}

  list(): Observable<Venda[]> {
    return this.http.get<Venda[]>(this.apiUrl);
  }

  create(venda: Venda): Observable<Venda> {
    return this.http.post<Venda>(this.apiUrl, venda);
  }

  update(id: number, venda: Venda): Observable<Venda> {
    return this.http.put<Venda>(`${this.apiUrl}/${id}`, venda);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
