import { Container, Row, Col, Image, Nav } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFacebook, faInstagram, faLinkedin } from '@fortawesome/free-brands-svg-icons';

function Footer() {
    return (
        <footer style={{ padding: "20px 0", textAlign: "center", backgroundColor: "transparent" }}>
            <Container>
                <Row className="justify-content-center">
                    <Col xs={12} sm={3} className="mb-2 text-center">
                        <Image src="/logoBleu.png" alt="Nihel Logo" style={{ width: '105px', height: 'auto' }} />
                    </Col>
                    <Col xs={12} sm={3} className="mb-2 text-center">
                        Email: nihel@gnet.tn<br/>
                        Tel: 74 279 004<br/>
                        Site web: <a href="https://nihel.com/" style={{ color: "black", textDecoration: "none" }}>www.nihel.com</a>
                    </Col>
                    <Col xs={12} sm={3} className="text-center">
                        <Nav className="justify-content-center">
                            <Nav.Link href="https://www.facebook.com/Laboratoires.NIHEL.1982/" style={{ color: "black" }}>
                                <FontAwesomeIcon icon={faFacebook} size="lg" />
                            </Nav.Link>
                            <Nav.Link href="https://www.instagram.com/laboratoires_nihel/" style={{ color: "black" }}>
                                <FontAwesomeIcon icon={faInstagram} size="lg" />
                            </Nav.Link>
                            <Nav.Link href="https://www.linkedin.com/company/laboratoires-nihel/mycompany/" style={{ color: "black" }}>
                                <FontAwesomeIcon icon={faLinkedin} size="lg" />
                            </Nav.Link>
                        </Nav>
                    </Col>
                </Row>
            </Container>
        </footer>
    );
}

export default Footer;
