import React, { Component } from 'react';
import PropTypes from 'prop-types';

class AAMyClassComponent extends Component {
    render() {
        const {name, favoriteNumber, children} = this.props; // 비구조할당
        return (
            <div>
                안녕하세요, 제 이름은 {name} 입니다.<br/>
                children 값은 {children}
                입니다.
                <br></br>
                제가 좋아하는 숫자는 {favoriteNumber}입니다.
            </div>
        );
    }
}

AAMyClassComponent.defaultProps = {
    name:"기본 이름"
}

AAMyClassComponent.propTypes = {
    name:PropTypes.string,
    favoriteNumber:PropTypes.number.isRequired
}

export default AAMyClassComponent;