import { createContext, useContext, useState, useEffect } from 'react';
import { storage } from '../utils/storage';
import { authService } from '../services/authService';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // Load user from localStorage on mount
  useEffect(() => {
    const storedUser = storage.getUser();
    const token = storage.getToken();
    
    if (storedUser && token) {
      setUser(storedUser);
    }
    setLoading(false);
  }, []);

  // Register function
  const register = async (userData) => {
    const response = await authService.register(userData);
    
    // Save token and user data
    storage.setToken(response.token);
    storage.setUser({
      id: response.id,
      email: response.email,
      firstName: response.firstName,
      lastName: response.lastName
    });
    
    setUser({
      id: response.id,
      email: response.email,
      firstName: response.firstName,
      lastName: response.lastName
    });
    
    return response;
  };

  // Login function
  const login = async (credentials) => {
    const response = await authService.login(credentials);
    
    // Save token and user data
    storage.setToken(response.token);
    storage.setUser({
      id: response.id,
      email: response.email,
      firstName: response.firstName,
      lastName: response.lastName
    });
    
    setUser({
      id: response.id,
      email: response.email,
      firstName: response.firstName,
      lastName: response.lastName
    });
    
    return response;
  };

  // Logout function
  const logout = () => {
    storage.clearAuth();
    setUser(null);
    authService.logout();
  };

  const value = {
    user,
    login,
    register,
    logout,
    isAuthenticated: !!user,
    loading
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook to use auth context
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider');
  }
  return context;
};
