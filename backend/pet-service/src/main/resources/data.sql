INSERT INTO pets (owner_id, name, species, breed, age, weight, created_at)
SELECT owner_id, name, species, breed, age, weight, CURRENT_DATE
FROM (
    SELECT 1, 'Max',   'DOG',     'LABRADOR',          5, 28.5
    UNION ALL
    SELECT 1, 'Luna',  'DOG',     'GOLDEN_RETRIEVER',  3, 24.0
    UNION ALL
    SELECT 2, 'Milo',  'CAT',     'SIAMESE',           2, 4.5
    UNION ALL
    SELECT 2, 'Nala',  'CAT',     'PERSIAN',           4, 5.2
    UNION ALL
    SELECT 3, 'Rocky', 'DOG',     'BULLDOG',           6, 22.0
    UNION ALL
    SELECT 3, 'Bella', 'DOG',     'POODLE',            1, 6.8
    UNION ALL
    SELECT 4, 'Simba', 'CAT',     'MAINE_COON',        3, 6.1
    UNION ALL
    SELECT 4, 'Chispa','RABBIT',  'MINI_REX',          2, 1.9
    UNION ALL
    SELECT 5, 'Toby',  'DOG',     'BEAGLE',            7, 14.3
    UNION ALL
    SELECT 5, 'Kiara', 'CAT',     'MIXED',             1, 3.2
    UNION ALL
    SELECT 6, 'Thor',  'DOG',     'GERMAN_SHEPHERD',   4, 32.7
    UNION ALL
    SELECT 6, 'Loki',  'DOG',     'HUSKY',             2, 27.4
    UNION ALL
    SELECT 7, 'Pelusa','HAMSTER', 'SYRIAN',            1, 0.12
    UNION ALL
    SELECT 7, 'Coco',  'BIRD',    'CANARY',            2, 0.03
    UNION ALL
    SELECT 8, 'Bruno', 'DOG',     'ROTTWEILER',        5, 40.1
) AS seed(owner_id, name, species, breed, age, weight)
WHERE NOT EXISTS (SELECT 1 FROM pets);
