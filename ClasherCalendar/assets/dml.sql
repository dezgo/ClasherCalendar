-- cost types
INSERT INTO tblCostType (CostTypeID, CostTypeName) VALUES (1, 'Gold');
INSERT INTO tblCostType (CostTypeID, CostTypeName) VALUES (2, 'Elixir');
INSERT INTO tblCostType (CostTypeID, CostTypeName) VALUES (3, 'Dark Elixir');

-- categories
INSERT INTO tblCategory (CategoryID, CategoryName) VALUES (1, 'Defence');
INSERT INTO tblCategory (CategoryID, CategoryName) VALUES (2, 'Resource');
INSERT INTO tblCategory (CategoryID, CategoryName) VALUES (3, 'Army');
INSERT INTO tblCategory (CategoryID, CategoryName) VALUES (4, 'Other');
INSERT INTO tblCategory (CategoryID, CategoryName) VALUES (5, 'Trap');
INSERT INTO tblCategory (CategoryID, CategoryName) VALUES (6, 'Troop');

-- elements
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (1, 'Air Bomb', 1, 5);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (2, 'Air Defence', 1, 1);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (3, 'Archer Tower', 1, 1);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (4, 'Army Camp', 2, 3);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (5, 'Barracks', 2, 3);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (6, 'Bomb', 1, 5);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (7, 'Cannon', 1, 1);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (8, 'Clan Castle', 1, 4);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (9, 'Dark Barracks', 2, 3);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (10, 'Dark Elixir Storage', 2, 2);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (11, 'Elixir Collector', 1, 2);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (12, 'Elixir Storage', 1, 2);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (13, 'Giant Bomb', 1, 5);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (14, 'Gold Mine', 2, 2);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (15, 'Gold Storage', 2, 2);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (16, 'Hidden Tesla', 1, 1);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (17, 'Laboratory', 2, 3);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (18, 'Mortar', 1, 1);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (19, 'Seeking Air Mine', 1, 5);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (20, 'Spell Factory', 2, 3);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (21, 'Wizard Tower', 1, 1);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (22, 'Dark Elixir Drill', 2, 2);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (23, 'Town Hall', 1, 4);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (24, 'X-Bow', 1, 1);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (25, 'Wall', 1, 1);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (26, 'Inferno Tower', 2, 1);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (27, 'Spring Trap', 1, 5);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (28, 'Builder''s Hut', 2, 2);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (29, 'Barbarian King Alter', 3, 3);
INSERT INTO tblElement (ElementID, ElementName, CostType, Category) VALUES (30, 'Archer Queen Alter', 3, 3);

-- th element quantities
-- air bomb
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 1, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 1, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 1, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 1, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 1, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 1, 5);

-- air defence
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 2, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 2, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 2, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 2, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 2, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 2, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 2, 4);

-- archer towers
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 3, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 3, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 3, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 3, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 3, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 3, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 3, 5);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 3, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 3, 7);

-- army camps
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 4, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 4, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 4, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 4, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 4, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 4, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 4, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 4, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 4, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 4, 4);

-- barracks
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 5, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 5, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 5, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 5, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 5, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 5, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 5, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 5, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 5, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 5, 4);

-- bombs
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 6, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 6, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 6, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 6, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 6, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 6, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 6, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 6, 6);

-- cannons
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 7, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 7, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 7, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 7, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 7, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 7, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 7, 5);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 7, 5);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 7, 5);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 7, 6);

-- dark barracks
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 9, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 9, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 9, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 9, 2);

-- dark elixir storage
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 10, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 10, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 10, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 10, 1);

-- elixir collector
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 11, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 11, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 11, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 11, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 11, 5);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 11, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 11, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 11, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 11, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 11, 7);
 
-- elixir storage
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 12, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 12, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 12, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 12, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 12, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 12, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 12, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 12, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 12, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 12, 4);

-- Giant Bomb
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 13, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 13, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 13, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 13, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 13, 5);

