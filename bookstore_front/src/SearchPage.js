import React from "react";
import {IconButton, InputAdornment} from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";
import Form from 'react-bootstrap/Form'

const SearchPage = () => {
    return (
        <div className="search-bar">
            <InputAdornment position="end" >
                <IconButton>
                    <SearchIcon style={{ color: 'white' }} />
                </IconButton>
                <Form.Group>
                <Form.Control id="search" placeholder="Wyszukaj" />
            </Form.Group>
            </InputAdornment>
        </div>
    );
}

export default SearchPage;