import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { Container, Row, Col, Form, Button, Table, Dropdown, ButtonGroup, FormControl, FormGroup, Modal, Card } from 'react-bootstrap';
import PillExample from '../components/statusColor';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';

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
  chefProjetId:number;
  
}

interface Phase{
  id:number;
  dateDebut:string;
  dateFin:string;
  statut:string;
  fromFabrication:boolean;
  fromTest:boolean;
  formule:Formule;
}


interface Formule {
  id: number;
  dateCreation: string;
  marqueInspireDe: string;
  produitInspireDe:string;
  statut: string;
  /*phaseFabrication: string;
  phaseTest: string;*/
  formuleIngredients: FormuleIngredient[];
  projet: Projet;
  phases:Phase[];
  phaseFabrication:Phase;
  phaseTest:Phase;
}



const DetailsFormule = () => {
  const [formule, setFormule] = useState<Formule | null>(null);
  const [editMode, setEditMode] = useState(false);
  const [ingredients, setIngredients] = useState<Ingredient[]>([]);
  const [newIngredient, setNewIngredient] = useState<{ id: number; quantite: number }>({ id: 0, quantite: 0 });
  const { id } = useParams<{ id: string }>();
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(1);
  const [selectedIngredient, setSelectedIngredient] = useState(null);
  const [quantite, setQuantite] = useState('');
  const [beginMode,setBeginMode]=useState(false);
  const [beginFab,setBeginFab]=useState(false);
  const [askBeginTest,setAskBeginTest]=useState(false);
  const [beginTest,setBeginTest]=useState(false);
  // Récupérer l'index du sessionStorage
  const formuleIndex = sessionStorage.getItem('currentFormuleIndex');
  const [projet,setProjet]=useState<Projet|null>();
  const navigate=useNavigate();
  const[showConfirmModal,setShowConfirmModal]=useState(false);
  const[showSuccessSuppModal,setShowSuccessSuppModal]=useState(false);
  const[showResultTest,setShowResultTest]=useState(false);
const[localFormuleIngredients,setLocalFormuleIngredinents]=useState([]);
//const {idF,mode}=useParams<{idF:string, mode?:string}>();

const [aspect, setAspect] = useState('');
    const [couleur, setCouleur] = useState('');
    const [odeur, setOdeur] = useState('');
    const [ph, setPH] = useState('');
    const [densite, setDensite] = useState('');
    const [viscosite, setViscosite] = useState('');
    const[stabilitéCentri,setStabilitéCentri]=useState('');
    const[stabilitéThermique,setStabilitéThermique]=useState('');
    const [normesAerobies, setNormesAerobies] = useState('');
    const [resultatsAerobies, setResultatsAerobies] = useState('');
    const [normesLevures, setNormesLevures] = useState('');
    const [resultatsLevures, setResultatsLevures] = useState('');
    const [normesPseudomonas, setNormesPseudomonas] = useState('');
    const [resultatsPseudomonas, setResultatsPseudomonas] = useState('');
    const [normesStaphylococcus, setNormesStaphylococcus] = useState('');
    const [resultatsStaphylococcus, setResultatsStaphylococcus] = useState('');
    const [normesEColi, setNormesEColi] = useState('');
    const [resultatsEColi, setResultatsEColi] = useState('');
    const [normesCandida, setNormesCandida] = useState('');
    const [resultatsCandida, setResultatsCandida] = useState('');
    const role = localStorage.getItem('role');
    const chefId=localStorage.getItem('chefId');


useEffect(() => {
  
    const fetchData = async () => {
      try {
        const formuleResponse = await axios.get(`http://localhost:8080/formules/${id}`);
        setFormule(formuleResponse.data);
       
        if (window.location.href.includes('/edit')) {
          setEditMode(true);
        }

        if(formule){
          setLocalFormuleIngredinents(formule.formuleIngredients);
        }
            const ingredientsResponse = await axios.get('http://localhost:8080/ingredients');
            setIngredients(ingredientsResponse.data);

        if(formuleResponse.data  && formuleResponse.data.statut=="EN_COURS" ){
          setBeginFab(true)
    }
    if(formuleResponse.data && formuleResponse.data.statut=="EN_COURS" && formuleResponse.data.phaseFabrication.statut=="TERMINE"){
          setAskBeginTest(true);
          setBeginFab(false);
    }
    if(formuleResponse.data && formuleResponse.data.statut=="EN_COURS" && formuleResponse.data.phaseTest.statut=="EN_COURS"){
      setAskBeginTest(false);
      setBeginTest(true);
      setShowResultTest(true);
      
}
if (formuleResponse.data && (formuleResponse.data.phaseTest.statut === "TERMINE" || formuleResponse.data.phaseTest.statut === "REJETE")) {
  setShowResultTest(true);
}

if(formuleResponse.data&& formuleResponse.data.phases&&formuleResponse.data.phaseTest&&formuleResponse.data.phaseTest.description){
  const testResult=JSON.parse(formuleResponse.data.phaseTest.description);
  if (testResult) {
    setAspect(testResult.aspect || '');
    setCouleur(testResult.couleur || '');
    setOdeur(testResult.odeur || '');
    setPH(testResult.ph || '');
    setDensite(testResult.densite || '');
    setViscosite(testResult.viscosite || '');
    setStabilitéCentri(testResult.stabilitéCentri || '');
    setStabilitéThermique(testResult.stabilitéThermique || '');
}
  
  if (testResult.microbiologie) {
    if (testResult.microbiologie.aerobies) {
        setNormesAerobies(testResult.microbiologie.aerobies.normes || '');
        setResultatsAerobies(testResult.microbiologie.aerobies.resultats || '');
    }
    if (testResult.microbiologie.levures) {
        setNormesLevures(testResult.microbiologie.levures.normes || '');
        setResultatsLevures(testResult.microbiologie.levures.resultats || '');
    }
    if (testResult.microbiologie.pathogenes) {
        if (testResult.microbiologie.pathogenes.pseudomonasAeruginosa) {
            setNormesPseudomonas(testResult.microbiologie.pathogenes.pseudomonasAeruginosa.normes || '');
            setResultatsPseudomonas(testResult.microbiologie.pathogenes.pseudomonasAeruginosa.resultats || '');
        }
        if (testResult.microbiologie.pathogenes.staphylococcusAureus) {
            setNormesStaphylococcus(testResult.microbiologie.pathogenes.staphylococcusAureus.normes || '');
            setResultatsStaphylococcus(testResult.microbiologie.pathogenes.staphylococcusAureus.resultats || '');
        }
        if (testResult.microbiologie.pathogenes.escherichiaColi) {
            setNormesEColi(testResult.microbiologie.pathogenes.escherichiaColi.normes || '');
            setResultatsEColi(testResult.microbiologie.pathogenes.escherichiaColi.resultats || '');
        }
        if (testResult.microbiologie.pathogenes.candidaAlbicans) {
            setNormesCandida(testResult.microbiologie.pathogenes.candidaAlbicans.normes || '');
            setResultatsCandida(testResult.microbiologie.pathogenes.candidaAlbicans.resultats || '');
        }
    }
}

}




  } catch (error) {
    console.error('Failed to fetch data:', error);
  }
};

fetchData();

}, [id,window.location.href]);


const canManageFormules = () => {
    
  const role = localStorage.getItem('role');
  const isChef = formule?.projet.chefProjetId && chefId && parseInt(chefId) === formule?.projet.chefProjetId;
  const isAdmin = role === "ADMIN";
  return isChef || isAdmin;
};

const deleteFormule = () => {
  axios.delete("http://localhost:8080/formules/delete/"+id)
  .then(response=>{
    axios.put("http://localhost:8080/projets/"+id+"/statut/change/");
   console.log("formule supprimée avec succès",response)
   setShowConfirmModal(false);
   setShowSuccessSuppModal(true);
   setTimeout(()=>{
     setShowSuccessSuppModal(false);
     navigate(`/projets/${formule?.projet.id}/formules`)},3000); 

 })
 
  .catch(error=>{console.error("erreur en suppression");
 })
 };
const toggleEdit=()=>{
  setEditMode(!editMode);
  if (editMode && formule){
    saveChanges();
  }
};

const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  const { name, value } = e.target;
  setFormule((prev) => ({
    ...(prev as Formule),
    [name]: value,
  }));
};

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

