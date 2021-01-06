DROP TABLE IF EXISTS XG_TS_BT;
CREATE TABLE XG_TS_BT(
	sensorDataID bigint(20) NOT NULL AUTO_INCREMENT,
	sourceAddr int(11) DEFAULT NULL,
	groupAddr int(11) DEFAULT NULL,
	samplingTime bigint(20) DEFAULT NULL,
	item1 double DEFAULT NULL,
	item2 double DEFAULT NULL,
	item3 double DEFAULT NULL,
	item4 double DEFAULT NULL,
 PRIMARY KEY (sensorDataID)
);
DROP TABLE IF EXISTS XG_YG_BT;
CREATE TABLE XG_YG_BT(
	sensorDataID bigint(20) NOT NULL AUTO_INCREMENT,
	sourceAddr int(11) DEFAULT NULL,
	groupAddr int(11) DEFAULT NULL,
	samplingTime bigint(20) DEFAULT NULL,
	item1 double DEFAULT NULL,
	item2 double DEFAULT NULL,
 PRIMARY KEY (sensorDataID)
);
DROP TABLE IF EXISTS XG_WS_BT;
CREATE TABLE XG_WS_BT(
	sensorDataID bigint(20) NOT NULL AUTO_INCREMENT,
	sourceAddr int(11) DEFAULT NULL,
	groupAddr int(11) DEFAULT NULL,
	samplingTime bigint(20) DEFAULT NULL,
	item1 double DEFAULT NULL,
	item2 double DEFAULT NULL,
	item3 double DEFAULT NULL,
	item4 double DEFAULT NULL,
 PRIMARY KEY (sensorDataID)
);
DROP TABLE IF EXISTS XG_SJ_BT;
CREATE TABLE XG_SJ_BT(
	sensorDataID bigint(20) NOT NULL AUTO_INCREMENT,
	sourceAddr int(11) DEFAULT NULL,
	groupAddr int(11) DEFAULT NULL,
	samplingTime bigint(20) DEFAULT NULL,
	item1 double DEFAULT NULL,
	item2 double DEFAULT NULL,
 PRIMARY KEY (sensorDataID)
);
DROP TABLE IF EXISTS XG_MC_BT;
CREATE TABLE XG_MC_BT(
	sensorDataID bigint(20) NOT NULL AUTO_INCREMENT,
	sourceAddr int(11) DEFAULT NULL,
	groupAddr int(11) DEFAULT NULL,
	samplingTime bigint(20) DEFAULT NULL,
	item1 double DEFAULT NULL,
	item2 double DEFAULT NULL,
 PRIMARY KEY (sensorDataID)
);
DROP TABLE IF EXISTS XG_SW_BT;
CREATE TABLE XG_SW_BT(
	sensorDataID bigint(20) NOT NULL AUTO_INCREMENT,
	sourceAddr int(11) DEFAULT NULL,
	groupAddr int(11) DEFAULT NULL,
	samplingTime bigint(20) DEFAULT NULL,
	item1 double DEFAULT NULL,
	item2 double DEFAULT NULL,
 PRIMARY KEY (sensorDataID)
);
DROP TABLE IF EXISTS XG_CS_BT;
CREATE TABLE XG_CS_BT(
	sensorDataID bigint(20) NOT NULL AUTO_INCREMENT,
	sourceAddr int(11) DEFAULT NULL,
	groupAddr int(11) DEFAULT NULL,
	samplingTime bigint(20) DEFAULT NULL,
	item1 double DEFAULT NULL,
	item2 double DEFAULT NULL,
	item3 double DEFAULT NULL,
	item4 double DEFAULT NULL,
	item5 double DEFAULT NULL,
	item6 double DEFAULT NULL,
	item7 double DEFAULT NULL,
	item8 double DEFAULT NULL,
 PRIMARY KEY (sensorDataID)
);
DROP TRIGGER IF EXISTS sensorDataUpdate;
CREATE TRIGGER sensorDataUpdate AFTER INSERT ON sensordata FOR EACH ROW begin
	declare tmpType varchar(100);
	select sensorType into tmpType
		 from SENSORTYPE
	where SourceAddr=new.SourceAddr and GroupAddr=new.GroupAddr limit 1;

	if strcmp(tmpType,'XG_TS_BT')=0 then
		insert into XG_TS_BT (SourceAddr,GroupAddr,SamplingTime,Item1,Item2,Item3,Item4)
			values(new.SourceAddr,new.GroupAddr,new.SamplingTime,new.Item1,new.Item2,new.Item3,new.Item4);
	end if;

	if strcmp(tmpType,'XG_YG_BT')=0 then
		insert into XG_YG_BT (SourceAddr,GroupAddr,SamplingTime,Item1,Item2)
			values(new.SourceAddr,new.GroupAddr,new.SamplingTime,new.Item1,new.Item2);
	end if;

	if strcmp(tmpType,'XG_WS_BT')=0 then
		insert into XG_WS_BT (SourceAddr,GroupAddr,SamplingTime,Item1,Item2,Item3,Item4)
			values(new.SourceAddr,new.GroupAddr,new.SamplingTime,new.Item1,new.Item2,new.Item3,new.Item4);
	end if;

	if strcmp(tmpType,'XG_SJ_BT')=0 then
		insert into XG_SJ_BT (SourceAddr,GroupAddr,SamplingTime,Item1,Item2)
			values(new.SourceAddr,new.GroupAddr,new.SamplingTime,new.Item1,new.Item2);
	end if;

	if strcmp(tmpType,'XG_MC_BT')=0 then
		insert into XG_MC_BT (SourceAddr,GroupAddr,SamplingTime,Item1,Item2)
			values(new.SourceAddr,new.GroupAddr,new.SamplingTime,new.Item1,new.Item2);
	end if;

	if strcmp(tmpType,'XG_SW_BT')=0 then
		insert into XG_SW_BT (SourceAddr,GroupAddr,SamplingTime,Item1,Item2)
			values(new.SourceAddr,new.GroupAddr,new.SamplingTime,new.Item1,new.Item2);
	end if;

	if strcmp(tmpType,'XG_CS_BT')=0 then
		insert into XG_CS_BT (SourceAddr,GroupAddr,SamplingTime,Item1,Item2,Item3,Item4,Item5,Item6,Item7,Item8)
			values(new.SourceAddr,new.GroupAddr,new.SamplingTime,new.Item1,new.Item2,new.Item3,new.Item4,new.Item5,new.Item6,new.Item7,new.Item8);
	end if;

end;
