export interface Proposal {
  id?: number;
  potenciaInstaladaRecomendada: string;
  numeroModulos: number;
  inversor: string;
  estrutura: string;
  cabeamento: string;
  outros?: string;
  valor: number;
  parcelas: number;
  valorParcelado: number;
  formaPagamento: string;
  metodoPagamento: string;
  garantias: string;
  validade: string;
  horarioRegistro: string;
  visualizacoes: number;
}
