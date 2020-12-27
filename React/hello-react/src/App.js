import React from 'react';
import MyFuncComponent from './MyFuncComponent';
import MyClassComponent from './MyClassComponent';
import CounterClass from './CounterClass';
import CounterFunc from './CounterFunc';
import EventClass from './EventClass';
import ValidationSampleClass from './ValidationSampleClass';
import './App.css';

const App = () => {
  return (
    <div>
      <MyFuncComponent name="MyFuncComponent" favoriteNumber={1}>리엑트</MyFuncComponent>
      <MyClassComponent name="MyClassComponent" favoriteNumber={1}>리엑트</MyClassComponent>
      <br></br>
      <br></br>
      <CounterClass/>
      <CounterFunc/>
      <br></br>
      <br></br>
      <EventClass/>
      <br></br>
      <br></br>
      <ValidationSampleClass />
    </div>
  );
}

export default App;
