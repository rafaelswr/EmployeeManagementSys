import React, { useEffect, useState } from "react";
import { Button, Image } from "react-bootstrap";
import "./Card.css";
import { getPhotoPerfil } from "../firebase/services";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleInfo, faX } from "@fortawesome/free-solid-svg-icons";


const CardEmployee = ({employee, clickDelete}) => {
  
  const [photo, setPhoto] = useState(null);

  useEffect(()=>{
    
    getPhotoPerfil((url)=>{
      setPhoto(url);
    }, employee.firstName, employee.lastName ); 

    return ()=>{
      setPhoto();
    }
  },[employee])
  
  return (

    <div className="card">
      <div className="imageDiv">
        <Image style={{width:50, height:50}} src={`${photo}`} roundedCircle></Image>
      </div>
      <div className="infoDiv">
        <h3 style={{fontSize:14, fontWeight:"bold"}}>{employee.employeeId+" - "+employee.firstName+" "+ employee.lastName}</h3>
        <p>{employee.job}</p>
      </div>
    
      <div>
        <div className="buttonsDiv">
          <Button onClick={()=>{clickDelete(employee)}}>
          <span>Info </span>
            <FontAwesomeIcon icon={faCircleInfo} />
          </Button>
          <Button style={{backgroundColor:"red", border:"1px solid red"}} onClick={()=>{clickDelete(employee)}}>
            <span>Delete </span>
            <FontAwesomeIcon icon={faX} />
          </Button>
        </div>
      </div>
  </div>
)};

export default CardEmployee;

