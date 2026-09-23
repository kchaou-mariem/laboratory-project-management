
import { Navigate } from "react-router-dom";


function ProtectedRoute({children,allowedRoles,projetId}){
    const role=localStorage.getItem('role');
    return allowedRoles.includes(role)? children:<Navigate to="/"/>
}
export default ProtectedRoute;