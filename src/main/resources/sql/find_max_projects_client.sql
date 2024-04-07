SELECT CLIENT.NAME,  count(P2.CLIENT_ID) as PROJECT_COUNT from CLIENT JOIN PUBLIC.PROJECT P2 on CLIENT.ID = P2.CLIENT_ID GROUP BY CLIENT.ID HAVING count(P2.CLIENT_ID) = (select MAX(p.count)
from (select count(PROJECT.CLIENT_ID) as count, CLIENT_ID from PROJECT group by CLIENT_ID) as p);