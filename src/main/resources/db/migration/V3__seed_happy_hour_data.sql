-- Seed data for Belmont Shore, Naples, and 2nd & PCH establishments
-- Last verified: January 2026

-- Establishments
INSERT INTO establishments (id, name, type, address, phone, website, instagram, description, last_verified_at, created_at, updated_at) VALUES
('est-001', 'Riley''s on 2nd Street', 'bar_restaurant', '5331 2nd St, Long Beach, CA 90803', '(562) 434-1423', 'https://rileysonsecond.com', '@rileysonsecond', 'Great little bar restaurant in Long Beach with excellent happy hour specials.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-002', 'Saint & Second', 'bar', '4828 2nd St, Long Beach, CA 90803', '(562) 433-4232', 'https://saintandsecond.com', '@saintandsecond', 'Popular bar on 2nd Street known for craft cocktails and lively atmosphere.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-003', 'Belmont Brewing Co', 'bar_restaurant', '25 39th Pl, Long Beach, CA 90803', '(562) 433-3891', 'https://belmontbrewing.com', '@belmontbrewing', 'Oceanfront brewpub with house-made beers and California cuisine. Great place for happy hour.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-004', 'Angel''s Share Whiskey Lounge', 'bar', '5110 2nd St, Long Beach, CA 90803', '(562) 343-2787', NULL, '@angelssharewhiskey', 'Cocktail bar specializing in whiskey with delicious cocktails and late happy hour.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-005', 'The Good Bar', 'bar', '140 Linden Ave, Long Beach, CA 90802', '(562) 436-9800', NULL, '@thegoodbarlb', 'Skater-themed bar with American eatery. Fun atmosphere and good drinks.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-006', 'Nick''s on 2nd', 'restaurant', '5334 E 2nd St, Long Beach, CA 90803', '(562) 434-1212', 'https://nicksonsecond.com', '@nicksonsecond', 'American comfort food restaurant with a great bar scene.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-007', 'Roe Seafood', 'restaurant', '5308 E 2nd St, Long Beach, CA 90803', '(562) 434-4763', 'https://roeseafood.com', '@roeseafoodlb', 'Market fresh fish in striking surrounds. Upscale seafood dining.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-008', 'Beachwood Brewing 2nd & PCH', 'bar_restaurant', '210 E 3rd St, Long Beach, CA 90802', '(562) 436-4020', 'https://beachwoodbrewing.com', '@beachwoodbrewing', 'Award-winning craft brewery with excellent food and beer selection.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-009', 'The Attic', 'bar_restaurant', '3441 E Broadway, Long Beach, CA 90803', '(562) 433-0153', 'https://theatticonbroadway.com', '@theatticlb', 'Neighborhood gastropub with creative American fare and craft cocktails.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-010', 'Boathouse on the Bay', 'bar_restaurant', '190 N Marina Dr, Long Beach, CA 90803', '(562) 493-6200', 'https://boathouseonthebay.com', '@boathouseonthebay', 'Waterfront dining with beautiful views of Alamitos Bay. Great for cocktails and seafood.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-011', 'The Bungalow', 'bar_restaurant', '5003 E Ocean Blvd, Long Beach, CA 90803', '(562) 433-0478', NULL, '@bungalowlb', 'Casual beach bar and restaurant with great happy hour deals.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-012', 'Legends Sports Bar', 'bar', '5236 E 2nd St, Long Beach, CA 90803', '(562) 433-5743', NULL, '@legendslb', 'Classic sports bar with multiple TVs and great drink specials.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-013', 'Panama Joe''s', 'bar_restaurant', '5100 E 2nd St, Long Beach, CA 90803', '(562) 434-7417', 'https://panamajoes.com', '@panamajoes', 'Tropical-themed restaurant and bar with great margaritas and Mexican food.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-014', 'Michael''s on Naples', 'restaurant', '5620 E 2nd St, Long Beach, CA 90803', '(562) 439-7080', 'https://michaelsonnaples.com', '@michaelsonnaples', 'Upscale Italian restaurant in Naples with excellent wine selection.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('est-015', 'Simmzy''s', 'bar_restaurant', '5271 E 2nd St, Long Beach, CA 90803', '(562) 439-9200', 'https://simmzys.com', '@simmzys', 'Gastropub with craft beers and elevated pub fare.', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Riley's on 2nd Street (Mon-Thu 3-7pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-001-1', 'est-001', 1, '15:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-001-2', 'est-001', 2, '15:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-001-3', 'est-001', 3, '15:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-001-4', 'est-001', 4, '15:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Saint & Second (Mon-Fri 4-7pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-002-1', 'est-002', 1, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-002-2', 'est-002', 2, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-002-3', 'est-002', 3, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-002-4', 'est-002', 4, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-002-5', 'est-002', 5, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Belmont Brewing Co (Daily 3-6pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-003-0', 'est-003', 0, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-003-1', 'est-003', 1, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-003-2', 'est-003', 2, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-003-3', 'est-003', 3, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-003-4', 'est-003', 4, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-003-5', 'est-003', 5, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-003-6', 'est-003', 6, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Angel's Share (Mon-Thu 3-6pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-004-1', 'est-004', 1, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-004-2', 'est-004', 2, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-004-3', 'est-004', 3, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-004-4', 'est-004', 4, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for The Good Bar (Mon-Fri 4-7pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-005-1', 'est-005', 1, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-005-2', 'est-005', 2, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-005-3', 'est-005', 3, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-005-4', 'est-005', 4, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-005-5', 'est-005', 5, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Nick's on 2nd (Tue-Fri 3-6pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-006-2', 'est-006', 2, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-006-3', 'est-006', 3, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-006-4', 'est-006', 4, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-006-5', 'est-006', 5, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Roe Seafood (Mon-Fri 4-6pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-007-1', 'est-007', 1, '16:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-007-2', 'est-007', 2, '16:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-007-3', 'est-007', 3, '16:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-007-4', 'est-007', 4, '16:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-007-5', 'est-007', 5, '16:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Beachwood Brewing (Mon-Fri 3-6pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-008-1', 'est-008', 1, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-008-2', 'est-008', 2, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-008-3', 'est-008', 3, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-008-4', 'est-008', 4, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-008-5', 'est-008', 5, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for The Attic (Mon-Fri 3-6pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-009-1', 'est-009', 1, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-009-2', 'est-009', 2, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-009-3', 'est-009', 3, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-009-4', 'est-009', 4, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-009-5', 'est-009', 5, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Boathouse on the Bay (Mon-Fri 3-6pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-010-1', 'est-010', 1, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-010-2', 'est-010', 2, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-010-3', 'est-010', 3, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-010-4', 'est-010', 4, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-010-5', 'est-010', 5, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for The Bungalow (Daily 4-7pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-011-0', 'est-011', 0, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-011-1', 'est-011', 1, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-011-2', 'est-011', 2, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-011-3', 'est-011', 3, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-011-4', 'est-011', 4, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-011-5', 'est-011', 5, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-011-6', 'est-011', 6, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Legends Sports Bar (Daily 4-7pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-012-0', 'est-012', 0, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-012-1', 'est-012', 1, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-012-2', 'est-012', 2, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-012-3', 'est-012', 3, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-012-4', 'est-012', 4, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-012-5', 'est-012', 5, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-012-6', 'est-012', 6, '16:00', '19:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Panama Joe's (Mon-Fri 3-6pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-013-1', 'est-013', 1, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-013-2', 'est-013', 2, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-013-3', 'est-013', 3, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-013-4', 'est-013', 4, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-013-5', 'est-013', 5, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Michael's on Naples (Tue-Sat 4-6pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-014-2', 'est-014', 2, '16:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-014-3', 'est-014', 3, '16:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-014-4', 'est-014', 4, '16:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-014-5', 'est-014', 5, '16:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-014-6', 'est-014', 6, '16:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Happy Hours for Simmzy's (Mon-Fri 3-6pm)
INSERT INTO happy_hours (id, establishment_id, day_of_week, start_time, end_time, last_verified_at, created_at, updated_at) VALUES
('hh-015-1', 'est-015', 1, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-015-2', 'est-015', 2, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-015-3', 'est-015', 3, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-015-4', 'est-015', 4, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hh-015-5', 'est-015', 5, '15:00', '18:00', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Riley's on 2nd Street
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-001-1', 'hh-001-1', 'House Margarita', 'House Margarita', 'drink', 6.00, 12.00, '$6 (normally $12)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-001-2', 'hh-001-1', 'Draft Beer', 'Draft Beer', 'drink', 4.00, 7.00, '$4 (normally $7)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-001-3', 'hh-001-1', 'Well Drinks', 'Well Drinks', 'drink', 5.00, 9.00, '$5 (normally $9)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-001-4', 'hh-001-1', 'Fish Tacos (2)', 'Fish Tacos', 'food', 8.00, 14.00, '$8 (normally $14)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Saint & Second
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-002-1', 'hh-002-1', 'Craft Cocktails', 'Craft Cocktails', 'drink', 8.00, 14.00, '$8 (normally $14)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-002-2', 'hh-002-1', 'House Wine', 'House Wine', 'drink', 6.00, 10.00, '$6 (normally $10)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-002-3', 'hh-002-1', 'Local Draft Beer', 'Local Draft Beer', 'drink', 5.00, 8.00, '$5 (normally $8)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Belmont Brewing Co
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-003-1', 'hh-003-1', 'House Beers', 'House Beers', 'drink', 4.50, 7.50, '$4.50 (normally $7.50)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-003-2', 'hh-003-1', 'Fish & Chips', 'Fish & Chips', 'food', 12.00, 18.00, '$12 (normally $18)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-003-3', 'hh-003-1', 'Calamari', 'Calamari', 'food', 9.00, 14.00, '$9 (normally $14)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-003-4', 'hh-003-1', 'Well Drinks', 'Well Drinks', 'drink', 5.00, 9.00, '$5 (normally $9)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Angel's Share
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-004-1', 'hh-004-1', 'Select Whiskey Flights', 'Whiskey Flight', 'drink', 12.00, 20.00, '$12 (normally $20)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-004-2', 'hh-004-1', 'Classic Cocktails', 'Classic Cocktails', 'drink', 8.00, 13.00, '$8 (normally $13)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-004-3', 'hh-004-1', 'Draft Beer', 'Draft Beer', 'drink', 4.00, 7.00, '$4 (normally $7)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for The Good Bar
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-005-1', 'hh-005-1', 'PBR Tallboys', 'PBR Tallboy', 'drink', 3.00, 5.00, '$3 (normally $5)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-005-2', 'hh-005-1', 'Well Drinks', 'Well Drinks', 'drink', 5.00, 8.00, '$5 (normally $8)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-005-3', 'hh-005-1', 'Loaded Fries', 'Loaded Fries', 'food', 6.00, 10.00, '$6 (normally $10)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Nick's on 2nd
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-006-1', 'hh-006-2', 'House Wine', 'House Wine', 'drink', 5.00, 9.00, '$5 (normally $9)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-006-2', 'hh-006-2', 'Draft Beer', 'Draft Beer', 'drink', 4.00, 7.00, '$4 (normally $7)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-006-3', 'hh-006-2', 'Sliders (3)', 'Sliders', 'food', 8.00, 13.00, '$8 (normally $13)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Roe Seafood
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-007-1', 'hh-007-1', 'Oysters (6)', 'Oysters', 'food', 12.00, 18.00, '$12 (normally $18)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-007-2', 'hh-007-1', 'House Wine', 'House Wine', 'drink', 7.00, 12.00, '$7 (normally $12)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-007-3', 'hh-007-1', 'Select Cocktails', 'Select Cocktails', 'drink', 9.00, 14.00, '$9 (normally $14)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Beachwood Brewing
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-008-1', 'hh-008-1', 'House Beers', 'House Beers', 'drink', 5.00, 8.00, '$5 (normally $8)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-008-2', 'hh-008-1', 'Beer Flights', 'Beer Flight', 'drink', 10.00, 15.00, '$10 (normally $15)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-008-3', 'hh-008-1', 'Wings (8)', 'Wings', 'food', 8.00, 13.00, '$8 (normally $13)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for The Attic
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-009-1', 'hh-009-1', 'Well Drinks', 'Well Drinks', 'drink', 5.00, 9.00, '$5 (normally $9)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-009-2', 'hh-009-1', 'Draft Beer', 'Draft Beer', 'drink', 4.00, 7.00, '$4 (normally $7)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-009-3', 'hh-009-1', 'Deviled Eggs', 'Deviled Eggs', 'food', 6.00, 10.00, '$6 (normally $10)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Boathouse on the Bay
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-010-1', 'hh-010-1', 'House Margarita', 'House Margarita', 'drink', 7.00, 12.00, '$7 (normally $12)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-010-2', 'hh-010-1', 'Draft Beer', 'Draft Beer', 'drink', 5.00, 8.00, '$5 (normally $8)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-010-3', 'hh-010-1', 'Shrimp Cocktail', 'Shrimp Cocktail', 'food', 10.00, 16.00, '$10 (normally $16)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for The Bungalow
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-011-1', 'hh-011-1', 'House Margarita', 'House Margarita', 'drink', 6.00, 11.00, '$6 (normally $11)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-011-2', 'hh-011-1', 'Draft Beer', 'Draft Beer', 'drink', 4.00, 7.00, '$4 (normally $7)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-011-3', 'hh-011-1', 'Nachos', 'Nachos', 'food', 8.00, 13.00, '$8 (normally $13)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Legends Sports Bar
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-012-1', 'hh-012-1', 'Domestic Beer', 'Domestic Beer', 'drink', 3.00, 5.00, '$3 (normally $5)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-012-2', 'hh-012-1', 'Well Drinks', 'Well Drinks', 'drink', 4.00, 7.00, '$4 (normally $7)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-012-3', 'hh-012-1', 'Wings (10)', 'Wings', 'food', 7.00, 12.00, '$7 (normally $12)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Panama Joe's
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-013-1', 'hh-013-1', 'House Margarita', 'House Margarita', 'drink', 5.00, 10.00, '$5 (normally $10)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-013-2', 'hh-013-1', 'Draft Beer', 'Draft Beer', 'drink', 4.00, 7.00, '$4 (normally $7)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-013-3', 'hh-013-1', 'Street Tacos (3)', 'Street Tacos', 'food', 7.00, 12.00, '$7 (normally $12)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Michael's on Naples
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-014-1', 'hh-014-2', 'House Wine', 'House Wine', 'drink', 8.00, 14.00, '$8 (normally $14)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-014-2', 'hh-014-2', 'Select Cocktails', 'Select Cocktails', 'drink', 10.00, 15.00, '$10 (normally $15)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-014-3', 'hh-014-2', 'Bruschetta', 'Bruschetta', 'food', 8.00, 13.00, '$8 (normally $13)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Specials for Simmzy's
INSERT INTO specials (id, happy_hour_id, description, item_name, item_type, happy_hour_price, original_price, discount_info, last_verified_at, created_at, updated_at) VALUES
('sp-015-1', 'hh-015-1', 'Craft Beer', 'Craft Beer', 'drink', 5.00, 8.00, '$5 (normally $8)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-015-2', 'hh-015-1', 'House Wine', 'House Wine', 'drink', 6.00, 10.00, '$6 (normally $10)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sp-015-3', 'hh-015-1', 'Truffle Fries', 'Truffle Fries', 'food', 7.00, 11.00, '$7 (normally $11)', '2026-01-15 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
