import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/navbar/Navbar';
import ReservaContainer from './components/admin/containers/ReservaContainer';
import UsuarioContainer from './components/admin/containers/UsuarioContainer';
import CabañaContainer from './components/admin/containers/CabañaContainer';
import TipoCabañaContainer from './components/admin/containers/TipoCabañaContainer';
import CaracteristicaContainer from './components/admin/containers/CaracteristicaContainer';
import RutaAdmin from './routes/RutaAdmin';
import Login from './components/login/Login';
import Registro from './components/registro/Registro';
import ResultadosBusquedaCabaña from './components/inicio/ResultadosBusquedaCabaña';
import DetalleReserva from './components/reserva/DetalleReserva';
import { decodeToken } from 'react-jwt';
import NavbarUsuario from './components/navbar/NavbarUsuario';
import NavbarEmpleado from './components/navbar/NavbarEmpleado';
import NavbarAdministrador from './components/navbar/NavbarAdministrador';
import Home from './components/pages/Home';
import './app.css'

const App = () => {
  const [userRoles, setUserRoles] = useState(0);
  const [currentLocation, setCurrentLocation] = useState('');

  useEffect(() => {
    const token = localStorage.getItem('accessToken');

    if (token) {
      const decodedToken = decodeToken(token, 'secret');
      const cantRoles = decodedToken.cantRoles;

      setUserRoles(cantRoles);
    }
    
    // Actualiza la ubicación actual cuando cambia la ruta
    setCurrentLocation(window.location.pathname);
  }, []);

  return (
    <BrowserRouter>
      {userRoles === 0 && !['/login', '/registro'].includes(currentLocation) && (
        <Navbar />
      )}
      {userRoles === 1 && (
        <NavbarUsuario />
      )}
      {userRoles === 2 && (
        <NavbarEmpleado />
      )}
      {userRoles === 3 && (
        <NavbarAdministrador />
      )}
      <div className='wrapper'>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/resultados-busqueda" element={<ResultadosBusquedaCabaña />} />
        <Route path="/detalle-reserva/:idReserva" element={<DetalleReserva />} />

        {userRoles === 0 && (
          <>
            <Route path="/login" element={<Login />} />
            <Route path="/registro" element={<Registro />} />
          </>
        )}

        {userRoles >= 1 && (
          <>
            <Route path="/detalle-reserva/:idReserva" element={<DetalleReserva />} />
          </>
        )}

        {userRoles >= 1 && (
          <>
            <Route path="/admin/*" element={<RutaAdmin />} />
            <Route path="/admin/reservas" element={<ReservaContainer />} />
          </>
        )}

        {userRoles >= 1 && (
          <>
            <Route path="/admin/*" element={<RutaAdmin />} />
            <Route path="/admin/usuarios" element={<UsuarioContainer />} />
            <Route path="/admin/cabañas" element={<CabañaContainer />} />
            <Route path="/admin/tipos-cabaña" element={<TipoCabañaContainer />} />
            <Route path="/admin/caracteristicas" element={<CaracteristicaContainer />} />
          </>
        )}

        <Route
          path="/forbidden"
          element={<Navigate to="/" replace />}
        />
      </Routes>
      </div>
    </BrowserRouter>
  );
};

export default App;
