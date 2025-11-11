import axios from 'axios';
import { Vaga, Empresa, Cargo, VagaPayload } from "../types";

//url base para api
const api = axios.create({
  baseURL: "http://localhost:8080/apis", 
});

//vagas
export const getVagas = async () => {
  const response = await api.get<Vaga[]>("/vagas/get-all");
  return response.data;
};

export const createVaga = async (vagaData: VagaPayload) => {
  const response = await api.post<Vaga>("/vagas", vagaData);
  return response.data;
};

//aqui tem que mandar o object completo
export const updateVaga = async (vagaData: Vaga) => {
  const response = await api.put<Vaga>("/vagas", vagaData);
  return response.data;
};

export const deleteVaga = async (registro: string) => {
  const response = await api.delete(`/vagas/${registro}`);
  return response.data; 
};

//empresas
export const getEmpresas = async () => {
  const response = await api.get<Empresa[]>("/empresas/get-all");
  return response.data;
};

//cargos
export const getCargos = async () => {
  const response = await api.get<Cargo[]>("/cargos/get-all");
  return response.data;
};