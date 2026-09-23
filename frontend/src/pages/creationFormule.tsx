import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Row, Col, Button, Dropdown, Form, Table,FormControl,ButtonGroup, Modal, Container } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSave, faTimes, faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { faCheckCircle } from '@fortawesome/free-solid-svg-icons';

const IngredientToggle = React.forwardRef(({ children, onClick }, ref) => (
  <a href="" ref={ref} onClick={(e) => {
    e.preventDefault();
    onClick(e);
  }}>{children} &#x25bc;</a>
));

const IngredientMenu = React.forwardRef(
  ({ children, style, className, 'aria-labelledby': labeledBy, loadMoreIngredients }, ref) => {
    const [filter, setFilter] = useState('');

    const handleScroll = (e) => {
      const bottom = e.target.scrollHeight - e.target.scrollTop === e.target.clientHeight;
      if (bottom) loadMoreIngredients();
    };

    const filteredChildren = React.Children.toArray(children).filter(
      child => !filter || child.props.children.toLowerCase().includes(filter.toLowerCase())
    );

    return (
      <div ref={ref} style={{ ...style, maxHeight: '300px', overflowY: 'auto' }} className={className} aria-labelledby={labeledBy} onScroll={handleScroll}>
        <FormControl
          autoFocus
          className="mx-3 my-2 w-auto"
          placeholder="Rechercher..."
          onChange={(e) => setFilter(e.target.value)}
          value={filter}
        />
        <ul className="list-unstyled">
        {filteredChildren.map((child, index) => (
  <li key={child.props.id}>{child}</li>
))}
        </ul>
      </div>
    );
  },
);
interface Phase{
  id:number;
  dateDebut:string;
  dateFin:string;
  statut:string;
  fromFabrication:boolean;
  fromTest:boolean;
}

interface Ingredient {
  id: number;
  nomCommercial: string;
  unite: string;
  inci:string;
  fonction:string;
  casN:string;
  statut:string;

}

interface FormuleIngredient {
  id: number;
  quantite: number;
  ingredient: Ingredient;
  formuleId?: number; 
}

interface Projet {
  id: number;
  nom: string;
}

interface Formule {
  id: number;
  dateCreation: string;
  marqueInspireDe: string;
  produitInspireDe:string;
  statut: string;
  formuleIngredients: FormuleIngredient[];
  projet: Projet;
  phases:Phase[];
  phaseFabrication:Phase;
  phaseTest:Phase;
}

