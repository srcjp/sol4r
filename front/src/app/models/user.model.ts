export enum Role {
  USER = 'USER',
  ADMIN = 'ADMIN',
  DIRETOR_COMERCIAL = 'DIRETOR_COMERCIAL',
  GERENTE_VENDAS = 'GERENTE_VENDAS',
  SDR = 'SDR',
  VENDEDOR = 'VENDEDOR'
}

export interface Name {
  firstName: string;
  lastName: string;
  fullName: string;
}

export interface User {
  id: number;
  name: Name;
  email: string;
  phoneNumber: string;
  gender?: string;
  role: Role;
  fullName: string;
}
