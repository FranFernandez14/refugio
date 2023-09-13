import React, { useState } from 'react';
import "./login.css";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(''); // Agregar estado para el manejo de errores
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      // Realizar una solicitud POST al servidor para iniciar sesión
      const response = await axios.post('http://localhost:8080/api/auth/login', {
        email,
        password
      });

      // Verificar si la solicitud fue exitosa y si se recibió un token de acceso
      if (response.status === 200 && response.data.accessToken) {
        // Almacenar el token en el almacenamiento local del navegador
        localStorage.setItem('accessToken', response.data.accessToken);
        navigate('/');
        window.location.reload();
      } else {
        setError('Inicio de sesión fallido. Verifica tus credenciales.');
      }
    } catch (error) {
      setError('Error al iniciar sesión. Por favor, inténtalo de nuevo.');
      console.error('Error al iniciar sesión:', error);
    }
  };

  return (
    <div className="login-container">
      <h2 id="titulo-sesion"> Refugio de Montaña </h2>
      <form>
        <div>
          <label htmlFor="email">Correo Electrónico</label>
          <input
            type="text"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="password">Contraseña</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <p
          type="button"
          style={{
            fontSize: '12px',
            padding: '4px 8px',
            textAlign: 'left',
            textDecoration: 'underline',
            cursor: 'pointer',
          }}
        >
          Recuperar Contraseña {/* Botón de recuperar contraseña */}
        </p>
        <div style={{ marginTop: '30px' }}>
          <button type="button" onClick={handleLogin} style={{ fontSize: '20px' }}>
            Iniciar Sesión
          </button>
        </div>
        <p
          style={{
            color: 'grey',
            textDecoration: 'underline',
            cursor: 'pointer',
          }}
        >
          Registrarse
        </p>
        {error && <p style={{ color: 'red' }}>{error}</p>} {/* Mostrar mensaje de error */}
      </form>
    </div>
  );
}

export default Login;
