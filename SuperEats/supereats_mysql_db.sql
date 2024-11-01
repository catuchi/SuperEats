-- Create Database and Use it
CREATE DATABASE IF NOT EXISTS supereats;
USE supereats;

-- Drop existing tables
DROP TABLE IF EXISTS MealPlanRecipe;
DROP TABLE IF EXISTS RecipeIngredient;
DROP TABLE IF EXISTS GroceryListIngredient;
DROP TABLE IF EXISTS Rating;
DROP TABLE IF EXISTS MealPlan;
DROP TABLE IF EXISTS GroceryList;
DROP TABLE IF EXISTS Recipe;
DROP TABLE IF EXISTS Ingredient;
DROP TABLE IF EXISTS Admin;
DROP TABLE IF EXISTS User;

-- Create Tables

-- DDL and inserts for table USER
CREATE TABLE USER (
    USERID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100),
    EMAIL VARCHAR(100),
    PASSWORD VARCHAR(100),
    ROLE VARCHAR(20),
    DIETARYPREFERENCES VARCHAR(100),
    PROFILEPICTURE VARCHAR(100)
);

INSERT INTO USER (NAME, EMAIL, PASSWORD, ROLE, DIETARYPREFERENCES, PROFILEPICTURE) VALUES
('Alice Johnson', 'alice@example.com', 'password1', 'USER', 'Vegetarian', 'alice.jpg'),
('Bob Smith', 'bob@example.com', 'password2', 'USER', 'Vegan', 'bob.jpg'),
('Charlie Brown', 'charlie@example.com', 'password3', 'USER', 'Gluten-Free', 'charlie.jpg'),
('David Lee', 'david@example.com', 'password4', 'USER', 'Pescatarian', 'david.jpg'),
('Eve Turner', 'eve@example.com', 'password5', 'USER', 'None', 'eve.jpg');

-- DDL and inserts for table ADMIN
CREATE TABLE ADMIN (
    USERID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100),
    EMAIL VARCHAR(100),
    PASSWORD VARCHAR(100),
    ROLE VARCHAR(20)
);

INSERT INTO ADMIN (NAME, EMAIL, PASSWORD, ROLE) VALUES
('Admin One', 'admin1@example.com', 'adminpass1', 'ADMIN'),
('Admin Two', 'admin2@example.com', 'adminpass2', 'ADMIN'),
('Admin Three', 'admin3@example.com', 'adminpass3', 'ADMIN'),
('Admin Four', 'admin4@example.com', 'adminpass4', 'ADMIN'),
('Admin Five', 'admin5@example.com', 'adminpass5', 'ADMIN');

-- DDL and inserts for table RECIPE
CREATE TABLE RECIPE (
    RECIPEID INTEGER PRIMARY KEY AUTO_INCREMENT,
    TITLE VARCHAR(100),
    DESCRIPTION VARCHAR(255),
    INSTRUCTIONS TEXT,
    CUISINETYPE VARCHAR(100),
    PREPTIME INTEGER,
    CALORIES INTEGER,
    APPROVED BOOLEAN
);

INSERT INTO RECIPE (TITLE, DESCRIPTION, INSTRUCTIONS, CUISINETYPE, PREPTIME, CALORIES, APPROVED) VALUES
('Spaghetti Bolognese', 'Classic Italian pasta dish', 'Cook pasta, prepare sauce, mix together.', 'Italian', 30, 600, TRUE),
('Vegan Tacos', 'Delicious plant-based tacos', 'Prepare vegetables, cook beans, assemble tacos.', 'Mexican', 20, 450, TRUE),
('Chicken Curry', 'Spicy chicken curry with rice', 'Cook chicken, add spices, simmer with sauce.', 'Indian', 45, 700, TRUE),
('Miso Soup', 'Traditional Japanese soup with tofu and seaweed', 'Boil water, add miso paste, add tofu and seaweed.', 'Japanese', 15, 100, FALSE),
('Caesar Salad', 'Crispy romaine lettuce with Caesar dressing', 'Mix lettuce, croutons, dressing, and cheese.', 'American', 10, 200, TRUE);

-- DDL and inserts for table RATING
CREATE TABLE RATING (
    RATINGID INTEGER PRIMARY KEY AUTO_INCREMENT,
    USERID INTEGER,
    RECIPEID INTEGER,
    RATING INTEGER,
    FOREIGN KEY (USERID) REFERENCES USER(USERID),
    FOREIGN KEY (RECIPEID) REFERENCES RECIPE(RECIPEID)
);

