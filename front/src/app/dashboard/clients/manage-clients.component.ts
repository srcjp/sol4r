import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../../services/client.service';
import { Client } from '../../models/client.model';

@Component({
  selector: 'app-manage-clients',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './manage-clients.component.html',
  styleUrls: ['./manage-clients.component.scss']
})
export class ManageClientsComponent implements OnInit {
  clients: Client[] = [];
  selected!: Client;
  popupOpen = false;
  editing = false;

  emptyClient(): Client {
    return {
      enderecos: [],
      cpf: '',
      email: '',
      cidade: '',
      origem: '',
      propostas: [],
      contratos: [],
      proprietario: null,
      validade: ''
    };
  }

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.clientService.list().subscribe(c => {
      this.clients = c.sort((a, b) => a.email.localeCompare(b.email));
    });
  }

  openAdd() {
    this.selected = this.emptyClient();
    this.editing = false;
    this.popupOpen = true;
  }

  edit(client: Client) {
    this.selected = { ...client };
    this.editing = true;
    this.popupOpen = true;
  }

  cancel() {
    this.popupOpen = false;
  }

  save() {
    if (this.editing && this.selected.id) {
      this.clientService.update(this.selected.id, this.selected).subscribe(() => {
        this.popupOpen = false;
        this.load();
      });
    } else {
      this.clientService.create(this.selected).subscribe(() => {
        this.popupOpen = false;
        this.load();
      });
    }
  }

  confirmRemove(id: string | undefined) {
    if (!id) return;
    if (confirm('Remover cliente?')) {
      this.clientService.delete(id).subscribe(() => this.load());
    }
  }
}
