import Result from "./Result";
import TestChoice from "./TestChoice";
import CharacteristicsInput from "./CharacteristicsInput";
import Card from 'react-bootstrap/Card';
import Nav from 'react-bootstrap/Nav';
import Estimate from "./Estimate";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import DisplayResult from "./DisplayResult";


function Body() {
    return (
        <Card>
            <Card.Header>
                <Nav variant="tabs" defaultActiveKey="#first">
                    <Nav.Item>
                        <Nav.Link href="#home">Home</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link href="#test">Test Log</Nav.Link>
                    </Nav.Item>
                </Nav>
            </Card.Header>
            <Card.Body>
                <Card.Title>Input-Space Partitioning (ISP)</Card.Title>
                <br />
                <Card.Subtitle>Enter set of characteristics:</Card.Subtitle>
                <br />
                <CharacteristicsInput/>
                <br />
                <Card.Subtitle>Choose one of the combination criteria below:</Card.Subtitle>
                <br />
                <TestChoice/>

                <Container>
                    <Row>
                        <Col xl={2}><Result/></Col>
                        <Col xl={2}> <Estimate/></Col>
                        <Col xl={2}></Col>
                        <Col xl={6}></Col>
                    </Row>
                </Container>
                <br />
                <DisplayResult />
            </Card.Body>
        </Card>
    );
}
export default Body
