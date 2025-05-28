	update sin_rapport
				set requete_sql='select 
                   case  length(SIN_SINISTRE.NUMEROSINISTRE)
                        when 21 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 4, 1) || '' '' || 
                        substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 9, 3)
                        || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12, 4) ||  '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 16, 6)                         
                        else SIN_SINISTRE.NUMEROSINISTRE
                        end NUMEROSINISTRE, 
                    SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                    SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE
                    
                from SIN_SINISTRE SIN_SINISTRE, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, SIN_REGLEMENT SIN_REGLEMENT
                where SIN_DETAIL_REGLEMENT.CODEPRESTATION in(23,28)            
                and SIN_DETAIL_REGLEMENT.IDREGLEMENT=SIN_REGLEMENT.ID                
                and SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID'
        where id=24;
		
		
		
				
	update sin_rapport
    set requete_sql='select 
                   case  length(SIN_SINISTRE.NUMEROSINISTRE)
                        when 21 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 4, 1) || '' '' || 
                        substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 9, 3)
                        || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 16, 6)                         
                        else SIN_SINISTRE.NUMEROSINISTRE
                        end NUMEROSINISTRE,                              
                   SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                   SIN_SINISTRE.IPP as IPP, 
                   SIN_EVENEMENT.DATEACCIDENT as DATEACCIDENT,
                   SIN_SINISTRE.ITT as ITT,
                   SIN_DETAIL_REGLEMENT.MONTANT as MONTANT,
                   null as ACHAT,
                   null as CAPITAUX_GSR,
                   SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE,
                   SIN_SINISTRE.RESERVEORDINAIRE as RESERVEORDINAIRE,
                   nvl(SIN_SINISTRE.RESERVEGRAVE, 0) + nvl(SIN_SINISTRE.RESERVEORDINAIRE, 0) as TOTAL,
                   case  SIN_ETAT_SINISTRE.CODETAT
                        when ''0'''' then ''''I''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end CODETAT
                               
                FROM SIN_SINISTRE SIN_SINISTRE, SIN_EVENEMENT SIN_EVENEMENT, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, 
                SIN_REGLEMENT SIN_REGLEMENT, SIN_ETAT_SINISTRE SIN_ETAT_SINISTRE
                WHERE SIN_DETAIL_REGLEMENT.CODEPRESTATION in(24,21,32,23,27)                
                AND SIN_DETAIL_REGLEMENT.IDREGLEMENT = SIN_REGLEMENT.ID                
                AND SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID                
                AND SIN_SINISTRE.IDEVENEMENT=SIN_EVENEMENT.ID                 
                AND SIN_SINISTRE.ID =SIN_ETAT_SINISTRE.IDSINISTRE'
        where id=20;


		
		
			update sin_rapport
				set requete_sql='select 
                   case  length(SIN_SINISTRE.NUMEROSINISTRE)
                        when 21 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 4, 1) || '' '' || 
                        substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 9, 3)
                        || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 16, 6)                         
                        else SIN_SINISTRE.NUMEROSINISTRE
                        end NUMEROSINISTRE, 
                    SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                    SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE
                    
                from SIN_SINISTRE SIN_SINISTRE, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, SIN_REGLEMENT SIN_REGLEMENT
                where SIN_SINISTRE.RESERVEGRAVE =0    
                and SIN_DETAIL_REGLEMENT.CODEPRESTATION = 22            
                and SIN_DETAIL_REGLEMENT.IDREGLEMENT=SIN_REGLEMENT.ID                
                and SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID'
        where id=23;
		
		
		
	
		
		update sin_rapport
set requete_sql='select 
                   case  length(SIN_SINISTRE.NUMEROSINISTRE)
                        when 21 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 4, 1) || '' '' || 
                        substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 9, 3)
                        || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 16, 6)                         
                        else SIN_SINISTRE.NUMEROSINISTRE
                        end NUMEROSINISTRE,                              
                   SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                   SIN_SINISTRE.IPP as IPP, 
                   SIN_EVENEMENT.DATEACCIDENT as DATEACCIDENT,
                   SIN_SINISTRE.ITT as ITT,
                   SIN_DETAIL_REGLEMENT.MONTANT as MONTANT,
                   null as ACHAT,
                   null as CAPITAUX_GSR,
                   SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE,
                   SIN_SINISTRE.RESERVEORDINAIRE as RESERVEORDINAIRE,
                   nvl(SIN_SINISTRE.RESERVEGRAVE, 0) + nvl(SIN_SINISTRE.RESERVEORDINAIRE, 0) as TOTAL,
                   case  SIN_ETAT_SINISTRE.CODETAT
                        when ''0'' then ''I''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end CODETAT
                               
                FROM SIN_SINISTRE SIN_SINISTRE, SIN_EVENEMENT SIN_EVENEMENT, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, 
                SIN_REGLEMENT SIN_REGLEMENT, SIN_ETAT_SINISTRE SIN_ETAT_SINISTRE
                WHERE SIN_DETAIL_REGLEMENT.CODEPRESTATION in(24,21,32,23,27)                
                AND SIN_DETAIL_REGLEMENT.IDREGLEMENT = SIN_REGLEMENT.ID                
                AND SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID                
                AND SIN_SINISTRE.IDEVENEMENT=SIN_EVENEMENT.ID                 
                AND SIN_SINISTRE.ID =SIN_ETAT_SINISTRE.IDSINISTRE '
        where id=20;
		
		
		update sin_rapport_element
