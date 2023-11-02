import React,{useEffect, useRef} from 'react'
import { useParams } from 'react-router-dom';
import {Form, Button} from "react-bootstrap";
import api from "../../api/axiosConfig";

const UpdateEmployee = ({getSingleEmployee, employee, setEmployee}) => {

    const {employeeID} = useParams(); 
    const emailRef = useRef(); 

    useEffect(()=>{
        if(employeeID!=null){
            getSingleEmployee(employeeID);
        }
        return ()=>{setEmployee({})}
    },[employeeID]);
    
    const onClickSubmit = async (e)=>{
        e.preventDefault(); 
        //console.log(emailRef.current.value);
        const updated = {...employee, email:emailRef.current.value}
        console.log(updated);

        try {
            const response = await api.put(`/employees/${employeeID}`, updated);
            console.log(response.data);
        } catch (error) {
            console.log("Error updating employee with ID: ", employeeID);
        }

    }

  
    return (

        <Form onSubmit={onClickSubmit}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Email address</Form.Label>
                <Form.Control type="email" ref={emailRef} defaultValue={employee.email} placeholder="Enter email" />
            </Form.Group>
                <Button variant="primary" type="submit">
                Submit
            </Button>
       </Form>
    
  
  
   )
}

export default UpdateEmployee; 

/**
 *    <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password" />
            </Form.Group>
 */