-- gold mine
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 14, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 14, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 14, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 14, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 14, 5);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 14, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 14, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 14, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 14, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 14, 7);

-- gold storage
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 15, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 15, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 15, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 15, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 15, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 15, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 15, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 15, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 15, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 15, 4);

-- Hidden Tesla
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 16, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 16, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 16, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 16, 4);

-- laboratory
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 17, 1);

-- mortars
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 18, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 18, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 18, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 18, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 18, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 18, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 18, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 18, 3);

-- Seeking Air Mine
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 19, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 19, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 19, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 19, 5);

-- spell factory
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 20, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 20, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 20, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 20, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 20, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 20, 1);

-- wizard tower
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 21, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 21, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 21, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 21, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 21, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 21, 4);

-- dark elixir drills
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 22, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 22, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 22, 3);

-- town hall
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 23, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 23, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 23, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 23, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 23, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 23, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 23, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 23, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 23, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 23, 1);

-- x-bow
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 24, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 24, 3);

-- walls
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 25, 25);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 25, 50);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 25, 75);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 25, 100);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 25, 125);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 25, 175);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 25, 225);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 25, 250);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 25, 250);

-- Inferno Tower
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 26, 2);

-- Spring Trap
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 27, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 27, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 27, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 27, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 27, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 27, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 27, 6);

-- Barbarian King Alter
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 29, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 29, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 29, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 29, 1);

-- Archer Queen Alter
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 30, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 30, 1);