INSERT INTO RATING (USERID, RECIPEID, RATING) VALUES
(1, 1, 5),
(2, 1, 4),
(3, 2, 5),
(4, 3, 3),
(5, 4, 4);

-- DDL and inserts for table INGREDIENT
CREATE TABLE INGREDIENT (
    INGREDIENTID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL
);

INSERT INTO INGREDIENT (NAME) VALUES
('Tomato'),
('Onion'),
('Garlic'),
('Pasta'),
('Ground Beef');

-- DDL and inserts for table RECIPEINGREDIENT
CREATE TABLE RECIPEINGREDIENT (
    RECIPEINGREDIENTID INTEGER PRIMARY KEY AUTO_INCREMENT,
    RECIPEID INTEGER,
    INGREDIENTID INTEGER,
    QUANTITY DOUBLE,
    UNIT VARCHAR(20),
    FOREIGN KEY (RECIPEID) REFERENCES RECIPE(RECIPEID),
    FOREIGN KEY (INGREDIENTID) REFERENCES INGREDIENT(INGREDIENTID)
);

INSERT INTO RECIPEINGREDIENT (RECIPEID, INGREDIENTID, QUANTITY, UNIT) VALUES
(1, 1, 3, 'cups'),
(1, 2, 1, 'whole'),
(2, 3, 0.5, 'cup'),
(2, 4, 1, 'lb'),
(3, 5, 2, 'tbsp');

-- DDL and inserts for table MEALPLAN
CREATE TABLE MEALPLAN (
    MEALPLANID INTEGER PRIMARY KEY AUTO_INCREMENT,
    USERID INTEGER,
    NAME VARCHAR(100),
    STARTDATE DATE,
    ENDDATE DATE,
    FOREIGN KEY (USERID) REFERENCES USER(USERID)
);

INSERT INTO MEALPLAN (USERID, NAME, STARTDATE, ENDDATE) VALUES
(1, 'Weekly Vegetarian Plan', '2023-10-01', '2023-10-07'),
(2, 'Keto Meal Plan', '2023-10-01', '2023-10-07'),
(3, 'Family Dinner Plan', '2023-10-01', '2023-10-07'),
(4, 'Vegan Meal Plan', '2023-10-01', '2023-10-07'),
(5, 'Balanced Diet Plan', '2023-10-01', '2023-10-07');

-- DDL and inserts for table MEALPLANRECIPE
CREATE TABLE MEALPLANRECIPE (
    MEALPLANRECIPEID INTEGER PRIMARY KEY AUTO_INCREMENT,
    MEALPLANID INTEGER,
    RECIPEID INTEGER,
    FOREIGN KEY (MEALPLANID) REFERENCES MEALPLAN(MEALPLANID),
    FOREIGN KEY (RECIPEID) REFERENCES RECIPE(RECIPEID)
);

INSERT INTO MEALPLANRECIPE (MEALPLANID, RECIPEID) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 1),
(4, 4);

-- DDL and inserts for table GROCERYLIST
CREATE TABLE GROCERYLIST (
    LISTID INTEGER PRIMARY KEY AUTO_INCREMENT,
    USERID INTEGER,
    FOREIGN KEY (USERID) REFERENCES USER(USERID)
);

INSERT INTO GROCERYLIST (USERID) VALUES
(1),
(2),
(3),
(4),
(5);

-- DDL and inserts for table GROCERYLISTINGREDIENT
CREATE TABLE GROCERYLISTINGREDIENT (
    LISTINGREDIENTID INTEGER PRIMARY KEY AUTO_INCREMENT,
    LISTID INTEGER,
    INGREDIENTID INTEGER,
    QUANTITY DOUBLE,
    UNIT VARCHAR(20),
    FOREIGN KEY (LISTID) REFERENCES GROCERYLIST(LISTID),
    FOREIGN KEY (INGREDIENTID) REFERENCES INGREDIENT(INGREDIENTID)
);

INSERT INTO GROCERYLISTINGREDIENT (LISTID, INGREDIENTID, QUANTITY, UNIT) VALUES
(1, 1, 2, 'pieces'),
(1, 2, 1, 'whole'),
(2, 3, 0.5, 'lb'),
(3, 4, 3, 'tbsp'),
(4, 5, 1, 'cup');