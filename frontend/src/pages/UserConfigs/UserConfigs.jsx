// CSS
import { useSVGOverlay } from "react-leaflet/SVGOverlay";
import "./UserConfigs.css";
import { useState } from "react";

const UserConfigs = () => {

  const [name, setName] = useState("");
  const [birth, setBirth] = useState("");
  const [email, setEmail] = useState("");
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");

  const [info, setInfo] = useState(null);

  const handleSubmit = async(e) => {
    e.preventDefault();

    if(name === "") {
      setInfo({ type: "ERROR", message: "O campo nome não pode estar vazio" });
      return;
    }

    if(birth === "") {
      setInfo({ type: "ERROR", message: "O campo aniversario não pode estar vazio" });
      return;
    }

    if(email === "") {
      setInfo({ type: "ERROR", message: "O campo email não pode estar vazio" });
      return;
    }

    if(currentPassword === "") {
      setInfo({ type: "ERROR", message: "O campo senha atual não pode estar vazio" });
      return;
    }

    if(newPassword === "") {
      setInfo({ type: "ERROR", message: "O campo nova senha não pode estar vazio" });
      return;
    }

    setInfo({ type: "SUCCESS", message: "Dados alterados com sucesso!" })
    console.log("Submit");
  }

  return (
    <main className="user-configs-container">
      <h1>Configurações</h1>
      <p className="hint">Atualize seus dados</p>
      <form onSubmit={handleSubmit}>
        <div className="box">
          <h2>Meus dados</h2>
          <div>
            <input type="text" name="name" placeholder="Nome" value={name} onChange={(e) => setName(e.target.value)} />
            <input type="date" name="birth" placeholder="Data de nascimento" value={birth} onChange={(e) => setBirth(e.target.value)} />
            <input type="email" name="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
          </div>
        </div>
        <div className="box">
          <h2>Alterar senha</h2>
          <div>
            <input type="password" name="current-password" placeholder="Senha atual" value={currentPassword} onChange={(e) => setCurrentPassword(e.target.value)} />
            <input type="password" name="new-password" placeholder="Nova senha" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} />
          </div>
        </div>
        {info && info.type === "SUCCESS" && <p className="form-success">{info.message}</p>}
        {info && info.type === "ERROR" && <p className="form-error">{info.message}</p>}
        <div className="actions">
          <input type="submit" className="btn" value="Salvar alterações" />
          <input type="submit" className="btn" value="Excluir conta" />
        </div>
      </form>
    </main>
  )
}

export default UserConfigs;