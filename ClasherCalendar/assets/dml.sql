-- elements
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (1, 'Air Bomb', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (2, 'Air Defence', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (3, 'Archer Tower', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (4, 'Army Camp', 2);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (5, 'Barracks', 2);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (6, 'Bomb', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (7, 'Cannon', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (8, 'Clan Castle', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (9, 'Dark Barracks', 2);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (10, 'Dark Elixir Storage', 2);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (11, 'Elixir Collector', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (12, 'Elixir Storage', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (13, 'Giant Bomb', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (14, 'Gold Mine', 2);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (15, 'Gold Storage', 2);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (16, 'Hidden Tesla', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (17, 'Laboratory', 2);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (18, 'Mortar', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (19, 'Seeking Air Mine', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (20, 'Spell Factory', 2);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (21, 'Wizard Tower', 1);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (22, 'Dark Elixir Drill', 2);
INSERT INTO tblElement (ElementID, ElementName, CostType) VALUES (23, 'Town Hall', 1);

-- th element quantities
-- gold mines and elixir collectors
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 14, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 11, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 14, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 11, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 14, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 11, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 14, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 11, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 14, 5);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 11, 5);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 14, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 11, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 14, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 11, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 14, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 11, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 14, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 11, 6);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 14, 7);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 11, 7);
 
-- dark elixir drills
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 22, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 22, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 22, 3);

-- gold and elixir storage
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 12, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (1, 15, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 12, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (2, 15, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 12, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 15, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 12, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 15, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 12, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 15, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 12, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 15, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 12, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 15, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 12, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 15, 3);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 12, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 15, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 12, 4);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 15, 4);

-- dark elixir storage
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 10, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 10, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 10, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 10, 1);

-- dark barracks
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 9, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 9, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 9, 2);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 9, 2);

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

-- laboratory
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (3, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (4, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 17, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 17, 1);

-- spell factory
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (5, 20, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (6, 20, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (7, 20, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (8, 20, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (9, 20, 1);
INSERT INTO tblTHElement (THLevel, ElementID, Quantity) VALUES (10, 20, 1);


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
