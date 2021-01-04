import React, { Component } from 'react';

class AERefSample extends Component {
    input = React.createRef(); // 1.

    handleFocus = () => {
        this.input.current.focus();
    }

    render() {
        return (
            <div>
                <input ref={this.input} /> {/* 2. */}
            </div>
        );
    }
}

export default AERefSample;