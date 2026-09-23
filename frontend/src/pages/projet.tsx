import axios from "axios";
import { useEffect, useState } from "react"
import { Container,Row,Col, Form, Tabs, Tab, Button,Modal, Navbar, Alert } from "react-bootstrap";
import {  useNavigate, useParams } from "react-router-dom";
import React from 'react';
import PillExample from "../components/statusColor";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheckCircle, faTrash } from "@fortawesome/free-solid-svg-icons";
import { faExclamationTriangle } from "@fortawesome/free-solid-svg-icons"; 
interface Projet {
  id: number;
  nom: string;
  statut: string;
  dateDebut: string;
  dateFin: string;
  comment: string;
  description: string;
  parQUI: string;
  formules: Formule[];
  chefProjetId?: number; 
}

interface Formule {
  id: number;
  dateCreation: string;
  formuleIndex: string;
  statut: string;
  formuleIngredients: FormuleIngredient[];
}

interface FormuleIngredient {
  quantite: number;
  ingredient: {
    id: number;
    nomCommercial: string;
    unite: string;
  };
}

const projectDetails=()=>{
const [projet,setProjet]=useState<Projet | null>(null);
const {id}=useParams<{id:string}>();
const [formules,setFormules]=useState<Formule[]>([]);
const navigate =useNavigate();
const [formulesTR,setFormulesTR]=useState<Formule[]>([]);
const [formulesT,setFormulesT]=useState<Formule[]>([]);
const [formuleT,setFormuleT]=useState<Formule | null>(null);
const[showModalT,setShowModalT]=useState<Boolean>(false);
const[showModalTs,setShowModalTs]=useState<Boolean>(false);
const [showConfirmModal, setShowConfirmModal] = useState(false);
const[showSuccessValiderModal,setShowSuccessValiderModal]=useState(false);
const [projetUpdated, setProjetUpdated] = useState<Projet>({ 
  id: 0, 
  nom: '',
  statut: '',
  dateDebut: '',
  dateFin: '',
  comment: '', 
  description: '',
  parQUI: '',
  formules: []
});
const [editMode,setEditMode]=useState<Boolean>(false);
const [showConfirmDeleteModal, setShowConfirmDeleteModal] = useState(false);
const [showSuccessDeleteModal, setShowSuccessDeleteModal] = useState(false);
const[showAlerteDate,setShowAlerteDate]=useState(false);
const [chefProjet, setChefProjet] = useState<Compte>();
const [chefsProjet, setChefsProjet] = useState<Compte[]>([]);
const role = localStorage.getItem('role');
const chefId=localStorage.getItem('chefId');

interface Compte{
  nom:string;
  prenom:string;
}
useEffect(
()=>{
  
const fetchData=async()=>{
  try{
    const projetResponse=await axios.get(`http://localhost:8080/projets/${id}`);
    const projetData=projetResponse.data;
    setProjet(projetData);
    setProjetUpdated(projetData);

    if (projetData ){
      const formulesResponse=await axios.get(`http://localhost:8080/projets/${id}/formules`);
      const formulesData=formulesResponse.data || [];
      setFormules(formulesData);
      const chefProjet=await axios.get(`http://localhost:8080/comptes/id/${projetData?.chefProjetId}`);
      const chefData=chefProjet.data;
      setChefProjet(chefData);

      const chefsProjetResponse = await axios.get('http://localhost:8080/comptes/chefs');
        setChefsProjet(chefsProjetResponse.data);
    }
   

    }
     catch(error){ console.error('erreur lors de la récupération du projet',error);}
  }
  
     fetchData();


},[id]);

const canManageFormules = () => {
  const role = localStorage.getItem('role');
  const isChef = projet?.chefProjetId && chefId && parseInt(chefId) === projet.chefProjetId;
  const isAdmin = role === "ADMIN";
  return isChef || isAdmin;
};


const handleSelectChefProjet = (event) => {
  event.preventDefault();
  const newChefProjetId = parseInt(event.target.value, 10);
  const updatedProjet = { ...projetUpdated, chefProjetId: newChefProjetId };

  setProjetUpdated(updatedProjet); 

  setProjet(prevProjet => ({
    ...prevProjet,
    chefProjetId: newChefProjetId
  }));
};

const isAlertEcheanceNeeded=()=>{
  const today=new Date();
  const dateFin=new Date(projetUpdated.dateFin);
  return dateFin<today&&projetUpdated.statut!=="VALIDE";
}

const navigateToFormuleCreation =()=>{
  navigate(`/projets/${id}/createFormule`);
}
const navigateToFormules=()=>{
  navigate(`/projets/${id}/formules`);
}
const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  const { name, value } = e.target;
  setProjetUpdated((prev) => ({
    ...(prev as Projet),
    [name]: value,
  }));

  if(name==="dateDebut" || name==="dateFin"){
    setShowAlerteDate(false);
  }
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

const handleUpdate=(e)=>{
  e.preventDefault();
  if(projetUpdated.dateFin< projetUpdated.dateDebut){
    setShowAlerteDate(true);
    return;
  }

  if (projetUpdated){
    axios.put(`http://localhost:8080/projets/update/${id}`,projetUpdated)
    .then(response=>{
      setProjet(response.data);
      setProjetUpdated(response.data);
      setEditMode(false);

    })
    .catch(error=>console.error('erreur lors de la mise à jour de projet',error))
  }
}; 


const handleValider = () => {
  // Filtrer pour obtenir seulement les formules terminées
  const formulesTerminees = formules.filter(f => f.statut === "TERMINE");
  setFormulesT(formulesTerminees);

  // Afficher le modal si au moins une formule est terminée
  if (formulesTerminees.length > 0) {
    setShowModalTs(true);
  }
}


const validerF = (formule) => {
  setShowModalTs(false); 
  setFormuleT(formule);  
  setShowConfirmModal(true);
}
const confirmerValidationFormule = () => {
  if (formuleT) {
    axios.put(`http://localhost:8080/formules/${formuleT.id}/valider`)
      .then(() => {
        const updatedFormules = formules.map(f => 
          f.id === formuleT.id ? { ...f, statut: "VALIDE" } : f
        );
        setFormules(updatedFormules);
        setShowConfirmModal(false);  // Fermer le modal de confirmation
        setProjet(prev => ({ ...prev, statut: "VALIDE" }));
        setShowSuccessValiderModal(true); // Valider le projet
        setTimeout(() => {
          setShowSuccessValiderModal(false);
        }, 2000);
      })
      .catch(error => console.error("Erreur lors de la validation de la formule", error));
  }
}


const deleteProjet = () => {
  axios.delete(`http://localhost:8080/projets/delete/${id}`)
    .then(() => {
      setShowConfirmDeleteModal(false);
      setShowSuccessDeleteModal(true);
      setTimeout(() => {
        setShowSuccessDeleteModal(false);
        navigate('/'); // Rediriger vers la liste des projets après la suppression
      }, 2000);
    })
    .catch(error => {
      console.error('Erreur lors de la suppression du projet', error);
      setShowConfirmDeleteModal(false);
    });
};

return(
<Container>
<Row className="d-flex justify-content-between mt-4">
<Col>
<h2>{projet && projet.nom}
{isAlertEcheanceNeeded() && (
              <FontAwesomeIcon icon={faExclamationTriangle} color="red" className="ms-2" />
            )}
</h2>
</Col>
<Col style={{ display: 'flex', justifyContent: 'flex-end' }} >
  <h2><PillExample name={projet && projet.statut}/></h2>
</Col>
</Row>
 <hr />

 <Form>
 {showAlerteDate && 
        <Alert variant='danger' onClose={()=>setShowAlerteDate(false)} dismissible> 
        <Alert.Heading>Attention !!!</Alert.Heading>
        <p>Date de fin doit etre postérieure à la date de début</p> 
        </Alert>}

  <Row className="mb-3">
    <Col sm={4}>
      <Form.Label>Comment</Form.Label>
      <Form.Control
      type="text"
      name="comment"
      value={projetUpdated.comment}
      readOnly={!editMode}
      onChange={handleChange}
      />
    </Col>

    <Col sm={4}>
      <Form.Label>Date de début</Form.Label>
      <Form.Control
      type="date"
      name="dateDebut"
      value={projetUpdated.dateDebut}
      readOnly={!editMode}
      onChange={handleChange}     
       />
    </Col>
    
    <Col sm={4}>
      <Form.Label>date de fin</Form.Label>
      <Form.Control
        type="date"
        name="dateFin"
        value={projetUpdated.dateFin}
        readOnly={!editMode}
        onChange={handleChange}      
        />
    </Col>
  </Row>

<Row className="mb-3">
  <Form.Label>Description</Form.Label>
  <Form.Control 
  as="textarea"
  name="description"
  value={projetUpdated.description}
  readOnly={!editMode}
  onChange={handleChange} 
  />
</Row>

  <Row className="mb-3">
    <Col sm={4}>
      <Form.Label>Par qui</Form.Label>
      <Form.Control
      type="text"
      name="parQUI"
      value={projetUpdated.parQUI}
      readOnly={!editMode}
      onChange={handleChange}
      />
    </Col>
{!editMode &&
  <Col>

<Form.Label>Chef de projet</Form.Label>
<Form.Control
      type="text"
      name="chefProjetId"
      value={chefProjet ? `${chefProjet.nom} ${chefProjet.prenom}` : 'Chargement...'}
      readOnly
      onChange={handleChange}
      />
</Col>
}
{editMode &&
<Col>
          <Form.Label>Chef de projet</Form.Label>
          {projet && (
            <Form.Select value={projet.chefProjetId} onChange={handleSelectChefProjet} disabled={!editMode}>
              {chefsProjet.map((chef) => (
                <option key={chef.id} value={chef.id}>
                  {chef.nom} {chef.prenom}
                </option>
              ))}
            </Form.Select>
          )}
        </Col>
}   
      <Col>
      {editMode && (
        <Button  className=' mt-4' style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={handleUpdate}>Confirmer la modification</Button>
      )}
      </Col>
      </Row>
    </Form>
    <Tabs defaultActiveKey="details" id="uncontrolled-tab-example"  className="mb-3">
       {formules.map((formule, index) => (
        <Tab eventKey={`formule${index}`} title={`Formule ${formule.formuleIndex}`} key={formule.id}>
        <div>
           {formatIngredients(formule.formuleIngredients)} 
        </div>
      </Tab>
      
      ))}
</Tabs>
        <Row className="mt-4">
        <Col>
        

        {canManageFormules()  && projet?.statut!=="VALIDE" &&  <Button onClick={navigateToFormuleCreation} style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} className="me-2">+ Nouvelle formule</Button>
}

    <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={navigateToFormules} className="me-2">Tous les formules</Button>
    

{(role==="ADMIN" || role==="RESPONSABLE_TECHNIQUE")&&projet && projet.statut === "A_VENIR" &&  formules.length === 0 && (
  <Button style={{ fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} variant="danger" onClick={() => setShowConfirmDeleteModal(true)}>
    <FontAwesomeIcon icon={faTrash} /> Supprimer Projet
  </Button>
)}
  </Col>
  {(role==="ADMIN" || role==="RESPONSABLE_TECHNIQUE")&&
          <Col className="text-end">
          <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} className="me-2"  onClick={()=> setEditMode(!editMode)}>{editMode ? "Annuler" : "Modifier"}</Button>
          {!editMode &&  projet?.statut==="TERMINE" &&
          <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} className="me-2" onClick={handleValider}>Valider</Button>
          }
          </Col>
 } 
        </Row>


 {
showModalTs && 
<Modal
      show={showModalTs}
      onHide={()=>setShowModalTs(false)}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
       <Modal.Header closeButton style={{ backgroundColor: "#f8f9fa", borderColor: "#e9ecef" }}>
       <div style={{ width: '100%', display: 'flex', justifyContent: 'center' }}>
        <Modal.Title  id="contained-modal-title-vcenter" style={{color:"#0056b3", fontWeight:'bold'}}>
           Sélectionnez une formule à valider
           </Modal.Title>
        </div>
        </Modal.Header>
      <Modal.Body >
        <div style={{ gap: '10px' }} className="d-flex flex-wrap justify-content-center">
        { formulesT.map( 
          (f)=>
        <Button style={{
          minWidth: 'calc(100% / 6 - 12px)', // Adjust the division factor to manage the number of buttons per row
          maxWidth: 'calc(100% / 6 - 12px)', // Ensure each button has the same width
        }}
         key={f.id} variant="outline-primary" className="m-2" onClick={()=>validerF(f)}>{f?.formuleIndex}</Button>
        )}     
          </div>  
       
      </Modal.Body>
      
    </Modal>
}

