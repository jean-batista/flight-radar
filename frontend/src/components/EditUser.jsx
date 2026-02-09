// CSS
import "./EditUser.css";

// Hooks
import { useState } from 'react'

// Icons
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faX } from "@fortawesome/free-solid-svg-icons";

// Utils
import Message from "./Message";

const EditUser = ({ user, updateUser, info, loading, close }) => {

    const [name, setName] = useState(user.person.name);
    const [email, setEmail] = useState(user.username);

    const handleSubmit = async(e) => {
      e.preventDefault();

      updateUser({ name, email });

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
            {info && <Message type={info.type} message={info.message} />}
            <input type="submit" className="btn" value={loading ? "Atualizando..." : "Salvar alterações"} />
        </form>
    </main>
  )
}

export default EditUser;