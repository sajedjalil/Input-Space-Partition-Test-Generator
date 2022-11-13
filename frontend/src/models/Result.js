import axios from "axios";
import { useState } from "react";
import { backend_base_url } from "../constants/constant";
import Button from 'react-bootstrap/Button';

function Result(){
    const [result, setResult] = useState(null)
    const url = backend_base_url+'/getmessage'

    const handleClick= async() => {
        axios.get(url).then( response=> {
            console.log("Hit this!");
            setResult(response.data)
        })
    }

    
    return <div>
        <Button variant="primary" onClick={handleClick}>Generate Test</Button>
        <div>{result}</div>
        </div>
}

export default Result