DROP TABLE IF EXISTS tblElementData;

DROP TABLE IF EXISTS tblElement;

CREATE TABLE tblElement (
	ElementID INTEGER NOT NULL,
	ElementName TEXT NOT NULL,
	CostType INTEGER NULL,
	PRIMARY KEY (ElementID));

CREATE TABLE tblElementData (
	ElementID INTEGER,
	ElementLevel INTEGER,
	HitPoints INTEGER NULL,
	BuildCost INTEGER NULL,
	BuildTime INTEGER NULL,
	THMinLevel INTEGER NULL,
	PRIMARY KEY (ElementID, ElementLevel));
