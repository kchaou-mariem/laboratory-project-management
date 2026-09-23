import React,{useEffect, useState } from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import {useNavigate} from 'react-router-dom';
import PillExample from '../components/statusColor';
import { Col, Container, Row } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronLeft, faChevronRight } from '@fortawesome/free-solid-svg-icons';

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
  interface Projet {
    id: number;
    nom: string;
    statut: string;
}
 
interface Formule {
    id: number;
    marqueInspireDe: string;
    statut: string;
    phaseFabrication: string;
    phaseStatut:string;   
    formuleIngredients: FormuleIngredient[];
    projet: Projet;

}

function Formules() {
  const [formules, setFormules] = useState<Formule[]>([]);
  const navigate = useNavigate();  
  const [page, setPage] = useState(1);
  const itemsPerPage = 15;
  const startIndex = (page - 1) * itemsPerPage;
  const displayedFormules = formules.slice(startIndex, startIndex + itemsPerPage);
  const isFirstPage = page === 1;
  const isLastPage = startIndex + itemsPerPage >= formules.length;
  const role = localStorage.getItem('role');
  const chefId=localStorage.getItem('chefId');

  useEffect(() => {
    
    axios.get('http://localhost:8080/formules')
      .then(response => {
        setFormules(response.data);
      })
      .catch(error => console.error('Erreur de récupération de formules', error));
  }, []);
  const handleRowClick = (formuleId: number) => {
    navigate(`/formules/${formuleId}`);
};
const makeFormulation = (formuleIngredients: FormuleIngredient[]) => {
    return formuleIngredients.map(ing => `${ing.quantite} ${ing.ingredient.nomCommercial}`).join(' + ');
  };

  return (
<Container>     
   <div  className='d-flex justify-content-between  my-4' >
   <h2 style={{ fontFamily:'Helvetica', fontSize: '2.15rem', color: '#333', textShadow: '0px 2px 4px rgba(0,0,0,0.1)' }}>Liste des formules</h2>
      </div>
      <hr />

      <Table striped bordered hover responsive>
        <thead className="bg-primary text-white">
          <tr>
            <th>Numero</th>
            <th>Formule</th>
            <th>Statut</th>
            <th>Projet</th>
          </tr>
        </thead>
        <tbody>
        {
           displayedFormules.map((formule,index) =>(
            <tr key={formule.id} onClick={() => handleRowClick(formule.id)} style={{ cursor: 'pointer' }}> 
            <td>{index+1}</td>
              <td>{makeFormulation(formule.formuleIngredients)}</td>
              <td><PillExample name={formule.statut}/></td>
              <td>    <span style={{ marginRight: '20px' }}>{formule.projet.nom}</span>
                      <PillExample name={formule.projet.statut}/> 
              </td>
            </tr>
          ))
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

</Container>  );
}

export default Formules;
