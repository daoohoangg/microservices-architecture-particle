import axios from 'axios';

const API_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api/auth';

export const login = async (credentials) => {
  return axios.post(`${API_URL}/login`, credentials);
};
