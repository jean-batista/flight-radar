// CSS
import "./EditUser.css";

// Hooks
import { useState } from 'react'

// Icons
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faX } from "@fortawesome/free-solid-svg-icons";

// Backend
import backend from "../services/backend";

const EditUser = ({ user, close }) => {

    const [name, setName] = useState(user.person.name);
    const [email, setEmail] = useState(user.username);
    const [info, setInfo] = useState(null);

    const handleSubmit = async(e) => {
      e.preventDefault();

      const profile = {
        userId: user.id,
        name,
        email
      }
      
      const headers = { 
        headers: { 
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      }

      try {
        await backend.put("/api/admin/v1", profile, headers);
        setInfo({ type: "SUCCESS", message: "Dados atualizados com sucesso!" });
        setTimeout(() => { 
          setInfo(null);
          close();
        }, 1000);
      } catch(error) {
        setInfo({ type: "ERROR", message: "Não foi possível atualizar os dados, tente novamente mais tarde!" });
        setTimeout(() => { setInfo(null) }, 3000);
      }

    }

  return (
    <main className="edit-user-container">
        <button onClick={close}>
          <FontAwesomeIcon icon={faX} />
        </button>
        <h2>Editar Usuário</h2>
        <form onSubmit={handleSubmit}>
            <label htmlFor="name">Nome do usuário</label>
            <input type="text" name="name" value={name} onChange={(e) => setName(e.target.value)}  />
            <label htmlFor="name">Email do usuário</label>
            <input type="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)}  />
            {info && info.type === "SUCCESS" && <p className="form-success">{info.message}</p>}
            {info && info.type === "ERROR" && <p className="form-error">{info.message}</p>}
            <input type="submit" className="btn" value="Salvar alterações" />
        </form>
    </main>
  )
}

export default EditUser;