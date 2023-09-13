import React from 'react';
import { Link } from 'react-router-dom';
import './sidebar.css'

const Sidebar = () => {
    return (
        <div className="sidebar">

            <ol className='sidebar-buttons'>
                <li><Link to="/admin/reservas">Reservas</Link></li>
                <li><Link to="/admin/usuarios">Usuarios</Link></li>
                <li><Link to="/admin/tipos-cabaña">Tipos de Cabaña</Link></li>
                <li><Link to="/admin/cabañas">Cabañas</Link></li>
                <li><Link to="/admin/caracteristicas">Características</Link></li>
            </ol>


        </div>
    );
};

export default Sidebar;
