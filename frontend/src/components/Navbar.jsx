// CSS
import "./Navbar.css";

// React Router
import { NavLink } from "react-router-dom";

// Icons
import { faGear } from "@fortawesome/free-solid-svg-icons";
import logoImg from "../assets/logo-image.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

// Hooks
import { useEffect, useState } from "react";

const Navbar = () => {

  const [user, setUser] = useState(null);

  useEffect(() => {
    const username = localStorage.getItem("username");
    const token = localStorage.getItem("token");
  
    if(username !== null && token !== null) {
      setUser({ username, token });
    }
  }, []);

  return (
    <nav className="navbar-container">
        <NavLink to="/" className={({ isActive }) => ("")}>
        <img id="logo" src={logoImg} alt="logo" />
        </NavLink>
        <ul>
            {!user && (
              <li>
                <NavLink to="/login" className={({ isActive }) => (isActive ? "active" : "")}>Login</NavLink>
              </li>
            )}
            {!user && (
              <li>
                <NavLink to="/cadastro" className={({ isActive }) => (isActive ? "active" : "")}>Registre-se</NavLink>
              </li>
            )}
            <li>
              <NavLink to="/planos" className={({ isActive }) => (isActive ? "active" : "")}>Buscar plano de voo</NavLink>
            </li>
            {user && (
              <li>
                <NavLink to="/configs" className={({ isActive }) => (isActive ? "active" : "")}>
                  <FontAwesomeIcon icon={faGear} />
                </NavLink>
              </li>
            )}
        </ul>
    </nav>
  )
}

export default Navbar;