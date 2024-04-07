select NAME, SALARY from WORKER where SALARY = (select max(SALARY) from worker);
