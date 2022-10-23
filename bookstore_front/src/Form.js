import React, { useState } from 'react';
import './Form.css';
import Signup from './Signup';
import Success from './Success';

const Form = () => {
    const [isSubmitted, setIsSubmitted] = useState(false);

    function submitForm() {
        setIsSubmitted(true);
    }
    return (
        <>
            <div className='form-container'>
                <span className='close-btn'>×</span>
                <div className='form-content-left'>

                </div>
                {!isSubmitted ? (
                    <Signup submitForm={submitForm} />
                ) : (
                    <Success />
                )}
            </div>
        </>
    );
};

export default Form;