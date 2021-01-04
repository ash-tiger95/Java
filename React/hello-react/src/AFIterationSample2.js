import React,{useState} from 'react';

// 동적인 배열을 랜더링해보기
const AFIterationSample2 = () => {
    // 1. 초기 상태 설정하기
    const [names, setNames] = useState([
        {id:1, text:'눈사람'},
        {id:2, text:'얼음'},
        {id:3, text:'눈'},
        {id:4, text:'바람'}
    ]);
    const [inputText, setInputText] = useState(''); // 텍스트를 입력할 수 있는 input 상태
    const [nextId, setNextId] = useState(5); // 새로운 항목을 추가할 때 사용할 고유 id를 위한 상태

    // 2. 데이터 추가 기능 구현하기 (새로운 이름을 등록할 수 있는 기능 구현)
    const onChange = e => setInputText(e.target.value);
    const onClick = () => {
        const nextNames = names.concat({
            id: nextId,
            text:inputText
        });
        setNextId(nextId + 1); // nextId값에 1을 더해준다.
        setNames(nextNames); // names 값을 업데이트한다.
        setInputText(''); // inputText를 비운다.
    };

    // 3. 데이터 제거 기능 구현하기 (불변성을 유지하면서 업데이트를 하기 위해서는 filter를 사용한다.)
    // filter함수는 특정 원소만 제외시킬 수 있다.
    const onRemove = id => {
        const nextNames = names.filter(name => name.id !== id);
        setNames(nextNames);
    };

    const namesList = names.map(name => (
        <li key={name.id} onDoubleClick={() => onRemove(name.id)}>
            {name.text}
        </li>
    ));
    return (
        <div>
            <input value={inputText} onChange={onChange}></input>
            <button onClick={onClick}>추가</button>
            <ul>{namesList}</ul>
        </div>
    );
};

export default AFIterationSample2;