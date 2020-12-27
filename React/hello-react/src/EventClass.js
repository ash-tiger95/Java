import React, { Component } from 'react';

class EventClass extends Component {
    state = {
        message:'',
        username:''
    }
    // 이 작업은 새 메서드를 만들때마다 바인딩 작업을 해야한다.
    // 바벨의 transform-class-properties문법(화살표 함수)을 사용하면 생략 가능하다.
    /* constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }
    */
    handleChange = (e) => { // transform-class-properties문법(화살표 함수)
        this.setState({
            // message: e.target.value
            [e.target.name]:e.target.value
        });
        console.log(e.target.value);
    }
    handleClick = () => {
        alert(this.state.username+" : "+this.state.message);
        this.setState({
            username:'',
            message:''
        });
    }
    handleKeyPress = (e) => {
        if(e.key === 'Enter'){
            this.handleClick();
        }
    }
    render() {
        return (
            <div>
                <h1>이벤트연습</h1>
                <input type="text" name="username" placeholder="사용자명을 입력해 보세요" value={this.state.username} onChange={this.handleChange} />
                <input type="text" name="message" placeholder="아무거나 입력해 보세요" value={this.state.message} onChange={this.handleChange} onKeyPress={this.handleKeyPress}/>
                <button onClick={this.handleClick}>확인</button>
            </div>
        );
    }
}

export default EventClass;