DROP TABLE IF EXISTS tblElementData;
DROP TABLE IF EXISTS tblElement;
DROP TABLE IF EXISTS tblTHElement;
DROP TABLE IF EXISTS tblPlayerElement;
DROP TABLE IF EXISTS tblPlayer;
DROP TABLE IF EXISTS tblPlayerElements;
DROP TABLE IF EXISTS tblCostType;

CREATE TABLE tblCostType (
	CostTypeID INTEGER NOT NULL,
	CostTypeName TEXT NOT NULL,
	PRIMARY KEY (CostTypeID));

CREATE TABLE tblElement (
	ElementID INTEGER NOT NULL,
	ElementName TEXT NOT NULL,
	CostType INTEGER NOT NULL,
	Category INTEGER NOT NULL,
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

CREATE TABLE tblPlayerElement (
	PlayerElementID INTEGER NOT NULL,
	PlayerID INTEGER NOT NULL,
	ElementID INTEGER NOT NULL,
	Level INTEGER NOT NULL,
	PRIMARY KEY (PlayerElementID));

CREATE TABLE tblPlayer (
	PlayerID INTEGER NOT NULL,
	VillageName TEXT NULL,
	THLevel INTEGER NOT NULL,
	PRIMARY KEY (PlayerID));
	
