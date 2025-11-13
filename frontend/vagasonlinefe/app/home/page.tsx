"use client";

import { useState, useEffect } from "react";
import { Vaga, Empresa, Cargo, VagaPayload } from "@/types";
import * as api from "@/api/api"; // Importa todas as funções da API
import { Button } from "@/app/components/ui/button";
import { Plus } from "lucide-react";
import { VagaCard } from "@/app/components/commom/vaga-card";
import { VagaForm } from "@/app/components/commom/vaga-form";
import Toast from "react-hot-toast";

export default function Home() {
  const toast = Toast;

  const [vagas, setVagas] = useState<Vaga[]>([]);
  const [empresas, setEmpresas] = useState<Empresa[]>([]);
  const [cargos, setCargos] = useState<Cargo[]>([]);

  const [isFormOpen, setIsFormOpen] = useState(false);
  const [editingVaga, setEditingVaga] = useState<Vaga | null>(null);
  const [isLoading, setIsLoading] = useState(true); // Estado de loading

  const loadData = async () => {
    setIsLoading(true);
    try {
      const [vagasData, empresasData, cargosData] = await Promise.all([
        api.getVagas(),
        api.getEmpresas(),
        api.getCargos(),
      ]);
      setVagas(vagasData);
      setEmpresas(empresasData);
      setCargos(cargosData);
    } catch (error) {
      toast.error("Erro ao carregar dados: " + error);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  // --- Funções CRUD ---

  const handleAddNew = () => {
    setEditingVaga(null);
    setIsFormOpen(true);
  };

  const handleEdit = (vaga: Vaga) => {
    setEditingVaga(vaga);
    setIsFormOpen(true);
  };

  const handleSave = async (
    formData: VagaPayload,
    vagaOriginal: Vaga | null
  ) => {
    try {
      if (vagaOriginal) {
        // --- MODO EDIÇÃO ---
        const vagaAtualizada = {
          ...vagaOriginal,
          ...formData,
        };
        await api.updateVaga(vagaAtualizada);
        toast.success(
          "Vaga para " +
            formData.cargo +
            " para a empresa " +
            formData.empresa.nome_fantasia +
            " atualizada com sucesso!"
        );
      } else {
        // --- MODO CRIAÇÃO ---
        const registroJaExiste = vagas.some(
          (v) => v.registro === formData.registro
        );

        if (registroJaExiste) {
          toast.error(
            "Erro ao Salvar. O código de registro da vaga já existe."
          );
          return;
        }

        await api.createVaga(formData);
        toast.success(
          "Vaga para " +
            formData.cargo +
            " para a empresa " +
            formData.empresa.nome_fantasia +
            " criada com sucesso!"
        );
      }
      setIsFormOpen(false);
      setEditingVaga(null);
      await loadData();
    } catch (error) {
      toast.error("Erro ao salvar vaga:" + error);
    }
  };

  const handleDelete = async (registro: string) => {
    if (window.confirm("Tem certeza que deseja excluir esta vaga?")) {
      try {
        await api.deleteVaga(registro);
        toast.success("Vaga excluída com sucesso!");
        await loadData(); // Recarrega os dados após excluir
      } catch (error) {
        toast.error("Erro ao excluir vaga: " + error);
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
          vagas.map((vaga) => (
            <VagaCard
              key={vaga._id}
              vaga={vaga}
              onEdit={handleEdit}
              onDelete={handleDelete}
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
