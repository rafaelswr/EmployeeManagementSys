import React from "react"
import { Container } from "react-bootstrap";
import { Nav } from "react-bootstrap";
import { Navbar } from "react-bootstrap";
import "./Header.css";
import {NavLink }from "react-router-dom";

const Header = () => {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
        <Container className="Black" style={{ margin:"0 auto", maxWidth:1280}}>
            <Navbar.Brand >
                <h2>Employee Management System</h2>
            </Navbar.Brand>
            <Nav className="me-right  my-lg-0">
                <NavLink className="nav-link" to="/employees">Home</NavLink>
                <NavLink className="nav-link" to="/new_employee">New Employee</NavLink>
          </Nav>
        </Container>
    </Navbar>
  )
};

export default Header;
