import React from 'react';
import {Container, Nav, Navbar} from "react-bootstrap";

import Signup from "./Signup";
import {BrowserRouter} from 'react-router-dom';
import {Route, Routes} from "react-router";
import Signin from "./Signin";
import Homepage from "./Homepage";
import SearchPage from "./SearchPage";
import AdminPanel from "./AdminPanel";

function navbar(){
    return (
        <BrowserRouter>
                <Navbar style={{backgroundColor:"#323756", height: '8vh'}} variant="dark">
                    <Container>
                        <Navbar.Brand href="/homepage">KSIĘGARNIA</Navbar.Brand>
                        <Nav className="me-auto">
                            <Nav.Link href="/signin">Logowanie</Nav.Link>
                            <Nav.Link href="/signup">Rejestracja</Nav.Link>
                        </Nav>
                        <SearchPage/>
                    </Container>
                </Navbar>
                <div>
                    <Routes>
                        <Route path="/" element={<Homepage/>} />
                        <Route path="/homepage" element={<Homepage/>}></Route>
                        <Route path="/signin" element={<Signin/>}></Route>
                        <Route path="/signup" element={<Signup/>}></Route>
                        <Route path="/admin" element={<AdminPanel/>}></Route>
                    </Routes>
                </div>
        </BrowserRouter>
    );
}
export default navbar;