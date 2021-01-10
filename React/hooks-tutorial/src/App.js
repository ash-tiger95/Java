import React, { useState } from 'react';
import A81Counter from './A81Counter'; //useState
import A82Info from './A82Info'; // useEffect
import A83Counter from './A83Counter'; // useReducer

const App = () => {
  const [visible, setVisible] = useState(false);

  return (
    <div>
      <button onClick={() => {
        setVisible(!visible)
      }}> {visible ? '숨기기' : '보이기'} </button>
      <hr></hr>
      {visible && <A81Counter /> && <A82Info />}
      <A83Counter />
    </div>
  );
};

export default App;