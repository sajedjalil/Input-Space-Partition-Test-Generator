import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import Offcanvas from 'react-bootstrap/Offcanvas';

function NavBar() {
    return (
        <>
            {['lg'].map((expand) => (
                <Navbar key={expand} bg="white" expand={expand} className="mb-3">
                    <Container fluid>
                        <Navbar.Brand href="#">
                            <img id="MasonLogo" src="https://provapps.gmu.edu/DynamicForms/Images/GreenLogo.png" alt="MasonLogo"/>
                        </Navbar.Brand>
                        <Navbar.Toggle aria-controls={`offcanvasNavbar-expand-${expand}`} />
                        <Navbar.Offcanvas
                            id={`offcanvasNavbar-expand-${expand}`}
                            aria-labelledby={`offcanvasNavbarLabel-expand-${expand}`}
                            placement="end"
                        >
                            <Offcanvas.Header closeButton>
                                <Offcanvas.Title id={`offcanvasNavbarLabel-expand-${expand}`}>
                                    Mobile Friendly
                                </Offcanvas.Title>
                            </Offcanvas.Header>
                            <Offcanvas.Body>
                                <Navbar.Toggle />
                                <Navbar.Collapse className="justify-content-end">
                                    <Navbar.Text>
                                        Signed in as: <a href="#login">Vu Doan</a>
                                    </Navbar.Text>
                                </Navbar.Collapse>
                            </Offcanvas.Body>
                        </Navbar.Offcanvas>
                    </Container>
                </Navbar>
            ))}
        </>
    );
}

export default NavBar