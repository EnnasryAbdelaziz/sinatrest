/*Création de la procédure stockée pour la génération 
du numéro de recours en fonction de l'année en entrée */
--MFBO@EVO : 205
create or replace PROCEDURE PROC_NUM_RECOURS_AT(annee_in IN VARCHAR, countOut OUT VARCHAR)
   IS

    v_count NUMBER(10);
    v_count_num NUMBER(10);
    v_valeur NUMBER(10);
       

BEGIN 
    
    select numSin.count into v_count from SIN_NUM_RECOURS numSin where numSin.annee=annee_in ;
       
    v_count_num := v_count+1;
    update SIN_NUM_RECOURS set count=v_count_num where annee=annee_in ;

    countOut := v_count_num ;
    commit;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Inserer la nouvelle valeur ds la table avec comme valeurs (count, 1)
        insert into SIN_NUM_RECOURS (annee,count) values (annee_in,1);
        countOut := '1' ;
        commit;   
END; -- Procedure 