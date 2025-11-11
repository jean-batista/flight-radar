// CSS
import "./Register.css";

// Hooks
import { useEffect, useState } from "react";

// React Router
import { NavLink, useNavigate } from "react-router-dom";

// Backend
import backend from "../../services/backend";

const Register = () => {

  const [fullName, setFullName] = useState("");
  const [birth, setBirth] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [info, setInfo] = useState(null);

  const navigate = useNavigate();

  // Verifica se existe um usuário logado
  useEffect(() => {
    const username = localStorage.getItem("username");
    const token = localStorage.getItem("token");
  
    if(username !== null && token !== null) {
      navigate("/");
    }
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if(password !== confirmPassword) {
      setInfo({ type: "ERROR", message: "As senhas precisam ser iguais!" });
      return;
    }

    const data = {
      name: fullName,
      birthDate: birth,
      email,
      password,
    }

    try {
      await backend.post("/auth/register", data);
      setInfo({ type: "SUCCESS", message: "Usuario cadastrado com sucesso!" });
      setTimeout(() => { navigate("/login") }, 3000);
    } catch(error) {
      setInfo({ type: "ERROR", message: "Algo deu errado, tente novamente mais tarde!" });
    }
  }

  return (
    <main className="register-container">
      <h1>Crie sua conta</h1>
      <p className="hint">Preencha seus dados para se cadastrar.</p>
      <form onSubmit={handleSubmit}>
        <div className="field">
          <label htmlFor="name">Nome completo</label>
          <input type="text" name="name" required value={fullName} onChange={(e) => setFullName(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="birth">Data de nascimento</label>
          <input type="date" name="birth" required value={birth} onChange={(e) => setBirth(e.target.value)} />
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