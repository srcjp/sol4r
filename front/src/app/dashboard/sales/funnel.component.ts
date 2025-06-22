import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DragDropModule, CdkDragDrop, transferArrayItem, moveItemInArray } from '@angular/cdk/drag-drop';
import { FormsModule } from '@angular/forms';
import { VendaService } from '../../services/venda.service';
import { Venda } from '../../models/venda.model';

@Component({
  selector: 'app-funnel',
  standalone: true,
  imports: [CommonModule, DragDropModule, FormsModule],
  template: `
    <div class="funnel">
      <div
        class="stage"
        *ngFor="let stage of stages; let i = index"
        cdkDropList
        [cdkDropListData]="stage.vendas"
        class="list"
        (cdkDropListDropped)="dropSale($event, i)">
        <header cdkDrag>{{ stage.name }} <button (click)="removeStage(i)">x</button></header>
        <div class="card" *ngFor="let venda of stage.vendas" cdkDrag>{{ venda.modulo }} - {{ venda.valor | currency:'BRL' }}</div>
      </div>
      <div class="add-stage">
        <input placeholder="Nova etapa" [(ngModel)]="newStage" />
        <button (click)="addStage()">Adicionar</button>
      </div>
    </div>
  `,
  styles: [
    `.funnel { display: flex; gap: 1rem; overflow-x: auto; }
     .stage { background: #f7f7f7; padding: .5rem; width: 200px; border-radius: 4px; }
     .list { min-height: 200px; }
     header { font-weight: bold; margin-bottom: .5rem; cursor: move; display:flex; justify-content:space-between; }
     .card { background: #fff; padding: .5rem; margin-bottom: .5rem; border: 1px solid #ddd; border-radius: 4px; cursor: move; }
     .add-stage { display:flex; flex-direction:column; justify-content:flex-start; }
    `]
})
export class FunnelComponent {
  stages: { name: string; vendas: Venda[] }[] = [
    { name: 'Novas oportunidades', vendas: [] },
    { name: 'Qualificação', vendas: [] },
    { name: 'Apresentação do orçamento', vendas: [] },
    { name: 'Negociação', vendas: [] },
    { name: 'Fechamento', vendas: [] },
  ];

  newStage = '';

  constructor(private vendaService: VendaService) {
    this.vendaService.list().subscribe(vendas => {
      vendas.forEach(v => {
        const stage = this.stages.find(s => s.name === v.etapa);
        if (stage) {
          stage.vendas.push(v);
        } else {
          this.stages[0].vendas.push(v);
        }
      });
    });
  }

  addStage() {
    if (this.newStage.trim()) {
      this.stages.push({ name: this.newStage.trim(), vendas: [] });
      this.newStage = '';
    }
  }

  removeStage(index: number) {
    this.stages.splice(index, 1);
  }

  dropSale(event: CdkDragDrop<Venda[]>, stageIndex: number) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }
}
