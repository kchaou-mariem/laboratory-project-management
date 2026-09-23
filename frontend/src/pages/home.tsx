import axios from "axios";
import React from "react";
import { useEffect, useState } from "react";
import { Button, Card, Col, Container, Dropdown, Form, InputGroup, Pagination, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import PillExample from "../components/statusColor";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlusCircle, faExclamationTriangle, faArrowLeft, faArrowRight } from "@fortawesome/free-solid-svg-icons";
import Footer from "../components/Footer";

interface Projet {
  id: number;
  nom: string;
  statut: string;
  dateDebut: string;
  dateFin: string;
  chefProjetId: number; // Ajouter l'ID du chef de projet
}

interface ChefProjet {
  nom: string;
  prenom: string;
}

function Home() {
  const [projets, setProjets] = useState<Projet[]>([]);
  const [chefProjets, setChefProjets] = useState<{ [key: number]: ChefProjet }>({});
  const navigate = useNavigate();
  const [selectedStatus, setSelectedStatus] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const [projetsPerPage] = useState(12);
  const [search, setSearch] = useState("");
  const role = localStorage.getItem('role');

  const indexOfLastProjet = currentPage * projetsPerPage;
  const indexOfFirstProjet = indexOfLastProjet - projetsPerPage;
  const currentProjets = projets.slice(indexOfFirstProjet, indexOfLastProjet);

  const paginateNext = () => {
    if (currentPage < Math.ceil(projets.length / projetsPerPage)) {
      setCurrentPage(currentPage + 1);
    }
  };

  const paginatePrev = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/projets/search?search=${search}`);
        const filteredProjects = response.data.filter(projet =>
          selectedStatus ? projet.statut === selectedStatus : true
        );
        const sortedProjects = filteredProjects.sort((a, b) => new Date(b.dateDebut) - new Date(a.dateDebut));
        setProjets(sortedProjects);

        // Fetch chefProjet details for each project
        const chefProjetData = {};
        for (const projet of sortedProjects) {
          const chefResponse = await axios.get(`http://localhost:8080/comptes/id/${projet.chefProjetId}`);
          chefProjetData[projet.chefProjetId] = chefResponse.data;
        }
        setChefProjets(chefProjetData);
      } catch (error) {
        console.error("Erreur de récupération de projets", error);
      }
    };

    fetchProjects();
  }, [search, selectedStatus]);

  const handleRCardClick = (id: number) => {
    navigate(`/projets/${id}`);
  };

  const navigateToCreate = () => {
    navigate(`/projets/create`);
  };

  function truncateTitle(title) {
    const words = title.split(' ');
    if (words.length > 2) {
      return words.slice(0, 2).join(' ') + '...';
    }
    return title;
  }

  return (
    <Container>
      <Row className="mb-4">
        <Col xs={6} style={{ paddingRight: '2px' }}>
          <img src="/image.png" alt="Banner" style={{ width: '100%', height: '200px', objectFit: 'cover' }} />
        </Col>
        <Col xs={6} style={{ paddingLeft: '2px', display: 'flex', justifyContent: 'center' }}>
          <img src="/laboo.png" alt="Laboratoires Nihel" style={{ width: '70%', height: '200px', objectFit: 'cover' }} />
        </Col>
      </Row>

      <div className="d-flex justify-content-between align-items-center my-4 mt-4 ">
        <h2 style={{ fontFamily: 'Helvetica', fontSize: '2.15rem', color: '#333', textShadow: '0px 2px 4px rgba(0,0,0,0.1)' }}>Liste des projets</h2>
        {(role === "ADMIN" || role === "RESPONSABLE_TECHNIQUE") &&
          <Button onClick={navigateToCreate} style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' }}>
            <FontAwesomeIcon icon={faPlusCircle} className="me-2" /> Nouveau projet
          </Button>
        }
      </div>

      <InputGroup className="mb-3">
        <Dropdown onSelect={(eventKey: string | null) => setSelectedStatus(eventKey || '')}>
          <Dropdown.Toggle variant="outline-secondary">
            {selectedStatus || 'Filtrer par statut'}
          </Dropdown.Toggle>
          <Dropdown.Menu>
            <Dropdown.Item eventKey="">Tous</Dropdown.Item>
            <Dropdown.Item eventKey="A_VENIR">À venir</Dropdown.Item>
            <Dropdown.Item eventKey="EN_COURS">En cours</Dropdown.Item>
            <Dropdown.Item eventKey="TERMINE">Terminé</Dropdown.Item>
            <Dropdown.Item eventKey="VALIDE">Validé</Dropdown.Item>
            <Dropdown.Item eventKey="EN_ATTENTE">En attente</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
        <Form.Control
          placeholder="Rechercher par nom..."
          onChange={e => setSearch(e.target.value)}
        />
      </InputGroup>

      <hr />

      <Row xs={1} md={3} lg={4} className="g-4">
        {currentProjets.map(projet => (
          <Col key={projet.id}>
            <Card className="card-custom" onClick={() => handleRCardClick(projet.id)} style={{ cursor: 'pointer' }}>
              <Card.Body>
                <Card.Title className="card-title-custom">
                  {truncateTitle(projet.nom)}
                  {(() => {
                    const today = new Date();
                    const endDate = new Date(projet.dateFin);
                    return endDate < today && projet.statut !== "VALIDE" && (
                      <FontAwesomeIcon icon={faExclamationTriangle} color="red" className="ms-2" />
                    );
                  })()}
                </Card.Title>
                <Card.Text>
                  Chef de projet: {chefProjets[projet.chefProjetId]?.nom} {chefProjets[projet.chefProjetId]?.prenom}
                </Card.Text>
                <Card.Text>
                  Début: {projet.dateDebut}
                </Card.Text>
                <Card.Text>
                  Fin: {projet.dateFin}
                </Card.Text>
                <Card.Footer className="bg-white text-dark">
                  <PillExample name={projet.statut} />
                </Card.Footer>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>

      <Pagination className="justify-content-center my-4">
        <Pagination.Prev onClick={paginatePrev}>
          <FontAwesomeIcon icon={faArrowLeft} />
        </Pagination.Prev>
        <Pagination.Next onClick={paginateNext}>
          <FontAwesomeIcon icon={faArrowRight} />
        </Pagination.Next>
      </Pagination>

      <Footer />
    </Container>
  );
}

export default Home;