const handleAddIngredient = () => {
  if (!selectedIngredient || !quantite) {
    alert("Veuillez sélectionner un ingrédient et sa quantité.");
    return;
  }
  const isAlreadyAdded = formule?.formuleIngredients.some(ingredient => ingredient.ingredient.id === selectedIngredient.id);
  if (isAlreadyAdded) {
      alert("Cet ingrédient a déjà été ajouté.");
      return;
  }
  const newIngredient = { ingredient: selectedIngredient, quantite: parseFloat(quantite) , isNew:true //flag
  };
  setFormule(currentFormule => ({
    ...currentFormule as Formule,
    formuleIngredients: [...currentFormule?.formuleIngredients, newIngredient]
  }));
  setSelectedIngredient(null);
  setQuantite('');
};

const handleDeleteIngredient = (ingredientId,isTemporary) => {
  if(isTemporary){ //inNew est true
    setFormule(prev=>({
      ...prev,
      formuleIngredients:prev?.formuleIngredients.filter(ing=>ing.id!==ingredientId)
    }));}

else{
  axios.delete(`http://localhost:8080/formuleIngredients/delete/${ingredientId}`)
    .then(() => {
      setFormule(prev => ({
        ...prev as Formule,
        formuleIngredients: prev.formuleIngredients.filter(fi => fi.id !== ingredientId)
      }));
    })
    .catch(error => console.error('Failed to delete ingredient:', error));
};
  }
