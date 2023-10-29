import React from 'react';
import api from "../../api/axiosConfig";
import "./Home.css";
import { Table, Button } from 'react-bootstrap';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPencil, faTrash } from "@fortawesome/free-solid-svg-icons";
import { Link } from 'react-router-dom';

const Home = ({employees, theads}) => {

    const onClickDelete = async (employeeID)=>{
        try {
            const response = await api.delete(`/employees/delete/${employeeID}`);
            console.log("deleted successfully");
        } catch (error) {
            console.log("Error on deleting employee");
        }
    }

    return (
     <div className='Title'>
        <Table striped bordered hover>
      <thead>
        <tr>
            {theads.map((ele) => (
                <th  key={ele}>{ele}</th>
            ))}
        </tr>
      </thead>
      <tbody>
        
        {employees.map((employee, index) => (
          <tr key={index}>
            {Object.values(employee).map((value, index) => (
              <td key={index}>{value}</td>
            ))}
            <div style={{flex:1, flexDirection:"row", justifyContent:"flex-start", alignItems:"flex-start"}}>
                <Link onClick={()=>{
                    onClickDelete(employee.employeeId);
                }}>
                    <Button style={{backgroundColor:"#be4d25", marginRight:10}} variant="contained" >
                            <FontAwesomeIcon icon={faTrash} color='white'></FontAwesomeIcon>
                    </Button>
                </Link>
                <Link to={`${employee.employeeId}`}>
                    <Button style={{backgroundColor:"#2596be"}} variant="contained">
                            <FontAwesomeIcon icon={faPencil} color='white'></FontAwesomeIcon>
                    </Button>
                </Link>
            </div>
          </tr>
        ))}
        
      </tbody>
    </Table>
      </div>  
  )
}
export default Home; 
