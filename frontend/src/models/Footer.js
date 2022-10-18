import Card from 'react-bootstrap/Card';
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
export default Footer