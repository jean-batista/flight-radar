// CSS
import "./Login.css";

// Hooks
import { useState } from "react";

const Login = () => {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();

    if(email === "") {
      setError("Preencha o campo de email!");
      return;
    }


    if(password === "") {
      setError("A senha não pode estar vazia!");
      return;
    }

    if(email !== "test@test.com") {
      setError("Email incorreto!");
      return;
    }

    if(password !== "admin") {
      setError("Senha incorreta!");
      return;
    }

    setError(null);

    window.alert("Login realizado com sucesso!");
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