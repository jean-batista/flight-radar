// CSS
import "./DeleteUser.css";

// Components
import Message from "../components/Message";

// Hooks
import { useState } from "react";

// Font Awesome
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faX } from "@fortawesome/free-solid-svg-icons";

const DeleteUser = ({ userId, deleteUser, info, loading, close }) => {

  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [confirm, setConfirm] = useState("");

  const handleSubmit = async(e) => {
    e.preventDefault();

    const data = {
      userId,
      admin: { password, confirmPassword, confirm }
    }

    deleteUser(data);

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
            {info && <Message type={info.type} message={info.message} />}
            <input type="submit" className="btn" value={loading ? "Excluindo..." : "Excluir usuário"} />
        </form>
    </main>
  )
}

export default DeleteUser;