import axios from "axios";
import React from "react";
import { useEffect, useState } from "react";
import { Alert, Button, Card, Col, Container, Form, Modal, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheckCircle } from '@fortawesome/free-solid-svg-icons';
interface Compte{
    nom:string;
    prenom:string;
    email:string;
    dateNaissance:string;
    password:string;
    role:string;
}
const signUp=()=>{
const[compte,setCompte]=useState<Compte>();
const[comptes,setComptes]=useState<Compte[]>([]);
const [passwordConfirmation,setPasswordConfirmation]=useState<String>();
const navigate=useNavigate();
const[showSuccessModal,setShowSuccessModal]=useState(false);
const role = localStorage.getItem('role');


const handleChange=(e)=>{
    const{name,value}=e.target;
    setCompte(prev=>({
        ...prev as Compte,
        [name]:value
    }))
   
}

const handleSubmit=(e)=>{
  e.preventDefault();
  axios.get(`http://localhost:8080/comptes/email/${compte.email}`)
  .then(response=>{
     if(response.data){
      alert("Attention !!! Adresse est déjà utilisé !!!");
      setShowSuccessModal(false);
      return;
     }

      if(compte?.password!==passwordConfirmation){
        alert("Attention !! les mots de passe ne correspondent pas");
        setShowSuccessModal(false);
        return;
      }
     
  axios.post(`http://localhost:8080/comptes/create`,compte)
    .then(response=>{
    setShowSuccessModal(true);
    setTimeout(()=>navigate(`/comptes/${response.data.id}`),2000); 
})
  
  .catch(error=>{
    console.error("erreur en création de projet",error);
    setShowSuccessModal(false);
  });
})
};





return(
    <Container>
         <Row className='container mt-4' >
            <h2>Création de nouveau compte</h2>
        </Row>
        <hr />
        <br />
        
        <Card className="mb-3 bg-light" >
            <Card.Body>
             <Card.Title>Informations</Card.Title>
        <Form >
         <Row>
            <Col>
            <Form.Label>Nom</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control 
            type="text"
             name='nom'
             value={compte?.nom}
             onChange={handleChange}   
             
            />
            </Col>
         </Row>
          <br />
          <Row>
            <Col>
            <Form.Label>Prenom</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control 
            type="text"
             name='prenom'
             value={compte?.prenom}
             onChange={handleChange} 
               
            />
            </Col>
         </Row>
          
         <br />
         <Row>
            <Col>
            <Form.Label>Adresse-Email</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control 
            type="email"
             name='email'
             value={compte?.email}
             onChange={handleChange}   
             
            />
            </Col>
         </Row>
         <br />
          <Row>
          <Col>
            <Form.Label>Rôle</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Select
            name="role"
            value={compte?.role}
            onChange={handleChange}
          >
            <option value="">Sélectionner un rôle</option>
            <option value="ADMIN">Admin</option>
            <option value="RESPONSABLE_TECHNIQUE">Responsable technique</option>
            <option value="CHEF_PROJET">Chef de projet</option>
            <option value="TECHNICIEN">Technicien</option>
          </Form.Select>           
            </Col>
          </Row>
</Form>
</Card.Body>
</Card>

<Card className="mb-3 bg-light" >
    <Card.Body >
        <Card.Title>Authentification</Card.Title>
<Form>
          <Row>
            <Col>
            <Form.Label>Password</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control 
            type="password"
             name='password'
             value={compte?.password}
             onChange={handleChange}   
            />
            </Col>
         </Row>
         <br />
         <Row>
            <Col>
            <Form.Label>Confirmation de password</Form.Label>
            </Col>
            <Col sm="10">
            <Form.Control 
            type="password"
            value={passwordConfirmation}
            onChange={(e)=>setPasswordConfirmation(e.target.value)}
            />
            </Col>
         </Row>
          
          
          </Form>
          </Card.Body>
          </Card>
          <br />
          <div >
            <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} type="submit" onClick={handleSubmit} >Créer</Button>
          </div>
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
          Compte créé
          </Modal.Title>
          </Modal.Header>
          <Modal.Body style={{backgroundColor:'#f8f9fa'}}>
            <p style={{color:'#20c997'}}>
              <FontAwesomeIcon icon={faCheckCircle} className='me-2'/>
              Votre compte a été crée avec succès!
            </p>
          </Modal.Body>
        </Modal>


    </Container>
)

}

export default signUp;