import Result from "./Result";
import TestChoice from "./TestChoice";
import CharacteristicsInput from "./CharacteristicsInput";
import Card from 'react-bootstrap/Card';
import Nav from 'react-bootstrap/Nav';

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
                <br />
                <Card.Subtitle>Enter set of characteristics:</Card.Subtitle>
                <br />
                <CharacteristicsInput/>
                <br />
                <Card.Subtitle>Choose one of the combination criteria below:</Card.Subtitle>
                <br />
                <TestChoice/>
                <Result/>
            </Card.Body>
        </Card>
    );
}
export default Body
