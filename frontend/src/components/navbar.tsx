import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Container, Nav, Navbar } from "react-bootstrap";
import React from 'react';
import { faFlask, faHome, faSeedling, faSignOutAlt, faUser } from "@fortawesome/free-solid-svg-icons";
function NavBarGeneral(){
    return(
        <>
        <Navbar style={{backgroundColor:"#0056b3"}} variant="dark" expand="lg" sticky="top">
            <Container>
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                  <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                    <Nav.Link href="/home"><FontAwesomeIcon icon={faHome} />Home</Nav.Link>
                    <Nav.Link href="/formules"><FontAwesomeIcon icon={faFlask} />Formules</Nav.Link>
                    <Nav.Link href="/ingredients"><FontAwesomeIcon icon={faSeedling} />Ingrédients</Nav.Link>
                    <Nav.Link href="/comptes"><FontAwesomeIcon icon={faUser} />Comptes</Nav.Link>
                    </Nav>
                     <Nav>
                        <Nav.Link href="/signIn"><FontAwesomeIcon icon={faSignOutAlt}/> Déconnexion</Nav.Link>
                    </Nav>
                  </Navbar.Collapse>

            </Container>


        </Navbar>
        </>
    )
}
export default NavBarGeneral;