// CSS
import "./UserManagement.css";

// Components
import EditUser from "../../components/EditUser";
import DeleteUser from "../../components/DeleteUser";

// Hooks
import { useEffect, useState } from "react";

// React Router
import { useNavigate } from "react-router-dom";

// Backend
import backend from "../../services/backend";

// Context
import { useAuthValue } from "../../context/AuthContext";

const UserManagement = () => {

    const [selectedUser, setSelectedUser] = useState(null);
    const [systemUsers, setSystemUsers] = useState([]);
    const [action, setAction] = useState(null);

    const { user: admin, loading } = useAuthValue();
    const navigate = useNavigate();

    // Verifica se existe um usuário logado e se seu acesso é permitido
    useEffect(() => {
        if(!admin && !loading) navigate("/");
        if(admin !== null && !admin.roles.includes("ADMIN")) {
            window.alert("Acesso não autorizado!");
            navigate("/");
        }
    }, [admin, loading]);

    // Carrega os usuarios
    useEffect(() => {
        const fetchData = async() => {
            const response = await backend.get("/api/admin/v1", { headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            } });
            setSystemUsers(response.data);
        }
        fetchData();
    }, [admin, loading]);

    const getUserById = (id) => {
        const selectedUser = systemUsers.find((selectedUser) => selectedUser.id === id);
        setSelectedUser(selectedUser);
    }

    const editPersonById = (id) => {
        getUserById(id);
        setAction("EDIT");
    }
    
    const deleteUserById = (id) => {
        getUserById(id);
        setAction("DELETE");
    }

    const closeScreen = async() => {
        const response = await backend.get("/api/admin/v1", { headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        } });
        setSystemUsers(response.data);
        setAction(null);
        setSelectedUser(null);
    }

    // Estado de Carregamento
    if(loading) return <div>Carregando...</div>;

    return (
        <main className="user-management-container">
            <h1>Usuários Cadastrados</h1>
            <table>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Função</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    {systemUsers && systemUsers.length > 0 && systemUsers.map((selectedUser) => (
                        <tr key={selectedUser.person.id}>
                            <td>{selectedUser.person.name}</td>
                            <td>{selectedUser.person.email}</td>
                            <td>
                                {selectedUser.roles.includes("ADMIN") ? "Administrador" : "Usuário Comum"}
                            </td>
                            <td>
                                {admin && admin.username === selectedUser.username && (
                                    <>
                                        <button className="edit-button disabled"  disabled>Editar</button>
                                        <button className="delete-button disabled" disabled>Excluir</button>
                                    </>
                                )}
                                {admin && admin.username !== selectedUser.username && (
                                    <>
                                        <button className="edit-button" onClick={() => editPersonById(selectedUser.person.id)}>Editar</button>
                                        <button className="delete-button" onClick={() => deleteUserById(selectedUser.person.id)}>Excluir</button>
                                    </>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {selectedUser && action === "EDIT" && (
                <EditUser user={selectedUser} close={closeScreen} />
            )}
            {selectedUser && action === "DELETE" && (
                <DeleteUser user={selectedUser} close={closeScreen} />
            )}
        </main>
    );
}

export default UserManagement;