{showConfirmModal && (
<Modal
    show={showConfirmModal}
    onHide={() => setShowConfirmModal(false)}
    size="md"
    aria-labelledby="contained-modal-title-vcenter"
    centered
>
<Modal.Header closeButton style={{ backgroundColor: "#f8f9fa", borderColor: "#e9ecef" }}>
      <Modal.Title id="contained-modal-title-vcenter" style={{color:"#0056b3", fontWeight:'bold'}}>
        <FontAwesomeIcon icon={faCheckCircle} className='me-2' />
        Confirmation de validation
      </Modal.Title>
    </Modal.Header>
    <Modal.Body style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', textAlign: 'center' }}>
      <h4 >Êtes-vous sûr de vouloir valider la formule {formuleT?.formuleIndex} ?</h4>
    </Modal.Body>
    <Modal.Footer>
      <Button onClick={confirmerValidationFormule} style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }}>Oui, valider</Button>
      <Button style={{ fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={() => setShowConfirmModal(false)} variant="secondary">Non</Button>
    </Modal.Footer>
</Modal>
)}


<Modal
        show={showSuccessValiderModal}
        onHide={()=>setShowSuccessValiderModal(false)}
        centered
        size='sm'
        aria-labelledby="contained-modal-title-vcenter"
        >      
        <Modal.Header closeButton style={{backgroundColor:"#f8f9fa",borderColor:"#e9ecef"}}>
      <Modal.Title id="contained-modal-title-vcenter" style={{color:'#198754',fontWeight:'bold'}}>
      <FontAwesomeIcon icon={faCheckCircle} className='me-2'/>
      Formule validée</Modal.Title>
      </Modal.Header>
      <Modal.Body style={{backgroundColor:'#f8f9fa'}}>
        <p style={{color:'#20c997'}}>
    <FontAwesomeIcon icon={faCheckCircle} className='me-2'/>
    Votre formule a été validée avec succès!
  </p>
