
<jsp:directive.page import="ma.co.omnidata.framework.services.ihm.commun.sessions.UtilisateurSession"/>




<table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
         <td width="20px" id="col-left"><img src="commun/images/charte2/bg_eyecatcher_left.jpg" alt="k" /></td>
         <td width="100%">
     <table cellpadding="0" cellspacing="0" width="100%" id="table-header" border="0">
        <tr>
        <td colspan="2" >
             <table id="header-meta" cellpadding="0" cellspacing="0">
                   	<tr>
                  		<td ><font style="font-size: 10px;color: #007694">
                    			[Vous êtes : <%= ((UtilisateurSession) session.getAttribute("utilisateur")).getNom() %>] </font>
                    			V 1.0
                   			<a href="./habLogoutAction.do">Déconnexion</a> 
                   		</td>
                  	</tr>
               </table>
         </td>
      </tr>
      <tr>
         <td width="12%"><img src="commun/images/charte2/logo.jpg" alt="a" /></td>
         <td bgcolor="#CBEBF3" width="78%"><img src="commun/images/charte2/slogan.jpg" alt="b" /></td>
      </tr>
</table>
