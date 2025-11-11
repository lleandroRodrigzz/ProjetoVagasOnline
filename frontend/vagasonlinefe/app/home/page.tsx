"use client";

import { useState, useEffect } from "react";
import { Vaga, Empresa, Cargo, VagaPayload } from "@/types";
import * as api from "@/api/api"; // Importa todas as funções da API
import { Button } from "@/app/components/ui/button";
import { Plus } from "lucide-react";
import { VagaCard } from "@/app/components/commom/vaga-card";
import { VagaForm } from "@/app/components/commom/vaga-form";

export default function Home() {
  // Estados para os dados da API
  const [vagas, setVagas] = useState<Vaga[]>([]);
  const [empresas, setEmpresas] = useState<Empresa[]>([]);
  const [cargos, setCargos] = useState<Cargo[]>([]);

  // Estados para controle do UI
  const [isFormOpen, setIsFormOpen] = useState(false);
  const [editingVaga, setEditingVaga] = useState<Vaga | null>(null);
  const [isLoading, setIsLoading] = useState(true); // Estado de loading

  // Função para carregar todos os dados da API
  const loadData = async () => {
    setIsLoading(true);
    try {
      // Busca tudo em paralelo
      const [vagasData, empresasData, cargosData] = await Promise.all([
        api.getVagas(),
        api.getEmpresas(),
        api.getCargos(),
      ]);
      setVagas(vagasData);
      setEmpresas(empresasData);
      setCargos(cargosData);
    } catch (error) {
      console.error("Erro ao buscar dados:", error);
      // TODO: Mostrar um toast/alerta de erro para o usuário
    } finally {
      setIsLoading(false);
    }
  };

  // Carrega os dados quando o componente é montado
  useEffect(() => {
    loadData();
  }, []);

  // --- Funções CRUD ---

  const handleAddNew = () => {
    setEditingVaga(null); // Garante que não está editando
    setIsFormOpen(true);
  };

  const handleEdit = (vaga: Vaga) => {
    setEditingVaga(vaga); // Define a vaga para edição
    setIsFormOpen(true);
  };

  const handleSave = async (
    formData: VagaPayload,
    vagaOriginal: Vaga | null
  ) => {
    try {
      if (vagaOriginal) {
        // --- MODO EDIÇÃO ---
        // Remonta o objeto Vaga completo para enviar à API
        const vagaAtualizada = {
          ...vagaOriginal, // Contém _id e registro
          ...formData, // Contém os dados atualizados do formulário
        };
        await api.updateVaga(vagaAtualizada);
        console.log("Vaga atualizada:", vagaAtualizada);
      } else {
        // --- MODO CRIAÇÃO ---
        await api.createVaga(formData);
        console.log("Vaga criada:", formData);
      }

      setIsFormOpen(false);
      setEditingVaga(null);
      await loadData(); // Recarrega os dados após salvar
    } catch (error) {
      console.error("Erro ao salvar vaga:", error);
      // TODO: Mostrar alerta de erro
    }
  };

  const handleDelete = async (registro: string) => {
    if (window.confirm("Tem certeza que deseja excluir esta vaga?")) {
      try {
        await api.deleteVaga(registro);
        console.log("Vaga excluída:", registro);
        await loadData(); // Recarrega os dados após excluir
      } catch (error) {
        console.error("Erro ao excluir vaga:", error);
        // TODO: Mostrar alerta de erro
      }
    }
  };

  return (
    <div className="container mx-auto p-4 md:p-8 mt-20">
      <header className="flex flex-col md:flex-row justify-between md:items-center gap-4 mb-8">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">VagasOnline</h1>
          <p className="text-lg text-slate-600 dark:text-slate-400">
            Gerencie as vagas de emprego da sua plataforma.
          </p>
        </div>
        <Button size="lg" onClick={handleAddNew}>
          <Plus className="mr-2 h-5 w-5" /> Adicionar Nova Vaga
        </Button>
      </header>

      {/* --- Exibição das Vagas --- */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {isLoading && (
          <p className="text-slate-500 col-span-3 text-center">
            Carregando vagas...
          </p>
        )}

        {!isLoading && vagas.length === 0 && (
          <p className="text-slate-500 col-span-3 text-center">
            Nenhuma vaga cadastrada.
          </p>
        )}

        {!isLoading &&
          vagas
            .map((vaga) => (
              <VagaCard
                key={vaga._id} // Agora isso é 100% seguro
                vaga={vaga}
                onEdit={handleEdit}
                onDelete={handleDelete} // Certifique-se que onDelete está aqui
              />
            ))}
      </div>

      {/* --- Formulário (Dialog) --- */}
      <VagaForm
        // Usar a key força o React a remontar o formulário ao trocar de vaga
        key={editingVaga ? editingVaga._id : "new-vaga"}
        vaga={editingVaga}
        empresas={empresas}
        cargos={cargos}
        isOpen={isFormOpen}
        onOpenChange={setIsFormOpen}
        onSave={handleSave}
      />
    </div>
  );
}
