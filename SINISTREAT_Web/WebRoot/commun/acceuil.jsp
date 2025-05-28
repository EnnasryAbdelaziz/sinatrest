<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<tiles:insert template="/commun/template.jsp">
	<tiles:put name="title" value="" />
	<tiles:put name="top" value="/commun/top.jsp" />
	<tiles:put name="menu"   value="/commun/menu.jsp" />
	<tiles:put name="footer" value="/commun/footer.jsp" />
	<tiles:put name="contenu" value="/commun/welcome.jsp" />
</tiles:insert>




