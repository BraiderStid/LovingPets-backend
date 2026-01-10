-- =========================
-- Appointments
-- =========================
INSERT INTO appointments (
    id,
    appointment_date_time,
    created_at,
    notes,
    owner_id,
    pet_id,
    status
) VALUES
(1, NOW() + INTERVAL '1 day',   NOW(), 'General checkup',              1, 1, 'PENDING'),
(2, NOW() - INTERVAL '2 days',  NOW(), 'Vaccination visit',           2, 2, 'ATTENDED'),
(3, NOW() - INTERVAL '5 days',  NOW(), 'Post surgery review',         3, 3, 'ATTENDED'),
(4, NOW() + INTERVAL '3 days',  NOW(), 'Dental cleaning',             4, 4, 'PENDING'),
(5, NOW() - INTERVAL '1 day',   NOW(), 'Emergency visit',             5, 5, 'CANCELLED'),
(6, NOW() - INTERVAL '10 days', NOW(), 'Skin allergy consultation',   6, 6, 'ATTENDED')
ON CONFLICT (id) DO NOTHING;

-- =========================
-- Medical Records
-- =========================
INSERT INTO medical_records (
    id,
    created_at,
    diagnosis,
    employee_id,
    appointment_id
) VALUES
(1, NOW(), 'Routine vaccination completed',         2, 2),
(2, NOW(), 'Surgery recovery progressing well',     2, 3),
(3, NOW(), 'Chronic skin allergy detected',         2, 6)
ON CONFLICT (id) DO NOTHING;

-- =========================
-- Treatments (NO start_date)
-- =========================
INSERT INTO treatments (
    id,
    description,
    next_review_date,
    status,
    medical_record_id
) VALUES
-- Finished treatments MUST NOT have next review date
(1, 'Antibiotic treatment for 7 days',
    NULL,
    'FINISHED',
    1),

(2, 'Pain management medication',
    NULL,
    'FINISHED',
    2),

-- Active treatments MAY have next review date
(3, 'Topical allergy treatment',
    CURRENT_DATE + 14,
    'ACTIVE',
    3),

(4, 'Diet adjustment and supplements',
    CURRENT_DATE + 30,
    'ACTIVE',
    3)
ON CONFLICT (id) DO NOTHING;

-- =========================
-- Reset sequences to MAX(id)
-- =========================
SELECT setval(
    pg_get_serial_sequence('appointments', 'id'),
    COALESCE((SELECT MAX(id) FROM appointments), 1)
);

SELECT setval(
    pg_get_serial_sequence('medical_records', 'id'),
    COALESCE((SELECT MAX(id) FROM medical_records), 1)
);

SELECT setval(
    pg_get_serial_sequence('treatments', 'id'),
    COALESCE((SELECT MAX(id) FROM treatments), 1)
);
