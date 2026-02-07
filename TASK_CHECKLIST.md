DONE

## Backend Setup
- Set up Spring Boot project structure (initial commit)
- Add Spring Security, JWT, and validation dependencies - `adc4c45`
- Configure database (MySQL) and JWT settings - `f9b9594`
- Create User entity with email, password, and name fields - `9e47d31`
- Create UserRepository with email lookup methods - `2b7a67e`
- Implement JWT TokenProvider for token generation/validation - `a3a85e6`
- Configure Spring Security with BCrypt password encoder and CORS - `17079f3`
- Create authentication DTOs (RegisterRequest, LoginRequest, AuthResponse, MessageResponse) - `e5723ed`
- Implement AuthService with register and login logic - `702e50c`
- Create AuthController with register/login/logout endpoints - `a1e6434`

IN-PROGRESS

- None

TODO

## Backend Testing (Manual via Postman)
- Test user registration endpoint
- Test user login endpoint
- Verify JWT token generation
- Test duplicate email validation

## Frontend
- Set up frontend project (React/Vite)
- Set up frontend routing and page structure
- Implement frontend auth flow and API client
- Build core UI components and screens (Login, Register, Dashboard)
