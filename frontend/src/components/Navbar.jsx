// CSS
import "./Navbar.css";

// React Router
import { NavLink } from "react-router-dom";

// Icons
// import gearSvg from "../assets/gear-solid-full.svg";
import { faGear } from "@fortawesome/free-solid-svg-icons";
import logoImg from "../../public/logo-image.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Navbar = () => {
  return (
    <nav className="navbar-container">
        <NavLink to="/" className={({ isActive }) => ("")}>
        <img id="logo" src={logoImg} alt="logo" />
        </NavLink>
        <ul>
            <li>
              <NavLink to="/login" className={({ isActive }) => (isActive ? "active" : "")}>Login</NavLink>
            </li>
            <li>
              <NavLink to="/cadastro" className={({ isActive }) => (isActive ? "active" : "")}>Registre-se</NavLink>
            </li>
            <li>
              <NavLink to="/planos" className={({ isActive }) => (isActive ? "active" : "")}>Buscar plano de voo</NavLink>
            </li>
            <li>
              <NavLink to="/configs" className={({ isActive }) => (isActive ? "active" : "")}>
                <FontAwesomeIcon icon={faGear} />
              </NavLink>
            </li>
        </ul>
    </nav>
  )
}

export default Navbar;