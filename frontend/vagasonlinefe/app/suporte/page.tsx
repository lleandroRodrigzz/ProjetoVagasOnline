import { Mail, HelpCircle } from "lucide-react";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/app/components/ui/card";
import { Button } from "@/app/components/ui/button";
import { Avatar, AvatarImage, AvatarFallback } from "@/components/ui/avatar";

const Suporte = () => {
  return (
    <div className="container mx-auto p-4 md:p-8 mt-20">
      <header className="flex flex-col md:flex-row justify-between md:items-center gap-4 mb-12">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">VagasOnline</h1>
          <p className="text-lg text-muted-foreground">
            Esta com problemas no sistema ? Contate-nos
          </p>
        </div>
      </header>

      <div className="mb-12 grid grid-cols-1 md:grid-cols-2 gap-6">
        <Card>
          <CardHeader>
            <div className="flex items-center gap-2">
              <HelpCircle className="h-5 w-5 text-primary" />
              <CardTitle className="text-2xl">
                Perguntas Frequentes (FAQ)
              </CardTitle>
            </div>
            <CardDescription>
              Encontre respostas rápidas para as dúvidas mais comuns sobre a
              plataforma.
            </CardDescription>
          </CardHeader>
          <CardContent>
            <Button
              variant="link"
              className="p-0 h-auto text-primary font-medium"
            >
              Ver FAQ &rarr;
            </Button>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <div className="flex items-center gap-2">
              <Mail className="h-5 w-5 text-primary" />
              <CardTitle className="text-2xl">Suporte por E-mail</CardTitle>
            </div>
            <CardDescription>
              Prefere falar por e-mail? Nossa equipe responderá o mais rápido
              possível.
            </CardDescription>
          </CardHeader>
          <CardContent>
            <a
              href="mailto:suporte@vagasonline.com"
              className="text-primary font-medium hover:underline"
            >
              suporte@vagasonline.com
            </a>
          </CardContent>
        </Card>
      </div>

      <div className="mb-6">
        <h2 className="text-2xl font-bold tracking-tight text-center">
          Conheça nossa Equipe
        </h2>
        <p className="text-lg text-center text-muted-foreground mt-2">
          Estamos aqui para ajudar no que for preciso.
        </p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-6">
        <Card>
          <CardContent className="flex flex-col items-center text-center pt-6">
            <Avatar className="w-24 h-24 mb-4">
              <AvatarImage
                src="https://avatars.githubusercontent.com/u/178163554?v=4"
                alt="Foto de Leandro Rodrigues"
              />
              <AvatarFallback>LR</AvatarFallback>
            </Avatar>
            <h3 className="text-xl font-semibold mb-1">Leandro Rodrigues</h3>
            <p className="text-muted-foreground mb-4">
              Desenvolvedor Full-Stack
            </p>
            <Button className="w-full">Contatar</Button>
          </CardContent>
        </Card>

        <Card>
          <CardContent className="flex flex-col items-center text-center pt-6">
            <Avatar className="w-24 h-24 mb-4">
              <AvatarImage
                src="https://avatars.githubusercontent.com/u/134321348?v=4"
                alt="Foto de João Manuel"
              />
              <AvatarFallback>JM</AvatarFallback>
            </Avatar>
            <h3 className="text-xl font-semibold mb-1">João Manuel</h3>
            <p className="text-muted-foreground mb-4">Desenvolvedor Mobile</p>
            <Button className="w-full">Contatar</Button>
          </CardContent>
        </Card>
      </div>
    </div>
  );
};

export default Suporte;
