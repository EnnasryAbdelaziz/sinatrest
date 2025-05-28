<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>RMAWATANYA :: BMCE Bank</TITLE>

<LINK media=screen href="css/standard.css" type=text/css rel=stylesheet>
<LINK media=print href="css/standard.css" type=text/css rel=stylesheet>
<link rel="shortcut icon" href="img/favicon.ico">
<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
<META content=ALL name=robots>
<META content=true name=MSSmartTagsPreventParsing>

<META content="MSHTML 6.00.2800.1106" name=GENERATOR>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="js/util.js"></script>

<script language="javascript"> 
function onSubmit(){
	
	login = document.forms[0].login.value;
	pwd = document.forms[0].motdepasse.value;

	if(login == "" || pwd == "") {
		alert("Veuillez saisir votre login et mot de passe");
		return false;
	}
	document.forms[0].submit();
	
	
}
</script>


</HEAD>
<BODY leftMargin=0 topMargin=0 marginheight="0" marginwidth="0" onkeydown="if (event.keyCode == 13) onSubmit();" >
<TABLE cellSpacing=0 cellPadding=0 width=772 border=0 align="center">
	<tr>
		<td height="100%" width="1" bgcolor="#645F5F"></td>
		<td>
		<TABLE cellSpacing=0 cellPadding=0 width=100% border=0>
			<tr>
				<td width="100%">
				<table cellSpacing=0 cellPadding=0 width=100% border=0 height="20">
					<tr>
						<TD background="img/bg.gif" align="left"
							style="padding-left: 5px;">
							</TD>
						<TD background="img/bg.gif" align="right" width="70"></TD>
						<TD background="img/bg.gif" align="right"
							style="padding-right: 3px;" width="100"></TD>
					</tr>
				</table>
				<table cellSpacing=0 cellPadding=0 width=100% border=0>
					<tr>
						<TD><IMG src="img/logo.jpg" width="900" height="200" border="0"></TD>
					</tr>
				</table>
				<TABLE height=15 cellSpacing=0 cellPadding=0 width=100% border=0>
					<tr>
						<td background="img/bg.gif" align="right"
							style="padding-right: 3px;"></td>
					</tr>
				</TABLE>

				<TABLE height=5 cellSpacing=0 cellPadding=0 width=100% border=0>
					<tr>
						<td></td>
					</tr>
				</TABLE>

				</td>
			</tr>
		</table>

		<table cellSpacing=0 cellPadding=0 width=100% border=0 height="490">
			<tr>
				<td>

				<TABLE cellSpacing=0 cellPadding=0 width=90% border=0 align="center">
					<tr>
						<td><jsp:include page="/error.jsp" /></td>
					</tr>
				</TABLE>

				<table cellSpacing=0 cellPadding=0 width=90% border=0 align="center">

					<tr>
						<td width="37" height="31" class="entetetableauConnexion"><img
							src="img/index/coin-haut-gauche.gif" alt="" width="37"
							height="31"></td>
						<td height="31" colspan="2" align="center" valign="middle"
							class="entetetableauConnexion">Espace de connexion</td>
						<td width="36" height="31" align="right"
							class="entetetableauConnexion"><img
							src="img/index/coin-haut-droit.gif" alt="" width="36" height="31"></td>
					</tr>
					<tr bgcolor="#D3D7DE">
						<td height="127" class="bord-gauche-connexion">&nbsp;</td>
						<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="303" height="96">
								<table width="265" border="0" cellpadding="0" cellspacing="0"
									class="texteNoir11px">
									<tr>
										<td width="200" class="texteGrisGras11px">Bienvenue dans
										notre application</td>
									</tr>
									<tr>
										<td height="40" valign="bottom">Veuillez vous identifier
										en saisissant votre nom d’utilisateur et votre mot de passe
										s’il vous plait.</td>
									</tr>
								</table>
								</td>
								<td width="10" class="separateurPageConnexion">&nbsp;</td>
								<td width="397"><form method="post" action="connectExterneAction.do">
									<table width="304" border="0" align="center" cellpadding="2"
										cellspacing="0" class="texteNoir11px">
										<tr><td>
										<input type="hidden" name="actions" maxlength="19" value="connectIHM" />
										</td></tr>
										<tr>
											<td width="150" valign="middle">Nom d'utilisateur</td>
											<td valign="top"><input type="text" name="login" maxlength="19" value="admin" /></td>
											<td width="17">&nbsp;</td>
										</tr>
										<tr>
											<td height="25" valign="middle">Mot de passe</td>
											<td valign="top"><input type="password" name="motdepasse" value="123" onkeydown="if (event.keyCode == 13) onSubmit();"
												maxlength="8" /></td>
											<td valign="top" style="padding-top: 3px"><input
												name="btnValider" type="button" class="Boutton"
												id="btnValider" value="Valider" onclick="onSubmit()" ></td>
										</tr>
									</table>
								</form></td>
							</tr>
						</table>
						</td>
						<td class="bord-droit-connexion ">&nbsp;</td>
					</tr>
					<tr bgcolor="#D3D7DE">
						<td align="left" valign="bottom" bgcolor="#D3D7DE"
							class="footerTableauConnexion"><img
							src="img/index/coin-bas-gauche.gif" alt="" width="9" height="15"></td>
						<td colspan="2" class="footerTableauConnexion">&nbsp;</td>
						<td align="right" valign="bottom" bgcolor="#D3D7DE"
							class="footerTableauConnexion"><img
							src="img/index/coin-bas-droit.gif" alt="" width="9" height="15"></td>
					</tr>
					<tr>
						<td height="176" background="img/index/gradian.gif">&nbsp;</td>
						<td colspan="2" background="img/index/gradian.gif">&nbsp;</td>
						<td align="right" background="img/index/gradian.gif">&nbsp;</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<table cellSpacing=0 cellPadding=0 width="100%">
			<TR>
				<TD background="img/dotlinebg_horiz.gif" colSpan=22 height=1></TD>
			</TR>
			<TR>
				<TD bgColor=#e7e7e7 colSpan=22 height="25">
				<DIV align=center><FONT color=#656668>© Copyright 2006
				RMAWATANYA, Tous droits réservés.</FONT></DIV>
				</TD>
			</TR>
			<TR>
				<TD background="img/dotlinebg_horiz.gif" colSpan=22 height=1></TD>
			</TR>
		</table>
		</td>
		<td height="100%" width="1" bgcolor="#645F5F"></td>
	</tr>
</table>

</body>
</html>
