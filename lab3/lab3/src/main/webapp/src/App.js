import React from 'react';
import logo from './logo.svg';
import './App.css';
import Film from './components/Film';
import {Button} from 'primereact/button';
import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Hello Borås!
        </p>
        <Button label="knapp" className="p-button-secondary"/>
        <Film />
      </header>
    </div>
  );
}

export default App;
