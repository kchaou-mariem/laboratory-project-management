import { Routes, Route } from 'react-router-dom';
import Home from './pages/home';
import SignIn from './pages/signIn';
import ProjectDetails from './pages/projet';
import Formule from './pages/formules';
import DetailsFormule from './pages/formule';
import FormulesProjet from './pages/formules_projet';
import CreateProject from './pages/creationProjet';
import CreateFormule from './pages/creationFormule';
import CompteDetails from './pages/compte';
import SignUp from './pages/signUp';
import './App.css'
import ListeIngredients from './pages/ingredients';
import Comptes from './pages/comptes';
import AppLayout from './pages/AppLayout';
import ProtectedRoute from './components/ProtectedRoute'; 

function App() {

  return (
    <Routes>
    <Route path="/" element={<AppLayout />} >
    <Route index element={<Home />} />
    <Route path="projets/:id" element={<ProjectDetails />} />
    <Route path="formules" element={<Formule />} />
    <Route path="formules/:id" element={<DetailsFormule />} />
    <Route path="projets/:id/formules" element={<FormulesProjet />} />
    <Route path="projets/create" element={<ProtectedRoute allowedRoles={['ADMIN', 'RESPONSABLE_TECHNIQUE']}><CreateProject /></ProtectedRoute>} />

    <Route path="projets/:id/createFormule" element={<CreateFormule />} />
    
    <Route path="ingredients" element={<ProtectedRoute allowedRoles={['ADMIN', 'RESPONSABLE_TECHNIQUE','CHEF_PROJET']}><ListeIngredients /></ProtectedRoute>} />

    <Route path="comptes/:id" element={<ProtectedRoute allowedRoles={['ADMIN', 'RESPONSABLE_TECHNIQUE']}><CompteDetails /></ProtectedRoute>} />

    <Route path="signUp" element={<ProtectedRoute allowedRoles={['ADMIN', 'RESPONSABLE_TECHNIQUE']}><SignUp /></ProtectedRoute>} />

    <Route path="comptes" element={<ProtectedRoute allowedRoles={['ADMIN', 'RESPONSABLE_TECHNIQUE']}><Comptes /></ProtectedRoute>} />
    <Route path="formules/:id/edit" element={<DetailsFormule />} />

    </Route>
    <Route path="/signIn" element={<SignIn />} />


  </Routes>
  )
}

export default App
