// CSS
import "./Home.css";
import "leaflet/dist/leaflet.css";

// Hooks
import { useEffect, useState } from "react";

// Axios
import { useAxios } from "../../hooks/useAxios";

// Components
import Message from "../../components/Message";
import PlaneInformations from "../../components/PlaneInformations";

// Open Street Map / Leaflet
import { MapContainer, TileLayer, Marker, Popup, Polyline } from "react-leaflet";
import Leaflet from "leaflet";
import 'leaflet-rotatedmarker';

// Icons
import planeIcon from "../../assets/plane-up-solid-full.svg"


const Home = () => {

    // Informacoes sobre o voo e seus estados
    const [states, setStates] = useState(null);

    // Selecionar um determinado aviao
    const [plan, setPlan] = useState(null);

    // Capturar errors
    const [info, setInfo] = useState(null);
    
    // Coordenadas do Brasil
    const coordinates = [-15, -60];

    const { request } = useAxios();

    useEffect(() => {
        const fetchData = async () => {
            const response = await request("API", "/api/simulation/v1/status");
            if(response.error) {
                setInfo(response.error);
                return;
            }
            setStates(response.data);
            setInfo(null);
        }

        if(!states) fetchData();
        
        const intervalId = setInterval(fetchData, 2000);

        // Limpa o intervalo e evita o memory leaks
        return () => clearInterval(intervalId);

    }, []);

    // Mantem as informacoes de plan atualizadas
    useEffect(() => {
        if(plan) {
            const fetchData = async () => {
                const response = await request("API", `/api/flights/v1/${plan.id}`);
                if(response.error) {
                    setInfo(response.error);
                    return;
                }
                setPlan(response.data);
            }
            fetchData();
        }
    }, [states])

    // Fazendo o icone personalizado
    const customIcon = new Leaflet.Icon({
        iconUrl: planeIcon,
        iconSize: [24, 24],
        iconAnchor: [12, 12],
        popupAnchor: [0, -16],
        className: "plane-icon"
    });

    // Funcao para fechar a tela de detalhes do aviao
    const closePlaneDetails = (boolean) => {
        if(boolean) setPlan(null);
    }

    // Formata a latitude e longitude para o formato do OpenStreetMap
    const formatCoordinatesForPolyline = (trail) => {
        if (!trail || trail.length === 0) return [];
        // Transforma [{lat, lon}, {lat, lon}] em [[lat, lon], [lat, lon]]
        return trail.map(coord => [coord.latitude, coord.longitude]);
    };

    // Busca um plano de voo de acordo com um id
    const getFlightPlan = async (id) => {
        const response = await request("API", `/api/flights/v1/${id}`);
        if(response.info) {
            setInfo(response.error);
            return;
        }
        setPlan(response.data);
    }

  return (
    <div className="home-container">
        {info && (
            <div className="map-error">
                <Message type={info.type} message={info.message} />
            </div>
        )}
        <MapContainer className="map-container" center={coordinates}  zoom={5} scrollWheelZoom={false} minZoom={2} maxZoom={10}>
            {/* Define o servidor de tiles, nesse caso o Open Street Map */}
            <TileLayer
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            />

            {states && states.length > 0 && states.map((state, index) => (
                <div key={index}>

                    {/* Criar um marcador */}
                    <Marker 
                        key={`${state.flightNumber}-${state.currentPosition.latitude}-${state.currentPosition.longitude}-${state.currentPosition.direction}`} 
                        position={[state.currentPosition.latitude, state.currentPosition.longitude]} 
                        icon={customIcon}
                        rotationOrigin="center"
                        rotationAngle={state.currentPosition.direction}
                        eventHandlers={
                            { 
                                click: () => getFlightPlan(state.flightPlanId),
                                mouseover: (e) => e.target.openPopup(),
                                mouseout: (e) => e.target.closePopup()
                            }
                        }
                    >
                        {/* Criar o popup com as informações dos avioes */}
                        <Popup closeButton={false}>
                            <div className="popup-informations">
                                {state.flightNumber}
                            </div>
                        </Popup>
                    </Marker>

                    {/* Trajeto  */}
                    {plan && plan.route.waypoints.length > 0 && (
                        <Polyline
                            positions={formatCoordinatesForPolyline(plan.route.waypoints)}
                            color="gray"
                            weight={1}
                            dashArray="5, 10"
                            opacity={1}
                        />
                    )}

                    {/* Trail  */}
                    {plan && plan.route.waypoints.length > 0 && plan.id == state.flightPlanId && (
                        <Polyline
                            positions={formatCoordinatesForPolyline(state.trail)}
                            color="red"
                            weight={2}
                            opacity={1}
                        />
                    )}

                </div>

            ))}
        </MapContainer>
        {plan && (
            <PlaneInformations plan={plan} closePlanDetails={(boolean) => closePlaneDetails(boolean)} />
        )}
    </div>
  )
}

export default Home;