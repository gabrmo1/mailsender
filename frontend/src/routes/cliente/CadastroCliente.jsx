import React, { useState } from 'react';

export default function CadastroCliente() {
  const [errors, setErrors] = useState({});
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [dataNascimento, setdataNascimento] = useState('');

  function doFetchBody(url, method, body) {
    return fetch(url, { method, headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(body) });
  }

  function goToHome() {
    window.location.href = '/';
  }

  function handleSubmit(event) {
    event.preventDefault();

    const newErrors = {};

    if (!nome) {
      newErrors.nome = 'O nome precisa ser informado';
    } else if (nome.length > 100) {
      newErrors.nome = 'O nome pode ter no máximo 100 caracteres';
    }

    if (!email) {
      newErrors.email = 'o email precisa ser informado';
    } else if (email.length > 150) {
      newErrors.email = 'O email pode ter no máximo 150 caracteres';
    }

    if (!dataNascimento) {
      newErrors.dataNascimento = 'A data de nascimento precisa ser informada';
    }

    if (Object.keys(newErrors).length === 0) {
      doFetchBody('http://localhost:8080/clientes/cadastrar', 'POST', {
        nome,
        email,
        dataNascimento,
      }).then((response) => {
        response.json()
          .then((data) => {
            if (data.message) {
              alert(data.message);
            } else {
              alert('Sucesso ao cadastrar cliente');
              goToHome();
            }
          }).catch(() => {
            alert('Falha ao obter dados de retorno');
          });
      }).catch(() => {
        alert('Falha ao conectar-se com o backend');
      });
    }

    setErrors(newErrors);
  }

  return (
    <div className="container stylized">
      <div className="row">
        <div className="col-md-12">
          <form onSubmit={handleSubmit}>
            <h1 className="mb-5 text-center text-shadow-black">Cadastro de Cliente</h1>
            <div className="mb-4">
              <label htmlFor="nome" className="form-label">Nome</label>
              <input type="text" className={`form-control ${errors.nome && 'is-invalid'}`} id="nome" value={nome} onChange={(e) => setNome(e.target.value)} />
              {errors.nome && (
                <div className="invalid-feedback position-absolute">{errors.nome}</div>
              )}
            </div>
            <hr />
            <div className="mb-4">
              <label htmlFor="email" className="form-label">E-mail</label>
              <input type="text" className={`form-control ${errors.email && 'is-invalid'}`} id="email" value={email} onChange={(e) => setEmail(e.target.value)} />
              {errors.email && (
                <div className="invalid-feedback position-absolute">{errors.email}</div>
              )}
            </div>
            <hr />
            <div className="mb-4">
              <label htmlFor="texto" className="form-label">Data de nascimento</label>
              <input type="date" className={`form-control ${errors.dataNascimento && 'is-invalid'}`} id="dataNascimento" value={dataNascimento} onChange={(e) => setdataNascimento(e.target.value)} />
              {errors.dataNascimento && (
                <div className="invalid-feedback position-absolute">{errors.dataNascimento}</div>
              )}
            </div>
            <hr className="mb-3" />
            <button type="submit" className="btn btn-primary w-100 mb-2">Enviar</button>
            <button type="button" className="btn btn-secondary w-100" onClick={goToHome}>Voltar</button>
          </form>
        </div>
      </div>
    </div>

  );
}
