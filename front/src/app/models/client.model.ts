import { Address } from './address.model';
import { Proposal } from './proposal.model';
import { Contract } from './contract.model';

export interface Client {
  id?: string;
  dataCriacao?: string;
  enderecos: Address[];
  cpf: string;
  email: string;
  cidade: string;
  origem: string;
  propostas: Proposal[];
  contratos: Contract[];
  notas?: string;
  visualizacoes?: number;
  proprietario: any;
  validade: string;
  ultimaAtualizacao?: string;
}
