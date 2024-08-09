CREATE TABLE Tz(
  ts TIMESTAMP WITHOUT TIME ZONE,
  tstz TIMESTAMPTZ,
  z TEXT
);

SELECT CAST('2024-05-10T00:28:36+03' AS TIMESTAMPTZ) AT TIME ZONE 'America/Denver';

SELECT TIMESTAMP '2001-02-16 20:38:40' AT TIME ZONE 'America/Chicago';

SELECT TIMESTAMP WITH TIME ZONE '2001-02-16 20:38:40-05' AT TIME ZONE 'America/Denver';

SELECT TIMESTAMP WITHOUT TIME ZONE '2001-02-16 20:38:40-05' AT TIME ZONE 'America/Chicago';

SELECT CURRENT_TIMESTAMP AT TIME ZONE 'America/Chicago';

SELECT CURRENT_TIMESTAMP(3) AT TIME ZONE 'America/Denver';

SELECT ts AT TIME ZONE 'America/Chicago' FROM Tz;

SELECT tstz AT TIME ZONE 'America/Denver' FROM Tz;

SELECT CAST(? AS TIMESTAMP) AT TIME ZONE 'America/Denver' FROM Tz;

SELECT CAST(? AS TIMESTAMP) AT TIME ZONE z FROM Tz;
