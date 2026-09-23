import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Container, Nav, Navbar, Image } from "react-bootstrap";
import React, { useEffect, useState } from 'react';
import { faFlask, faHome, faSeedling, faSignOutAlt, faUser } from "@fortawesome/free-solid-svg-icons";
import { Outlet, useNavigate } from "react-router-dom";
import axios from 'axios';

function AppLayout(){
    const navigate = useNavigate();
    const [role, setRole] = useState("");
    const [nom, setNom] = useState("");
    const [prenom, setPrenom] = useState("");

    useEffect(() => {
        const isAuth = localStorage.getItem("isAuth");
        const storedRole = localStorage.getItem("role");
        const compteId = localStorage.getItem("compteId");

        if (isAuth == null || isAuth.toString() === "false") {
            navigate("/signIn");
        } else {
            setRole(storedRole);
            if (compteId) {
                // Fetch user details from the backend
                axios.get(`http://localhost:8080/comptes/id/${compteId}`)
                    .then(response => {
                        setNom(response.data.nom);
                        setPrenom(response.data.prenom);
                    })
                    .catch(error => {
                        console.error('Erreur lors de la récupération des informations du compte', error);
                    });
            }
        }
    }, [navigate]);

    const handleDeconnexion = () => {
        localStorage.setItem("isAuth", false);
        localStorage.removeItem("role");
        localStorage.removeItem("chefId");
        localStorage.removeItem("compteId");
        navigate("/signIn");
    };

    return (
        <>
            <Navbar className="app_navbar" style={{backgroundColor: "#1C1C63", fontFamily: "Helvetica, Arial, sans-serif", padding: '4px 10px', boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)', border: 'none'}} 
            variant="dark" expand="lg" sticky="top">
                <Container fluid>
                    <Navbar.Brand href="/" style={{ display: 'flex', alignItems: 'center' }}>
                        <Image src="/public/laboNihelLogo.png" style={{ width: '110px', height: 'auto' }} alt="Nihel Logo" />
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto" style={{ alignItems: 'center', fontSize: '0.9rem' }}>
                            <Nav.Link href="/" style={{ color: 'white', margin: '0 12px' }}>
                                <FontAwesomeIcon icon={faHome} className="me-2" />Accueil
                            </Nav.Link>
                            <Nav.Link href="/formules" style={{ color: 'white', margin: '0 12px' }}>
                                <FontAwesomeIcon icon={faFlask} className="me-2" />Formules
                            </Nav.Link>
                            {(role === "ADMIN" || role === "RESPONSABLE_TECHNIQUE" || role === "CHEF_PROJET") &&
                                <Nav.Link href="/ingredients" style={{ color: 'white', margin: '0 12px' }}>
                                    <FontAwesomeIcon icon={faSeedling} className="me-2" />Ingrédients
                                </Nav.Link>
                            }
                            {(role === "ADMIN" || role === "RESPONSABLE_TECHNIQUE") &&
                                <Nav.Link href="/comptes" style={{ color: 'white', margin: '0 12px' }}>
                                    <FontAwesomeIcon icon={faUser} className="me-2" />Comptes
                                </Nav.Link>
                            }
                        </Nav>
                        <Nav className="d-flex align-items-center">
                            <span style={{ color: 'white', margin: '0 12px' }}>{nom} {prenom}</span>
                            <Nav.Link onClick={handleDeconnexion} style={{ color: 'white', margin: '0 12px' }}>
                                <FontAwesomeIcon icon={faSignOutAlt} className="me-2"/> Déconnexion
                            </Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
            <Outlet/>
        </>
    )
}

export default AppLayout;
