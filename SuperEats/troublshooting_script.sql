USE supereats;

INSERT INTO Recipe (title, description, instructions, cuisineType, prepTime, calories, approved) VALUES 
('Beef Stroganoff', 'Classic Russian beef dish with a creamy mushroom sauce.', 'Sauté beef, add mushrooms and sauce, simmer.', 'Russian', 40, 700, true),
('Chicken Alfredo', 'Creamy pasta dish with chicken and Alfredo sauce.', 'Cook pasta, prepare sauce, add chicken.', 'Italian', 25, 600, true),
('Fish Tacos', 'Grilled fish tacos with a refreshing slaw.', 'Grill fish, assemble tacos, add slaw.', 'Mexican', 20, 450, true),
('Pad Thai', 'Thai stir-fried noodles with shrimp and peanuts.', 'Cook noodles, stir-fry with shrimp and sauce.', 'Thai', 30, 550, true),
('Vegetable Stir Fry', 'A healthy vegetable medley stir-fried with soy sauce.', 'Stir fry vegetables with sauce, serve hot.', 'Chinese', 15, 300, true),
('BBQ Pulled Pork Sandwich', 'Slow-cooked pork with BBQ sauce on a bun.', 'Slow cook pork, shred and mix with BBQ sauce.', 'American', 480, 650, true),
('Shakshuka', 'Middle Eastern poached eggs in a spicy tomato sauce.', 'Cook tomato sauce, add eggs, simmer.', 'Middle Eastern', 25, 250, true),
('Caprese Salad', 'Fresh Italian salad with mozzarella, tomato, and basil.', 'Layer ingredients, drizzle with olive oil.', 'Italian', 10, 200, true),
('Butter Chicken', 'Indian chicken in a creamy tomato-based sauce.', 'Marinate chicken, cook in sauce, serve with rice.', 'Indian', 35, 600, true),
('Falafel Wrap', 'Mediterranean falafel wrapped with vegetables and sauce.', 'Fry falafel, assemble with vegetables in wrap.', 'Mediterranean', 20, 400, true);

-- Ratings for Recipe with ID 1
INSERT INTO Rating (userId, recipeId, rating) VALUES (1, 1, 5);
INSERT INTO Rating (userId, recipeId, rating) VALUES (2, 1, 4);
INSERT INTO Rating (userId, recipeId, rating) VALUES (3, 1, 5);

-- Ratings for Recipe with ID 2
INSERT INTO Rating (userId, recipeId, rating) VALUES (1, 2, 3);
INSERT INTO Rating (userId, recipeId, rating) VALUES (2, 2, 2);
INSERT INTO Rating (userId, recipeId, rating) VALUES (3, 2, 3);

-- Ratings for Recipe with ID 3
INSERT INTO Rating (userId, recipeId, rating) VALUES (1, 3, 5);
INSERT INTO Rating (userId, recipeId, rating) VALUES (2, 3, 5);
INSERT INTO Rating (userId, recipeId, rating) VALUES (3, 3, 4);

-- Ratings for Recipe with ID 4
INSERT INTO Rating (userId, recipeId, rating) VALUES (1, 4, 4);
INSERT INTO Rating (userId, recipeId, rating) VALUES (2, 4, 4);
INSERT INTO Rating (userId, recipeId, rating) VALUES (3, 4, 4);

-- Ratings for Recipe with ID 5
INSERT INTO Rating (userId, recipeId, rating) VALUES (1, 5, 2);
INSERT INTO Rating (userId, recipeId, rating) VALUES (2, 5, 3);
INSERT INTO Rating (userId, recipeId, rating) VALUES (3, 5, 3);

-- Ratings for Recipe with ID 6
INSERT INTO Rating (userId, recipeId, rating) VALUES (1, 6, 5);
INSERT INTO Rating (userId, recipeId, rating) VALUES (2, 6, 4);
INSERT INTO Rating (userId, recipeId, rating) VALUES (3, 6, 5);