const handleChangeIngredientQuantity = (index, event) => {
    const value =event.target.value;
    setFormule(prev => {
      const updatedFormuleIngredients = prev?.formuleIngredients.map((fi, idx) => {
        if (idx === index) {
          return { ...fi, quantite: value };
        }
        return fi;
      });
      return { ...prev, formuleIngredients: updatedFormuleIngredients };
    });
  };

const saveChanges = () => {
   if (!formule) return;
   const updateFormule={
    ...formule,
    formuleIngredientId:formule.formuleIngredients.map(fi=>({
      ...fi,
      ingredientId:fi.ingredient.id,
      quantite:fi.quantite
    }))
    };
    axios.put(`http://localhost:8080/formules/update/${formule.id}`,updateFormule)
    .then(response=>{
      setFormule(response.data);
      setEditMode(false);
    })
    .catch(error=>{console.error("erreur en sauvegarder les modifications:", error);
  });
};


const onChangeProjectStatus=()=>{
  axios.put(`http://localhost:8080/projets/${formule?.projet.id}/statut/change`)
  .then(response=>{
    setProjet(response.data)
    console.log(response.data) 
    })
  }

  const onCompleteFabPhase=()=>{
    axios.put("http://localhost:8080/phases/terminer/fabrication/"+formule?.phaseFabrication.id)
    .then(response=>{
      const updatedFormule = {
      ...formule,
      phaseFabrication: response.data
    };
    setFormule(updatedFormule);
    setBeginFab(false);
    setAskBeginTest(true);
  }
    )
    }
  

const onCompleteTestPhase=(statut)=>{
  console.log(statut);
  axios.put("http://localhost:8080/phases/update/statut/"+formule?.phaseTest.id+"/"+statut)
  .then(response=>{
    const updatedFormule = {
      ...formule,
      statut:  statut== "TERMINE"?"TERMINE":"REJETE",
      phaseTest: response.data
    };
    setFormule(updatedFormule);
    setBeginTest(false);
    onChangeProjectStatus();
    setShowResultTest(true);
  })
}

const onBeginFabPhase=()=>{
  setBeginMode(true);
  const fabricationPhase={
    dateDebut:new Date().toISOString().slice(0, 10),
    statut:"EN_COURS",
    fromFabrication:true,
    fromTest:false,
    formule: formule
  };
  axios.post("http://localhost:8080/phases/create/fabrication/"+formule?.id,fabricationPhase)
  .then(response=>{
    const updatedFormule = {
      ...formule,
      statut:"EN_COURS",
      phaseTest: response.data
    };
    setFormule(updatedFormule);
    setBeginFab(true);
    // onChangeProjectStatus();
  })
}


const onBeginTestPhase=()=>{
  setAskBeginTest(false);
  const testPhase={
    dateDebut:new Date().toISOString().slice(0, 10),
    statut:"EN_COURS",
    fromFabrication:false,
    fromTest:true,
    formule:formule
  };
  axios.post("http://localhost:8080/phases/create/test/"+formule?.id,testPhase)
  .then(response=>{
    const updatedFormule = {
      ...formule,
      phaseTest: response.data
    };
    setFormule(updatedFormule);
    setBeginTest(true);
  })
}

