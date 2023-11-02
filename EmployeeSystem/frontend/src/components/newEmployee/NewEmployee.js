import React, { useReducer} from 'react';
import "./NewEmployee.css";
import { Form, Button} from 'react-bootstrap';
import api from "../../api/axiosConfig";
import { useNavigate } from 'react-router-dom';
import "./NewEmployee.css";

const NewEmployee = () => {


    const navigate = useNavigate();

    const employee_reducer = (state, action) => {
        switch (action.type) {
            case 'new_employee':
                return {...state, [action.payload.field]: action.payload.value }
            default:
                return state;
        }
    };

    const [state, dispatch ] = useReducer(employee_reducer, {email:"", firstName:"", lastName:"", birthday:null})

    const onClickSubmit = async () => {
        try {
            const response = await api.post("/employees/add", state);
            if(response.status === 200 ){
                navigate("/employees")
            }
            console.log("Employee " + state + " created succesfully");
            
        } catch (error) {
            console.log("Error on create employee ", error);
        }
    }

  return (
    <div>
      <div>
            <Form onSubmit={onClickSubmit} className='form max'>
                <Form.Group className="firstLastForm"  controlId="formFirstName">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control 
                        type="text" 
                        placeholder="name"
                        onChange={(val)=>{
                            dispatch({type:"new_employee",payload:{field:"firstName", value:val.target.value}})
                        }}
                        />
                </Form.Group>

                <Form.Group className="firstLastForm"  controlId="formLastName" >
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control 
                        type="text"
                        placeholder="surname"
                        onChange={(val)=>{
                            dispatch({type:"new_employee",payload:{field:"lastName", value:val.target.value}})
                        }}
                        />
                </Form.Group>
                
                <Form.Group className='birthdayForm' controlId="formBirthday">
                    <Form.Label>Birthday</Form.Label>
                    <Form.Control type="date" onChange={(val)=>{
                            dispatch({type:"new_employee",payload:{field:"birthday", value:val.target.value}})
                        }} />
                </Form.Group>
                

                <Form.Group className='emailForm' controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email"  placeholder="ex: a@gmail.com" 
                    onChange={(val)=>{
                        dispatch({type:"new_employee",payload:{field:"email", value:val.target.value}})
                    }} />
                </Form.Group>
            
                <div className='button'>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </div>
            </Form>
        </div>
    </div>
  
  )
}


export default NewEmployee; 

/**
 * 
 * 
        <div className='form max '>  
            <div className='son'> 
                <p>Hello guys, how are you doing </p>
            </div>
            <div className='son'>  
                <p>Hello guys, how are you doing </p>
            </div>
        </div>

 */