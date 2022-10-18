import './App.css';
import NavBar from './models/NavBar';
import Body from './models/Body';
import Footer from './models/Footer';
import Container from 'react-bootstrap/Container';

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
