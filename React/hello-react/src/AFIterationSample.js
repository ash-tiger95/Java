import React from 'react';

// 6장 컴포넌트 반복
const AFIterationSample = () => {
    const names = ['눈사람','얼음','눈','바람'];
    const nameList = names.map((name,index) => <li key={index}>{name}</li>);
    /* arr.map(callback, [thisArg])
        callback: 새로운 배열의 요소를 생성하는 함수로 파라미터는 3가지이다.
            1. currentValue: 현재 처리하고 있는 요소
            2. index: 현재 처리하고 있는 요소의 index 값
            3. array: 현재 처리하고 있는 원본 배열
        thisArg(선택항목): callback 함수 내부에서 사용할 this 레퍼런스

        주의! 고유한 값이 없을 때만 index값을 key로 사용한다. index를 key로 사용하면 배열이 변경될 때 효율적으로 리렌더링하지 못한다.
    */
    return (
        <ul>
            {nameList}
        </ul>
    );
};

export default AFIterationSample;