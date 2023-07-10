import React, { useState } from 'react';
import '../../index.css';

export default function Home() {
  function redirectCadastroCliente() {
    window.location.href = '/cadastrar-cliente';
  }

  function redirectCadastroNoticia() {
    window.location.href = '/cadastrar-noticia';
  }

  return (
    <div className="container stylized">
      <div className="row">
        <div className="col-md-6 border-right">
          <div>
            <h1 className="text-center mb-4 texto-preto-sombra-cinza">
              Cliente
            </h1>
          </div>
          <hr />
          <div className="mt-4">
            <button type="button" className="btn btn-primary w-100" onClick={redirectCadastroCliente}>
              Acessar Cadastro de Cliente >>
            </button>
          </div>
        </div>
        <div className="col-md-6">
          <div>
            <h1 className="text-center mb-4 texto-preto-sombra-cinza">
              Notícia
            </h1>
          </div>
          <hr />
          <div className="mt-4">
            <button type="button" className="btn btn-primary w-100" onClick={redirectCadastroNoticia}>
              Acessar Cadastro de Notícia >>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
