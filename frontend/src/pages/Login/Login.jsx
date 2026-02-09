// CSS
import "./Login.css";

// Components
import Message from "../../components/Message";

// Hooks
import { useEffect, useState } from "react";
import { useLogin } from "../../hooks/useLogin";

// React Router
import { NavLink, useNavigate } from "react-router-dom";

// Context
import { useAuthValue } from "../../context/AuthContext";

const Login = () => {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { user, loading: authLoading } = useAuthValue();
  const { login, info, loading } = useLogin();
  const navigate = useNavigate();


  // Verifica se existe um usuário logado
  useEffect(() => {
    if(user && !authLoading) {
      navigate("/");
    }
  }, [user, authLoading]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    await login({ email, password });

  }

  // Estado de Carregamento
  if(authLoading && !user) return <div>Carregando...</div>;

  return (
    <main className="login-container">
      <h1>Bem-vindo ao MSN</h1>
      <p className="hint">Acesse sua conta com e-mail e senha.</p>
      <form onSubmit={handleSubmit}>
        <div className="field">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="field">
          <label htmlFor="password">Senha</label>
          <input
            type="password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {info && <Message type={info.type} message={info.message} />}
        <div className="actions">
            <span>Não tem uma conta? <NavLink to="/cadastro">Registre-se</NavLink></span>
        </div>
        <input type="submit" value={!loading ? "Login" : "Carregando..."} className="btn" />
      </form>
    </main>
  )
}

export default Login;