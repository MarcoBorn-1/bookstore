import React, {Component} from 'react';
import './Signup.css';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'

//import './Login.css';

import RegistrationAlert from './RegistrationAlert.js';



class SignUp extends Component{
    constructor(props) {
        super(props);

        this.state = {
            name: "",
            surname: "",
            phoneNumber: "",
            email: "",
            password1: "",
            password2: "",
            checked: "",
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
            this.registerUser(event.target.name.value, event.target.surname.value, event.target.phoneNumber.value,
                event.target.email.value, event.target.password1.value);
    }

    validate = () => {
        const errors = {};
        let isValid = true;
        const emailRegex= /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
        if(this.state.name.length===0){
            errors.name = "Podaj imię!";
            isValid = false;
        } else if(this.state.name.length<3 || this.state.name.charAt(0) !== this.state.name.charAt(0).toUpperCase() || (/^[\s\p{L}]+$/u.test(this.state.name.substring(1,this.state.name.length)))===false ){
            errors.name = "Wpisz poprawne imię!";
            isValid = false;
        }
        if(this.state.surname.length===0){
            errors.surname = "Podaj nazwisko!";
            isValid = false;
        } else if(this.state.surname.length<3 || this.state.surname.charAt(0) !== this.state.surname.charAt(0).toUpperCase() || (/^[\s\p{L}]+$/u.test(this.state.surname.substring(1,this.state.surname.length)))===false ){
            errors.surname = "Wpisz poprawne nazwisko!";
            isValid = false;
        }
        if(this.state.phoneNumber.length===0){
            errors.phoneNumber = "Podaj numer telefonu!";
            isValid = false;
        } else if((/^\d+$/.test(this.state.phoneNumber.substring(0,this.state.phoneNumber.length)))===false || this.state.phoneNumber.length!==9){
            errors.phoneNumber = "Wpisz poprawny numer telefonu!";
            isValid = false;
        }
        if(this.state.email.length===0){
            errors.email = "Podaj adres email!";
            isValid = false;
        } else if(!emailRegex.test(this.state.email.substring(0,this.state.email.length))){
            errors.email = "Wpisz poprawny adres email!";
            isValid = false;
        }
        if(this.state.password1.length<6){
            errors.password = "Hasło musi zawierać od 6 do 20 znaków!";
            isValid = false;
        }
        if(this.state.password1!==this.state.password2){
            errors.password = "Podane hasła nie są identyczne!";
            isValid = false;
        }
        if(!this.state.checked){
            errors.checked = "Musisz zaakceptować regulamin!";
            isValid = false;
        }
        this.setState({errors});
        return isValid;
    }

    handleChange = (event) => {
        this.setState({[event.target.name] : event.target.value});
    }

    handleCheck = (event) => {
        this.setState({[event.target.name] : event.target.checked});
    }


    registerUser(name, surname, phone, email, password1){
        fetch('http://localhost:8090/register',{
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: name,
                surname: surname,
                phoneNumber: phone,
                email: email,
                password: password1
            })
        }).then(function(response) {
            if (response.status === 200) {
                this.showRegistrationAlert("success", "Zarejestrowano użytkownika!", "Dziękujemy za rejestrację, życzymy udanych zakupów!");
            } else if (response.status === 422) {
                this.showRegistrationAlert("danger", "Użytkownik o podanym adresie email już istnieje!", "Proszę wpisać inny adres email lub zalogować się.");
            } else {
                this.showRegistrationAlert("danger", "Rejestracja nie powiodła się!", "W tej chwili rejestracja nie jest możliwa. Przepraszamy za utrudnienia.");
            }
        }.bind(this)).catch(function() {
            this.showRegistrationAlert("danger", "Rejestracja nie powiodła się!", "W tej chwili rejestracja nie jest możliwa. Przepraszamy za utrudnienia.");
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
                <h1>REJESTRACJA</h1>
                <form onSubmit= { this.handleSubmit } >
                    <Form.Group controlId = "name">
                        <Form.Control name="name" type="text" maxLength={12} placeholder="Imię" defaultValue={this.state.name} onChange={this.handleChange}/>
                        <Form.Control.Feedback type="invalid">{this.state.errors.name}</Form.Control.Feedback>
                    </Form.Group>

                    <Form.Group controlId = "surname">
                        <Form.Control name="surname" type="text" maxLength={16} placeholder="Nazwisko" defaultValue={this.state.surname} onChange={this.handleChange}/>
                        <Form.Control.Feedback type="invalid">{this.state.errors.surname}</Form.Control.Feedback>
                    </Form.Group>

                    <Form.Group controlId = "phone-number">
                        <Form.Control name="phoneNumber" type="text" maxLength={9} placeholder="Numer telefonu" defaultValue={this.state.phoneNumber} onChange={this.handleChange}/>
                        <Form.Control.Feedback type="invalid">{this.state.errors.phoneNumber}</Form.Control.Feedback>
                    </Form.Group>

                    <Form.Group controlId = "email">
                        <Form.Control name="email" type="text" maxLength={50} placeholder="Email" defaultValue={this.state.email} onChange={this.handleChange}/>
                        <Form.Control.Feedback type="invalid">{this.state.errors.email}</Form.Control.Feedback>
                    </Form.Group>

                    <Form.Group controlId = "password1">
                        <Form.Control name="password1" type="password" maxLength={20} placeholder="Hasło" defaultValue={this.state.password1} onChange={this.handleChange}/>
                        <Form.Control.Feedback type="invalid">{this.state.errors.password}</Form.Control.Feedback>
                    </Form.Group>

                    <Form.Group controlId = "password2">
                        <Form.Control name="password2" type="password" maxLength={20} placeholder="Powtórz hasło" defaultValue={this.state.password2} onChange={this.handleChange}/>
                    </Form.Group>

                    <Form.Group controlId= "checked">
                        <Form.Check name="checked" type="checkbox" label="Akceptuję regulamin sklepu" onChange={this.handleCheck}/>
                        <Form.Control.Feedback type="invalid">{this.state.errors.checked}</Form.Control.Feedback>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        ZAREJESTRUJ
                    </Button>
                </form>
                <RegistrationAlert ref = { this.registrationAlert }/>
            </div>
        )
    }
}
export default SignUp;