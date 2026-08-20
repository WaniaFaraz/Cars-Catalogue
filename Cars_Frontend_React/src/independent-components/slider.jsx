import React from 'react';
import * as SliderPrimitive from '@radix-ui/react-slider';
import './slider.css'

import '@radix-ui/colors/black-alpha.css';
import '@radix-ui/colors/violet.css';

export const Slider = React.forwardRef((props, forwardedRef) => {
  // Safe array fallback prevents the .map() crash if default values are missing
  const value = props.value || props.defaultValue || [];

  return (
    <SliderPrimitive.Root 
      {...props} 
      ref={forwardedRef} 
      className="SliderRoot"
    >
      <SliderPrimitive.Track className="SliderTrack">
        <SliderPrimitive.Range className="SliderRange" />
      </SliderPrimitive.Track>
      
      {value.map((_, i) => (
        <SliderPrimitive.Thumb 
          key={i} 
          className="SliderThumb" 
        />
      ))}
    </SliderPrimitive.Root>
  );
});

export default Slider;

