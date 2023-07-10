import React, { useState } from 'react';

export default function CadastroNoticia() {
  const [errors, setErrors] = useState({});
  const [titulo, setTitulo] = useState('');
  const [descricao, setDescricao] = useState('');
  const [link, setLink] = useState('');
  const [urlImagem, setUrlImagem] = useState('');

  function doFetchBody(url, method, body) {
    return fetch(url, { method, headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(body) });
  }

  function goToHome() {
    window.location.href = '/';
  }

  function handleSubmit(event) {
    event.preventDefault();

    const newErrors = {};

    if (!titulo) {
      newErrors.titulo = 'O título precisa ser informado';
    } else if (titulo.length > 50) {
      newErrors.titulo = 'O título pode ter no máximo 50 caracteres';
    }

    if (!descricao) {
      newErrors.descricao = 'A descrição precisa ser informada';
    }

    if (!link) {
      newErrors.link = 'O link precisa ser informado';
    }

    if (!urlImagem) {
      newErrors.urlImagem = 'A URL da imagem precisa ser informada';
    }

    if (Object.keys(newErrors).length === 0) {
      doFetchBody('http://localhost:8080/noticias/cadastrar', 'POST', {
        titulo,
        descricao,
        link,
        urlImagem,
      }).then(() => {
        alert('Sucesso ao cadastrar notícia');
        goToHome();
      }).catch(() => {
        alert('Falha ao cadastrar notícia');
      });
    }

    setErrors(newErrors);
  }

  return (
    <div className="container stylized">
      <div className="row">
        <div className="col-md-12">
          <form onSubmit={handleSubmit}>
            <h1 className="mb-5 text-center text-shadow-black">Cadastro de Notícia</h1>
            <div className="mb-4">
              <label htmlFor="titulo" className="form-label">Título</label>
              <input type="text" className={`form-control ${errors.titulo && 'is-invalid'}`} id="titulo" value={titulo} onChange={(e) => setTitulo(e.target.value)} />
              {errors.titulo && (
                <div className="invalid-feedback position-absolute">{errors.titulo}</div>
              )}
            </div>
            <hr />
            <div className="mb-4">
              <label htmlFor="descricao" className="form-label">Descrição</label>
              <input type="text" className={`form-control ${errors.descricao && 'is-invalid'}`} id="descricao" value={descricao} onChange={(e) => setDescricao(e.target.value)} />
              {errors.descricao && (
                <div className="invalid-feedback position-absolute">{errors.descricao}</div>
              )}
            </div>
            <hr />
            <div className="mb-4">
              <label htmlFor="texto" className="form-label">Link</label>
              <input type="url" className={`form-control ${errors.link && 'is-invalid'}`} id="link" value={link} onChange={(e) => setLink(e.target.value)} />
              {errors.link && (
                <div className="invalid-feedback position-absolute">{errors.link}</div>
              )}
            </div>
            <hr />
            <div className="mb-4">
              <label htmlFor="texto" className="form-label">Url da Imagem</label>
              <input type="url" className={`form-control ${errors.link && 'is-invalid'}`} id="urlImagem" value={urlImagem} onChange={(e) => setUrlImagem(e.target.value)} />
              {errors.urlImagem && (
                <div className="invalid-feedback position-absolute">{errors.urlImagem}</div>
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
