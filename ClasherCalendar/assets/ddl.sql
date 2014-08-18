DROP TABLE IF EXISTS tblElementData;

DROP TABLE IF EXISTS tblElement;

CREATE TABLE tblElement (
	ElementID INTEGER NOT NULL,
	ElementName TEXT NOT NULL,
	CostType INTEGER NULL,
	PRIMARY KEY (ElementID));

CREATE TABLE tblElementData (
	ElementID INTEGER NOT NULL,
	ElementLevel INTEGER NOT NULL,
	HitPoints INTEGER NULL,
	BuildCost INTEGER NULL,
	BuildTime INTEGER NULL,
	THMinLevel INTEGER NULL,
	PRIMARY KEY (ElementID, ElementLevel));

CREATE TABLE tblTHElement (
	THLevel INTEGER NOT NULL,
	ElementID INTEGER NOT NULL,
	Quantity INTEGER NOT NULL,
	PRIMARY KEY (THLevel, ElementID));

CREATE TABLE tblPlayerElements (
	PlayerElementID INTEGER NOT NULL,
	ElementID INTEGER NOT NULL,
	Level INTEGER NOT NULL,
	PRIMARY KEY (PlayerElementID));
	