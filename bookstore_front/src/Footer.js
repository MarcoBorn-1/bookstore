import React from 'react';
import {Container, Nav, Navbar} from "react-bootstrap";

import Signup from "./Signup";
import {BrowserRouter} from 'react-router-dom';
import {Route, Routes} from "react-router";
import Signin from "./Signin";
import Homepage from "./Homepage";
import SearchPage from "./SearchPage";
import AdminPanel from "./AdminPanel";

function Footer() {
    return (

            <Navbar style={{backgroundColor:"#323756", height: '7vh', display: "flex", justifyContent: "right", color: "white", paddingRight: "80px"}} variant="dark">
                    © 2022 - Mateusz Jeż & Marco Born
            </Navbar>
    )
}
export default Footer;