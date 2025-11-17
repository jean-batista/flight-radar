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

const UserManagement = () => {

    const [user, setUser] = useState(null);
    const [users, setUsers] = useState([]);
    const [action, setAction] = useState(null);

    const navigate = useNavigate();

    // Verifica se existe um usuário logado e se seu acesso é permitido
    useEffect(() => {
        const username = localStorage.getItem("username");
        const token = localStorage.getItem("token");
        const roles = localStorage.getItem("roles");

        if(username === null && token === null && roles === null && roles.length === 0) {
            navigate("/");
        }

        if(!roles.includes("ADMIN")) {
            window.alert("Rota não autorizada!");
            navigate("/");
        }
    }, []);

    useEffect(() => {
        const fetchData = async() => {
            const response = await backend.get("/api/admin/v1", { headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            } });
            setUsers(response.data);
        }
        fetchData();
    }, []);

    const getUserById = (id) => {
        const user = users.find((user) => user.id === id);
        setUser(user);
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
        setUsers(response.data);
        setAction(null);
        setUser(null);
    }

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
                    {users && users.length > 0 && users.map((user) => (
                        <tr key={user.person.id}>
                            <td>{user.person.name}</td>
                            <td>{user.person.email}</td>
                            <td>
                                {user.roles.includes("ADMIN") ? "Administrador" : "Usuário Comum"}
                            </td>
                            <td>
                                {localStorage.getItem("username") === user.username && (
                                    <>
                                        <button className="edit-button disabled"  disabled>Editar</button>
                                        <button className="delete-button disabled" disabled>Excluir</button>
                                    </>
                                )}
                                {localStorage.getItem("username") !== user.username && (
                                    <>
                                        <button className="edit-button" onClick={() => editPersonById(user.person.id)}>Editar</button>
                                        <button className="delete-button" onClick={() => deleteUserById(user.person.id)}>Excluir</button>
                                    </>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {user && action === "EDIT" && (
                <EditUser user={user} close={closeScreen} />
            )}
            {user && action === "DELETE" && (
                <DeleteUser user={user} close={closeScreen} />
            )}
        </main>
    );
}

export default UserManagement;