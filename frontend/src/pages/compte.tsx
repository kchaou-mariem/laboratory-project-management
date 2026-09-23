import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, ButtonGroup, Col, Container, Form, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";

interface Compte{
    nom:string;
    prenom:string;
    email:string;
    dateNaissance:string;
    password:string;
    role:string;
}
const CompteDetails=()=>{
    const[compte,setCompte]=useState<Compte | null>(null);
    const {id}=useParams();
    const [editMode,setEditMode]=useState<boolean>(false);
    const[updatedCompte,setUpdatedCompte]=useState<Compte | null>(null);
    const [passwordConfirmation,setPasswordConfirmation]=useState<string>('');
    const navigate=useNavigate();
    const role = localStorage.getItem('role');

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
      const { name, value } = e.target;
      setUpdatedCompte((prev) => ({
        ...(prev as Compte),
        [name]: value,
      }));
    };

    const handleUpdate=()=>{
      if (updatedCompte){
        if(updatedCompte.password !== passwordConfirmation){
          alert("Attention !! les mots de passe ne correspondent pas");
          return;
        }
        axios.put(`http://localhost:8080/comptes/update/${id}`, updatedCompte)
        .then(response=>{
          setEditMode(false);
          setCompte(updatedCompte);
        })
        .catch(error=>console.error('erreur lors de la mise à jour de compte',error))
      }
    }; 

    useEffect(() => {
        const fetchData = async () => {
          try {
            const compteResponse = await axios.get(`http://localhost:8080/comptes/id/${id}`);
            setCompte(compteResponse.data);
            setUpdatedCompte(compteResponse.data);
          } catch (error) {
            console.error('erreur de récupérer le compte:', error);
          }
        };
        fetchData();
    }, [id]);

    return(
       <Container>
        <Row className="d-flex justify-content-between mt-4">
          <Col>
            <h2 style={{ fontFamily:'Helvetica', fontSize: '2.15rem', color: '#333', textShadow: '0px 2px 4px rgba(0,0,0,0.1)'}} >
              Compte: {compte && compte.nom} {compte && compte.prenom}
            </h2>
          </Col>
        </Row>
        <hr />
        <Form>
          <Row className="mb-3">
            <Col sm={4}>
              <Form.Label>Adresse-Email</Form.Label>
              <Form.Control
                type="email"
                name="email"
                value={updatedCompte?.email || ''}
                readOnly={!editMode}
                onChange={handleChange}
              />
            </Col>
            <Col sm={4}>
              <Form.Label>Nom </Form.Label>
              <Form.Control
                type="text"
                name="nom"
                value={updatedCompte?.nom || ''}
                readOnly={!editMode}
                onChange={handleChange}     
              />
            </Col>
            <Col sm={4}>
              <Form.Label>Prénom</Form.Label>
              <Form.Control
                type="text"
                name="prenom"
                value={updatedCompte?.prenom || ''}
                readOnly={!editMode}
                onChange={handleChange}      
              />
            </Col>
          </Row>
          <Row className="mb-3">
            {!editMode &&
              <Col sm={4}>
                <Form.Label>Role</Form.Label>
                <Form.Control
                  type="text"
                  name="role"
                  readOnly
                  value={updatedCompte?.role || ''}
                />
              </Col>
            }
            {editMode &&
              <Col>
                <Form.Label>Rôle</Form.Label>
                <Col sm="10">
                  <Form.Select
                    name="role"
                    value={updatedCompte?.role || ''}
                    onChange={handleChange}
                  >
                    <option value="">Sélectionner un rôle</option>
                    <option value="ADMIN">Admin</option>
                    <option value="RESPONSABLE_TECHNIQUE">Responsable technique</option>
                    <option value="CHEF_PROJET">Chef de projet</option>
                    <option value="TECHNICIEN">Technicien</option>
                  </Form.Select>           
                </Col>
              </Col>
            }
          </Row>
          {editMode &&
            <div className="mb-3 mt-4">
              <h5>Modification de mot de passe</h5>
              <Row className="mb-3 mt-4">
                <Col>
                  <Form.Label>Nouveau mot de passe</Form.Label>
                </Col>
                <Col sm="10">
                  <Form.Control 
                    type="password"
                    name='password'
                    value={updatedCompte?.password || ''}
                    onChange={handleChange}   
                    style={{ width: '30%' }}
                  />
                </Col>
              </Row>
              <Row className="mb-3 ">
                <Col>
                  <Form.Label>Confirmation de nouveau mot de passe</Form.Label>
                </Col>
                <Col sm="10">
                  <Form.Control 
                    type="password"
                    value={passwordConfirmation}
                    onChange={(e)=>setPasswordConfirmation(e.target.value)}
                    style={{ width: '30%' }}
                  />
                </Col>
              </Row>
            </div>
          }
        </Form>
        <div>
          <ButtonGroup className="mt-3">
            <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} className="me-2" onClick={()=> setEditMode(!editMode)}>
              {editMode ? "Annuler" : "Modifier"}
            </Button>
            {editMode && (
              <Button className="me-2" style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={handleUpdate}>
                Confirmer la modification
              </Button>
            )}
          </ButtonGroup>
        </div>
      </Container>
    )
}
export default CompteDetails;
