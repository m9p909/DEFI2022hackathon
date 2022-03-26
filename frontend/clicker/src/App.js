import logo from './logo.svg';
import './App.css';
import {motion} from "framer-motion"
import { useState } from 'react';

function App() {
  const [count, setCount] = useState(0)

  const onClick = () => {
    setCount(count + 1);
  }
  return (
    <div className="App">
      <header className="App-header">
        <h1> count: {count}</h1>
        <motion.button onClick={onClick} whileTap={{scale: 0.8}}>
       <img src="cookie.png" width="150px" height="150px"></img>
       </motion.button>
      </header>
    </div>
  );
}

export default App;