set width=185
        where id=243;
		
		
		update sin_rapport_element
set width=185
        where id=267;
		
		update sin_rapport_element
set width=185
        where id=270;
	

					
					
					
				
update sin_rapport
set requete_sql='select  
                    case length(sinistre.numerosinistre)
                        when 21 then substr(sinistre.numerosinistre, 1, 3) || '' '' || substr(sinistre.numerosinistre, 4, 1) || '' '' || 
                        substr(sinistre.numerosinistre, 5, 4) || '' '' || substr(sinistre.numerosinistre, 9, 3)
                        || '' '' || substr(sinistre.numerosinistre, 12, 4) || '' '' || substr(sinistre.numerosinistre, 16, 6)                         
                        else sinistre.numerosinistre
                        end numerosinistre,
                   sinistre.numerograve as numerograve,
                   evenement.dateaccident as dateaccident, mouvement.reserveordold as reserveordold, mouvement.reserveord as reserveord,
                   mouvement.reservegraveold as reservegraveold, mouvement.reservegrave as reservegrave, 
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
                   
                    case length(sinistre.numeropolice)
                         when 15 then substr(sinistre.numeropolice, 1, 4) || '' '' || substr(sinistre.numeropolice, 5, 3) || '' '' || substr(sinistre.numeropolice, 8, 4)
                            || '' '' || substr(sinistre.numeropolice, 12) 
                         else sinistre.numeropolice
                    end numeropolice,
                   
                   mouvement.datecreation as date_operation, mouvement.utilisateur as redacteur
                   
            from sin_sinistre sinistre, sin_evenement evenement, sin_mouvement mouvement
                
            where to_date(to_char(mouvement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date($P{dateDebut}, ''dd/MM/yyyy'') and to_date($P{dateFin}, ''dd/MM/yyyy'')
            and sinistre.idevenement = evenement.id
            and mouvement.idsinistre = sinistre.id
            and (mouvement.code_etat_old <> mouvement.code_etat_new
                    or mouvement.reserveordold <> mouvement.reserveord
                    or mouvement.reservegraveold <> mouvement.reservegrave
                    or mouvement.reserveprotheseold <> mouvement.reserveprothese)'
        where id=2;	
		
		
		
		
					
	
 
       update sin_ligne_titre
set libelle='Etat des sinistres en cours avec réserves à zero'
        where id=3;
       
     update sin_rapport
set requete_sql='select DISTINCT etat.codetat ,mouvement.id ID, 
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
        
        ''E'' as  etat_nouv,        
        mouvement.reserveordold  as reserveordinaireold,
        sinistre.reserveordinaire as reserveordinaire,
        mouvement.reservegraveold as reservegravold,
         sinistre.reservegrave as reservegrave,
         to_date(to_char( mouvement.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') as dateoperation,
        sinistre.usercreateur as redacteur
        from sin_sinistre sinistre,sin_mouvement mouvement, sin_evenement evenement, sin_etat_sinistre etat

        where to_date(to_char(sinistre.datecreation, ''dd/MM/yyyy''), ''dd/MM/yyyy'') between to_date(''01/01/2014'', ''dd/MM/yyyy'') and to_date(''06/06/2014'', ''dd/MM/yyyy'')
      
        and sinistre.reservegrave = 0
        and sinistre.reserveordinaire = 0
        and sinistre.id=mouvement.idsinistre
        and sinistre.idevenement = evenement.id
        and sinistre.id = etat.idsinistre
        and etat.codetat = ''1'' 
     
       and  (select max(etat_sin.codetat) from sin_etat_sinistre etat_sin where  sinistre.id = etat_sin.idsinistre)=''1''
         and mouvement.id in (select max(mvt.id) from sin_mouvement mvt where sinistre.id = mvt.idsinistre) '
        where id=3;
        
        
				
				
				
				update sin_rapport
set requete_sql='select 
                    case  length(SIN_SINISTRE.NUMEROSINISTRE)
                        when 21 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 4, 1) || '' '' || 
                        substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 9, 3)
                        || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 16, 6)                         
                        else SIN_SINISTRE.NUMEROSINISTRE
                        end NUMEROSINISTRE,    
                    SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                    SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE
                    
                from SIN_SINISTRE SIN_SINISTRE, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, SIN_REGLEMENT SIN_REGLEMENT
                
                where SIN_DETAIL_REGLEMENT.CODEPRESTATION = 22                
                and    SIN_DETAIL_REGLEMENT.IDREGLEMENT=SIN_REGLEMENT.ID                
                and    SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID'
        where id=22;
		
		
		
		
		
		
		update sin_rapport
set requete_sql='select 
                  case  length(SIN_SINISTRE.NUMEROSINISTRE)
                        when 21 then substr(SIN_SINISTRE.NUMEROSINISTRE, 1, 3) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 4, 1) || '' '' || 
                        substr(SIN_SINISTRE.NUMEROSINISTRE, 5, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 9, 3)
                        || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 12, 4) || '' '' || substr(SIN_SINISTRE.NUMEROSINISTRE, 16, 6)                         
                        else SIN_SINISTRE.NUMEROSINISTRE
                        end NUMEROSINISTRE,                            
                   SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                   SIN_SINISTRE.IPP as IPP, 
                   SIN_EVENEMENT.DATEACCIDENT as DATEACCIDENT,
                   SIN_SINISTRE.ITT as ITT,
                   SIN_DETAIL_REGLEMENT.MONTANT as MONTANT,
                   null as ACHAT,
                   null as CAPITAUX_GSR,
                   SIN_SINISTRE.RESERVEGRAVE as RESERVEGRAVE,
                   SIN_SINISTRE.RESERVEORDINAIRE as RESERVEORDINAIRE,
                   nvl(SIN_SINISTRE.RESERVEGRAVE, 0) + nvl(SIN_SINISTRE.RESERVEORDINAIRE, 0) as TOTAL,
                   case  SIN_ETAT_SINISTRE.CODETAT
                        when ''0'' then ''I''
                        when ''1'' then ''E''
                        when ''2'' then ''J'' 
                        when ''3'' then ''T'' 
                        when ''4'' then ''S''
                    end CODETAT
                               
                FROM SIN_SINISTRE SIN_SINISTRE, SIN_EVENEMENT SIN_EVENEMENT, SIN_DETAIL_REGLEMENT SIN_DETAIL_REGLEMENT, 
                SIN_REGLEMENT SIN_REGLEMENT, SIN_ETAT_SINISTRE SIN_ETAT_SINISTRE, SIN_VICTIME SIN_VICTIME
                where SIN_DETAIL_REGLEMENT.CODEPRESTATION in(24,21,32,23,27)                
                AND SIN_DETAIL_REGLEMENT.IDREGLEMENT = SIN_REGLEMENT.ID                
                AND SIN_REGLEMENT.IDSINISTRE=SIN_SINISTRE.ID                
                AND SIN_SINISTRE.IDEVENEMENT=SIN_EVENEMENT.ID                 
                AND SIN_SINISTRE.ID =SIN_ETAT_SINISTRE.IDSINISTRE                 
                AND SIN_VICTIME.ISDECEDE=1 
                AND SIN_VICTIME.ID=SIN_SINISTRE.IDVICTIME'
        where id=19;
        
        
        
        
				 update sin_rapport
