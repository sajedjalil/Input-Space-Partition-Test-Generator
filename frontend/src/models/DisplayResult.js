import Table from 'react-bootstrap/Table';

function DisplayResult() {
    return (
        <Table striped>
            <thead>
            <tr>
                <th>Partitions</th>
                <th>b1</th>
                <th>b2</th>
                <th>b3</th>
                <th>b4</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>major</td>
                <td>testMajor</td>
                <td>testMajor</td>
                <td>testMajor</td>
                <td>testMajor</td>
            </tr>
            <tr>
                <td>year</td>
                <td>testYear</td>
                <td>testYear</td>
                <td>testYear</td>
                <td>testYear</td>
            </tr>
            <tr>
                <td>status</td>
                <td>testStatus</td>
                <td>testStatus</td>
                <td>testStatus</td>
                <td>testStatus</td>
            </tr>
            <tr>
                <td>visa</td>
                <td>testVisa</td>
                <td>testVisa</td>
                <td>testVisa</td>
                <td>testVisa</td>
            </tr>
            </tbody>
        </Table>
    );
}

export default DisplayResult;