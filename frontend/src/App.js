import React, { useState, useEffect, useRef } from 'react';

function App() {
  const [cpf, setCpf] = useState('');
  const [password, setPassword] = useState('');
  const [loggedIn, setLoggedIn] = useState(false);
  const [error, setError] = useState('');
  const [status, setStatus] = useState('disconnected');
  const logoutTimer = useRef(null);

  // hard–coded credentials for now; will replace with API call later
  const validCpf = 'admin';
  const validPassword = 'admin';

  const handleSubmit = (e) => {
    e.preventDefault();
    if (cpf === validCpf && password === validPassword) {
      setLoggedIn(true);
      setError('');
    } else {
      setError('CPF ou senha inválidos.');
    }
  };

  const handleExit = () => {
    setLoggedIn(false);
    setStatus('disconnected');
    if (logoutTimer.current) {
      clearTimeout(logoutTimer.current);
      logoutTimer.current = null;
    }
  };

  useEffect(() => {
    if (loggedIn) {
      setStatus('connected');
      // set a 2‑minute logout timer
      logoutTimer.current = setTimeout(() => {
        setLoggedIn(false);
        setStatus('disconnected');
        setError('Sessão expirada. Faça login novamente.');
      }, 2 * 60 * 1000);
    } else {
      // cleanup timer when not logged in
      if (logoutTimer.current) {
        clearTimeout(logoutTimer.current);
        logoutTimer.current = null;
      }
      setStatus('disconnected');
    }
    return () => {
      if (logoutTimer.current) {
        clearTimeout(logoutTimer.current);
        logoutTimer.current = null;
      }
    };
  }, [loggedIn]);

  if (!loggedIn) {
    return (
      <div className="login-container">
        <h2>Login</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <label>CPF:</label>
            <input
              type="text"
              value={cpf}
              onChange={(e) => setCpf(e.target.value)}
            />
          </div>
          <div>
            <label>Senha:</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button type="submit">Entrar</button>
        </form>
        {error && <p style={{ color: 'red' }}>{error}</p>}
      </div>
    );
  }

  return (
    <div className="App">
      <header className="App-header">
        <h1>Cartório de Registros de Títulos e Documentos de Pessoas Civis</h1>
        <p>Aplicação inicial em React.</p>
        {error && <p style={{ color: 'red' }}>{error}</p>}
      </header>
      <footer className="App-footer">
        <p className={status === 'connected' ? 'status-connected' : 'status-disconnected'}>
          {status}
        </p>
        <button onClick={handleExit}>Exit</button>
      </footer>
    </div>
  );
}

export default App;