const handleFormSubmit = async () => {
  const descriptionData = {
    aspect,
    couleur,
    odeur,
    ph,
    densite,
    viscosite,
    stabilitéCentri,
    stabilitéThermique,
    microbiologie: {
        aerobies: {
            normes: normesAerobies,
            resultats: resultatsAerobies
        },
        levures: {
            normes: normesLevures,
            resultats: resultatsLevures
        },
        pathogenes: {
            pseudomonasAeruginosa: {
                normes: normesPseudomonas,
                resultats: resultatsPseudomonas
            },
            staphylococcusAureus: {
                normes: normesStaphylococcus,
                resultats: resultatsStaphylococcus
            },
            escherichiaColi: {
                normes: normesEColi,
                resultats: resultatsEColi
            },
            candidaAlbicans: {
                normes: normesCandida,
                resultats: resultatsCandida
            }
        }
    }
};

  const descriptionString = JSON.stringify(descriptionData);

  try {
      await axios.put(`http://localhost:8080/phases/${formule?.phaseTest.id}/description`, descriptionString, {
          headers: { 'Content-Type': 'application/json' }
      });
      alert('Résultats des tests enregistrés avec succès!');
  } catch (error) {
      console.error('Erreur lors de l\'envoi des données:', error);
      alert('Erreur lors de l\'enregistrement des résultats des tests.');
  }
}


return (
  <Container>
    <Row className="d-flex justify-content-between mt-4">
<Col>
<h2>Formule {formule?.formuleIndex}</h2>
</Col>
<Col style={{display:'flex',justifyContent:'flex-end'}}>
<h2><PillExample name={formule?.statut}/></h2> 
</Col>
 </Row>
 <hr />
 <Row>
  <Col>
  {formule && (
    <Form>
      <Form.Group as={Row} className="mb-3">
      <Form.Label column sm="2">Date de création</Form.Label>
      <Col sm="10">
      <Form.Control  
      type="date"
      readOnly
      value={formule.dateCreation}
      />
      </Col>
    
      </Form.Group>

      <Form.Group as={Row} className="mb-3">
      <Form.Label column sm="2">Projet</Form.Label>
      <Col>
      <Form.Control  
      type="text"
      readOnly
      value={formule.projet.nom}
      />
      </Col>
      </Form.Group>

      <Form.Group as={Row} className="mb-3">
      <Form.Label column sm="2">Marques inspirées de</Form.Label>
      <Col>
      <Form.Control  
      type="text"
      name='marqueInspireDe'
      value={formule.marqueInspireDe}
      readOnly={!editMode}
      onChange={handleChange}
      />
      </Col>
      </Form.Group>

      <Form.Group as={Row} className="mb-3">
      <Form.Label column sm="2">Produits inspirés de</Form.Label>
      <Col>
      <Form.Control  
      type="text"
      name='produitInspireDe'
      value={formule.produitInspireDe}
      readOnly={!editMode}
      onChange={handleChange}
      />
      </Col>
      </Form.Group>
      <br />
      {canManageFormules()&&!editMode && (
              <>
             {!beginMode && formule && formule.statut==="A_VENIR"  && <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} className='mb-4' onClick={()=>onBeginFabPhase()}>Commencer</Button>}
            
             {beginFab&& formule && formule.statut==="EN_COURS" &&
              <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} className='mb-4' onClick={()=>onCompleteFabPhase()}>Terminer Fabrication</Button>
             }

             {askBeginTest && formule && formule.statut==="EN_COURS" &&
              <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} className='mb-4' onClick={()=>{onBeginTestPhase();setShowResultTest(true)}}>commencer test</Button>
            }
            {beginTest && formule && formule.statut==="EN_COURS" &&
            < ButtonGroup className='mb-4'>
              <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={()=> onCompleteTestPhase("REJETE")}>rejeter</Button>
              <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={()=> onCompleteTestPhase("TERMINE")}>terminer</Button>
            </ButtonGroup>
            }
            </>
              )
          }
      
