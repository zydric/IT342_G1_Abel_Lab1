// Auth API service - handles all authentication related API calls

const API_BASE_URL = 'http://localhost:8080/api/auth';

// Helper function for API requests
const makeRequest = async (endpoint, options = {}) => {
  const url = `${API_BASE_URL}${endpoint}`;
  
  const config = {
    headers: {
      'Content-Type': 'application/json',
      ...options.headers
    },
    ...options
  };

  try {
    const response = await fetch(url, config);
    const data = await response.json();

    if (!response.ok) {
      throw new Error(data.message || 'Something went wrong');
    }

    return data;
  } catch (error) {
    throw error;
  }
};

export const authService = {
  // Register new user
  register: async (userData) => {
    return makeRequest('/register', {
      method: 'POST',
      body: JSON.stringify(userData)
    });
  },

  // Login user
  login: async (credentials) => {
    return makeRequest('/login', {
      method: 'POST',
      body: JSON.stringify(credentials)
    });
  },

  // Logout user (client-side)
  logout: () => {
    // Since backend logout doesn't require server call for JWT,
    // we just clear local storage
    return Promise.resolve();
  }
};
