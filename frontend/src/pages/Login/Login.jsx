// CSS
import "./Login.css";

// Hooks
import { useEffect, useState } from "react";

// React Router
import { NavLink, useNavigate } from "react-router-dom";

// Backend
import backend from "../../services/backend";

// Context
import { useAuthValue } from "../../context/AuthContext";

const Login = () => {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  const { user, setUser, loading } = useAuthValue();
  const navigate = useNavigate();

  // Verifica se existe um usuário logado
  useEffect(() => {
    if(user && !loading) {
      navigate("/");
    }
  }, [user, loading]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await backend.post("/auth/signin", { username: email, password });
      setUser({
        username: response.data.username,
        token: response.data.token,
        roles: response.data.roles
      });
      navigate("/");
    } catch(error) {
      setError(error.message);
    }

  }

  // Estado de Carregamento
  if(loading) return <div>Carregando...</div>;

  return (
    <main className="login-container">
      <h1>Bem-vindo ao MSN</h1>
      <p className="hint">Acesse sua conta com e-mail e senha.</p>
      <form onSubmit={handleSubmit}>
        <div className="field">
          <label htmlFor="email">Email</label>
          <input type="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="password">Senha</label>
          <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        {error && <p className="form-error">{error}</p>}
        <div className="actions">
            {/* <span>Não tem uma conta?<a href="#">Registre-se</a></span> */}
            <span>Não tem uma conta? <NavLink to="/cadastro">Registre-se</NavLink></span>
        </div>
        <input type="submit" value="Login" className="btn" />
      </form>
    </main>
  )
}

export default Login;