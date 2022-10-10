import './App.css';
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import Card from 'react-bootstrap/Card';
import Offcanvas from 'react-bootstrap/Offcanvas';
import Button from 'react-bootstrap/Button';
import Result from './models/Result';
function NavBar() {
  return (
    <>
      {['lg'].map((expand) => (
        <Navbar key={expand} bg="white" expand={expand} className="mb-3">
          <Container fluid>
            <Navbar.Brand href="#">
            <img id="MasonLogo" src="https://provapps.gmu.edu/DynamicForms/Images/GreenLogo.png"/>
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
                    Team Members: <a href="#login">Jessica, Sajed & Vu</a>
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

function Body() {
  return (
    <Card>
      <Card.Header>
        <Nav variant="tabs" defaultActiveKey="#first">
          <Nav.Item>
            <Nav.Link href="#first">Home</Nav.Link>
          </Nav.Item>
          <Nav.Item>
            <Nav.Link href="#link">Test Logs</Nav.Link>
          </Nav.Item>
        </Nav>
      </Card.Header>
      <Card.Body>
        <Card.Title>Input-Space Partitioning (ISP)</Card.Title>
        <Card.Text>
        </Card.Text>
        <Result/>
      </Card.Body>
    </Card>
  );
}

function Footer() {
  return (
    <Card>
      <Card.Header>Acknoledgement</Card.Header>
      <Card.Body>
        <blockquote className="blockquote mb-0">
          <p>{' '}© 2022 This project is for educational purpose only. {' '}</p>
          <footer className="blockquote-footer">
            Team members: <cite title="Source Title">Vu Doan, Jessica Tran , Sajed Jalil</cite>
          </footer>
        </blockquote>
      </Card.Body>
    </Card>
  );
}

function App() {
  return (
    <Container fluid>
      <NavBar/>
      <Body/>
      <br />
      <Footer/>
    </Container>
  );
}
export default App;