const CreateFormule = () => {
  const navigate=useNavigate();
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(1);
  const { id } = useParams();
  const [project, setProject] = useState(null);
  const [ingredients, setIngredients] = useState([]);
  const [selectedIngredient, setSelectedIngredient] = useState(null);
  const [quantite, setQuantite] = useState('');
  const chefId=localStorage.getItem('chefId');

  const [formule, setFormule] = useState({
    dateCreation: new Date().toISOString().slice(0, 10),
    marqueInspireDe: "",
    produitInspireDe:"",
    statut: "A_VENIR",
    quantite: '',
    projet: null,
    formuleIngredients: [],
    phases:null,
    phaseFabrication:null,
    phaseTest:null
  });
  const[showSuccessModal,setShowSuccessModal]=useState(false);
  const role = localStorage.getItem('role');

  const loadIngredients = async () => {
    if (loading) return;
    setLoading(true);
    try {
      const response = await axios.get(`http://localhost:8080/ingredients?page=${page}&size=10`);
      setIngredients(prev => [...prev, ...response.data]);
      setPage(prev => prev + 1);
    } catch (error) {
      console.error('Erreur de récupération des ingrédients', error);
    } finally {
      setLoading(false);
    }
  };
  useEffect(() => {
    
    axios.get(`http://localhost:8080/projets/${id}`)
      .then(response => {
        setProject(response.data);
        setFormule(f => ({ ...f, projet: response.data }));
      })
      .catch(error => console.error('Erreur lors de la récupération du projet', error));

    axios.get('http://localhost:8080/ingredients')
      .then(response => setIngredients(response.data))
      .catch(error => console.error('Erreur de récupération des ingrédients', error));
      loadIngredients();
  }, [id]);

  const addOrUpdateIngredient = () => {
    if (!selectedIngredient || !quantite) {
      alert("Veuillez sélectionner un ingrédient et sa quantité.");
      return;
    }
    const existingIndex = formule.formuleIngredients.findIndex(fi => fi.ingredient.id === selectedIngredient.id);
    if (existingIndex!==-1) {
const updateFormuleIngredients=formule.formuleIngredients.map((fi,index)=>{
if(index===existingIndex){
  return{...fi,quantite:parseFloat(quantite)};
}
return fi;
}
);
setFormule({ ...formule, formuleIngredients: updateFormuleIngredients });   
    }
    else{ //ajout ingredient
    const newIngredient = { ingredient: selectedIngredient, quantite: parseFloat(quantite) };
    setFormule(currentFormule => ({
      ...currentFormule,
      formuleIngredients: [...currentFormule.formuleIngredients, newIngredient]
    }));
    setSelectedIngredient(null);
    setQuantite('');
  };
  }

  const handleInputChange = (name, value) => {
    setFormule(currentFormule => ({ ...currentFormule, [name]: value }));
  };

  const handleSubmit = () => {
    if (!formule.marqueInspireDe || !formule.produitInspireDe) {
      alert("Veuillez remplir tous les champs requis.");
      return;
    }
    axios.post('http://localhost:8080/formules/create', formule)
      .then(response =>  {
        setShowSuccessModal(true);
      setTimeout(()=>navigate(`/formules/${response.data.id}`),2000); 
  })

      .catch(error => console.error('Échec de l enregistrement de la formule', error));
  };

  const handleSupprimerIngredient=(ingredientId:number)=>{
    const updatedFormuleIngredients=formule.formuleIngredients.filter(fi => fi.ingredient.id !== ingredientId)
    setFormule({...formule, formuleIngredients:updatedFormuleIngredients});
  };

  return (
    <Container >
      <div className="mt-4">
          <h2>Création de nouvelle formule</h2>

          </div>
        
      <hr />
    <Form className='mt-4'>
      <Row>
        <Col className="px-4">
          <Form.Label>Date de création:</Form.Label>
        </Col>
        <Col sm="10">
          <Form.Control type="date" readOnly value={formule.dateCreation} />
        </Col>
      </Row>
      <br />
      <Row>
        <Col className="px-4">
          <Form.Label>Marques inspirées de :</Form.Label>
        </Col>
        <Col sm="10">
          <Form.Control type="text"
            value={formule.marqueInspireDe}
            placeholder='marque x, marque y, ...'
            onChange={(e) => handleInputChange('marqueInspireDe', e.target.value)}
            required
          />
        </Col>
      </Row>
      <br />
      <Row>
        <Col className="px-4">
          <Form.Label>Produit inspiré de :</Form.Label>
        </Col>
        <Col sm="10">
          <Form.Control type="text"
            value={formule.produitInspireDe}
            placeholder='produit x'
            onChange={(e) => handleInputChange('produitInspireDe', e.target.value)}
            required
          />
        </Col>
      </Row>
      <br /><br />
      <Row className='mb-4'> 
            <h5>
            Liste des ingrédients pour la formule: 
            </h5>
         </Row>

      <Row className="align-items-center">
      
      <Col sm={4}>
          <Dropdown>
            <Dropdown.Toggle as={IngredientToggle} id="dropdown-custom-components">
              {selectedIngredient ? selectedIngredient.nomCommercial : "Ingrédient"}
            </Dropdown.Toggle>
            <Dropdown.Menu as={IngredientMenu} loadMoreIngredients={loadIngredients}>
              {ingredients.map((ingredient) => (
                <Dropdown.Item key={ingredient.id} onClick={() => setSelectedIngredient(ingredient)}>
                  {ingredient.nomCommercial}
                </Dropdown.Item>
              ))}
            </Dropdown.Menu>
          </Dropdown>
        </Col>
        <Col sm={1} className="px-1">
          <Form.Label className="me-2">Quantité :</Form.Label>
        </Col>
        <Col sm={2} className="px-1">
          <Form.Control type="number" placeholder="Quantité" value={quantite} onChange={(e) => setQuantite(e.target.value)} />
        </Col>
        <Col sm={1}>
          <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={addOrUpdateIngredient}>Ajouter/Modifier</Button>
        </Col>
      </Row>
      <br />
      <Table striped bordered hover size="sm">
  <thead>
    <tr>
      <th>#</th>
      <th>Nom de l'ingrédient</th>
      <th>Quantité</th>
      <th>INCI</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
    {formule.formuleIngredients.map((fi, index) => (
      <tr key={index}>
        <td>{index + 1}</td>
        <td>{fi.ingredient.nomCommercial}</td>
        <td>{fi.quantite}</td>
        <td>{fi.ingredient.inci }</td>
        <td><ButtonGroup>
                      <Button variant="outline-danger" onClick={() => handleSupprimerIngredient(fi.ingredient.id) }><FontAwesomeIcon icon={faTrash} /></Button>
                    </ButtonGroup>
 </td>
      </tr>
    ))}
  </tbody>
</Table>

      <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={handleSubmit} variant="primary">Enregistrer</Button>
      <Modal
        show={showSuccessModal}
        onHide={()=>setShowSuccessModal(false)}
        centered
        size='sm'
        aria-labelledby="contained-modal-title-vcenter"
        >      <Modal.Header closeButton style={{backgroundColor:"#f8f9fa",borderColor:"#e9ecef"}}>
<Modal.Title id="contained-modal-title-vcenter" style={{color:'#198754',fontWeight:'bold'}}>
<FontAwesomeIcon icon={faCheckCircle} className='me-2'/>
Formule créé
</Modal.Title>
</Modal.Header>
<Modal.Body style={{backgroundColor:'#f8f9fa'}}>
  <p style={{color:'#20c997'}}>
    <FontAwesomeIcon icon={faCheckCircle} className='me-2'/>
    Votre formule a été crée avec succès!
  </p>
</Modal.Body>
        </Modal>
    </Form>
    </Container>
  );
};

export default CreateFormule;
