import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Dashboard = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-card">
        <h1>Welcome to Dashboard</h1>
        
        <div className="user-info">
          <h2>Your Profile</h2>
          <div className="info-item">
            <span className="label">Name:</span>
            <span className="value">{user?.firstName} {user?.lastName}</span>
          </div>
          <div className="info-item">
            <span className="label">Email:</span>
            <span className="value">{user?.email}</span>
          </div>
          <div className="info-item">
            <span className="label">User ID:</span>
            <span className="value">{user?.id}</span>
          </div>
        </div>

        <button onClick={handleLogout} className="btn-danger">
          Logout
        </button>
      </div>
    </div>
  );
};

export default Dashboard;
