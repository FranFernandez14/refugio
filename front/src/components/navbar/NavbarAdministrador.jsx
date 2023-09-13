import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';


export default function NavbarAdministrador() {
  const navigate = useNavigate();
  const [menuVisible, setMenuVisible] = useState(false);

  const handleToggleMenu = () => {
    setMenuVisible(!menuVisible);
  };

  const handleLogout = () => {
    localStorage.removeItem('accessToken');
    navigate('/');
    window.location.reload();
  };

  const handleMisReservas = () => {
    navigate('/misreservas');
  };

  const handleAdmin = () => {
    navigate('/admin/reservas');
  };

  const handlePerfil = () => {
    navigate('/perfil');
  };

  return (
    <div className='navbar'>

      <h2>Refugio de montaña</h2>
      <div className='botones'>
        <div><li><Link to="/">Nuestras cabañas</Link></li></div>
        <div><li><Link to="/">Opiniones</Link></li></div>
        <div><div className={`menu-icon ${menuVisible ? 'open' : ''}`} onClick={handleToggleMenu}>
          <div className="bar"></div>
          <div className="bar"></div>
          <div className="bar"></div>
        </div>
        </div>
      </div>
      <ul className={`nav-links ${menuVisible ? 'active' : ''}`}>
        <li><button onClick={handleAdmin} className="nav-button">Menú de Administrador</button></li>
        <li><button onClick={handleMisReservas} className="nav-button">Mis Reservas</button></li>
        <li><button onClick={handlePerfil} className="nav-button">Perfil</button></li>
        <li><button onClick={handleLogout} className="nav-button">Cerrar Sesión</button></li>
      </ul>
    </div>
  );
}
