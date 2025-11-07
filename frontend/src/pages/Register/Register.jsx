// CSS
import { NavLink } from "react-router-dom";
import "./Register.css";

// Hooks
import { useState } from "react";

const Register = () => {

  const [fullName, setFullName] = useState("");
  const [birth, setBirth] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [error, setError] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();

    if(fullName === "") {
      setError("Preencha o campo de nome!");
      return;
    }

    if(birth === "") {
      setError("Preencha o campo de data de nascimento!");
      return;
    }

    if(email === "") {
      setError("Preencha o campo de data de email!");
      return;
    }

    if(password === "") {
      setError("Preencha o campo de data de senha!");
      return;
    }

    if(confirmPassword === "") {
      setError("Preencha o campo de data de confirmar a senha!");
      return;
    }

    if(password != confirmPassword) {
      setError("As senhas não coincidem!");
      return;
    }

    setError(null);

    window.alert("Cadastro realizado com sucesso!");
  }

  return (
    <main className="register-container">
      <h1>Crie sua conta</h1>
      <p className="hint">Preencha seus dados para se cadastrar.</p>
      <form onSubmit={handleSubmit}>
        <div className="field">
          <label htmlFor="name">Nome completo</label>
          <input type="text" name="name" value={fullName} onChange={(e) => setFullName(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="birth">Data de nascimento</label>
          <input type="date" name="birth" value={birth} onChange={(e) => setBirth(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="email">Email</label>
          <input type="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="password">Senha</label>
          <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="confirm-password">Confirmar senha</label>
          <input type="password" name="confirm-password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
        </div>
        {error && <p className="form-error">{error}</p>}
        <input type="submit" className="btn" value="Cadastrar" />
        <span>Já tem conta? <NavLink to="/login">Faça Login</NavLink></span>
      </form>
    </main>
  )
}

export default Register;