DROP TABLE IF EXISTS XG_BS_S;
CREATE TABLE XG_BS_S(
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
DROP TRIGGER IF EXISTS sensorDataUpdate;
CREATE TRIGGER sensorDataUpdate AFTER INSERT ON sensordata FOR EACH ROW begin
	declare tmpType varchar(100);
	select sensorType into tmpType
		 from SENSORTYPE
	where SourceAddr=new.SourceAddr and GroupAddr=new.GroupAddr limit 1;

	if strcmp(tmpType,'XG_BS_S')=0 then
		insert into XG_BS_S (SourceAddr,GroupAddr,SamplingTime,Item1,Item2,Item3,Item4)
			values(new.SourceAddr,new.GroupAddr,new.SamplingTime,new.Item1,new.Item2,new.Item3,new.Item4);
	end if;

end;
