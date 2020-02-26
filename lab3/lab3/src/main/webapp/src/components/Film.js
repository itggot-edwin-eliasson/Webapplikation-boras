/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import React from 'react';
import BootstrapTable from "react-bootstrap-table-next";
export default class Film extends React.Component {
    constructor(props) {
        super(props);
        this.state = {films: []};

        this.columns = [{
                dataField: "title",
                text: "Title"
            },
            {
                dataField: "releaseYear",
                text: "released"
            },
            {
                dataField: "genre",
                text: "genre"
            },
            {
                dataField: "director",
                text: "director"
            }, ];
    }

    componentDidMount() {
        fetch("http://localhost:8080/lab3/ws/filmDao")
                .then(res => res.json()).then((data) => {
            data.map((b, idx) => {
                b.id = idx;
                return b;
            });
            this.setState({films: data})
        }).catch(console.log);
    }

    render() {
        return (
                <BootstrapTable keyField="id" data={this.state.films} columns={this.columns} />
                );
    }
}


