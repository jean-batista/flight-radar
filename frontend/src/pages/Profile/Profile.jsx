// CSS
import "./Profile.css";

// Hooks
import { useEffect, useState } from "react";

// React Router
import backend from "../../services/backend";

// Backend
import { useNavigate } from "react-router-dom";

// Context
import { useAuthValue } from "../../context/AuthContext";

const Profile = () => {

  const [name, setName] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [email, setEmail] = useState("");
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmNewPassword, setConfirmNewPassword] = useState("");

  const [info, setInfo] = useState(null);

  const { user, loading } = useAuthValue();
  const navigate = useNavigate();

  // Verifica se existe um usuario logado
  useEffect(() => {
    if(!user && !loading) navigate("/login");
  }, [user]);

  // Carrega os dados do usuario
  useEffect(() => {

    const fetchData = async () => {
      if(user === null) return;
      try {
        const response = await backend.get("/api/users/v1", { headers: {
          Authorization: `Bearer ${user.token}`
        } });
        setName(response.data.name);
        setBirthDate(response.data.birthDate);
        setEmail(response.data.email);
      } catch(error) {
        setInfo({ type: "ERROR", message: "Erro ao se comunicar com o servidor" });
        setTimeout(() => { setInfo(null) }, 3000);
      }
    }
    fetchData();
  }, [user]);

  // Envia a requisicao de atualizacao de dados
  const handleSubmit = async(e) => {
    e.preventDefault();

    if(newPassword !== confirmNewPassword) {
      setInfo({ type: "ERROR", message: "As senhas precisam ser iguais!" });
      setTimeout(() => { setInfo(null) }, 3000);
      return;
    }

    const data = {
      name,
      birthDate,
      email,
      currentPassword: currentPassword.length === 0 ? null : currentPassword,
      newPassword: newPassword.length === 0 ? null : newPassword
    }

    try {
      const response = await backend.put("/api/users/v1", data, { headers: {
        Authorization: `Bearer ${user.token}`
      } });
      localStorage.setItem("token", response.data.token);
      setCurrentPassword("");
      setNewPassword("");
      setConfirmNewPassword("");
      setInfo({ type: "SUCCESS", message: "Dados atualizados com sucesso!" });
      setTimeout(() => { setInfo(null) }, 3000);
    } catch(error) {
      setInfo({ type: "ERROR", message: "Não foi possível atualizar os dados, tente novamente mais tarde!" });
      setTimeout(() => { setInfo(null) }, 3000);
    }
    
  }

  // Estado de Carregamento
  if(loading) return <div>Carregando...</div>;

  return (
    <main className="user-configs-container">
      <h1>Perfil</h1>
      <p className="hint">Atualize seus dados</p>
      <form onSubmit={handleSubmit}>
        <div className="box">
          <h2>Meus dados</h2>
          <div>
            <input type="text" name="name" placeholder="Nome" required value={name} onChange={(e) => setName(e.target.value)} />
            <input type="date" name="birthDate" placeholder="Data de nascimento" required value={birthDate} onChange={(e) => setBirthDate(e.target.value)} />
            <input type="email" name="email" placeholder="Email" required value={email} onChange={(e) => setEmail(e.target.value)} />
          </div>
        </div>
        <div className="box">
          <h2>Alterar senha</h2>
          <div>
            <input type="password" minLength="4" name="current-password" placeholder="Senha atual" value={currentPassword} onChange={(e) => setCurrentPassword(e.target.value)} />
            <input type="password" minLength="4" name="new-password" placeholder="Nova senha" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} />
            <input type="password" minLength="4" name="confirm-new-password" placeholder="Confirmar nova senha" value={confirmNewPassword} onChange={(e) => setConfirmNewPassword(e.target.value)} />
          </div>
        </div>
        {info && info.type === "SUCCESS" && <p className="form-success">{info.message}</p>}
        {info && info.type === "ERROR" && <p className="form-error">{info.message}</p>}
        <div className="actions">
          <input type="submit" className="btn" value="Salvar alterações" />
        </div>
      </form>
    </main>
  )
}

export default Profile;