</Modal.Body>
        </Modal>

        <Modal show={showConfirmDeleteModal} onHide={() => setShowConfirmDeleteModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirmation de suppression</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Êtes-vous sûr de vouloir supprimer ce projet ?
        </Modal.Body>
        <Modal.Footer>
          <Button style={{ fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} variant="secondary" onClick={() => setShowConfirmDeleteModal(false)}>Non</Button>
          <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={deleteProjet}>Oui, supprimer</Button>
        </Modal.Footer>
      </Modal>

 <Modal
  show={showSuccessDeleteModal}
  onHide={() => setShowSuccessDeleteModal(false)}
  centered
  size='sm'
  aria-labelledby="contained-modal-title-vcenter"
>
<Modal.Header closeButton style={{backgroundColor:"#f8f9fa",borderColor:"#e9ecef"}}>
<Modal.Title id="contained-modal-title-vcenter" style={{color:'#004085',fontWeight:'bold'}}>      <FontAwesomeIcon icon={faTrash} className='me-2' />
      projet supprimé
    </Modal.Title>
  </Modal.Header>
  <Modal.Body style={{ backgroundColor: '#f8f9fa' }}>
    <p style={{ color: '#0056b3' }}>  
      <FontAwesomeIcon icon={faTrash} className='me-2' />
      Le projet a été supprimé avec succès.
    </p>
  </Modal.Body>
</Modal>


</Container>


);

};

export default projectDetails;