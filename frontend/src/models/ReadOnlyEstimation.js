import Form from 'react-bootstrap/Form';

function ReadOnlyEstimation() {
    return (

        <>
            <Form.Control
                type="text"
                placeholder="Readonly Estimation"
                aria-label="Readonly Estimation"
                readOnly
            />
        </>

    );
}

export default ReadOnlyEstimation;