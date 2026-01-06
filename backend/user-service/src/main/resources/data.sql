INSERT INTO user_profiles (
    id,
    first_name,
    last_name,
    phone_number,
    date_of_birth,
    created_at,
    updated_at
) VALUES
(1, 'Juan', 'Pérez', '3000000001', '1995-04-12', NOW(), NULL),
(2, 'María', 'Gómez', '3000000002', '1998-09-23', NOW(), NULL),
(3, 'Carlos', 'Rodríguez', '3000000003', '1990-01-05', NOW(), NULL),
(4, 'Ana', 'Martínez', '3000000004', '2000-07-18', NOW(), NULL),
(5, 'Luis', 'Fernández', '3000000005', '1987-11-30', NOW(), NULL),
(6, 'Sofía', 'Ramírez', '3000000006', '1993-03-09', NOW(), NULL)
ON CONFLICT DO NOTHING;
