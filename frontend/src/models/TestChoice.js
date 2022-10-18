import Form from 'react-bootstrap/Form';

function TestChoice() {
    return (
        <Form>
            {['radio'].map((type) => (
                <div key={`inline-${type}`} className="mb-3">
                    <Form.Check
                        inline
                        label="All combinations (ACoC)"
                        name="group1"
                        type={type}
                        id={`inline-${type}-acoc`}
                    />
                    <Form.Check
                        inline
                        label="Each choice (ECC)"
                        name="group1"
                        type={type}
                        id={`inline-${type}-ecc`}
                    />
                    <Form.Check
                        inline
                        name="group1"
                        label="Base choice (BCC)"
                        type={type}
                        id={`inline-${type}-bcc`}
                    />
                </div>
            ))}
        </Form>
    );
}

export default TestChoice