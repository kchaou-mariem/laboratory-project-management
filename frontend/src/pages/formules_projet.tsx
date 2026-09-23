import  React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { Table, Row, Col, Button, Modal, Container } from 'react-bootstrap';
import {useNavigate} from 'react-router-dom';
import PillExample from '../components/statusColor';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronLeft, faChevronRight, faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';

interface Ingredient {
  id: number;
  nomCommercial: string;
  quantite: number;
  unite: string;
}

interface FormuleIngredient {
  id: number;
  quantite: number;
  ingredient: Ingredient;
}

interface Formule {
  id: number;
  marqueInspireDe: string;
  statut: string;
  phaseFabrication: string;
  phaseStatut:string;   
  formuleIngredients: FormuleIngredient[];
  dateCreation:string;
}



const FormulesProjet = () => {
  const [formules, setFormules] = useState<Formule[]>([]);
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();  
  const role = localStorage.getItem('role');
  const [page, setPage] = useState(1);
  const itemsPerPage = 10;
  const startIndex = (page - 1) * itemsPerPage;
  const displayedFormules= formules.slice(startIndex, startIndex + itemsPerPage);
  const isFirstPage = page === 1;
  const isLastPage = startIndex + itemsPerPage >= formules.length;
const [projet,setProjet]=useState<Projet>();
const chefId=localStorage.getItem('chefId');
const [showConfirmModal, setShowConfirmModal] = useState(false);
const [selectedFormuleId, setSelectedFormuleId] = useState(null);

  useEffect(() => {
   
    axios.get(`http://localhost:8080/projets/${id}/formules`)
      .then(response => {
        setFormules(response.data);
        axios.get(`http://localhost:8080/projets/${id}`)
        .then(r=>{
          setProjet(r.data)
        }

        )

      })
      .catch(error => {               
        console.error('Error loading formules:', error);
      });
  }, [id]);

  const handleOpenConfirmModal = (formuleId) => {
    setSelectedFormuleId(formuleId);
    setShowConfirmModal(true);
  };
  
  
  const formatIngredients = (ingredients) => {
    return ingredients.reduce((acc, ingredient, idx) => {
      const text = `${ingredient.quantite} ${ingredient.ingredient.nomCommercial}`;
      if (idx % 6 === 0 && idx !== 0) {  //idx est multiple de 6 et pas premier element
        acc.push(<br key={`br-${idx}`} />); 
      }
      acc.push(
        <span key={idx}>
          {text}{idx < ingredients.length - 1 ? '; ' : ''} 
        </span>
      );
      return acc;
    }, []);
  };
  const handleRowClick = ( formuleId: number) => {
        
    navigate(`/formules/${formuleId}`);
  };
  
  const handleEditClick=(formuleId)=>{
    axios.put(`http://localhost:8080/formules/reviser/${selectedFormuleId}`)
    .then(Response=>{
      navigate(`/formules/${selectedFormuleId}/edit`);  
      setShowConfirmModal(false);
      }
    )
    .catch(error => {
      console.error('Erreur lors de la révision de la formule', error);
    });
  }
  const canManageFormules = () => {
    
    const role = localStorage.getItem('role');
    const isChef = projet?.chefProjetId && chefId && parseInt(chefId) === projet.chefProjetId;
    const isAdmin = role === "ADMIN";
    return isChef || isAdmin;
  };
  return (
    <Container>
      <Row className="justify-content-center mt-4">
        <Col>
          <h2>Formules de Projet</h2>
          <hr />

          <Table className='mt-4' striped bordered hover>
            <thead>
              <tr>
              <th>Numéro</th>
                <th>Formule</th>
                <th>Statut</th>
                {canManageFormules() &&
                <th>Action</th>
}
              </tr>
            </thead>
            <tbody>
              {
           displayedFormules.map((formule) =>(
            <tr key={formule.id} onClick={() => handleRowClick(formule.id)} style={{ cursor: 'pointer' }}> 
            <td>{formule.formuleIndex}</td>
            <td>           {formatIngredients(formule.formuleIngredients)} 
</td>
              <td className='text-center'><PillExample name={formule.statut}/></td>
              {canManageFormules() &&
              <td className='text-center'> <Button variant="outline-primary" onClick={(e) =>{e.stopPropagation(); handleOpenConfirmModal(formule.id)}} style={{ fontSize: '0.875rem' }}>
                        <FontAwesomeIcon icon={faEdit} className="me-2" /> Réviser
                      </Button></td>
}
            </tr>
          ))
        }




            </tbody>
          </Table>
        </Col>
      </Row>

      <Row className="justify-content-center my-3">
  {!isFirstPage && (
    <Col className="text-center" xs="auto">
      <FontAwesomeIcon icon={faChevronLeft} onClick={() => setPage(page - 1)} style={{ cursor: 'pointer' }} />
    </Col>
  )}
  {!isLastPage && (
    <Col className="text-center" xs="auto">
      <FontAwesomeIcon icon={faChevronRight} onClick={() => setPage(page + 1)} style={{ cursor: 'pointer' }} />
    </Col>
  )}
</Row>
<Modal show={showConfirmModal} onHide={() => setShowConfirmModal(false)} centered>
  <Modal.Header closeButton>
    <Modal.Title>Confirmation de révision</Modal.Title>
  </Modal.Header>
  <Modal.Body>
    Êtes-vous sûr de vouloir réviser cette formule ?
  </Modal.Body>
  <Modal.Footer>
    <Button style={{ fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} variant="secondary" onClick={() => setShowConfirmModal(false)}>
      Annuler
    </Button>
    <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={handleEditClick}>
      Confirmer
    </Button>
  </Modal.Footer>
</Modal>

      </Container>  );
};

export default FormulesProjet;
