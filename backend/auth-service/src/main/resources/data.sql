-- =========================
-- ROLES
-- =========================
INSERT INTO roles (id, name)
VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_CLIENTE'),
(3, 'ROLE_EMPLEADO')
ON CONFLICT (id) DO NOTHING;

-- =========================
-- USERS
-- =========================
INSERT INTO users (id, email, password, enabled, created_at)
VALUES
(1, 'admin@lovingpets.com', 'admin123', true, now()),
(2, 'cliente1@lovingpets.com', 'cliente123', true, now()),
(3, 'cliente2@lovingpets.com', 'cliente123', true, now()),
(4, 'empleado1@lovingpets.com', 'empleado123', true, now()),
(5, 'empleado2@lovingpets.com', 'empleado123', true, now()),
(6, 'cliente3@lovingpets.com', 'cliente123', true, now())
ON CONFLICT (id) DO NOTHING;

-- =========================
-- USER_ROLES
-- =========================
INSERT INTO user_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 3),
(5, 3),
(6, 2)
ON CONFLICT (user_id, role_id) DO NOTHING;

-- =========================
-- RESET SEQUENCES
-- =========================
ALTER SEQUENCE user_roles_id_seq RESTART WITH 1;
