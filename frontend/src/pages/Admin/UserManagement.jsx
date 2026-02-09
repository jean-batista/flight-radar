// CSS
import "./UserManagement.css";

// Components
import EditUser from "../../components/EditUser";
import DeleteUser from "../../components/DeleteUser";
import Message from "../../components/Message";

// Hooks
import { useEffect, useState } from "react";
import { useUpdateUserByAdmin } from "../../hooks/useUpdateUserByAdmin";
import { useDeleteUser } from "../../hooks/useDeleteUser";

// React Router
import { useNavigate } from "react-router-dom";

// Context
import { useAuthValue } from "../../context/AuthContext";

// Axios
import { useAxios } from "../../hooks/useAxios";

// Utils
import { equals } from "../../utils/StringUtils";

const UserManagement = () => {

    const [info, setInfo] = useState(null);
    const [systemUsers, setSystemUsers] = useState([]);
    const [action, setAction] = useState(null);
    const { user: admin, loading: authLoading } = useAuthValue();
    const { request } = useAxios(admin?.token);
    const { update, info: updateInfo, loading: updateLoading } = useUpdateUserByAdmin();
    const { deleteUser, info: deleteInfo, loading: deleteLoading } = useDeleteUser();
    const navigate = useNavigate();

    // Verifica se existe um usuário logado e se seu acesso é permitido
    useEffect(() => {
        if(!admin && !authLoading) navigate("/");
        if(admin !== null && !admin.roles.includes("ADMIN")) {
            window.alert("Acesso não autorizado!");
            navigate("/");
        }
    }, [admin, authLoading]);

    // Carrega os usuarios
    useEffect(() => {
        if(!admin) return;
        const fetchData = async() => {
            const response = await request("BACKEND", "/api/admin/v1");
            if(response.error) {
                setInfo(response.error);
                return;
            }
            setSystemUsers(response.data);
        }
        fetchData();
    }, [admin, authLoading]);

    // Define a acao do admin (EDIT ou DELETE) e abre a tela correspondente
    const userAction = async (id, action) => {
        const response = await request("BACKEND", `/api/admin/v1/${id}`);
        if(response.error) {
            setInfo(response.error);
            return;
        }
        setAction({ type: action, user: response.data });
    }

    const updateUserAction = async ({ name, email }) => {
        update({ userId: action.user.id, name, email });
    }

    // admin: { password, confirmPassword, confirm }
    const deleteUserAction = async ({ userId, admin }) => {
        deleteUser({ userId, admin });
    }

    const closeScreen = async() => {
        const response = await request("BACKEND", "/api/admin/v1");
        if(response.error) {
            setInfo(response.error);
            return;
        }
        setSystemUsers(response.data);
        setAction(null);
    }

    // Estado de Carregamento
    if(authLoading) return <div>Carregando...</div>;

    return (
        <main className="user-management-container">
            {info && <Message type={info.type} message={info.message} />}
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
                    {systemUsers && systemUsers.length > 0 && systemUsers.map((user) => (
                        <tr key={user.id}>
                            <td>{user.person.name}</td>
                            <td>{user.person.email}</td>
                            <td>
                                {user.roles.includes("ADMIN") ? "Administrador" : "Usuário Comum"}
                            </td>
                            <td>
                                <button 
                                    className="edit-button" 
                                    disabled={admin && equals(admin.username, user.username) ? true : false}
                                    onClick={() => userAction(user.id, "EDIT")}
                                >Editar</button>
                                <button 
                                    className="delete-button"
                                    disabled={admin && equals(admin.username, user.username) ? true : false}
                                    onClick={() => userAction(user.id, "DELETE")}
                                >Excluir</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {action && action.type === "EDIT" && (
                <EditUser
                    user={action.user}
                    updateUser={updateUserAction}
                    info={updateInfo}
                    loading={updateLoading}
                    close={closeScreen}
                />
            )}
            {action && action.type === "DELETE" && (
                <DeleteUser
                    userId={action.user.id}
                    deleteUser={deleteUserAction}
                    info={deleteInfo}
                    loading={deleteLoading}
                    close={closeScreen}
                />
            )}
        </main>
    );
}

export default UserManagement;