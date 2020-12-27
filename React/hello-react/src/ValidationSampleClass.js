import React, { Component } from 'react';
import './ValidationSample.css';

class ValidationSampleClass extends Component {
    state = {
        password:'',
        clicked:false,
        validated:false
    }
    handleChange = (e) =>{
        this.setState({
            password:e.target.value
        });
    }
    handleButtonClick = () => {
        this.setState({
            clicked:true,
            validated:this.state.password === '0000'
        })
    }
    render() {
        return (
            <div>
                <h1>5장 ref: DOM에 이름달기</h1>
                <input type="password" value={this.state.password} onChange={this.handleChange} className={this.state.clicked ? (this.state.validated ? 'success' : 'failure'):'' } />
                <button onClick={this.handleButtonClick}>검증하기</button>
            </div>
        );
    }
}

export default ValidationSampleClass;