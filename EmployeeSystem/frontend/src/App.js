import './App.css';
import React,{useState, useEffect} from 'react';
import Header from './components/header/Header';
import Home from './components/home/Home';
import NewEmployee  from './components/newEmployee/NewEmployee';
import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import api from "./api/axiosConfig.js";
import UpdateEmployee from './components/updateEmployee/UpdateEmployee';

//import { Stomp } from '@stomp/stompjs';
//import SockJS from 'sockjs-client';

function App() {
 
const [employees, setEmployees] = useState([]);
const [theads, setTHeads] = useState([]);
const [employee, setEmployee] = useState({});

//WEBSOCKETS with Spring
/* 
useEffect(() => {

  const client = Stomp.over(function(){
    return new SockJS("http://localhost:8080/employees");
  });
  client.connect({}, () => {
    console.log('Connected');
    client.subscribe('/topic/employees', (message) => {
      const updatedEmployees = JSON.parse(message.body);
      setEmployees(updatedEmployees);
    });
  });
   return () => {
    client.deactivate(); // Use deactivate em vez de disconnect
  };
}, []);


*/

//get All 
     const getEmployees = async ()=>{
      try {
        const response = await api.get("/employees");
        setEmployees(response.data);
      } catch (error) {
        console.log("Error: ", error);
      }
    }

    useEffect(() => {
      getEmployees(); 
    },[]);


    useEffect(()=>{
      const eventSource = new EventSource('http://localhost:8080/sse-employees/stream');

        eventSource.onmessage = (event) => {
            const employeeData = JSON.parse(event.data);
            getEmployees(); 
        };

        return () => {
            eventSource.close();
        };
    },[]);

    useEffect(() => {
        const newTheads = [];
      
        employees.forEach((employee) => {
          for (const key in employee) {
            if (!newTheads.includes(key)) {
              newTheads.push(key);
            }
          }
        });
      
        setTHeads(newTheads);
          
      }, [employees]);

    const getSingleEmployee = async (employeeId)=> {
        try {
          const response = await api.get(`/employees/${employeeId}`);
          setEmployee(response.data);
        } catch (error) {
          console.log("Error get single employee");
        }
    }

  return (
    <div className="App" style={{margin:0, flex:1}}>
      <Header/>
      <Routes>
        <Route path='/' element={<Layout/>}>
            <Route path='/employees' element={<Home employees={employees} getSingleEmployee={getSingleEmployee} theads={theads}></Home>}/>
            <Route path='/new_employee' element={<NewEmployee></NewEmployee>}></Route>
            <Route path='/employees/:employeeID' element={<UpdateEmployee setEmployee={setEmployee} getSingleEmployee={getSingleEmployee} employee={employee}></UpdateEmployee>}></Route>

          </Route>
      </Routes>
    </div>
  );
}

export default App;
