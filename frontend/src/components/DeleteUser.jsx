// CSS
import "./DeleteUser.css";

// Hooks
import { useState } from "react";

// Font Awesome
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faX } from "@fortawesome/free-solid-svg-icons";
import backend from "../services/backend";

const DeleteUser = ({ user, close }) => {

  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [confirm, setConfirm] = useState("");
  const [info, setInfo] = useState(null);

  const handleSubmit = async(e) => {
    e.preventDefault();

    if(confirm !== "Confirmar") {
      setInfo({ type: "ERROR", message: "Confirme a exclusão antes de continuar" });
      setTimeout(() => { setInfo(null); }, 2000);
      return;
    }

    try {
      await backend.delete("/api/admin/v1", {
        data: {
          userId: user.id,
          password
        },
        headers: { 
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      });
      setInfo({ type: "SUCCESS", message: "Usuário excluido com sucesso!" });
      setTimeout(() => { 
        setInfo(null);
        close();
      }, 2000);
    } catch(error) {
      setInfo({ type: "ERROR", message: "Não foi possível excluir o usuário!" });
      setTimeout(() => { setInfo(null); }, 2000);
    }

  }

  return (
    <main className="delete-user-container">
        <button onClick={close}>
          <FontAwesomeIcon icon={faX} />
        </button>
        <h2>Excluir Usuário</h2>
        <p className="hint">Danger zone</p>
        <form onSubmit={handleSubmit}>
            <label htmlFor="name">Senha</label>
            <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)}  />
            <label htmlFor="name">Confirmar senha</label>
            <input type="password" name="confirm-password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)}  />
            <label htmlFor="name">Confirmação</label>
            <input type="text" name="confirm" placeholder="Escreva 'Confirmar'" value={confirm} onChange={(e) => setConfirm(e.target.value)}  />
            {info && info.type === "SUCCESS" && <p className="form-success">{info.message}</p>}
            {info && info.type === "ERROR" && <p className="form-error">{info.message}</p>}
            <input type="submit" className="btn" value="Excluir usuário" />
        </form>
    </main>
  )
}

export default DeleteUser;