<br />
{showResultTest &&
<FormGroup>
  <Container fluid style={{padding :"20px"}}>
    <Card>
<Card.Header style={{backgroundColor:"#e9ecef"}}>
<h5>1. Bulletin d'analyse physico-chimique </h5>
</Card.Header>
<Card.Body>
<Form>
  <Row>
    <Col>
<Form.Label>Aspect</Form.Label> 
<Form.Control 
size='sm'
type='text'
value={aspect}
onChange={(e) => setAspect(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col>
<Form.Label>Couleur</Form.Label> 
<Form.Control 
size='sm'
type='text'
value={couleur}
onChange={(e) => setCouleur(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col>
<Form.Label>Odeur</Form.Label> 
<Form.Control 
size='sm'
type='text'
value={odeur}
onChange={(e) => setOdeur(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
</Row>
<Row>
<Col>
<Form.Label>PH</Form.Label> 
<Form.Control 
size='sm'
type='text'
value={ph}
onChange={(e) => setPH(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col>
<Form.Label>Densité</Form.Label> 
<Form.Control 
size='sm'
type='text'
value={densite}
onChange={(e) => setDensite(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col>
<Form.Label>Viscosité</Form.Label> 
<Form.Control 
size='sm'
type='text'
value={viscosite}
onChange={(e) => setViscosite(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
</Row>
<Row>
<Col>
<Form.Label>Stabilité à la centrifugeuse</Form.Label> 
<Form.Control 
size='sm'
type='text'
value={stabilitéCentri}
onChange={(e) => setStabilitéCentri(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col>
<Form.Label>Stabilité thermique</Form.Label> 
<Form.Control 
size='sm'
type='text'
value={stabilitéThermique}
onChange={(e) => setStabilitéThermique(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
</Row>
</Form>
</Card.Body>
</Card>

<br />

<Card>
  <Card.Header style={{backgroundColor:"#e9ecef"}}>
    <h5>2. Bulletin d'analyse microbiologique</h5>
</Card.Header>
<Card.Body>
<Form>
  <Row className='mb-3'>
    <Col>
<h6><u>Spécification: Dénombrement des germes aérobies mésophiles totaux</u></h6>
<Row>
  <Col xs={6}>
<Form.Label>Normes</Form.Label>
<Form.Control
size='sm'
type='text'
value={normesAerobies}
onChange={(e) => setNormesAerobies(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col xs={6}>
<Form.Label>Résultats</Form.Label>
<Form.Control
size='sm'
type='text'
value={resultatsAerobies}
onChange={(e) => setResultatsAerobies(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
</Row>
</Col>
</Row>

<Row className='mb-3'>
  <Col>
<h6><u>Spécification: Dénombrement des levures et moisissures</u></h6>

<Row>
  <Col xs={6}>
<Form.Label>Normes</Form.Label>
<Form.Control
size='sm'
type='text'
value={normesLevures}
onChange={(e) => setNormesLevures(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col xs={6}>
<Form.Label>Résultats</Form.Label>
<Form.Control
size='sm'
type='text'
value={resultatsLevures}
onChange={(e) => setResultatsLevures(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
</Row>
</Col>
</Row>
<h6><u>Spécification: Détection des germes pathogènes</u></h6>
<Row>
  <Col>
<h6>Pseudomonas aeruginosa</h6>
<Row>
  <Col xs={6}>
<Form.Label>Normes</Form.Label>
<Form.Control
size='sm'
type='text'
value={normesPseudomonas}
onChange={(e) => setNormesPseudomonas(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col xs={6}>
<Form.Label>Résultats</Form.Label>
<Form.Control
size='sm'
type='text'
value={resultatsPseudomonas}
onChange={(e) => setResultatsPseudomonas(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
</Row>
</Col>
</Row>

<Row>
  <Col>
  <br />
<h6>Staphylococcus aureus</h6>
<Row>
  <Col xs={6}>
<Form.Label>Normes</Form.Label>
<Form.Control
size='sm'
type='text'
value={normesStaphylococcus}
onChange={(e) => setNormesStaphylococcus(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col xs={6}>
<Form.Label>Résultats</Form.Label>
<Form.Control
size='sm'
type='text'
value={resultatsStaphylococcus}
onChange={(e) => setResultatsStaphylococcus(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
</Row>
</Col>
</Row>

<Row>
  <Col>
  <br />
<h6>Escherichia coli</h6>
<Row>
  <Col xs={6}>
<Form.Label>Normes</Form.Label>
<Form.Control
size='sm'
type='text'
value={normesEColi}
onChange={(e) => setNormesEColi(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col xs={6}>
<Form.Label>Résultats</Form.Label>
<Form.Control
size='sm'
type='text'
value={resultatsEColi}
onChange={(e) => setResultatsEColi(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
</Row>
</Col>
</Row>
<Row>
  <Col>
<br />
<h6>Candida albicans</h6>
<Row>
  <Col xs={6}>
  <Form.Label>Normes</Form.Label>
<Form.Control
size='sm'
type='text'
value={normesCandida}
onChange={(e) => setNormesCandida(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
<Col>
<Form.Label>Résultats</Form.Label>
<Form.Control
size='sm'
type='text'
value={resultatsCandida}
onChange={(e) => setResultatsCandida(e.target.value)}
readOnly={!canManageFormules()}

/>
</Col>
</Row>
</Col>
</Row>
</Form>
</Card.Body>
</Card>
</Container>
<Row className="mt-3">  
<Col className="d-flex justify-content-end">
{canManageFormules() && (
  <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={handleFormSubmit} className="btn-sm">
    Soumettre les Résultats
  </Button>
)}
        </Col>
      </Row>
</FormGroup>
}
<br />

             
             

          <div className='mb-4'>
            <h5>
            Liste des ingrédients pour la formule: 
            </h5>
          </div>
          <Table striped bordered hover>
    <thead>
<tr>
  <th>#</th>
  <th>Nom de l'ingrédient</th>
  <th>Fonction/INCI</th>
  <th>Quantite</th>
  <th>Cas°N</th>
  {editMode && <th>Action</th>}
</tr>
    </thead>
<tbody>
  {formule.formuleIngredients.map(
    (fi,index)=>(
      <tr key={fi.id}>
         <td>{index+1}</td>
         <td>{fi.ingredient.nomCommercial}</td>
         <td>{fi.ingredient.inci}</td>
         
         <td>{
          editMode?(
            <Form.Control
            type="number"
            name="quantite"
            value={fi.quantite}
            onChange={(event) => handleChangeIngredientQuantity(index, event)}
            />
          ):( fi.quantite)
         }</td>

          <td>{fi.ingredient.casN}</td>
          {editMode && (
                        <td>
                          <Button variant="danger" onClick={() => handleDeleteIngredient(fi.id,fi.isNew)}>Supprimer</Button>
                        </td>
                      )}

      </tr>
    )
  )}
</tbody>
  </Table>

{
  editMode &&
  (
<Row className="mt-3">
 
  <Col sm="4">
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
  </Col >
<Col sm="4">
<Form.Control type="number" placeholder="Quantité" value={quantite} onChange={(e) => setQuantite(e.target.value)} />

</Col>

<Col sm="4">
<Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={handleAddIngredient}>Ajouter</Button>
</Col>
</Row>

  )
}
    </Form>

          )}
        </Col>
      </Row>
      <br />
      {canManageFormules()&&formule?.statut=="A_VENIR"  &&
        <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} className='me-2' onClick={toggleEdit}>{editMode ? 'Enregistrer' : 'Modifier'}</Button> 
    }
     
      {canManageFormules()&& !editMode && formule?.statut === "A_VENIR" && (
      
    <Button style={{ fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} className='me-2' variant="danger" onClick={() => setShowConfirmModal(true)}>
        <FontAwesomeIcon icon={faTrash} /> Supprimer Formule
      </Button>
    )}

      <Modal show={showConfirmModal} onHide={() => setShowConfirmModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirmation de suppression</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Êtes-vous sûr de vouloir supprimer cette formule ?
        </Modal.Body>
        <Modal.Footer>
          <Button style={{ fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} variant="secondary" onClick={() => setShowConfirmModal(false)}>Non</Button>
          <Button style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }} onClick={deleteFormule}>Oui, supprimer</Button>
        </Modal.Footer>
      </Modal>

      
      <Modal
  show={showSuccessSuppModal}
  onHide={() => setShowSuccessSuppModal(false)}
  centered
  size='sm'
  aria-labelledby="contained-modal-title-vcenter"
>
<Modal.Header closeButton style={{backgroundColor:"#f8f9fa",borderColor:"#e9ecef"}}>
<Modal.Title id="contained-modal-title-vcenter" style={{color:'#004085',fontWeight:'bold'}}>      <FontAwesomeIcon icon={faTrash} className='me-2' />
      Formule supprimée
    </Modal.Title>
  </Modal.Header>
  <Modal.Body style={{ backgroundColor: '#f8f9fa' }}>
    <p style={{ color: '#0056b3' }}>  
      <FontAwesomeIcon icon={faTrash} className='me-2' />
      La formule a été supprimé avec succès.
    </p>
  </Modal.Body>
</Modal>

    </Container>
  );
};

export default DetailsFormule;

   