export enum Role {
  USER = 'USER',
  ADMIN = 'ADMIN',
  DIRETOR_COMERCIAL = 'DIRETOR_COMERCIAL',
  GERENTE_VENDAS = 'GERENTE_VENDAS',
  SDR = 'SDR',
  VENDEDOR = 'VENDEDOR'
}

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  gender?: string;
  role: Role;
}
