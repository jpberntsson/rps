import React, { useState, useEffect, useRef } from 'react';

/**
 * Copied from https://overreacted.io/making-setinterval-declarative-with-react-hooks/#just-show-me-the-code
 * @param callback
 * @param delay
 */
function useInterval(callback, delay) {
    const savedCallback = useRef();

    // Remember the latest callback.
    useEffect(() => {
        savedCallback.current = callback;
    }, [callback]);

    // Set up the interval.
    useEffect(() => {
        let id = setInterval(() => {
            savedCallback.current();
        }, delay);
        return () => clearInterval(id);
    }, [delay]);
}
export default useInterval;