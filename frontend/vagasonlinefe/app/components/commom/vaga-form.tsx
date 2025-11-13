"use client";

import { useState, useEffect } from "react";
import { Vaga, Empresa, Cargo, VagaPayload } from "@/types";
import { Button } from "@/app/components/ui/button";
import { Input } from "@/app/components/ui/input";
import { Label } from "@/app/components/ui/label";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/app/components/ui/dialog";
import { ScrollArea } from "@/app/components/ui/scroll-area";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/app/components/ui/select";

interface VagaFormProps {
  vaga: Vaga | null;
  empresas: Empresa[];
  cargos: Cargo[];
  isOpen: boolean;
  onOpenChange: (open: boolean) => void;
  onSave: (data: VagaPayload, vagaOriginal: Vaga | null) => void;
}

// VALOR INICIAL VAZIO PARA O FORMULÁRIO
const emptyFormData: VagaPayload = {
  registro: "",
  empresa: { _id: "", nome_fantasia: "", razao_social: "", tipo: "" },
  cargo: "",
  cidade: "",
  estado: "",
  pre_requisitos: "",
  formacao: "",
  conhecimentos_requeridos: "",
  regime: "",
  jornada_trabalho: "",
  remuneracao: "",
};

export function VagaForm({
  vaga,
  empresas,
  cargos,
  isOpen,
  onOpenChange,
  onSave,
}: VagaFormProps) {
  const [formData, setFormData] = useState<VagaPayload>(emptyFormData);

  useEffect(() => {
    if (vaga) {
      const { ...dadosForm } = vaga;
      setFormData(dadosForm);
    } else {
      // Se estamos criando, resetamos o formulário
      setFormData(emptyFormData);
    }
  }, [vaga, isOpen]); // Roda quando o dialog abre ou a vaga muda

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setFormData((prev) => ({ ...prev, [id]: value }));
  };

  const handleEmpresaSelect = (nomeFantasiaSelecionado: string) => {
    console.log("Nome Fantasia selecionado:", nomeFantasiaSelecionado);

    const empresaSelecionada = empresas.find(
      (e) => e.nome_fantasia === nomeFantasiaSelecionado
    );

    console.log("Empresa encontrada:", empresaSelecionada);
    console.log("Lista de empresas:", empresas);

    if (empresaSelecionada) {
      setFormData((prev) => ({
        ...prev,
        empresa: empresaSelecionada,
      }));
    }
  };

  const handleCargoSelect = (selectedValue: string) => {
    console.log("Cargo selecionado:", selectedValue);
    setFormData((prev) => ({ ...prev, cargo: selectedValue }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSave(formData, vaga);
  };

  return (
    <Dialog open={isOpen} onOpenChange={onOpenChange}>
      <DialogContent className="sm:max-w-[600px]">
        <DialogHeader>
          <DialogTitle>{vaga ? "Editar Vaga" : "Criar Nova Vaga"}</DialogTitle>
          <DialogDescription>
            Preencha todos os campos para{" "}
            {vaga ? "salvar as alterações" : "publicar a vaga"}.
          </DialogDescription>
        </DialogHeader>
        <form onSubmit={handleSubmit}>
          <ScrollArea className="h-[60vh] p-4">
            <div className="space-y-4">
              <fieldset className="border p-4 rounded-md">
                <legend className="-ml-1 px-1 text-sm font-medium">
                  Empresa
                </legend>
                <div className="space-y-2 pt-2">
                  <Label htmlFor="registro">
                    Registro da Vaga (Código único)
                  </Label>
                  <Input
                    id="registro"
                    value={formData.registro}
                    onChange={handleChange}
                    disabled={!!vaga} // Desabilita o campo se estiver editando
                    required // Torna o campo obrigatório no HTML
                  />
                  <Label htmlFor="nome_fantasia">Nome Fantasia</Label>
                  <Select
                    value={formData.empresa.nome_fantasia}
                    onValueChange={handleEmpresaSelect}
                  >
                    <SelectTrigger id="nome_fantasia">
                      <SelectValue placeholder="Selecione uma empresa" />
                    </SelectTrigger>
                    <SelectContent>
                      {empresas.map((empresa, index) => (
                        <SelectItem key={index} value={empresa.nome_fantasia}>
                          {empresa.nome_fantasia}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>

                  <Label htmlFor="razao_social">Razão Social</Label>
                  <Input
                    id="razao_social"
                    value={formData.empresa.razao_social}
                    disabled
                    readOnly
                  />
                  <Label htmlFor="tipo">Tipo</Label>
                  <Input
                    id="tipo"
                    value={formData.empresa.tipo}
                    disabled
                    readOnly
                  />
                </div>
              </fieldset>

              <fieldset className="border p-4 rounded-md">
                <legend className="-ml-1 px-1 text-sm font-medium">
                  Detalhes da Vaga
                </legend>
                <div className="space-y-2 pt-2">
                  <Label htmlFor="cargo">Cargo</Label>
                  <Select
                    value={formData.cargo}
                    onValueChange={handleCargoSelect}
                  >
                    <SelectTrigger id="cargo">
                      <SelectValue placeholder="Selecione um cargo" />
                    </SelectTrigger>
                    <SelectContent>
                      {cargos.map((cargo) => (
                        <SelectItem key={cargo.nome} value={cargo.nome}>
                          {cargo.nome}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>

                  {/* ... Restante dos Inputs (cidade, estado, etc.) ... */}
                  <Label htmlFor="cidade">Cidade</Label>
                  <Input
                    id="cidade"
                    value={formData.cidade}
                    onChange={handleChange}
                  />
                  <Label htmlFor="estado">Estado (UF)</Label>
                  <Input
                    id="estado"
                    value={formData.estado}
                    onChange={handleChange}
                  />
                  <Label htmlFor="remuneracao">Remuneração</Label>
                  <Input
                    id="remuneracao"
                    value={formData.remuneracao}
                    onChange={handleChange}
                  />
                  <Label htmlFor="regime">Regime (ex: CLT, PJ)</Label>
                  <Input
                    id="regime"
                    value={formData.regime}
                    onChange={handleChange}
                  />
                  <Label htmlFor="jornada_trabalho">Jornada de Trabalho</Label>
                  <Input
                    id="jornada_trabalho"
                    value={formData.jornada_trabalho}
                    onChange={handleChange}
                  />
                </div>
              </fieldset>

              <fieldset className="border p-4 rounded-md">
                <legend className="-ml-1 px-1 text-sm font-medium">
                  Requisitos
                </legend>
                <div className="space-y-2 pt-2">
                  <Label htmlFor="formacao">Formação</Label>
                  <Input
                    id="formacao"
                    value={formData.formacao}
                    onChange={handleChange}
                  />
                  <Label htmlFor="pre_requisitos">Pré-Requisitos</Label>
                  <Input
                    id="pre_requisitos"
                    value={formData.pre_requisitos}
                    onChange={handleChange}
                  />
                  <Label htmlFor="conhecimentos_requeridos">
                    Conhecimentos Requeridos
                  </Label>
                  <Input
                    id="conhecimentos_requeridos"
                    value={formData.conhecimentos_requeridos}
                    onChange={handleChange}
                  />
                </div>
              </fieldset>
            </div>
          </ScrollArea>
          <DialogFooter className="pt-4">
            <Button
              type="button"
              variant="outline"
              onClick={() => onOpenChange(false)}
            >
              Cancelar
            </Button>
            <Button type="submit">Salvar Vaga</Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
