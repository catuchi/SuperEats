-- DDL for table ADMIN
CREATE TABLE ADMIN (
    USERID INTEGER(32) NOT NULL,
    NAME VARCHAR(100),
    EMAIL VARCHAR(100),
    PASSWORD VARCHAR(100),
    ROLE VARCHAR(20)
);

-- DDL for table GROCERYLIST
CREATE TABLE GROCERYLIST (
    LISTID INTEGER(32) NOT NULL,
    USERID INTEGER(32) NOT NULL
);

-- DDL for table GROCERYLISTINGREDIENT
CREATE TABLE GROCERYLISTINGREDIENT (
    LISTINGREDIENTID INTEGER(32) NOT NULL,
    LISTID INTEGER(32) NOT NULL,
    INGREDIENTID INTEGER(32) NOT NULL,
    QUANTITY DOUBLE(64),
    UNIT VARCHAR(20)
);

-- DDL for table INGREDIENT
CREATE TABLE INGREDIENT (
    INGREDIENTID INTEGER(32) NOT NULL,
    NAME VARCHAR(100) NOT NULL
);

-- DDL for table MEALPLAN
CREATE TABLE MEALPLAN (
    MEALPLANID INTEGER(32) NOT NULL,
    USERID INTEGER(32) NOT NULL,
    NAME VARCHAR(100),
    STARTDATE DATE(10),
    ENDDATE DATE(10)
);

-- DDL for table MEALPLANRECIPE
CREATE TABLE MEALPLANRECIPE (
    MEALPLANRECIPEID INTEGER(32) NOT NULL,
    MEALPLANID INTEGER(32) NOT NULL,
    RECIPEID INTEGER(32) NOT NULL
);

-- DDL for table RATING
CREATE TABLE RATING (
    RATINGID INTEGER(32) NOT NULL,
    USERID INTEGER(32),
    RECIPEID INTEGER(32),
    RATING INTEGER(32)
);

-- DDL for table RECIPE
CREATE TABLE RECIPE (
    RECIPEID INTEGER(32) NOT NULL,
    TITLE VARCHAR(100),
    DESCRIPTION VARCHAR(100),
    INSTRUCTIONS VARCHAR(100),
    CUISINETYPE VARCHAR(100),
    PREPTIME INTEGER(32),
    CALORIES INTEGER(32),
    APPROVED BOOLEAN(0)
);

-- DDL for table RECIPEINGREDIENT
CREATE TABLE RECIPEINGREDIENT (
    RECIPEINGREDIENTID INTEGER(32) NOT NULL,
    RECIPEID INTEGER(32) NOT NULL,
    INGREDIENTID INTEGER(32) NOT NULL,
    QUANTITY DOUBLE(64),
    UNIT VARCHAR(20)
);

-- DDL for table USER
CREATE TABLE USER (
    USERID INTEGER(32) NOT NULL,
    NAME VARCHAR(100),
    EMAIL VARCHAR(100),
    PASSWORD VARCHAR(100),
    ROLE VARCHAR(20),
    DIETARYPREFERENCES VARCHAR(100),
    PROFILEPICTURE VARCHAR(100)
);

