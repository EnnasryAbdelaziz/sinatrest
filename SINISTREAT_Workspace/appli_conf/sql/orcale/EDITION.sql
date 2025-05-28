delete from SIN_RAPPORT_ELEMENT where id=30;
delete from SIN_RAPPORT_ELEMENT where id=31;
delete from SIN_RAPPORT_ELEMENT where id=32;

delete from SIN_RAPPORT_ELEMENT where id=35;
delete from SIN_RAPPORT_ELEMENT where id=36;

delete from SIN_RAPPORT_ELEMENT where id=37;

delete from SIN_RAPPORT_ELEMENT where id=38;

delete from SIN_RAPPORT_ELEMENT where id=39;

delete from SIN_RAPPORT_ELEMENT where id=40;
delete from SIN_GROUP_BY where id=1;

Insert into SIN_RAPPORT_ELEMENT
   (ID, TYPE_FILLE, NAME, TYPE, LIBELLE, 
    PATTERN, TEXT_ALIGNMENT, WIDTH, CALCULATION, VARIABLE_EXPRESSION, 
    ORDRE_AFFICHAGE, ID_SIN_ENTETE, ID_SIN_RAPPORT, ID_SIN_GROUP_BY, ID_SIN_RECAP)
 Values
   (35, 'CHAMP', 'numeropolice', 'java.lang.String', 'N° Police', 
    NULL, 'Center', 118, NULL, NULL, 
    3, NULL, 3, NULL, NULL);
Insert into SIN_RAPPORT_ELEMENT
   (ID, TYPE_FILLE, NAME, TYPE, LIBELLE, 
    PATTERN, TEXT_ALIGNMENT, WIDTH, CALCULATION, VARIABLE_EXPRESSION, 
    ORDRE_AFFICHAGE, ID_SIN_ENTETE, ID_SIN_RAPPORT, ID_SIN_GROUP_BY, ID_SIN_RECAP)
 Values
   (36, 'CHAMP', 'dateaccident', 'java.sql.Timestamp', 'Date Acci.', 
    'dd/MM/yyyy', 'Center', 65, NULL, NULL, 
    4, NULL, 3, NULL, NULL);

Insert into SIN_RAPPORT_ELEMENT
   (ID, TYPE_FILLE, NAME, TYPE, LIBELLE, 
    PATTERN, TEXT_ALIGNMENT, WIDTH, CALCULATION, VARIABLE_EXPRESSION, 
    ORDRE_AFFICHAGE, ID_SIN_ENTETE, ID_SIN_RAPPORT, ID_SIN_GROUP_BY, ID_SIN_RECAP)
 Values
   (1000, 'CHAMP', 'etat_anci', 'java.lang.String', 'Anc.E', 
    NULL, 'Right', 78, NULL, NULL, 
    5, NULL, 3, NULL, NULL);
    
    Insert into SIN_RAPPORT_ELEMENT
   (ID, TYPE_FILLE, NAME, TYPE, LIBELLE, 
    PATTERN, TEXT_ALIGNMENT, WIDTH, CALCULATION, VARIABLE_EXPRESSION, 
    ORDRE_AFFICHAGE, ID_SIN_ENTETE, ID_SIN_RAPPORT, ID_SIN_GROUP_BY, ID_SIN_RECAP)
 Values
   (1002, 'CHAMP', 'reserveordinaireold', 'java.lang.Double', 'Anc.Rsv.Ord', 
    '#,##0.00', 'Right', 78, NULL, NULL, 
    7, NULL, 3, NULL, NULL);
Insert into SIN_RAPPORT_ELEMENT
   (ID, TYPE_FILLE, NAME, TYPE, LIBELLE, 
    PATTERN, TEXT_ALIGNMENT, WIDTH, CALCULATION, VARIABLE_EXPRESSION, 
    ORDRE_AFFICHAGE, ID_SIN_ENTETE, ID_SIN_RAPPORT, ID_SIN_GROUP_BY, ID_SIN_RECAP)
 Values
   (1004, 'CHAMP', 'reservegravold', 'java.lang.Double', 'Anc.Rsv.Grv', 
    '#,##0.00', 'Right', 78, NULL, NULL, 
    9, NULL, 3, NULL, NULL);
Insert into SIN_RAPPORT_ELEMENT
   (ID, TYPE_FILLE, NAME, TYPE, LIBELLE, 
    PATTERN, TEXT_ALIGNMENT, WIDTH, CALCULATION, VARIABLE_EXPRESSION, 
    ORDRE_AFFICHAGE, ID_SIN_ENTETE, ID_SIN_RAPPORT, ID_SIN_GROUP_BY, ID_SIN_RECAP)
 Values
   (1006, 'CHAMP', 'dateoperation', 'java.sql.Timestamp', 'Date Opé.', 
    'dd/MM/yyyy', 'Center', 78, NULL, NULL, 
    11, NULL, 3, NULL, NULL);