set requete_sql='select 
                    case length(SIN_RECOURS.NUMEROSINISTRE)
                                when 17 then substr(SIN_RECOURS.NUMEROSINISTRE, 1, 4) || '' '' || substr(SIN_RECOURS.NUMEROSINISTRE, 5, 3) || '' '' || substr(SIN_RECOURS.NUMEROSINISTRE, 8, 4)
                                || '' '' || substr(SIN_RECOURS.NUMEROSINISTRE, 12) 
                                else SIN_RECOURS.NUMEROSINISTRE
                    end NUMEROSINISTRE,
                    SIN_SINISTRE.NUMEROGRAVE as NUMEROGRAVE,
                    SIN_EVENEMENT.DATEACCIDENT as DATEACCIDENT,
                    SIN_RECOURS.IDRECOURS as NUM_REF,
                    null as REF,
                    SIN_RECOURS.CODECOMPAGNIEADVERSE as CODECOMPAGNIEADVERSE,
                    null as RGL_ORD_GRAVE,
                    SIN_RECOURS.MONTANTFINAL as COUT_TOTAL,
                    null as CODE_AVOCAT,
                    null as REF2,
                    null as MONTANT_RECUPERE,
                    null as OBSERVATIONS              
                 from SIN_RECOURS SIN_RECOURS, SIN_SINISTRE SIN_SINISTRE, SIN_EVENEMENT SIN_EVENEMENT
                 where    SIN_RECOURS.idsinistre=SIN_SINISTRE.id                
                 and SIN_SINISTRE.IDEVENEMENT=SIN_EVENEMENT.ID'
        where id=26;				
					
					
						