--BANANIYO
Insert into ORD_USER
   (ID, PRENOM, NOM, CODESAS)
 Values
   ((select max(id)+1 from moypai.ord_user), 'YOUNESS', 'BANANI', 'BANANIYO');

Insert into ORD_DELEGATION
   (ID, SEUILOPERATION, NOM, SEUILMOIS, NOMBREINTERVENANT, 
    ID_SUPERIEUR)
 Values
   ((select max(id)+1 from moypai.ORD_DELEGATION), 99999999999, 'DEG_BANANIYO', 99999999999, 1, 
   (select ID from MOYPAI.ORD_USER where CODESAS='BANANIYO'));
	
Insert into ord_user_delegation
(ID,DEL_ID,USE_ID) 
Values
((select max(id)+1 from ord_user_delegation),(select ID from MOYPAI.ORD_DELEGATION where ID_SUPERIEUR = (select ID from MOYPAI.ORD_USER where CODESAS='BANANIYO') and NOM like '%DEG_BANANIYO%'), (select ID from ORD_USER where CODESAS='BANANIYO'));

Insert into MOYPAI.ORD_DElAGATION_MOIS
(ID,DEL_ID,ANNEE,MOIS,SEUILMOIS,SEUILOPERATION) 
values 
((select max(id)+1 from ORD_DElAGATION_MOIS),
(select DEL_ID from MOYPAI.ORD_USER_DELEGATION where USE_ID=(select ID from MOYPAI.ORD_USER where CODESAS='BANANIYO') ), 2014, 02, 99999999999, 99999999999);

COMMIT;

--MADRARAS
Insert into ORD_USER
   (ID, PRENOM, NOM, CODESAS)
 Values
   ((select max(id)+1 from moypai.ord_user), 'MADRARAS', 'MADRARAS', 'MADRARAS');
   
Insert into ORD_DELEGATION
   (ID, SEUILOPERATION, NOM, SEUILMOIS, NOMBREINTERVENANT, 
    ID_SUPERIEUR)
 Values
   ((select max(id)+1 from ord_delegation), 1000000, 'DEG_MADRARAS', 1000000000, 1, 
    (select ID from MOYPAI.ORD_USER where CODESAS='BANANIYO'));
	
insert into ord_user_delegation
(ID,DEL_ID,USE_ID) 
Values 
((select max(id)+1 from ord_user_delegation),(select ID from MOYPAI.ORD_DELEGATION where ID_SUPERIEUR = (select ID from MOYPAI.ORD_USER where CODESAS='BANANIYO') and NOM like '%DEG_MADRARAS%'), (select ID from ORD_USER where CODESAS='MADRARAS'));

insert into MOYPAI.ORD_DElAGATION_MOIS
(ID,DEL_ID,ANNEE,MOIS,SEUILMOIS,SEUILOPERATION) 
values 
((select max(id)+1 from ORD_DElAGATION_MOIS),
(select DEL_ID from MOYPAI.ORD_USER_DELEGATION where USE_ID=(select ID from MOYPAI.ORD_USER where CODESAS='MADRARAS') ), 2014, 02, 1000000000, 1000000);

COMMIT;


--ATSN0003
Insert into ORD_USER
   (ID, PRENOM, NOM, CODESAS)
 Values
   ((select max(id)+1 from moypai.ord_user), 'ATSN0003', 'ATSN0003', 'ATSN0003');
   
Insert into ORD_DELEGATION
   (ID, SEUILOPERATION, NOM, SEUILMOIS, NOMBREINTERVENANT, 
    ID_SUPERIEUR)
 Values
   ((select max(id)+1 from ord_delegation), 200000, 'DEG_ATSN0003', 200000, 1, 
    (select ID from MOYPAI.ORD_USER where CODESAS='MADRARAS'));
	
insert into ord_user_delegation
(ID,DEL_ID,USE_ID) 
Values
((select max(id)+1 from ord_user_delegation),(select ID from MOYPAI.ORD_DELEGATION where ID_SUPERIEUR = (select ID from MOYPAI.ORD_USER where CODESAS='MADRARAS') and NOM like '%DEG_ATSN0003%'), (select ID from ORD_USER where CODESAS='ATSN0003'));

insert into MOYPAI.ORD_DElAGATION_MOIS
(ID,DEL_ID,ANNEE,MOIS,SEUILMOIS,SEUILOPERATION) 
values 
((select max(id)+1 from ORD_DElAGATION_MOIS),(select DEL_ID from MOYPAI.ORD_USER_DELEGATION where USE_ID=(select ID from MOYPAI.ORD_USER where CODESAS='ATSN0003') ), 2014, 02, 200000, 200000);

