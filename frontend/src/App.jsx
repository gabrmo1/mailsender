import React from 'react';
import { Outlet } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.css';
import './App.css';

function App() {
  return (
    <div className="App">
      <h1 className="text-center texto-branco-sombra-escura">MailSender v1</h1>
      <br />
      <Outlet />
    </div>
  );
}

export default App;
