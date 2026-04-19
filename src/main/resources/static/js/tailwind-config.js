// tailwind-config.js
tailwind.config = {
  darkMode: "class",
  theme: {
    extend: {
      colors: {
        "surface-container-lowest": "#ffffff",
        "tertiary": "#0a0500",
        "on-secondary-container": "#672c00",
        "on-secondary": "#ffffff",
        "tertiary-container": "#2a1c00",
        "error-container": "#ffdad6",
        "tertiary-fixed": "#ffdea4",
        "on-secondary-fixed": "#331200",
        "on-error-container": "#93000a",
        "secondary-fixed": "#ffdbc9",
        "surface-tint": "#4d5f7d",
        "surface-variant": "#e0e3e6",
        "surface-container-low": "#f2f4f7",
        "on-surface-variant": "#44474d",
        "background": "#f7f9fc",
        "on-primary-fixed-variant": "#364764",
        "secondary-container": "#fc8a40",
        "primary-fixed": "#d6e3ff",
        "primary": "#1e293b",
        "outline": "#75777e",
        "secondary-fixed-dim": "#ffb68d",
        "tertiary-fixed-dim": "#f4be4e",
        "inverse-on-surface": "#eff1f4",
        "secondary": "#9b4500",
        "on-primary-container": "#f1f5f9",
        "outline-variant": "#c4c6ce",
        "surface-dim": "#d8dadd",
        "on-secondary-fixed-variant": "#763300",
        "on-error": "#ffffff",
        "surface-bright": "#f7f9fc",
        "on-tertiary": "#ffffff",
        "inverse-primary": "#b5c7ea",
        "surface-container": "#eceef1",
        "inverse-surface": "#2d3133",
        "primary-fixed-dim": "#b5c7ea",
        "surface-container-highest": "#e0e3e6",
        "on-tertiary-fixed-variant": "#5d4200",
        "on-primary": "#ffffff",
        "on-tertiary-fixed": "#261900",
        "on-tertiary-container": "#ad7f0b",
        "error": "#ba1a1a",
        "surface-container-high": "#e6e8eb",
        "on-surface": "#191c1e",
        "primary-container": "#334155",
        "on-primary-fixed": "#071c36",
        "on-background": "#191c1e",
        "surface": "#f7f9fc"
      },
      fontFamily: {
        headline: ["Manrope"],
        body: ["Public Sans"],
        label: ["Inter"]
      },
      borderRadius: { DEFAULT: "0.25rem", lg: "0.5rem", xl: "0.75rem", full: "9999px" }
    }
  }
};