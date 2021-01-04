import React, {Component} from 'react';
import MyFuncComponent from './MyFuncComponent';
import MyClassComponent from './MyClassComponent';
import CounterClass from './CounterClass';
import CounterFunc from './CounterFunc';
import EventClass from './EventClass';
import ValidationSampleClass from './ValidationSampleClass';
import AEScrollBox from './AEScrollBox';
import AFIterationSample from './AFIterationSample';
import AFIterationSample2 from './AFIterationSample2';
import './App.css';

class App extends Component {
  render(){
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
        <AEScrollBox ref={(ref) => this.scrollBox = ref}/>
        <button onClick={() => this.scrollBox.scrollToBottom()}>
          맨 밑으로
        </button>
        <br></br>
        <br></br>
        <h1>6장 컴포넌트 반복</h1>
        <AFIterationSample />
        <AFIterationSample2 />
      </div>
    );
  }
  
}

export default App;
