import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
//import './index.css'
import './home_styles.css'
import CarApp from './CarApp.jsx'
import FilterDisplayLine from './filter-display/FilterDisplayLine.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <CarApp />
  </StrictMode>,
)
