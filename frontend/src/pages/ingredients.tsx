import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Button, Form, ButtonGroup, Container, Row, Col, Modal, Alert } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSave, faTimes, faEdit, faTrash, faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { faChevronLeft, faChevronRight } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { faCheckCircle } from '@fortawesome/free-solid-svg-icons';
interface Ingredient {
  id: number;
  nomCommercial: string;
  unite: string;
  inci:string;
  fonction:string;
  casN:string;
}

const ListeIngredients = () => {
  const navigate=useNavigate();
  const [page, setPage] = useState(1);
  const itemsPerPage = 15;
  const [ingredients, setIngredients] = useState([]);
  const [editId, setEditId] = useState(null);
  const [showAddModal, setShowAddModal] = useState(false);
  const[showSuccessSuppModal,setShowSuccessSuppModal]=useState(false);
  const [errorNomCommercial, setErrorNomCommercial] = useState(false);
const [errorCasN, setErrorCasN] = useState(false);
const [errorINCI, setErrorINCI] = useState(false);
const role = localStorage.getItem('role');

  const[showSuccessModal,setShowSuccessModal]=useState(false);
  const[showConfirmModal,setShowConfirmModal]=useState(false);
const[idToDelete,setIdToDelete]=useState<number|null>(null);
  const [newIngredient, setNewIngredient] = useState({
    nomCommercial: '',
    unite: '',
    fonction: '',
    casN: '',
    inci: ''
  });
  const startIndex = (page - 1) * itemsPerPage;
  const displayedIngredients = ingredients.slice(startIndex, startIndex + itemsPerPage);
  const isFirstPage = page === 1;
  const isLastPage = startIndex + itemsPerPage >= ingredients.length;

  useEffect(() => {
    
    fetchIngredients();
  }, []);

  const fetchIngredients = async () => {
    try {
      const response = await axios.get('http://localhost:8080/ingredients');
      setIngredients(response.data);
    } catch (error) {
      console.error('Erreur de récupération des ingredients:', error);
    }
  };

  const startEdit = (id) => {
    setEditId(id);
  };

  const cancelEdit = () => {
    setEditId(null);
  };

  const handleEditChange = (e, id) => {
    const { name, value } = e.target;
    setIngredients(ingredients.map(item => item.id === id ? { ...item, [name]: value } : item));
  };

  const saveEdit = async (ingredient) => {
    try {
      await axios.put(`http://localhost:8080/ingredients/update/${ingredient.id}`, ingredient);
      setEditId(null);  // Quitter le mode édition
      fetchIngredients();  // Rafraîchir la liste
    } catch (error) {
      console.error('Failed to update ingredient:', error);
    }
  };
  

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewIngredient(prev => ({ ...prev, [name]: value }));
  };

  const handleAddIngredient = async () => {
    try {
      await axios.post('http://localhost:8080/ingredients/create', newIngredient);
      setNewIngredient({
        nomCommercial: '',
        unite: '',
        fonction: '',
        casN: '',
        inci: ''
      });
      setShowAddModal(false);
      setShowSuccessModal(true);
      setTimeout(() => {
        setShowSuccessModal(false); 
        navigate(`/ingredients`); 
      }, 2000); 
        fetchIngredients();  
       
    } catch (error) {
      console.error("Erreur complète:", error);
      
  
      if (error.response && error.response.status === 500) {
        if (typeof error.response.data.message === 'string') {
          if (error.response.data.message.includes("Nom Commercial déjà utilisé")) {
            setErrorNomCommercial(true);
          } else if (error.response.data.message.includes("Cas°N déjà utilisé")) {
            setErrorCasN(true);
          } else if (error.response.data.message.includes("INCI déjà utilisé")) {
            setErrorINCI(true);
          }
        }
      } else {
        console.error("Autre erreur lors de l'ajout de l'ingrédient", error);
      }
    }
  };

  return (
    <Container>
<div className="d-flex justify-content-between align-items-center my-4 mt-4 ">
      <h2 style={{ fontFamily:'Helvetica', fontSize: '2.15rem', color: '#333', textShadow: '0px 2px 4px rgba(0,0,0,0.1)' }}>Liste des ingrédients</h2>
        {(role==="ADMIN" || role==="RESPONSABLE_TECHNIQUE" ||role==="CHEF_PROJET" )&&
          <Button onClick={() => setShowAddModal(true)} style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }}>
    <FontAwesomeIcon icon={faPlusCircle} className="me-2"/> Nouvel ingrédient
  </Button>          
   } 
      </div>
      <hr />
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Nom Commercial</th>
            <th>Unité</th>
            <th>Fonction</th>
            <th>CAS N°</th>
            <th>INCI</th>
            {(role==="ADMIN" || role==="RESPONSABLE_TECHNIQUE" ||role==="CHEF_PROJET" )&&

            <th>Actions</th>
  }
          </tr>
        </thead>
        <tbody>
          {displayedIngredients.map((ingredient) => (
            <tr key={ingredient.id}>
              {editId === ingredient.id ? (
                <>
                  <td><Form.Control type="text" name="nomCommercial" value={ingredient.nomCommercial} onChange={(e) => handleEditChange(e, ingredient.id)} /></td>
                  <td><Form.Control type="text" name="unite" value={ingredient.unite} onChange={(e) => handleEditChange(e, ingredient.id)} /></td>
                  <td><Form.Control type="text" name="fonction" value={ingredient.fonction} onChange={(e) => handleEditChange(e, ingredient.id)} /></td>
                  <td><Form.Control type="text" name="casN" value={ingredient.casN} onChange={(e) => handleEditChange(e, ingredient.id)} /></td>
                 
                 <td><Form.Control type="text" name="inci" value={ingredient.inci} onChange={(e) => handleEditChange(e, ingredient.id)} /></td>
                  <td>
                    <ButtonGroup>
                      <Button variant="outline-success" onClick={() => saveEdit(ingredient)}><FontAwesomeIcon icon={faSave} /></Button>
                      <Button variant="outline-secondary" onClick={cancelEdit}><FontAwesomeIcon icon={faTimes} /></Button>
                    </ButtonGroup>
                  </td>
                </>
              ) : (
                <>
                  <td>{ingredient.nomCommercial}</td>
                  <td>{ingredient.unite}</td>
                  <td>{ingredient.fonction}</td>
                  <td>{ingredient.casN}</td>
                  <td>{ingredient.inci}</td>
                  {(role==="ADMIN" || role==="RESPONSABLE_TECHNIQUE" ||role==="CHEF_PROJET" )&&

                  <td>
                    <ButtonGroup>
                      <Button variant="outline-primary" onClick={() => startEdit(ingredient.id)}><FontAwesomeIcon icon={faEdit} /></Button>
                      {/* <Button variant="outline-danger" onClick={() => clicDeleteIngredient(ingredient.id)}><FontAwesomeIcon icon={faTrash} /></Button> */}
                    </ButtonGroup>
                  </td>
}
                </>
              )}
            </tr>
          ))}
        </tbody>
      </Table>

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

      <Modal show={showAddModal} onHide={() => setShowAddModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Ajouter un nouvel ingrédient</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
          {errorNomCommercial && (
                <Alert variant='danger' onClose={() => setErrorNomCommercial(false)} dismissible>
                    <Alert.Heading>Erreur de création</Alert.Heading>
                    <p>Le nom commercial est déjà utilisé.</p>
                </Alert>
            )}
            {errorCasN && (
                <Alert variant='danger' onClose={() => setErrorCasN(false)} dismissible>
                    <Alert.Heading>Erreur de création</Alert.Heading>
                    <p>Cas°N est déjà utilisé.</p>
                </Alert>
            )}{errorINCI && (
              <Alert variant='danger' onClose={() => setErrorINCI(false)} dismissible>
                  <Alert.Heading>Erreur de création</Alert.Heading>
                  <p>INCI est déjà utilisé.</p>
              </Alert>
          )}
            <Form.Group className="mb-3">
              <Form.Label>Nom Commercial</Form.Label>
              <Form.Control
                type="text"
                name="nomCommercial"
                value={newIngredient.nomCommercial}
                onChange={handleInputChange}
              />
               
            </Form.Group>
           
            <Form.Group className="mb-3">
              <Form.Label>Unité</Form.Label>
              <Form.Select  name="unite" value={newIngredient.unite} onChange={handleInputChange}>
                            <option value="">Sélectionner une unité</option>
                            <option value="L">L</option>
                            <option value="KG">KG</option>
                            <option value="mL">mL</option>

                      </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Fonction</Form.Label>
              <Form.Control
                type="text"
                name="fonction"
                value={newIngredient.fonction}
                onChange={handleInputChange}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>CAS N°</Form.Label>
              <Form.Control
                type="text"
                name="casN"
                value={newIngredient.casN}
                onChange={handleInputChange}
              />
               <Form.Control.Feedback type="invalid">
    Cas°N déjà utilisé.
  </Form.Control.Feedback>
            </Form.Group>
           
            <Form.Group className="mb-3">
              <Form.Label>INCI</Form.Label>
              <Form.Control
                type="text"
                name="inci"
                value={newIngredient.inci}
                onChange={handleInputChange}
              />
               <Form.Control.Feedback type="invalid">
      INCI déjà utilisé.
  </Form.Control.Feedback>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button style={{ fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} variant="secondary" onClick={() => setShowAddModal(false)}>Fermer</Button>
          <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={handleAddIngredient}>Enregistrer</Button>
        </Modal.Footer>
      </Modal>
      <Modal
        show={showSuccessModal}
        onHide={()=>setShowSuccessModal(false)}
        centered
        size='sm'
        aria-labelledby="contained-modal-title-vcenter"
        >
      <Modal.Header closeButton style={{backgroundColor:"#f8f9fa",borderColor:"#e9ecef"}}>
      <Modal.Title id="contained-modal-title-vcenter" style={{color:'#198754',fontWeight:'bold'}}>
      <FontAwesomeIcon icon={faCheckCircle} className='me-2'/>
      Ingrédient créé
      </Modal.Title>
      </Modal.Header>
      <Modal.Body style={{backgroundColor:'#f8f9fa'}}>
        <p style={{color:'#20c997'}}>
          <FontAwesomeIcon icon={faCheckCircle} className='me-2'/>
          Votre ingrédient a été crée avec succès!
        </p>
      </Modal.Body>
        </Modal>

    </Container>
  );
};

export default ListeIngredients;
