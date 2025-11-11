
export type Cargo = {
  _id: string; 
  nome: string;
};

export type Empresa = {
  _id: string; 
  nome_fantasia: string;
  razao_social: string;
  tipo: string;
};

export type Vaga = {
  _id: string; 
  registro: string;
  empresa: Empresa;
  cargo: string; 
  cidade: string;
  estado: string;
  pre_requisitos: string;
  formacao: string;
  conhecimentos_requeridos: string;
  regime: string;
  jornada_trabalho: string;
  remuneracao: string;
};

// Tipo para criar/atualizar uma vaga (sem os IDs gerados pelo backend)
export type VagaPayload = Omit<Vaga, "_id">;