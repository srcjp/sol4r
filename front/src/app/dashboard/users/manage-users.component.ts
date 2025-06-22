import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User, Role } from '../../models/user.model';

@Component({
  selector: 'app-manage-users',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
  <div *ngIf="!editing">
    <table>
      <thead>
        <tr>
          <th>Nome</th>
          <th>Telefone</th>
          <th>Email</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let u of users">
          <td>{{ u.name.fullName }}</td>
          <td>{{ u.phoneNumber }}</td>
          <td>{{ u.email }}</td>
          <td>
            <button (click)="startEdit(u)">Editar</button>
            <button (click)="remove(u.id)">Remover</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <div *ngIf="editing">
    <h3>Editar Usuário</h3>
    <form (ngSubmit)="save()">
      <label>
        Nome:
        <input name="firstName" [(ngModel)]="selected.name.firstName" />
      </label>
      <label>
        Sobrenome:
        <input name="lastName" [(ngModel)]="selected.name.lastName" />
      </label>
      <label>
        Telefone:
        <input name="phoneNumber" [(ngModel)]="selected.phoneNumber" />
      </label>
      <label>
        Role:
        <select name="role" [(ngModel)]="selected.role">
          <option *ngFor="let r of roles" [value]="r">{{ r }}</option>
        </select>
      </label>
      <button type="submit">Salvar</button>
      <button type="button" (click)="cancel()">Cancelar</button>
    </form>
  </div>
  `
})
export class ManageUsersComponent implements OnInit {
  users: User[] = [];
  roles = Object.values(Role);
  editing = false;
  selected!: User;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.userService.list().subscribe(u => this.users = u);
  }

  startEdit(user: User) {
    this.selected = { ...user };
    this.editing = true;
  }

  cancel() {
    this.editing = false;
  }

  save() {
    this.userService.update(this.selected.id, this.selected).subscribe(() => {
      this.editing = false;
      this.load();
    });
  }

  remove(id: number) {
    this.userService.delete(id).subscribe(() => this.load());
  }
}
