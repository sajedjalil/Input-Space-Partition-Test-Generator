
import { useState } from "react";
import Button from 'react-bootstrap/Button';
import ReadOnlyEstimation from "./ReadOnlyEstimation";

function Estimate(){

    const [estimate, setEstimate] = useState(true);

    return (
            <div>
                <Button variant="primary" onClick={() => setEstimate(s => !s)}> Estimate </Button>
                {!estimate ? <ReadOnlyEstimation /> : null}
            </div>
    )}

export default Estimate