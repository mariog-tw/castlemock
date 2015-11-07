<%@ include file="../../../includes.jspf"%>
<div class="content-top">
    <h1><spring:message code="general.eventoverview.header.log"/></h1>
</div>
<div class="table-frame">
    <table class="entityTable">
        <tr>
            <th><spring:message code="general.eventoverview.column.id"/></th>
            <th><spring:message code="general.eventoverview.column.startdate"/></th>
            <th><spring:message code="general.eventoverview.column.enddate"/></th>
        </tr>
        <c:forEach items="${events}" var="event" varStatus="loopStatus">
            <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
                <td><a href="<c:url value="/web/${event.typeIdentifier.typeUrl}/event/${event.id}"/>">${event.id}</a></td>
                <td><a href="<c:url value="/web/${event.typeIdentifier.typeUrl}/event/${event.id}"/>">${event.startDate}</a></td>
                <td><a href="<c:url value="/web/${event.typeIdentifier.typeUrl}/event/${event.id}"/>">${event.endDate}</a></td>
            </tr>
        </c:forEach>
    </table>
</div>