-- element data
-- air bomb
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (1, 1, NULL, 4000, 0, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (1, 2, NULL, 20000, 14400, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (1, 3, NULL, 200000, 43200, 7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (1, 4, NULL, 1500000, 86400, 9);

-- air defence
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (2, 1, 800, 22500, 18000, 4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (2, 2, 860, 90000, 86400, 4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (2, 3, 900, 270000, 259200, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (2, 4, 940, 540000, 432000, 6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (2, 5, 990, 1080000, 518400, 7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (2, 6, 1040, 2160000, 691200, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (2, 7, 1100, 4320000, 864000, 9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (2, 8, 1160, 7560000, 1036800, 10);

-- archer tower
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 1, 400, 1000, 900, 2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 2, 450, 2000, 1800, 2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 3, 500, 5000, 2700, 3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 4, 550, 20000, 14400, 4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 5, 590, 80000, 43200, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 6, 610, 180000, 86400, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 7, 630, 360000, 172800, 6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 8, 660, 720000, 259200, 7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 9, 690, 1500000, 345600, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 10, 720, 2500000, 432000, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 11, 750, 5000000, 518400, 9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (3, 12, 790, 7500000, 604800, 10);

-- army camp
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (4, 1, 400, 250, 300, 1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (4, 2, 500, 2500, 3600, 2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (4, 3, 600, 10000, 10800, 3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (4, 4, 700, 100000, 28800, 4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (4, 5, 800, 250000, 86400, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (4, 6, 1000, 750000, 259200, 6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (4, 7, 1200, 2250000, 432000, 9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (4, 8, 1400, 6750000, 864000, 10);

-- barracks
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (5, 1, 250, 200, 60, 1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (5, 2, 270, 1000, 900, 1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (5, 3, 280, 2500, 7200, 1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (5, 4, 290, 5000, 14400, 2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (5, 5, 310, 10000, 36000, 3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (5, 6, 320, 80000, 57600, 4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (5, 7, 340, 240000, 86400, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (5, 8, 350, 700000, 172800, 6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (5, 9, 390, 1500000, 345600, 7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (5, 10, 420, 2000000, 518400, 8);

-- bomb
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (6, 1, NULL, 400, 0, 3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (6, 2, NULL, 1000, 900, 3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (6, 3, NULL, 10000, 7200, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (6, 4, NULL, 100000, 28800, 7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (6, 5, NULL, 1000000, 86400, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (6, 6, NULL, 1500000, 172800, 9);

-- cannon
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 1, 400, 250, 60, 1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 2, 450, 1000, 900, 1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 3, 500, 4000, 2700, 2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 4, 550, 16000, 7200, 3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 5, 590, 50000, 21600, 4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 6, 610, 100000, 43200, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 7, 630, 200000, 86400, 6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 8, 660, 400000, 172800, 7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 9, 690, 800000, 259200, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 10, 750, 1600000, 345600, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 11, 900, 3200000, 432000, 9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (7, 12, 1080, 6400000, 518400, 10);

-- clan castle
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (8, 1, 1000, 40000, 0, 3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (8, 2, 1400, 100000, 21600, 4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (8, 3, 2000, 800000, 86400, 6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (8, 4, 2600, 1800000, 172800, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (8, 5, 3000, 5000000, 604800, 9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (8, 6, 3400, 7000000, 1209600, 10);

-- dark barracks
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (9, 1, 250, 750000, 259200, 7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (9, 2, 300, 1250000, 432000, 7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (9, 3, 350, 1750000, 518400, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (9, 4, 400, 2250000, 604800, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (9, 5, 450, 2750000, 691200, 9);

-- dark elixir storage
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (10, 1, 2000, 600000,86400,7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (10, 2, 2200, 1200000,172800,7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (10, 3, 2400, 1800000,259200,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (10, 4, 2600, 2400000,345600,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (10, 5, 2900, 3000000,432000,9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (10, 6, 3200, 3600000,518400,9);

-- elixir collector
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 1, 400, 150,60,1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 2, 450, 300,300,1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 3, 500, 700,900,2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 4, 550, 1400,3600,2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 5, 590, 3500,7200,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 6, 610, 7000,21600,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 7, 630, 14000,43200,4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 8, 660, 28000,86400,4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 9, 680, 56000,172800,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 10, 710, 84000,259200,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (11, 11, 750, 168000,345600,7);

-- elixir storage
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 1, 400, 300,60,1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 2, 600, 750,1800,2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 3, 800, 1500,3600,2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 4, 1000, 3000,7200,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 5, 1200, 6000,10800,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 6, 1500, 12000,14400,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 7, 1650, 25000,21600,4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 8, 1740, 50000,28800,4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 9, 1820, 100000,43200,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 10, 1920, 250000,86400,6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (12, 11, 2016, 500000,172800,7);

-- giant bomb
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (13, 1, null, 12500,0,6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (13, 2, null, 75000,21600,6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (13, 3, null, 750000,86400,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (13, 4, null, 2500000,259200,10);

-- gold mine
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 1, 400, 150,60,1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 2, 450, 300,300,1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 3, 500, 700,900,2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 4, 550, 1400,3600,2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 5, 590, 3000,7200,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 6, 610, 7000,21600,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 7, 630, 14000,43200,4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 8, 660, 28000,86400,4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 9, 680, 56000,172800,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 10, 710, 84000,259200,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (14, 11, 750, 168000,345600,7);

-- gold storage
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 1, 400, 300,60,1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 2, 600, 750,1800,2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 3, 800, 1500,3600,2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 4, 1000, 3000,7200,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 5, 1200, 6000,10800,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 6, 1500, 12000,14400,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 7, 1650, 25000,21600,4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 8, 1740, 50000,28800,4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 9, 1820, 100000,43200,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 10, 1920, 250000,86400,6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (15, 11, 2016, 500000,172800,7);

-- hidden tesla
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (16, 1, 600, 1000000,86400,7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (16, 2, 630, 1250000,259200,7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (16, 3, 660, 1500000,432000,7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (16, 4, 690, 2000000,518400,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (16, 5, 730, 2500000,691200,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (16, 6, 770, 3000000,864000,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (16, 7, 810, 3500000,1036800,9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (16, 8, 850, 5000000,1209600,10);

-- laboratory
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (17, 1, 250, 25000,1800,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (17, 2, 270, 50000,18000,4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (17, 3, 280, 90000,43200,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (17, 4, 290, 270000,86400,6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (17, 5, 310, 500000,172800,7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (17, 6, 330, 1000000,345600,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (17, 7, 350, 2500000,432000,9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (17, 8, 370, 4000000,518400,10);

-- mortar
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (18, 1, 400, 8000,28800,3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (18, 2, 450, 32000,43200,4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (18, 3, 500, 120000,86400,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (18, 4, 550, 400000,172800,6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (18, 5, 590, 800000,345600,7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (18, 6, 610, 1600000,432000,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (18, 7, 640, 3200000,604800,9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (18, 8, 670, 6400000,864000,10);

-- seeking air mine
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (19, 1, 0, 15000,0,7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (19, 2, 0, 2000000,86400,9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (19, 3, 0, 4000000,259200,10);

-- spell factory
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (20, 1, 200, 200000,86400,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (20, 2, 300, 400000,172800,6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (20, 3, 400, 800000,345600,7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (20, 4, 500, 1600000,432000,9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (20, 5, 600, 3200000,518400,10);

-- wizard tower
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (21, 1, 620, 180000,43200,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (21, 2, 660, 360000,86400,5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (21, 3, 690, 720000,172800,6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (21, 4, 720, 1280000,259200,7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (21, 5, 760, 1960000,345600,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (21, 6, 800, 2680000,432000,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (21, 7, 840, 5360000,604800,9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (21, 8, 880, 6480000,864000,10);

-- Dark Elixir Drill
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (22, 1, 400, 1000000, 86400,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (22, 2, 480, 1500000, 172800,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (22, 3, 580, 2000000, 259200,8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (22, 4, 690, 3000000, 345600,9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (22, 5, 830, 4000000, 518400,9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (22, 6, 1000, 5000000, 691200,9);

-- Town Hall
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (23, 1, 1500, 0, 0, 0);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (23, 2, 1600, 1000, 300, 1);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (23, 3, 1850, 4000, 10800, 2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (23, 4, 2100, 25000, 86400, 3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (23, 5, 2400, 150000, 172800, 4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (23, 6, 2800, 750000, 345600, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (23, 7, 3200, 1200000, 518400, 6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (23, 8, 3700, 2000000, 691200, 7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (23, 9, 4200, 3000000, 864000, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (23, 10, 5000, 4000000, 1209600, 9);

-- X-Bow
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (24, 1, 1500, 3000000, 604800, 9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (24, 2, 1900, 5000000, 864000, 9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (24, 3, 2400, 7000000, 1209600, 9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (24, 4, 2800, 8000000, 1209600, 10);

-- wall
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 1, 300, 200, 0, 2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 2, 500, 1000, 0, 2);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 3, 700, 5000, 0, 3);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 4, 900, 10000, 0, 4);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 5, 1400, 30000, 0, 5);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 6, 2000, 75000, 0, 6);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 7, 2500, 200000, 0, 7);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 8, 3000, 500000, 0, 8);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 9, 4000, 1000000, 0, 9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 10, 5500, 3000000, 0, 9);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (25, 11, 7000, 4000000, 0, 10);

-- Inferno Tower
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (26, 1, 1500, 5000000, 604800, 10);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (26, 2, 1900, 6500000, 864000, 10);
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (26, 3, 2200, 8000000, 1209600, 10);

-- Spring Trap
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (27, 1, 0, 2000, 0, 4);

-- Builder's Hut
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (28, 1, 250, 0, 0, 1);

-- Barbarian King Alter
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (29, 1, 250, 10000, 0, 7);

-- Archer Queen Alter
INSERT INTO tblElementData (ElementID, ElementLevel, HitPoints, BuildCost, BuildTime, THMinLevel) VALUES (30, 1, 250, 40000, 0, 9);
