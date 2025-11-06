// CSS
import "./PlaneInformations.css";

// dayjs
import dayjs from "dayjs";

// Font Awesome
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlaneDeparture, faPlaneUp, faX } from "@fortawesome/free-solid-svg-icons";
import { faLocationDot } from "@fortawesome/free-solid-svg-icons";

const FlightPlanInformations = ({plan, closePlanDetails}) => {


  return (
    <aside className="flight-plan-informations">

      {/* Cabecalho */}
      <header>
        <div className="flight-informations">
          {/* flight icao */}
          <p>{plan.flight.icao}</p>
          {/* flight number */}
          <p>{plan.flight.number}</p>
          {/* aircraft icao code */}
          <p>{plan.aircraft.icao}</p>
        </div>
        <p>{plan.airline.name}</p>
      </header>

      <img className="plane-image" src={plan.aircraft.image_url} alt={plan.aircraft.name} />

      {/* Informacoes da rota */}
      <div className="route-informations">
        <div className="informations">
          <div>
            <p>{plan.departure.iata}</p>
            <p>{plan.departure.airport.city_name}</p>
          </div>
          <div>
            <p>Agendado: {dayjs(plan.departure.scheduled).format('HH:mm')}</p>
            <p>Estimado: {dayjs(plan.departure.estimated).format('HH:mm')}</p>
          </div>
        </div>

        <div className="division">
          <img src="src/assets/plane-up-solid-full.svg" alt="plane icon" />
        </div>
      
        <div className="informations">
          <div>
            <p>{plan.arrival.iata}</p>
            <p>{plan.arrival.airport.city_name}</p>
          </div>
          <div>
            <p>Agendado: {dayjs(plan.arrival.scheduled).format('HH:mm')}</p>
            <p>Estimado: {dayjs(plan.arrival.estimated).format('HH:mm')}</p>
          </div>
        </div>
      </div>

      {/* Barra de progresso */}
      <div className="progress">
          <div className="bar" style={{width: `${plan && plan.live.progress}%`}}>
            <img src="src/assets/plane-up-solid-full.svg" alt="plane icon" />
          </div>
      </div>

      {/* Informacoes da aeronave */}
      <div className="information-container">
        <div className="title">
          <div>
            <FontAwesomeIcon icon={faPlaneUp} />
          </div>
          <h2>Informações da Aeronave</h2>
        </div>
        <div className="informations">
          <p>Aircraft Type: {plan.aircraft.icao}</p>
          <p>{plan.aircraft.name}</p>
          <p>Registration: {plan.aircraft.registration}</p>
          <p>Aircraft Category: {plan.aircraft.aircraft_category}</p>
        </div>
      </div>

      {/* Informacoes do voo */}
      <div className="information-container">
        <div className="title">
          <div>
            <FontAwesomeIcon icon={faPlaneDeparture} />
          </div>
          <h2>Informações do Voo</h2>
        </div>
        <div className="informations">
          <p>Velocidade horizontal: {plan.live.speed_horizontal} kts</p>
          <p>Velocidade vertical: {plan.live.speed_vertical} ft/s</p>
          <p>Altitude: {plan.live.altitude} ft</p>
        </div>
      </div>
      
      {/* Informacoes de posicao */}
      <div className="information-container">
        <div className="title">
          <div>
            <FontAwesomeIcon icon={faLocationDot} />
          </div>
          <h2>Informações de Posição</h2>
        </div>
        <div className="informations">
          <p>Última atualização: {dayjs(plan.live.updated).format('DD/MM/YYYY - HH:mm:ss')}</p>
          <p>Última latitude: {plan.live.latitude.toFixed(2)}</p>
          <p>Última longitude: {plan.live.longitude.toFixed(2)}</p>
        </div>
      </div>
      <button className="close-button" onClick={() => closePlanDetails(true)}>
        <FontAwesomeIcon icon={faX} />
      </button>
    </aside>
  )
}

export default FlightPlanInformations;