// CSS
import "./Login.css";

// Hooks
import { useEffect, useState } from "react";

// Hooks
import { useNavigate } from "react-router-dom";

// Backend
import backend from "../../services/backend";

const Login = () => {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  const navigate = useNavigate();

  // Verifica se existe um usuário logado
  useEffect(() => {
    console.log("useEffect")
    const username = localStorage.getItem("username");
    const token = localStorage.getItem("token");

    if(username !== null && token !== null) {
      navigate("/");
    }
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = {
      username: email,
      password
    }

    try {
      const response = await backend.post("/auth/signin", data);
      localStorage.setItem("username", response.data.username);
      localStorage.setItem("token", response.data.token);

    } catch(error) {
      setError(error.message);
    }

    navigate("/");

  }

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
            <span>Não tem uma conta?<a href="#">Registre-se</a></span>
        </div>
        <input type="submit" value="Login" className="btn" />
      </form>
    </main>
  )
}

export default Login;