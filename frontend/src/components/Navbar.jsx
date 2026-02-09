// CSS
import "./Navbar.css";

// Context
import { useLogout } from "../hooks/useLogout";

// React Router
import { NavLink } from "react-router-dom";

// Icons
import { faGear, faUser } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import logoImg from "../assets/logo-image.png";

// Context
import { useAuthValue } from "../context/AuthContext";

const Navbar = () => {

  const { user } = useAuthValue();
  const { logout } = useLogout();

  return (
    <nav className="navbar-container">
        <NavLink to="/" className={({ isActive }) => ("")}>
        <img id="logo" src={logoImg} alt="logo" />
        </NavLink>
        <ul>
            <li>
              <NavLink to="/" className={({ isActive }) => (isActive ? "active" : "")}>Home</NavLink>
            </li>
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
              <NavLink to="/buscar" className={({ isActive }) => (isActive ? "active" : "")}>Buscar plano de voo</NavLink>
            </li>
            {user && (
              <li>
                <NavLink to="/perfil" className={({ isActive }) => (isActive ? "active" : "")}>
                  <FontAwesomeIcon icon={faUser} />
                </NavLink>
              </li>
            )}
            {/* Deve possuir role como admin */}
            {
              user && user.roles.includes("ADMIN") && (
                <li>
                  <NavLink to="/admin/usuarios" className={({ isActive }) => (isActive ? "active" : "")}>
                    <FontAwesomeIcon icon={faGear} />
                  </NavLink>
                </li>
              )
            }
            {user && (
              <li>
                <button className="logout-btn" onClick={logout}>Sair</button>
              </li>
            )}
        </ul>
    </nav>
  )
}

export default Navbar;