# Architecture

The project is organized as a monorepo containing two independent applications:

- `backend/` exposes the REST API, applies business rules and persists data in MySQL.
- `frontend/` provides the React interface and consumes the API through Axios.

The backend follows a standard separation between controllers, services, repositories, entities, DTOs and factories. Schema migrations are stored in `backend/src/main/resources/db/changelog/`.

Local configuration and secrets are provided through environment variables. Example values are documented in `.env.example`.