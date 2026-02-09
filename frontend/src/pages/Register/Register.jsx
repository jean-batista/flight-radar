// CSS
import "./Register.css";

// Components
import Message from "../../components/Message";

// Hooks
import { useEffect, useState } from "react";
import { useRegister } from "../../hooks/useRegister";

// React Router
import { NavLink, useNavigate } from "react-router-dom";

// Context
import { useAuthValue } from "../../context/AuthContext";

const Register = () => {

  const { user, loading: authLoading } = useAuthValue();

  const [name, setName] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  
  const { register, info, loading } = useRegister();
  const navigate = useNavigate();

  // Verifica se existe um usuário logado
  useEffect(() => {
    if(user && !authLoading) navigate("/");
  }, [user, authLoading]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    await register({ name, birthDate, email, password, confirmPassword });

  }

  // Estado de Carregamento
  if(authLoading) return <div>Carregando...</div>;

  return (
    <main className="register-container">
      <h1>Crie sua conta</h1>
      <p className="hint">Preencha seus dados para se cadastrar.</p>
      <form onSubmit={handleSubmit}>
        <div className="field">
          <label htmlFor="name">Nome completo</label>
          <input
            type="text"
            name="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div className="field">
          <label htmlFor="birthDate">Data de nascimento</label>
          <input
            type="date"
            name="birthDate"
            value={birthDate}
            onChange={(e) => setBirthDate(e.target.value)}
          />
        </div>
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
        <div className="field">
          <label htmlFor="confirm-password">Confirmar senha</label>
          <input
            type="password"
            name="confirm-password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
        </div>
        {info && <Message type={info.type} message={info.message} /> }
        <input
          type="submit"
          className="btn"
          value={!loading ? "Cadastrar" : "Carregando..."}
        />
        <span>
          Já tem conta?<NavLink to="/login">Faça Login</NavLink>
        </span>
      </form>
    </main>
  )
}

export default Register;