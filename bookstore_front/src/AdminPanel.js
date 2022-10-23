import React, { Component } from "react";
import { Container, Table } from "react-bootstrap";
import Button from "react-bootstrap/Button";
import Cookies from "js-cookie";
import Homepage from "./Homepage";

class Users extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user_list: [],
      is_admin: false,
    };
  }

  componentDidMount() {
    this.makeHttpRequest();
  }

  makeHttpRequest = async () => {
    const token = Cookies.get("jwt-token");
    if (token != null) {
      fetch("http://localhost:8090/api/customers/get/all", {
        method: "GET",
        headers: new Headers({
          Authorization: `Bearer ${token}`,
        }),
      }).then(
        function (response) {
          if (response.status === 200) {
            response
              .json()
              .then((data) => ({
                list: data,
              }))
              .then((res) => {
                this.setState({ user_list: res.list, is_admin: true });
              });
          } else {
            this.setState({ is_admin: false });
          }
        }.bind(this)
      );
    }
  };

  deleteUser(id) {
    const token = Cookies.get("jwt-token");
    fetch("http://localhost:8090/api/customers/del/" + id, {
      method: "DELETE",
      headers: new Headers({
        Authorization: `Bearer ${token}`,
      }),
    }).then(function (response) {
      console.log(response);
      window.location.reload();
      console.log(response);
    });
  }

  render() {
    const { user_list } = this.state;
    if (!this.state.is_admin) {
      return <Homepage />;
    } else {
      let customers =
        user_list.map((item) => {
          let deleteButton;
          if (item.role === "USER") {
            deleteButton = (
              <Button variant="danger" onClick={() => this.deleteUser(item.id)}>
                X
              </Button>
            );
          } else {
            deleteButton = "ADMIN";
          }

          return (
            <tr key={item.id}>
              <td>{item.name}</td>
              <td>{item.surname}</td>
              <td>{item.phoneNumber}</td>
              <td>{item.email}</td>
              <td>{item.city + ", " + item.street}</td>
              <td>{deleteButton}</td>
            </tr>
          );
        }) || "";

      return (
        <>
          <Container>
            <div className="app">
              <div className="back1">
                <Table className="table" striped bordered hover>
                  <thead>
                    <tr>
                      <th>Imię</th>
                      <th>Nazwisko</th>
                      <th>Telefon</th>
                      <th>Email</th>
                      <th>Adres</th>
                      <th>USUŃ</th>
                    </tr>
                  </thead>
                  <tbody>{customers}</tbody>
                </Table>
              </div>
            </div>
          </Container>
        </>
      );
    }
  }
}

export default Users;
