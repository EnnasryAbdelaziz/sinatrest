/*
 * Ajout d'un nouveau champ arrérages avant  -> le 18/06/2013
 */      
ALTER TABLE SINISTREAT.GSR_RENTIER_H
ADD (ARRERAGE_AVANT  NUMBER(10,3))

/*
 * Ajout d'un nouveau champ arrérages avant  -> le 18/06/2013
 */      
ALTER TABLE SINISTREAT.GSR_RENTIER_H
ADD (ARRERAGES  NUMBER(10,3))

/*
 * Ajout d'un nouveau champ USER MODIFICATUR -> le 19/04/2013
 */      
ALTER TABLE SINISTREAT.GSR_RENTIER_H
ADD (USERSAS_MODIFICATUR VARCHAR2(200 BYTE))

/*
 * Rename champ USERSAS -> le 19/04/2013
 */ 
ALTER TABLE SINISTREAT.GSR_RENTIER_H
RENAME COLUMN USERSAS TO USERSAS_CREATEUR;

/*
 * Ajout d'un nouveau champ données non-conformité (Histo)  ->  le 01/07/2013
 */
ALTER TABLE SINISTREAT_DEV.GSR_RENTIER_H
ADD (DONNEE_CONFORME CHAR(1 BYTE))

/*
 * Ajout d'un nouveau champ Observation données non-conforme (Histo) -> le 01/07/2013
 */      
ALTER TABLE SINISTREAT_DEV.GSR_RENTIER_H
ADD (OBSERVATION_DONNEE_CONFORME VARCHAR2(200 BYTE))
