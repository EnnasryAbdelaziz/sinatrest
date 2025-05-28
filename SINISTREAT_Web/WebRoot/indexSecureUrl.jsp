<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sUrl" uri="http://www.spiegssoftware.com/jsp/jstl/secureUrl" %>

Example url generated with encrypted parameters:

<br/>
<br/>

<%-- The 'encryptionEnabled' attribute is optional and defaults to true but is included for demonstration--%>

<a href="<sUrl:url value="success.jsp" encryptionEnabled="true">
                <sUrl:param name="client_id" value="1824-67-04-F9#4$" />
                <sUrl:param name="client_name" value="Big Box Company" />
               </sUrl:url>">View a Client</a>

<br/>
<br/>

- Hover your mouse over the above link and you can see the parameters are encrypted in the brower's status bar below
<br/>
- Click the link and, although the parameters appear in the brower's location bar, they are unreadable