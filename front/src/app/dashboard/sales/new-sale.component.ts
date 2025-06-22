import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VendaService } from '../../services/venda.service';
import { Venda } from '../../models/venda.model';

@Component({
  selector: 'app-new-sale',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h2>Nova Venda</h2>
    <form (ngSubmit)="create()">
      <label>
        Etapa:
        <input name="etapa" [(ngModel)]="venda.etapa" required />
      </label>
      <label>
        Módulo:
        <input name="modulo" [(ngModel)]="venda.modulo" required />
      </label>
      <label>
        Valor:
        <input type="number" name="valor" [(ngModel)]="venda.valor" required />
      </label>
      <button type="submit">Salvar</button>
    </form>
  `
})
export class NewSaleComponent {
  venda: Venda = {
    cliente: null as any,
    etapa: '',
    modulo: '',
    valor: 0,
    vendedorResponsavel: null as any
  };

  constructor(private vendaService: VendaService) {}

  create() {
    this.vendaService.create(this.venda).subscribe();
  }
}
