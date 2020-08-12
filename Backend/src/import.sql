DROP DATABASE IF EXISTS `Munchables`;
CREATE DATABASE `Munchables`;
USE `Munchables`;

CREATE TABLE `Recipe`
(
    `RecipeId` INT NOT NULL AUTO_INCREMENT,
    `RecipeName` NVARCHAR(120),
     CONSTRAINT `PK_Recipe` PRIMARY KEY  (`RecipeId`)
);

CREATE TABLE `RecipeIngredients`
(
	`RecipeId` INT,
    `IngredientId` INT
);

CREATE TABLE `Ingredients`
(
    `IngredientId` INT NOT NULL AUTO_INCREMENT,
    `IngredientName` NVARCHAR(120),
     CONSTRAINT `PK_IngredientId` PRIMARY KEY  (`IngredientId`)
);
CREATE TABLE `Steps`
(
	`StepId` INT NOT NULL AUTO_INCREMENT,
    `Step` TEXT(32766),
    `RecipeId` INT,
     CONSTRAINT `PK_Steps` PRIMARY KEY  (`StepId`)
);

-- ALTER TABLE `Steps` ADD CONSTRAINT `FK_StepsRecipeId`
--     FOREIGN KEY (`RecipeId`) REFERENCES `Recipe` (`RecipeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;
    
INSERT INTO `Recipe` (`RecipeId`, `RecipeName`) VALUES ('1','Cereal');
INSERT INTO `Recipe` (`RecipeId`, `RecipeName`) VALUES ('2','Pizza');
INSERT INTO `Recipe` (`RecipeId`, `RecipeName`) VALUES ('3','Cookies');
INSERT INTO `Recipe` (`RecipeId`, `RecipeName`) VALUES ('4','Pickles');

INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('1','1');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('1','2');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('2','3');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('2','4');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('2','5');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('2','6');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('3','7');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('3','8');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('3','9');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('3','10');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('3','11');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('3','12');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('3','13');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('3','14');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('3','15');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('4','13');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('4','14');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('4','16');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('4','17');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('4','18');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('4','19');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('4','20');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('4','21');
INSERT INTO `RecipeIngredients` (`RecipeId`, `IngredientId`) VALUES ('4','22');

INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('1','milk');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('2','Count Chocula');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('3','dough');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('4','pizza sauce');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('5','mozzarella');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('6','pepperoni');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('7','butter');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('8','sugar');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('9','brown sugar');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('10','eggs');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('11','vanilla extract');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('12','baking soda');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('13','salt');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('14','flour');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('15','chocolate chips');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('16','buttermilk');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('17','pepper');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('18','pickles');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('19','cornmeal');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('20','seafood seasoning');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('21','cajun seasoning');
INSERT INTO `Ingredients` (`IngredientId`, `IngredientName`) VALUES ('22','oil');

INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('Put cereal into bowl. Add milk', '1');
INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('Preheat oven to 425 degrees F','2');
INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('Add sauce, dough, cheese, and any toppings you desire to the dough','2');
INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('Bake in oven for about 15 minutes, or until crust is golden brown','2');
INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('Preheat oven to 350 degrees F','3');
INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('Mix all ingredients. Drop large spoonfuls onto ungreased pan','3');
INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('Bake in oven for about 10 minutes, or until edges are golden brown','3');
INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('In a bag, mix all ingredients except buttermilk, oil, and pickles','4');
INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('Add the pickles to a bowl of buttermilk and then put pickles into the bag and shake.','4');
INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('Heat oil to 350 degrees in a fryer or a frying pan','4');
INSERT INTO `Steps` (`Step`, `RecipeId`) VALUES ('Fry pickles for 1-2 minutes or until golden brown on each side','4');

-- CREATE TABLE `RecipeIngredients`
-- (
-- 	`JoinTableId` INT NOT NULL AUTO_INCREMENT,
-- 	`RecipeId` INT,
--     `IngredientId` INT,
--     CONSTRAINT `PK_RecipeIngredients` PRIMARY KEY  (`JoinTableId`)
-- );