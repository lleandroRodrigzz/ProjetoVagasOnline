"use client";

import { Vaga } from "@/types";
import { Button } from "@/app/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/app/components/ui/card";
import {
  Edit,
  Trash2,
  Building,
  MapPin,
  Briefcase,
  DollarSign,
} from "lucide-react";

interface VagaCardProps {
  vaga: Vaga;
  onEdit: (vaga: Vaga) => void;
  onDelete: (registro: string) => void; // <-- Mudou de ID para registro
}

export function VagaCard({ vaga, onEdit, onDelete }: VagaCardProps) {
  return (
    <Card className="flex flex-col justify-between shadow-lg">
      <CardHeader>
        <CardTitle className="text-xl">{vaga.cargo}</CardTitle>
        <CardDescription className="flex items-center gap-2 pt-1">
          <Building className="h-4 w-4" /> {vaga.empresa.nome_fantasia}
        </CardDescription>
      </CardHeader>
      <CardContent className="space-y-3">
        <div className="flex items-center gap-2 text-sm">
          <MapPin className="h-4 w-4" /> {vaga.cidade}, {vaga.estado}
        </div>
        <div className="flex items-center gap-2 text-sm">
          <Briefcase className="h-4 w-4" /> {vaga.regime} -{" "}
          {vaga.jornada_trabalho}
        </div>
        <div className="flex items-center gap-2 text-sm font-semibold text-green-600 dark:text-green-400">
          <DollarSign className="h-4 w-4" /> {vaga.remuneracao}
        </div>
        <div className="pt-2">
          <h4 className="font-semibold">Conhecimentos:</h4>
          <p className="text-sm text-slate-600 dark:text-violet-600">
            {vaga.conhecimentos_requeridos}
          </p>
        </div>
      </CardContent>
      <CardFooter className="flex justify-end gap-2 p-4">
        <Button
          variant="destructive"
          className="cursor-pointer"
          size="sm"
          onClick={() => onDelete(vaga.registro)} // <-- Passa o registro
        >
          <Trash2 className="h-4 w-4" />
        </Button>
        <Button variant="default" size="sm" className="cursor-pointer" onClick={() => onEdit(vaga)}>
          <Edit className="h-4 w-4 mr-2" /> Editar
        </Button>
      </CardFooter>
    </Card>
  );
}
