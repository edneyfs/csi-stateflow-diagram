/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
    "./site_suporte/**/*.{html,js}"
  ],
  theme: {
    extend: {
      colors: {
        mic: {
          bg: '#EAF1FB',
          border: '#6C8EBF',
          text: '#1C3D5A'
        },
        result: {
          bg: '#E2F0D9',
          border: '#70AD47',
          text: '#276A3C'
        },
        subflow: {
          bg: '#FFF2CC',
          border: '#D6B656',
          text: '#7A6014'
        },
        note: {
          bg: '#FFF9C4',
          border: '#FBC02D',
          text: '#5D4037'
        }
      }
    },
  },
  plugins: [],
}
