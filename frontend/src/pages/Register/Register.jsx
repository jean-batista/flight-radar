// CSS
import "./Register.css";

// Hooks
import { useEffect, useState } from "react";

// React Router
import { NavLink, useNavigate } from "react-router-dom";

// Backend
import backend from "../../services/backend";

// Context
import { useAuthValue } from "../../context/AuthContext";

const Register = () => {

  const [name, setName] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [info, setInfo] = useState(null);

  const { user, loading } = useAuthValue();
  const navigate = useNavigate();

  // Verifica se existe um usuário logado
  useEffect(() => {
    if(user && !loading) navigate("/");
  }, [user, loading]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if(password !== confirmPassword) {
      setInfo({ type: "ERROR", message: "As senhas precisam ser iguais!" });
      return;
    }

    try {
      await backend.post("/auth/register", { name, birthDate, email, password });
      setInfo({ type: "SUCCESS", message: "Usuario cadastrado com sucesso!" });
      setTimeout(() => { navigate("/login") }, 2000);
    } catch(error) {
      setInfo({ type: "ERROR", message: "Algo deu errado, tente novamente mais tarde!" });
    }
  }

  // Estado de Carregamento
  if(loading) return <div>Carregando...</div>;

  return (
    <main className="register-container">
      <h1>Crie sua conta</h1>
      <p className="hint">Preencha seus dados para se cadastrar.</p>
      <form onSubmit={handleSubmit}>
        <div className="field">
          <label htmlFor="name">Nome completo</label>
          <input type="text" name="name" required value={name} onChange={(e) => setName(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="birthDate">Data de nascimento</label>
          <input type="date" name="birthDate" required value={birthDate} onChange={(e) => setBirthDate(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="email">Email</label>
          <input type="email" name="email" required value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="password">Senha</label>
          <input type="password" name="password" required value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="confirm-password">Confirmar senha</label>
          <input type="password" name="confirm-password" required value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
        </div>
        {info && info.type === "SUCCESS" && <p className="form-success">{info.message}</p>}
        {info && info.type === "ERROR" && <p className="form-error">{info.message}</p>}
        <input type="submit" className="btn" value="Cadastrar" />
        <span>Já tem conta? <NavLink to="/login">Faça Login</NavLink></span>
      </form>
    </main>
  )
}

export default Register;