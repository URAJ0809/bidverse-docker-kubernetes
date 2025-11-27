import React from 'react';
import { Box, Typography } from '@mui/material';

import logo1 from '../assets/logo1.png';
import logo2 from '../assets/logo2.png';
import logo3 from '../assets/logo3.png';
import logo4 from '../assets/logo4.png';

const SUPPORTER_LOGOS = [
  { src: logo1, alt: 'Generator' },
  { src: logo2, alt: 'GLMP.IT' },
  { src: logo3, alt: 'gaze it' },
  { src: logo4, alt: 'mtvs.news' },
];

function Supporters() {
  return (
    <Box sx={{ width: '100%', py: 5, px: 2, textAlign: 'center' }}>
      <Typography variant="h4" gutterBottom>
        Our Supporters
      </Typography>
      <Box
        sx={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          gap: 6, // Increased spacing
          mt: 3,
          flexWrap: 'wrap', // Allows wrapping on smaller screens
        }}
      >
        {SUPPORTER_LOGOS.map((logo, index) => (
          <React.Fragment key={index}>
            {/* Logo */}
            <Box
              component="img"
              src={logo.src}
              alt={logo.alt}
              sx={{
                width: 150,  // Increased width
                height: 'auto',
                objectFit: 'contain',
              }}
            />
            {/* Vertical line, except after the last logo */}
            {index < SUPPORTER_LOGOS.length - 1 && (
              <Box
                sx={{
                  width: '2px',
                  height: 60, // Increased height
                  backgroundColor: '#000',
                }}
              />
            )}
          </React.Fragment>
        ))}
      </Box>
    </Box>
  );
}

export default Supporters;
