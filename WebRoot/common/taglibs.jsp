<%--引入标签库 <%@ taglib %>  ：为jsp页面提供更多的功能--%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld" %>
<%@ taglib prefix="pg" uri="/WEB-INF/tlds/pager-taglib.tld" %>


<%--设置变量：使用<c:set> 标签将应用的上下文路径设置为 ctx变量--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%--将 ctx变量 赋值给 JavaScript的变量 ctx--%>
<script>
    var ctx = "${ctx}";
</script>
