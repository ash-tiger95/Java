import React, { Component } from 'react';

class CounterClass extends Component {
    constructor(props){
        super(props);
        this.state = { // state 초기값 설정하기 (객체 형태)
            number :0,
            fixNumber:0,
            number2:0
        };
    }
    render() {
        const {number,fixNumber,number2} = this.state; // state를 조회할 때는 this.state로 조회
        return (
            <div>
                <h1>{number}</h1>
                <h2>바뀌지 않는 값: {fixNumber}</h2>
                <button
                    onClick={() => {
                        this.setState( // (값 변경, 콜벡함수)
                            {
                                number: number+1
                            },
                            () => { // setState 후 특정 작업을 하고 싶을 때, 콜백함수 등록 가능
                                console.log("방금 setState가 호출되었습니다.");
                                console.log(this.state);
                            }
                        );
                    }}
                >
                +1
                </button>


                <h1>{number2}</h1>
                <button
                    onClick = {()=>{
                        this.setState(prevState => {
                            return {
                                number2:prevState.number2+1
                            };
                        });
                        this.setState(prevState => ({ // 위와 동일한 결과, 값을 바로 반환하고 싶을 때는 return 생략 가능
                            number2:prevState.number2+1
                        }))
                    }}
                >
                    +1
                </button>
            </div>
        );
    }
}

export default CounterClass;