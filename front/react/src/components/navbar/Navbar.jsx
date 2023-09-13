import React from 'react';
import { useNavigate } from 'react-router-dom';

import './navbar.css';

export default function Navbar() {
  const navigate = useNavigate();

  const handleRegistro = () => {
    navigate('/registro');
    window.location.reload();
  };

  const handleInicioSesion = () => {
    navigate('/login');
    window.location.reload();
  };

  return (
    <div className='navbar'>
      <ul className='nav-link'>
        <li><h2>Refugio de montaña</h2></li>
      </ul>
      <ul className='nav-link'>
        <li><button onClick={handleRegistro} className="nav-button">Registrarse</button></li>
        <li><button onClick={handleInicioSesion} className="nav-button">Iniciar Sesión</button></li>
      </ul>
    </div>
  );
}
