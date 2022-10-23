import React, {Component} from 'react';
import {Col, Container, Nav, Navbar, Row, Table} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import "./Homepage.css";
import Footer from "./Footer";

import SearchPage from "./SearchPage";
import {Route, Routes} from "react-router";
import Signin from "./Signin";
import Signup from "./Signup";
import AdminPanel from "./AdminPanel";
import {BrowserRouter} from "react-router-dom";
import ReactPaginate from "react-paginate";
import BookImage from "./BookImage";

class Homepage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            list: [],
            booksPerPage: 3,
            numOfPage: 0,
            amountOfPages: 1
        }
    }

    componentDidMount() {
        this.sendPageRequest();
    }

    render() {
        let items = this.state.list;
        let books = items.map(item => {
            return (
                <tr key={item.id}>
                    <td style={{verticalAlign: 'middle'}}>{<BookImage filePath={item.photoURL}/>}<br/><b>{item.title}</b></td>
                    <td style={{verticalAlign: 'middle'}}>{item.author}</td>
                    <td style={{verticalAlign: 'middle'}}>{item.year}&nbsp;r.</td>
                    <td style={{verticalAlign: 'middle'}}>{item.description}</td>
                    <td style={{verticalAlign: 'middle'}}>{item.price}&nbsp;zł</td>
                    <td style={{verticalAlign: 'middle'}}><Button variant="outline-secondary"
                                                                  onClick={() => this.addToCart(item.id, item.productName,
                                                                      item.price, item.weight, item.producer)}>DO&nbsp;KOSZYKA</Button></td>
                </tr>
            )
        }) || '';

    return (
        <div className="app" style={{height: '90vh'}}>
            <Container>
                <div className="back1">
                    <Row xs={3} md={3} lg={6}>
                        <Nav.Link href="/cat1"><Button className="menuButton" variant="dark">DRAMAT</Button></Nav.Link>
                        <Nav.Link href="/cat2"><Button className="menuButton" variant="dark">FANTASY</Button></Nav.Link>
                        <Nav.Link href="/cat3"><Button className="menuButton" variant="dark">POEZJA</Button></Nav.Link>
                        <Nav.Link href="/cat4"><Button className="menuButton" variant="dark">HISTORIA</Button></Nav.Link>
                        <Nav.Link href="/cat5"><Button className="menuButton" variant="dark">KRYMINAŁ</Button></Nav.Link>
                        <Nav.Link href="/cat6"><Button className="menuButton" variant="dark">BIOGRAFIE</Button></Nav.Link>
                    </Row>
                </div>
            </Container>
            <Container style={{height: '78vh', boxSizing: "border-box"}}>
                <div className="products" style={{margin: "0", padding: "0"}}>
                    {/*<Row>*/}
                        <Table className="table" striped bordered hover >
                            <thead>
                            <tr>
                                <th></th>
                                <th>Autor</th>
                                <th>Wydanie</th>
                                <th>Opis</th>
                                <th>Cena</th>
                            </tr>
                            </thead>
                            <tbody>
                            {books}
                            </tbody>
                        </Table>

                        <ReactPaginate
                            previousLabel={"<"}
                            nextLabel={">"}
                            pageCount={this.state.amountOfPages}
                            onPageChange={this.handlePageClick}
                            containerClassName={'pagination'}
                            activeClassName={'active'}
                        />
                    {/*</Row>*/}
                </div>
            </Container>
            <Footer></Footer>
        </div>

    );
    }

    sendPageRequest = () => {
        fetch('http://localhost:8090/api/books/pagination/' + this.state.numOfPage +'/' + this.state.booksPerPage).then(function (response) {
            response.json().then(data => ({
                    data: data
                })
            ).then(res => {
                this.setState({list: res.data['content']})
                this.setState({amountOfPages: res.data['totalPages']})
            })
        }.bind(this));
    }

    handlePageClick = (event) => {
        this.setState({numOfPage: event.selected}, () => {
            this.sendPageRequest()
        })
    }
}

export default Homepage;