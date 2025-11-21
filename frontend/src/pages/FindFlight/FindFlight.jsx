// CSS
import "./FindFlight.css";

// Hooks
import { useEffect, useState } from "react";

// Font Awesome
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";

// API
import api from "../../services/api";

const FindFlight = () => {

  const [id, setId] = useState("");
  const [origin, setOrigin] = useState("");
  const [destiny, setDestiny] = useState("");
  const [airline, setAirline] = useState("");
  const [flights, setFlights] = useState(null);
  const [info, setInfo] = useState(null);

  useEffect(() => {
    const fetchData = async() => {
      try {
        const response = await api.get("/api/flights/v1");
        setFlights(response.data);
        setInfo(null);
      } catch (error) {
        setInfo({ type: "ERROR", message: "Não foi possível carregar os voos" });
      }
    }
    fetchData();
  }, []);

  const handleSubmit = async(e) => {
    e.preventDefault();

    const data = {
      id,
      origin,
      destiny,
      airline
    }

    try {
      const response = await api.get("/api/flights/v1/search", { params: data });
      if(response.data.length === 0) {
        setInfo({ type: "ERROR", message: "Voo não encontrado!" });
        return;
      }
      setInfo(null);
      setFlights(response.data);
    } catch(error) {
      setInfo({ type: "ERROR", message: "Não foi possível encontrar o voo" });
    }
  }

  return (
    <main className="find-flight-container">
      <form onSubmit={handleSubmit}>
        <div className="search-container">
          <div>
            <label htmlFor="id">ID do voo</label>
            <input type="number" name="id" placeholder="Nº" value={id} onChange={(e) => setId(e.target.value)} />
          </div>
          <div>
            <label htmlFor="origin">Origem</label>
            <input type="text" name="origin" placeholder="País, cidade ou aeroporto" value={origin} onChange={(e) => setOrigin(e.target.value)} />
          </div>
          <div>
            <label htmlFor="destiny">Destino</label>
            <input type="text" name="destiny" placeholder="País, cidade ou aeroporto" value={destiny} onChange={(e) => setDestiny(e.target.value)} />
          </div>
          <div>
            <label htmlFor="airline">Companhia aérea</label>
            <input type="text" name="airline" placeholder="Nome" value={airline} onChange={(e) => setAirline(e.target.value)} />
          </div>
        </div>
        <button type="submit">
          <span>Buscar</span>
          <FontAwesomeIcon icon={faSearch} />
        </button>
      </form>
      {info && info.type === "ERROR" && <p className="error form-error">{info.message}</p>}
      <table>
        <thead>
          <tr>
            <th>Id do voo</th>
            <th>Origem</th>
            <th>Destino</th>
            <th>Companhia aérea</th>
          </tr>
        </thead>
        <tbody>
          {flights && flights.length > 0 && flights.map(flight => (
            <tr key={flight.id}>
              <td>{flight.id}</td>
              <td>{flight.departure.airport.city_name}</td>
              <td>{flight.arrival.airport.city_name}</td>
              <td>{flight.airline.name}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </main>
  )
}

export default FindFlight;