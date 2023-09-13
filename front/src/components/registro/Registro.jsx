import React, { useState } from 'react';
import "./registro.css";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function Registro() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [domicilio, setDomicilio] = useState('');
  const [nombre, setNombre] = useState('');
  const [apellido, setApellido] = useState('');
  const [telefono, setTelefono] = useState('');
  const navigate = useNavigate();


  const handleCancelar = async () => {
    navigate('/')
    window.location.reload();
  }

  const handleRegistro = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/auth/registro', {
        email,
        password,
        nombre,
        apellido,
        domicilio,
        telefono
      });

      if (response.status === 200) {
        navigate('/login');
      } else {
        setError('Registro fallido, verifica tus credenciales.');
      }
    } catch (error) {
      setError('Error al registrarse. Por favor, inténtalo de nuevo.');
      console.error('Error al registrarse:', error);
    }
  };

  return (
    <div className="registro-container">
      <div className='registro2'>
        <h2>Registrarse</h2>
        <form>
          <div className="form-group">
            <label htmlFor="email">E-mail</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Contraseña</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="domicilio">Domicilio</label>
            <input
              type="text"
              id="domicilio"
              value={domicilio}
              onChange={(e) => setDomicilio(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="nombre">Nombre</label>
            <input
              type="text"
              id="nombre"
              value={nombre}
              onChange={(e) => setNombre(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="apellido">Apellido</label>
            <input
              type="text"
              id="apellido"
              value={apellido}
              onChange={(e) => setApellido(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="telefono">Teléfono</label>
            <input
              type="tel"
              id="telefono"
              value={telefono}
              onChange={(e) => setTelefono(e.target.value)}
            />
          </div>
          <div className='botones'>
            <button type="button" onClick={handleCancelar}>
              Cancelar
            </button>
            <button type="button" onClick={handleRegistro}>
              Registrarse
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Registro;
