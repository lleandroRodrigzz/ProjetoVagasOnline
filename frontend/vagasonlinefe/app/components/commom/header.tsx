"use client";

import { useState } from "react";
import Link from "next/link";
import { ThemeToggle } from "./theme-toggle";
import { usePathname } from "next/navigation";

export default function Cabecalho() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const pathname = usePathname();
  const isActive = (path: string) => pathname === path;

  return (
    <nav className="fixed top-0 left-0 right-0 z-50 bg-zinc-900/80 backdrop-blur-md border-b border-zinc-800">
      <div className="max-w-7xl mx-auto px-6 lg:px-8">
        <div className="flex justify-between items-center h-20">
          <Link
            href="/home"
            className="flex items-center space-x-3 group transition-transform duration-300 hover:scale-105"
          >
            <h1>
              <span className="text-white-400 font-bold">VAGAS</span>
              <span className="text-violet-400 font-bold"> ONLINE</span>
            </h1>
          </Link>

          <div className="hidden md:flex items-center space-x-1">
            <Link
              href="/home"
              className={`px-6 py-2 rounded-lg font-semibold transition-all duration-300 ${
                isActive("/home")
                  ? "text-violet-400 bg-violet-400/10"
                  : "text-zinc-300 hover:text-violet-400 hover:bg-zinc-800/50"
              }`}
            >
              Home
            </Link>
            <Link
              href="/suporte"
              className={`px-6 py-2 rounded-lg font-semibold transition-all duration-300 ${
                isActive("/suporte")
                  ? "text-violet-400 bg-violet-400/10"
                  : "text-zinc-300 hover:text-violet-400 hover:bg-zinc-800/50"
              }`}
            >
              Suporte
            </Link>
          </div>
        </div>
      </div>

      <div
        className={`md:hidden overflow-hidden transition-all duration-300 ease-in-out ${
          isMenuOpen ? "max-h-64 opacity-100" : "max-h-0 opacity-0"
        }`}
      >
        <div className="px-6 py-4 space-y-2 bg-zinc-900/95 backdrop-blur-md border-t border-zinc-800">
          <Link
            href="/home"
            onClick={() => setIsMenuOpen(false)}
            className={`block px-4 py-3 rounded-lg font-semibold transition-all duration-300 ${
              isActive("/home")
                ? "text-violet-400 bg-violet-400/10"
                : "text-zinc-300 hover:text-violet-400 hover:bg-zinc-800/50"
            }`}
          >
            Home
          </Link>
          <Link
            href="/suporte"
            onClick={() => setIsMenuOpen(false)}
            className={`block px-4 py-3 rounded-lg font-semibold transition-all duration-300 ${
              isActive("/suporte")
                ? "text-violet-400 bg-violet-400/10"
                : "text-zinc-300 hover:text-violet-400 hover:bg-zinc-800/50"
            }`}
          >
            Suporte
          </Link>
        </div>
      </div>
      <div className="absolute top-4 right-4 mt-1">
        <ThemeToggle />
      </div>
    </nav>
  );
}
