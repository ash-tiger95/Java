import React, {Component} from 'react';
import AAMyFuncComponent from './AAMyFuncComponent';
import AAMyClassComponent from './AAMyClassComponent';
import CounterClass from './CounterClass';
import CounterFunc from './CounterFunc';
import EventClass from './EventClass';
import ValidationSampleClass from './ValidationSampleClass';
import AEScrollBox from './AEScrollBox';
import AFIterationSample from './AFIterationSample';
import AFIterationSample2 from './AFIterationSample2';
import AGLifeCycleSample from './AGLifeCycleSample';
import ErrorBoundary from './ErrorBoundary';
import './App.css';

function getRandomColor(){
  return '#' + Math.floor(Math.random() * 16777215).toString(16);
}

class App extends Component {
  state = {
    color: '#000000'
  }

  handleClick = () => {
    this.setState({
      color:getRandomColor()
    });
  }
  render(){
    return (
      <div>
        <AAMyFuncComponent name="AAMyFuncComponent" favoriteNumber={1}>리엑트</AAMyFuncComponent>
        <AAMyClassComponent name="AAMyClassComponent" favoriteNumber={1}>리엑트</AAMyClassComponent>
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
        <h1>7장 라이프사이클</h1>
        <button onClick={this.handleClick}>랜덤 색상</button>
        <ErrorBoundary>
          <AGLifeCycleSample color={this.state.color} />
        </ErrorBoundary>
        
      </div>
    );
  }
  
}

export default App;
