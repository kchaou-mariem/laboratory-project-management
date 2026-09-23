import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Alert, Button, Col, Container, Form, Modal, ModalTitle, Row } from "react-bootstrap"
import { useNavigate } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheckCircle } from '@fortawesome/free-solid-svg-icons';
const createProject=()=>{
    const navigate=useNavigate();
    const [chefsProjet,setChefsProjet]=useState([]);
    const [showAlerteDate, setShowAlerteDate]=useState(false);
    const [showNameError, setShowNameError] = useState(false);
    const role = localStorage.getItem('role');

    const[showSuccessModal,setShowSuccessModal]=useState(false);
        const [projet,setProjet]=useState({
        nom:'',
        dateDebut:'',
        dateFin:'',
        realisation:0,
        description:'',
        parQUI:'',
        comment:'',
        statut:'A_VENIR'
      
    })
    
    useEffect(() => {
      axios.get('http://localhost:8080/comptes/chefs')
          .then(response => {
              setChefsProjet(response.data);
          })
          .catch(error => {
              console.error('Erreur lors de la récupération des chefs de projet', error);
          });
  }, []);
    

    const handleChange=(e)=>{
        const{name,value}=e.target;
        setProjet(prev=>({
            ...prev,
            [name]:value
        }));
        setShowAlerteDate(false);
        setShowNameError(false);
    }
const handleChefProjetChange = (e) => {
    const chefProjetId = e.target.value;
    const selectedChef = chefsProjet.find(chef => chef.id === parseInt(chefProjetId));
    setSelectedChefProjet(selectedChef);
    setProjet(prev => ({
      ...prev,
      chefProjet: selectedChef
    }));
  };

    const handleSubmit=(e)=>{
      e.preventDefault();
      if(projet.dateFin< projet.dateDebut){
        setShowAlerteDate(true);
        return;
      }
      
      console.log('Projet à créer:', projet);
      axios.post(`http://localhost:8080/projets/create`,projet)
      .then(response=>{
        setShowSuccessModal(true);

          setTimeout(()=>navigate(`/projets/${response.data.id}`),2000); 
      })
      .catch(error => {
        console.error("Erreur complète:", error);
       
         if (error.response && error.response.status === 500 && error.response.data && typeof error.response.data.message === 'string' && error.response.data.message.includes("Nom déjà utilisé")) {
            setShowNameError(true);
        } else {
            console.error("Autre erreur lors de la création du projet", error);
        }
    }); 
    
    }
    
return(
    <Container>
        <Row className='container mt-4'>
            <h2>Création de nouveau projet</h2>
        </Row>
        <hr />
        <br />
        {showAlerteDate && 
        <Alert variant='danger' onClose={()=>setShowAlerteDate(false)} dismissible> 
        <Alert.Heading>Attention !!!</Alert.Heading>
        <p>Date de fin doit etre postérieure à la date de début</p> 
        </Alert>}
        {showNameError && (
                <Alert variant='danger' onClose={() => setShowNameError(false)} dismissible>
                    <Alert.Heading>Erreur de création</Alert.Heading>
                    <p>Le nom du projet est déjà utilisé, veuillez en choisir un autre.</p>
                </Alert>
            )}
        <Form onSubmit={handleSubmit}>
         <Row>
            <Col>
            <Form.Label>Nom</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control 
            type="text"
             name='nom'
             value={projet.nom}
             onChange={handleChange}   
             required
            />
            </Col>
         </Row>
          <br />
          <Row>
            <Col>
            <Form.Label>Date de début</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control
            type="date"
            name='dateDebut'
            value={projet.dateDebut}
            onChange={handleChange}
            required
            />
            </Col>
          </Row>
           <br />
           <Row>
            <Col>
            <Form.Label>Date de fin</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control
            type="date"
            name='dateFin'
            value={projet.dateFin}
            onChange={handleChange}
            required
            />
            </Col>
          </Row>
           <br />
          
          <Row>
            <Col>
            <Form.Label>Description</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control 
            as="textarea"
            name='description'
            value={projet.description}
            onChange={handleChange}
            required
            />
            </Col>
          </Row>
<br />
<Row>
            <Col>
            <Form.Label>Chef de projet</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Select name="chefProjetId" value={projet.chefProjetId} onChange={handleChange} required>
                            <option value="">Sélectionner un chef de projet</option>
                            {chefsProjet.map(chef => (
                                <option key={chef.id} value={chef.id}>{chef.nom} {chef.prenom}</option>
                            ))}
                        </Form.Select>
            </Col>
          </Row>
<br />
          <Row>
            <Col>
            <Form.Label>Par qui</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control 
            type="text"
            name='parQUI'
            value={projet.parQUI}
            onChange={handleChange}
            required
            />
            </Col>
            </Row>
            <br />
            <Row>
            <Col>
            <Form.Label>Comment</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control
            type="text"
            name='comment'
            value={projet.comment}
            onChange={handleChange}
            required
            />
            </Col>
          </Row>
          <br />
          <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} type='submit'>Créer</Button>
        </Form>
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
Projet créé
</Modal.Title>
</Modal.Header>
<Modal.Body style={{backgroundColor:'#f8f9fa'}}>
  <p style={{color:'#20c997'}}>
    <FontAwesomeIcon icon={faCheckCircle} className='me-2'/>
    Votre projet a été crée avec succès!
  </p>
</Modal.Body>
        </Modal>
    </Container>
)


}
export default createProject;