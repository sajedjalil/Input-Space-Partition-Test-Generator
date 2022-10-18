import FloatingLabel from 'react-bootstrap/FloatingLabel';
import Form from 'react-bootstrap/Form';

function CharacteristicsInput() {
    return (
        <>
            <FloatingLabel controlId="characteristics"
                           label="
                           Example:
                           major = [swe, cs, infs, other]
                           year started = [2022, 2021, 2020 or earlier]
                           status = [part-time, full-time]
                           visa = [US, student, other]
                            "
            >
                <Form.Control
                    as="textarea"
                    placeholder="Hello"
                    style={{ height: '300px' }}
                />
            </FloatingLabel>
        </>
    );
}

export default CharacteristicsInput;