// CSS
import "./FindFlight.css";

// Font Awesome
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";

const FindFlight = () => {

  const handleSubmit = async(e) => {
    e.preventDefault();

    // Logica
  }

  return (
    <div>
      <main className="find-flight-container">
        <form onSubmit={handleSubmit}>
          <div className="search-container">
            <div>
              <label htmlFor="id">ID do voo</label>
              <input type="number" name="id" placeholder="Nº" />
            </div>
            <div>
              <label htmlFor="origin">Origem</label>
              <input type="text" name="origin" placeholder="País, cidade ou aeroporto" />
            </div>
            <div>
              <label htmlFor="destiny">Destino</label>
              <input type="text" name="destiny" placeholder="País, cidade ou aeroporto" />
            </div>
            <div>
              <label htmlFor="airline">Companhia aérea</label>
              <input type="text" name="airline" placeholder="Nome" />
            </div>
          </div>
          <button type="submit">
            <span>Buscar</span>
            <FontAwesomeIcon icon={faSearch} />
          </button>
        </form>
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
            <tr>
              <td>1</td>
              <td>Nome do aeroporto de origem</td>
              <td>Nome do aeroporto de destino</td>
              <td>Nome da companhia aérea</td>
            </tr>
          </tbody>
        </table>
      </main>
    </div>
  )
}

export default FindFlight;