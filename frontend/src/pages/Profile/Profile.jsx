// CSS
import "./Profile.css";

// Components
import Message from "../../components/Message";

// Hooks
import { useEffect, useState } from "react";
import { useAxios } from "../../hooks/useAxios";
import { useUpdateUser } from "../../hooks/useUpdateUser";

// React Router
import { useNavigate } from "react-router-dom";

// Context
import { useAuthValue } from "../../context/AuthContext";

const Profile = () => {

  const [info, setInfo] = useState(null);
  const { user, loading: authLoading } = useAuthValue();
  
  const [name, setName] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [email, setEmail] = useState("");
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmNewPassword, setConfirmNewPassword] = useState("");

  const { request } = useAxios(user?.token);
  const { update, info: updateInfo, updateLoading } = useUpdateUser();
  const navigate = useNavigate();

  // Verifica se existe um usuario logado
  useEffect(() => {
    if(!user && !authLoading) navigate("/login");
  }, [user]);

  // Carrega os dados do usuario
  useEffect(() => {
    if(!user) return;
    const fetchData = async () => {
      const response = await request("BACKEND", "/api/users/v1");
      if(response.error) {
        setInfo(response.error);
        return;
      }
      setName(response.data.name);
      setBirthDate(response.data.birthDate);
      setEmail(response.data.email);
      setName(response.data.name);
      setBirthDate(response.data.birthDate);
      setEmail(response.data.email);
    }
    fetchData();
  }, [user]);

  // Envia a requisicao de atualizacao de dados
  const handleSubmit = async(e) => {
    e.preventDefault();

    const data = {
      name,
      birthDate,
      email,
      currentPassword,
      newPassword,
      confirmNewPassword
    }

    await update(data);

    setCurrentPassword("");
    setNewPassword("");
    setConfirmNewPassword("");
  }

  // Estado de Carregamento
  if(authLoading) return <div>Carregando...</div>;

  return (
    <main className="user-configs-container">
      <h1>Perfil</h1>
      <p className="hint">Atualize seus dados</p>
      <form onSubmit={handleSubmit}>
        <div className="box">
          <h2>Meus dados</h2>
          <div>
            <input
              type="text"
              name="name"
              placeholder="Nome"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
            <input
              type="date"
              name="birthDate"
              placeholder="Data de nascimento"
              value={birthDate}
              onChange={(e) => setBirthDate(e.target.value)}
            />
            <input
              type="email"
              name="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
        </div>
        <div className="box">
          <h2>Alterar senha</h2>
          <div>
            <input
              type="password"
              name="current-password"
              placeholder="Senha atual"
              value={currentPassword}
              onChange={(e) => setCurrentPassword(e.target.value)}
            />
            <input
              type="password"
              name="new-password"
              placeholder="Nova senha"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
            />
            <input
              type="password"
              name="confirm-new-password"
              placeholder="Confirmar nova senha"
              value={confirmNewPassword}
              onChange={(e) => setConfirmNewPassword(e.target.value)}
            />
          </div>
        </div>
        {info && <Message type={info.type} message={info.message} />}
        {updateInfo && <Message type={updateInfo.type} message={updateInfo.message} />}
        <div className="actions">
          <input
            type="submit"
            className="btn"
            value={!updateLoading ? "Salvar alterações" : "Atualizando..."}
          />
        </div>
      </form>
    </main>
  )
}

export default Profile;