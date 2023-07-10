import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.css';

import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import Home from './routes/home/Home';
import CadastroCliente from './routes/cliente/CadastroCliente';
import CadastroNoticia from './routes/noticia/CadastroNoticia';

const router = createBrowserRouter(
  [
    {
      element: <App />,
      children: [
        {
          path: '/',
          element: <Home />,
        },
        {
          path: '/cadastrar-cliente',
          element: <CadastroCliente />,
        },
        {
          path: '/cadastrar-noticia',
          element: <CadastroNoticia />,
        },
      ],
    },
  ],
);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
);
