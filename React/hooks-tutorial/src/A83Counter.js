import React, {useReducer} from 'react';

function reducer(state, action){
    return {
        ...state,
        [action.name] : action.value
    }
}

const A83Counter = () => {
    const [states, dispatch] = useReducer(reducer, {name: '', nickname : ''});
    const {name, nickname} = states;

    const onChange = e => {
        dispatch(e.target);
    };

    return (
        <div>
            <div>
                <input name="name" value={name} onChange={onChange} />
                <input name="nickname" value={nickname} onChange={onChange} />
            </div>
            <div>
                <div>
                    <b>이름:</b> {name}
                </div>
                <div>
                    <b>닉네임:</b> {nickname}
                </div>
            </div>
        </div>
    );
};

export default A83Counter;