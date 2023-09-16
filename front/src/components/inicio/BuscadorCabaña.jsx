import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import axios from 'axios';
import es from 'date-fns/locale/es';
import './buscadorCabaña.css'
import Person from '../../assets/person.svg'

const BuscadorCabaña = () => {
  const [dateRange, setDateRange] = useState([null, null]);
  const [cantPersonas, setCantPersonas] = useState(1);
  const [idTipoCabaña, setIdTipoCabaña] = useState('0');
  const [tiposCabaña, setTiposCabaña] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const fetchTiposCabaña = () => {
    axios
      .get('http://localhost:8080/api/cabañas/tipos')
      .then((response) => {
        setTiposCabaña(response.data);
      })
      .catch((error) => {
        console.error('Error al obtener los tipos de cabaña:', error);
      });
  };

  useEffect(() => {
    fetchTiposCabaña();
  }, []);

  const handleTipoCabañaChange = (event) => {
    setIdTipoCabaña(event.target.value);
  };

  const handlePersonasIncrement = () => {
    setCantPersonas(cantPersonas + 1);
  };

  const handlePersonasDecrement = () => {
    if (cantPersonas > 1) {
      setCantPersonas(cantPersonas - 1);
    }
  };

  const handleBuscarClick = () => {
    const [fechaInicio, fechaFin] = dateRange;

    if (fechaInicio && fechaFin && cantPersonas >= 1) {
      if (fechaFin <= fechaInicio) {
        setError('La fecha de salida debe ser posterior a la fecha de entrada.');
      } else {
        axios
          .get(`http://localhost:8080/api/cabañas/buscar`, {
            params: {
              fechaInicio: fechaInicio.toISOString().substring(0, 10),
              fechaFin: fechaFin.toISOString().substring(0, 10),
              cantPersonas,
              idTipoCabaña,
            },
          })
          .then((response) => {
            navigate('/resultados-busqueda', {
              state: { resultados: response.data, fechaInicio, fechaFin },
            });
          })
          .catch((error) => {
            console.error('Error en la búsqueda de cabañas:', error);
          });
      }
    } else {
      setError('Todos los campos son obligatorios y la cantidad de personas debe ser al menos 1.');
    }
  };

  return (
    <div className="buscador">
      <div className='buscador2'>
        <div className='buscador3'>
          <div className='buscador4'>
            <div className='buscador5'>
              <div>
                <div className='labels'>Tipo de Cabaña:</div>
                <div className='sublabels'>
                  <select className= 'selector' value={idTipoCabaña} onChange={handleTipoCabañaChange}>
                    <option value="">Todos</option>
                    {tiposCabaña.map((tipoCabaña) => (
                      <option key={tipoCabaña.idtipoCabaña} value={tipoCabaña.idtipoCabaña}>
                        {tipoCabaña.nombre}
                      </option>
                    ))}
                  </select>
                </div>
              </div>
              <div>
                <div className='labels'>Huéspedes</div>
                <div className='sublabels'>
                  <div className='personas'>
                    <img src={Person} width="35px" height="35px" />
                    <button className='button2' onClick={handlePersonasDecrement}>-</button>
                    <span>{cantPersonas}</span>
                    <button className='button2' onClick={handlePersonasIncrement}>+</button>
                  </div>
                </div>
              </div>
              <div>
                <div className='labels'>Fechas</div>
                <div className='sublabels'>
                  <DatePicker
                    dateFormat="dd/MM/yyyy"
                    className='date-picker'
                    selected={dateRange[0]}
                    startDate={dateRange[0]}
                    endDate={dateRange[1]}
                    onChange={(dates) => setDateRange(dates)}
                    minDate={new Date()}
                    monthsShown={2}
                    selectsRange
                    locale={es}
                    calendarClassName="custom-month-container"

                  />
                </div>
              </div>
            </div>
          </div>
          <div className='buscador4-2'>
            <button className='buscar' onClick={handleBuscarClick}>Buscar</button>
            {error && <p style={{ color: 'red' }}>{error}</p>}
          </div>
        </div>
      </div>
    </div>
  );
};

export default BuscadorCabaña;
