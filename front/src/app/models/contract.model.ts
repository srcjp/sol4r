export interface Contract {
  id?: number;
  cliente: any;
  proposta: any;
  dataAssinatura: string;
  validadeProposta: string;
  status: string;
  formaPagamento: string;
  valorTotal: number;
}