COMMIT;

--ATSN0001
Insert into ORD_USER
   (ID, PRENOM, NOM, CODESAS)
 Values
   ((select max(id)+1 from moypai.ord_user), 'ATSN0001', 'ATSN0001', 'ATSN0001');  
   
Insert into ORD_DELEGATION
   (ID, SEUILOPERATION, NOM, SEUILMOIS, NOMBREINTERVENANT, 
    ID_SUPERIEUR)
 Values
   ((select max(id)+1 from ord_delegation), 300000, 'DEG_ATSN0001', 300000, 1, 
    (select ID from MOYPAI.ORD_USER where CODESAS='ATSN0003'));
	
insert into ord_user_delegation
(ID,DEL_ID,USE_ID) 
Values
((select max(id)+1 from ord_user_delegation),(select ID from MOYPAI.ORD_DELEGATION where ID_SUPERIEUR = (select ID from MOYPAI.ORD_USER where CODESAS='ATSN0003') and NOM like '%DEG_ATSN0001%'), (select ID from ORD_USER where CODESAS='ATSN0001'));

insert into MOYPAI.ORD_DElAGATION_MOIS
(ID,DEL_ID,ANNEE,MOIS,SEUILMOIS,SEUILOPERATION) 
values 
((select max(id)+1 from ORD_DElAGATION_MOIS),
(select DEL_ID from MOYPAI.ORD_USER_DELEGATION where USE_ID=(select ID from MOYPAI.ORD_USER where CODESAS='ATSN0001') ), 2014, 02, 300000, 300000);

COMMIT;

--CHLIYAHA
Insert into ORD_USER
   (ID, PRENOM, NOM, CODESAS)
 Values
   ((select max(id)+1 from ORD_USER), 'Haifa', 'CHLIYAH', 'CHLIYAHA');
   
Insert into ORD_DELEGATION
   (ID, SEUILOPERATION, NOM, SEUILMOIS, NOMBREINTERVENANT, 
    ID_SUPERIEUR)
 Values
   ((select max(id)+1 from ORD_DELEGATION), 50000, 'DEG_HAIFA', 1000000, 1, 
    (select ID from ORD_USER where CODESAS='CHLIYAHA'));

insert into ord_user_delegation
(ID,DEL_ID,USE_ID) 
values
((select max(id)+1 from ord_user_delegation),(select ID from MOYPAI.ORD_DELEGATION where ID_SUPERIEUR = (select ID from ORD_USER where CODESAS='CHLIYAHA') and NOM like '%DEG_HAIFA%'), (select ID from ORD_USER where CODESAS='CHLIYAHA'));

insert into MOYPAI.ORD_DElAGATION_MOIS
(ID,DEL_ID,ANNEE,MOIS,SEUILMOIS,SEUILOPERATION) 
values 
((select max(id)+1 from MOYPAI.ORD_DElAGATION_MOIS),
(select DEL_ID from MOYPAI.ORD_USER_DELEGATION where USE_ID=(select ID from MOYPAI.ORD_USER where CODESAS='CHLIYAHA') ), 2014, 02, 1000000, 50000);

COMMIT;

--BGD
Insert into ORD_USER
   (ID, PRENOM, NOM, CODESAS)
 Values
   ((select max(id)+1 from ORD_USER), 'CASABLANCA', 'BGD', 'RMATST04');

Insert into ORD_DELEGATION
   (ID, SEUILOPERATION, NOM, SEUILMOIS, NOMBREINTERVENANT, 
    ID_SUPERIEUR)
 Values
   ((select max(id)+1 from ORD_DELEGATION), 0, 'DEG_BGD', 1000000000, 1, 
    (select ID from ORD_USER where CODESAS='RMATST04'));   

insert into ord_user_delegation(ID,DEL_ID,USE_ID)
Values
 ((select max(id)+1 from ord_user_delegation),(select ID from MOYPAI.ORD_DELEGATION where ID_SUPERIEUR = (select ID from ORD_USER where CODESAS='RMATST04') and NOM like '%DEG_BGD%'), (select ID from ORD_USER where CODESAS='RMATST04'));

insert into MOYPAI.ORD_DElAGATION_MOIS(ID,DEL_ID,ANNEE,MOIS,SEUILMOIS,SEUILOPERATION) 
values ((select max(id)+1 from ORD_DElAGATION_MOIS),
(select DEL_ID from MOYPAI.ORD_USER_DELEGATION where USE_ID=(select ID from MOYPAI.ORD_USER where CODESAS='RMATST04') ), 2014, 02, 1000000000, 0);

COMMIT;


