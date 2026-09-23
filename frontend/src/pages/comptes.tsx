import axios from "axios";
import React from "react";
import { useEffect, useState } from "react";
import { Button, Col, Container, Modal, Row, Table } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSave, faTimes, faEdit, faTrash, faCheckCircle, faPlusCircle, faChevronLeft, faChevronRight } from '@fortawesome/free-solid-svg-icons';
//import { faChevronLeft, faChevronRight } from '@fortawesome/free-solid-svg-icons';

interface Compte{
    nom:string;
    prenom:string;
    email:string;
    dateNaissance:string;
    password:string;
    role:string;
}

function Comptes(){
const [comptes,setComptes]=useState<Compte[]>([]);
const[showConfirmModal,setShowConfirmModal]=useState(false);
const[idToDelete,setIdToDelete]=useState<number|null>(null);
const[showSuccessModal,setShowSuccessModal]=useState(false);
const navigate=useNavigate();
const itemsPerPage = 15;
const [page, setPage] = useState(1);
const startIndex = (page - 1) * itemsPerPage;
const displayedComptes = comptes.slice(startIndex, startIndex + itemsPerPage);
const isFirstPage = page === 1;
const isLastPage = startIndex + itemsPerPage >= comptes.length;
const role = localStorage.getItem('role');


useEffect(
  ()=>{
    
    axios.get("http://localhost:8080/comptes")
    .then(response=>{
      setComptes(response.data);
    })
    .catch(error=>console.error("Erreur de récupération de Comptes",error));
  },[]);

  const handleRowClick=(id:number)=>{
     navigate(`/comptes/${id}`);
  }
const navigateToCreate=()=>{
  navigate(`/signUp`);
}

const clicDeleteCompte=(id:number)=>{
  setIdToDelete(id);
  setShowConfirmModal(true);
}
 
const deleteCompte = async () => {
    try {
      await axios.delete(`http://localhost:8080/comptes/delete/${idToDelete}`);
      setShowConfirmModal(false);
      setComptes(comptes.filter(compte => compte.id !== idToDelete)); 
      setShowSuccessModal(true);
      setTimeout(()=>{
        setShowSuccessModal(false);
        navigate(`/comptes`)},3000); 

    } catch (error) {
      console.error('erreur pour la suppression de compte:', error);
    }
  };

  return(
<Container  >
 
<div className="d-flex justify-content-between align-items-center my-4 mt-4 ">
        <h2 style={{ fontFamily:'Helvetica', fontSize: '2.15rem', color: '#333', textShadow: '0px 2px 4px rgba(0,0,0,0.1)' }}>Liste des comptes</h2>
          {(role==="ADMIN" || role==="RESPONSABLE_TECHNIQUE")&& 
          <Button onClick={navigateToCreate} style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }}>
    <FontAwesomeIcon icon={faPlusCircle} className="me-2"/> Nouveau compte
  </Button>          
   } 
          </div>


      <hr />

     <Table striped bordered hover responsive>
       <thead>
         <tr>
          <th>Nom et Prénom</th>
          <th>Adresse-Email</th>
         <th>Role</th>
         {/* <th>Date de naissance</th> */}
        
         {/* <th>Date de fin</th> */}
         </tr>
       </thead>
       <tbody>
        {
          displayedComptes.map(
            compte=>(
              <tr key={compte.id} onClick={()=>handleRowClick(compte.id)} style={{cursor:'pointer'}}>
              <td>{compte.nom} {compte.prenom}</td>
              <td>{compte.email}</td>
              <td>{compte.role}</td>
              {/* <td>{compte.dateNaissance}</td> */}
              
              </tr>
            )
            
          )
        }
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

     <Modal show={showConfirmModal} onHide={() => setShowConfirmModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirmation de suppression</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Êtes-vous sûr de vouloir supprimer ce compte ?
        </Modal.Body>
        <Modal.Footer>
          <Button style={{ fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} variant="secondary" onClick={() => setShowConfirmModal(false)}>Non</Button>
          <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={deleteCompte}>Oui, supprimer</Button>
        </Modal.Footer>
      </Modal>

      <Modal
  show={showSuccessModal}
  onHide={() => setShowSuccessModal(false)}
  centered
  size='sm'
  aria-labelledby="contained-modal-title-vcenter"
>
<Modal.Header closeButton style={{backgroundColor:"#f8f9fa",borderColor:"#e9ecef"}}>
<Modal.Title id="contained-modal-title-vcenter" style={{color:'#004085',fontWeight:'bold'}}>      <FontAwesomeIcon icon={faTrash} className='me-2' />
      Compte supprimé
    </Modal.Title>
  </Modal.Header>
  <Modal.Body style={{ backgroundColor: '#f8f9fa' }}>
    <p style={{ color: '#0056b3' }}>  
      <FontAwesomeIcon icon={faTrash} className='me-2' />
      Le compte a été supprimé avec succès.
    </p>
  </Modal.Body>
</Modal>




</Container>       );


}

export default Comptes;