Insert into SIN_RAPPORT_ELEMENT
   (ID, TYPE_FILLE, NAME, TYPE, LIBELLE, 
    PATTERN, TEXT_ALIGNMENT, WIDTH, CALCULATION, VARIABLE_EXPRESSION, 
    ORDRE_AFFICHAGE, ID_SIN_ENTETE, ID_SIN_RAPPORT, ID_SIN_GROUP_BY, ID_SIN_RECAP)
 Values
   (1003, 'CHAMP', 'reserveordinaire', 'java.lang.Double', 'Eval. Ord.', 
    '#,##0.00', 'Right', 75, NULL, NULL, 
    8, NULL, 3, NULL, NULL);
Insert into SIN_RAPPORT_ELEMENT
   (ID, TYPE_FILLE, NAME, TYPE, LIBELLE, 
    PATTERN, TEXT_ALIGNMENT, WIDTH, CALCULATION, VARIABLE_EXPRESSION, 
    ORDRE_AFFICHAGE, ID_SIN_ENTETE, ID_SIN_RAPPORT, ID_SIN_GROUP_BY, ID_SIN_RECAP)
 Values
   (1005, 'CHAMP', 'reservegrave', 'java.lang.Double', 'Eval. Grave', 
    '#,##0.00', 'Right', 75, NULL, NULL, 
    10, NULL, 3, NULL, NULL);
Insert into SIN_RAPPORT_ELEMENT
   (ID, TYPE_FILLE, NAME, TYPE, LIBELLE, 
    PATTERN, TEXT_ALIGNMENT, WIDTH, CALCULATION, VARIABLE_EXPRESSION, 
    ORDRE_AFFICHAGE, ID_SIN_ENTETE, ID_SIN_RAPPORT, ID_SIN_GROUP_BY, ID_SIN_RECAP)
 Values
   (1007, 'CHAMP', 'redacteur', 'java.lang.String', 'Rédacteur', 
    NULL, 'Left', 80, NULL, NULL, 
    12, NULL, 3, NULL, NULL);
Insert into SIN_RAPPORT_ELEMENT
   (ID, TYPE_FILLE, NAME, TYPE, LIBELLE, 
    PATTERN, TEXT_ALIGNMENT, WIDTH, CALCULATION, VARIABLE_EXPRESSION, 
    ORDRE_AFFICHAGE, ID_SIN_ENTETE, ID_SIN_RAPPORT, ID_SIN_GROUP_BY, ID_SIN_RECAP)
 Values
   (1001, 'CHAMP', 'etat_nouv', 'java.lang.String', 'Nov.E', 
    NULL, 'Right', 78, NULL, NULL, 
    6, NULL, 3, NULL, NULL);
COMMIT;

 
	update sin_rapport
	set requete_sql='select DISTINCT mouvement.id ID, 
       case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end numerosinistre,
                        
        sinistre.numerograve as numerograve,                 
                        
       case length(sinistre.numeropolice)
        when 15 then substr(sinistre.numeropolice, 1, 4) || '' '' || substr(sinistre.numeropolice, 5, 3) || '' '' || substr(sinistre.numeropolice, 8, 4)
        || '' '' || substr(sinistre.numeropolice, 12) 
        else sinistre.numeropolice
        end numeropolice,
       evenement.dateaccident as dateaccident,
       (case  mouvement.code_etat_old
            when 0 then ''E''
            when 1 then ''E''
            when 2 then ''J'' 
            when 3 then ''T'' 
            when 4 then ''S''
        end) etat_anci , 
         (case  mouvement.code_etat_new
            when 0 then ''E''
            when 1 then ''E''
            when 2 then ''J'' 
            when 3 then ''T'' 
            when 4 then ''S''
        end) etat_nouv,        
        mouvement.reserveordold  as reserveordinaireold,
        sinistre.reserveordinaire as reserveordinaire,
        mouvement.reservegraveold as reservegravold,
         sinistre.reservegrave as reservegrave,
         to_date(to_char( mouvement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') as dateoperation,
        sinistre.usercreateur as redacteur
        from sin_sinistre sinistre,sin_mouvement mouvement, sin_evenement evenement, sin_etat_sinistre etat

        where to_date(to_char(sinistre.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
      
        and sinistre.reservegrave = 0
        and sinistre.reserveordinaire = 0
        and sinistre.id=mouvement.idsinistre
        and sinistre.idevenement = evenement.id
        and sinistre.id = etat.idsinistre
        and etat.codetat = ''1'' 
         and mouvement.id in (select max(mvt.id) from sin_mouvement mvt where sinistre.id = mvt.idsinistre) 
        ' 
        where id=3;
		
commit;