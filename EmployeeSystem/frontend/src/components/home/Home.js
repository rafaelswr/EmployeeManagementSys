import React, {useState, useEffect} from 'react';

import "./Home.css";
import { Table, Button, Card } from 'react-bootstrap';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPencil, faTrash, faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import { Link } from 'react-router-dom';
import CardEmployee from '../CardEmployee';
import { onDeleteEmployee } from '../../firebase/services';
import { Form} from 'react-bootstrap';


const Home = ({employees, theads}) => {

  const [searchTerm, setSearchTerm] = useState("");
  

  const filteredEmployees = employees.filter(employee =>
    employee.firstName.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
      <>
        <div className='mb-3' style={{ display: "flex", flexDirection: "row", justifyContent: "center", alignItems: "center" }}>
            <div > 
              <FontAwesomeIcon style={{ paddingRight: 5 }} icon={faMagnifyingGlass}/>
            </div>
            <div>
              <Form.Group style={{ width:600, display: "flex", alignItems: "center" }}>
                <Form.Control
                  placeholder="Pesquisar..."
                  aria-label="Pesquisar"
                  aria-describedby="search-bar"
                  value={searchTerm}
                  onChange={(val) =>
                      
                        setSearchTerm(val.target.value)
                  }
                />
              </Form.Group>
            </div>
        </div>
        <hr></hr>
         
        <div className='emplo'>
          {
            filteredEmployees.map((employee)=>{
              return(

                <CardEmployee key={employee.employeeId} clickDelete={onDeleteEmployee} employee={employee}></CardEmployee>
            )})
          }
        </div>
      </>
     /* 
     <div className='Title' >
     
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
      */
  )
}

export default Home; 
