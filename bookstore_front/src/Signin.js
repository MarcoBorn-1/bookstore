import React, {Component} from 'react';
import './Signup.css';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Cookies from 'universal-cookie';

//import './Login.css';

import RegistrationAlert from './RegistrationAlert.js';



class SignIn extends Component{
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            errors: {},
            response: ""
        };
        this.registrationAlert = React.createRef();
    }


    handleSubmit = event => {
        event.preventDefault();
        const isSubmit = this.validate();
        console.log(this.state.response);
        if(isSubmit === true)
            this.loginUser(event.target.email.value, event.target.password.value);
    }

    validate = () => {
        const errors = {};
        let isValid = true;
        const emailRegex= /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;

        if(this.state.email.length===0){
            errors.email = "Podaj adres email!";
            isValid = false;
        } else if(!emailRegex.test(this.state.email.substring(0,this.state.email.length))){
            errors.email = "Wpisz poprawny adres email!";
            isValid = false;
        }
        if(this.state.password.length===0){
            errors.password = "Podaj hasło!";
            isValid = false;
        }
        this.setState({errors});
        return isValid;
    }

    handleChange = (event) => {
        this.setState({[event.target.name] : event.target.value});
    }

    loginUser(email, password){
        fetch('http://localhost:8090/login',{
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: email,
                password: password
            })
        }).then(function(response) {
            if (response.status === 200) {
                this.showRegistrationAlert("success", "Zalogowano!", "Witamy ponownie!");
                response.json().then(data => ({
                        data: data,
                        status: response.status
                    })
                ).then(res => {
                    const cookies = new Cookies();
                    cookies.set('jwt-token', res.data['jwt-token'], { path: '/' });
                })
            } else if (response.status === 401) {
                this.showRegistrationAlert("danger", "Niepoprawne dane logowania!", "Wpisz poprawny email oraz hasło!");
            } else {
                this.showRegistrationAlert("danger", "Logowanie nie powiodło się!", "W tej chwili logowanie nie jest możliwe. Przepraszamy za utrudnienia.");
            }
        }.bind(this)).catch(function() {
            this.showRegistrationAlert("danger", "Logowanie nie powiodło się!", "W tej chwili logowanie nie jest możliwe. Przepraszamy za utrudnienia.");
        }.bind(this));
    }

    showRegistrationAlert(variant, heading, message) {
        this.registrationAlert.current.setVariant(variant);
        this.registrationAlert.current.setHeading(heading);
        this.registrationAlert.current.setMessage(message);
        this.registrationAlert.current.setVisible(true);
    }

    render(){

        return (

            <div className="login">
                <h1>LOGOWANIE</h1>
                <form onSubmit= { this.handleSubmit } >
                    <Form.Group controlId = "email">
                        <Form.Control name="email" type="text" maxLength={50} placeholder="Email" defaultValue={this.state.email} onChange={this.handleChange}/>
                        <Form.Control.Feedback type="invalid">{this.state.errors.email}</Form.Control.Feedback>
                    </Form.Group>

                    <Form.Group controlId = "password">
                        <Form.Control name="password" type="password" maxLength={20} placeholder="Hasło" defaultValue={this.state.password} onChange={this.handleChange}/>
                        <Form.Control.Feedback type="invalid">{this.state.errors.password}</Form.Control.Feedback>
                    </Form.Group>

                    <Button variant="primary" type="submit">
                        ZALOGUJ
                    </Button>
                </form>
                <RegistrationAlert ref = { this.registrationAlert }/>
            </div>
        )
    }
}
export default SignIn;