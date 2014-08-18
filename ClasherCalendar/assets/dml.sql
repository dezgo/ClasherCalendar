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

