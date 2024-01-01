import React, { useReducer, useState} from 'react';
import "./NewEmployee.css";

import api from "../../api/axiosConfig";
import "./NewEmployee.css"; 
import { Form, Button, Image} from 'react-bootstrap';
import { uploadPhotoPerfil } from '../../firebase/services';


const NewEmployee = () => {

    const [photo, setPhoto ] = useState(null);

    const employee_reducer = (state, action) => {
        switch (action.type) {
            case 'new_employee':
                return {...state, [action.payload.field]: action.payload.value }
            default:
                return state;
        }
    };

    const [state, dispatch ] = useReducer(employee_reducer, {email:"", firstName:"", lastName:"", job:"", birthday:null})

    const onClickSubmit = async () => {
        try{
         
            if(photo!=null){
                uploadPhotoPerfil(photo, state.firstName, state.lastName);
                console.log("Employee photo uploaded successfully");
            }
            console.log("Employee created successfully:", state);

            await api.post("/employees/add", state);
            
        }catch(error){
            console.error("Error on create employee: ", error.message);
        }  
    }

    const handlePhoto = (event) => {
        const photo = event.target.files[0];
        console.log(photo);
        setPhoto(photo);
        
    }

  return (
    <div>
      <div>
            <Form onSubmit={onClickSubmit} className='form max'>
                <Form.Group className="firstLastForm" controlId="formFirstName">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control 
                        type="text" 
                        placeholder="name"
                        onChange={(val)=>{
                            dispatch({type:"new_employee",payload:{field:"firstName", value:val.target.value}})
                        }}
                        />
                </Form.Group>

                <Form.Group className="firstLastForm" controlId="formLastName" >
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
                <Form.Group className="jobForm" controlId="jon">
                    <Form.Label>Job</Form.Label>
                    <Form.Control 
                        type="text" 
                        placeholder="Job"
                        onChange={(val)=>{
                            dispatch({type:"new_employee",payload:{field:"job", value:val.target.value}})
                        }}
                        />
                </Form.Group>

                    <div style={{flexDirection:"column", display:'flex', paddingLeft:15}}>            
                        <div className="mb-2 mt-1">
                            Photo ID
                            <div>
                                <input onChange={handlePhoto} type="file" id="file-input" accept="image/png, image/gif, image/jpeg" name="ImageStyle"/>
                            </div>    
                        </div> 
                    {
                        photo!= null && (
                            <div style={{marginBottom:10}}>
                                <Image
                                src={URL.createObjectURL(photo)}
                                alt="Uploaded"
                                style={{ width: '50px', height: '50px' }}
                                />
                          </div>
                        )
                    }
                      
               

                      <div className='button'>
                        <Button variant="primary" type="submit">
                            Submit
                        </Button>
                    </div>   

                </div>
             
            </Form>
               
            
        </div>
    </div>
  
  )
}


export default NewEmployee; 