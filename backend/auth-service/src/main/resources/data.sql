-- =========================
-- ROLES
-- =========================
INSERT INTO roles (id, name)
VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_CLIENT'),
(3, 'ROLE_EMPLOYEE')
ON CONFLICT (id) DO NOTHING;

-- =========================
-- USERS
-- =========================
INSERT INTO users (id, email, password, enabled, created_at)
VALUES
(1, 'admin@lovingpets.com', '$2y$10$p1BaRBPZ3dKZo82hd9QAVeDVX/Vzp815KKIJaJpz3ILSdiKvXQUiy', true, now()),
(2, 'cliente1@lovingpets.com', '$2y$10$rPnyrOJe0Xh.fDxvvAUP7OLPCTe4QMVqFiaqCswzsbS.wRAuzzEPq', true, now()),
(3, 'cliente2@lovingpets.com', '$2y$10$rPnyrOJe0Xh.fDxvvAUP7OLPCTe4QMVqFiaqCswzsbS.wRAuzzEPq', true, now()),
(4, 'empleado1@lovingpets.com', '$2y$10$lki3R/u8cy0g5kfUMH.XVu2on1DCwvL.FC8LdNYTOZB0Ahn6UZnZu', true, now()),
(5, 'empleado2@lovingpets.com', '$2y$10$lki3R/u8cy0g5kfUMH.XVu2on1DCwvL.FC8LdNYTOZB0Ahn6UZnZu', true, now()),
(6, 'cliente3@lovingpets.com', '$2y$10$rPnyrOJe0Xh.fDxvvAUP7OLPCTe4QMVqFiaqCswzsbS.wRAuzzEPq', true, now())
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