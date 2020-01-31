'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {companies: []};
    }

    componentDidMount() {
        var root = '/api';
        client({method: 'GET', path: root + '/companies'}).done(response => {
            this.setState({companies: response.entity._embedded.companies});
        });
    }


    render() {
        return (
            <CompanyList companies={this.state.companies}/>
        )
    }
}

class CompanyList extends React.Component{
    render() {
        const companies = this.props.companies.map(company =>
            <Company key={company._links.self.href} company={company}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Name</th>
                    <th>Url</th>
                </tr>
                {companies}
                </tbody>
            </table>
        )
    }
}

class Company extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.company.name}</td>
                <td>{this.props.company.url}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)
