<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
	<tbody>
		<tr>
			<td>
				<a class="l-btn l-btn-small l-btn-plain">
					<span class="l-btn-left">
						<span class="l-btn-text">
							<button class="btn btn-success" title="${param.item}" onclick="location.href='${pageContext.request.contextPath}/${param.module}'">
								<i class="glyphicon glyphicon-eye-open"></i>${param.item}
							</button>
						</span>
					</span>
				</a>
			</td>
			<td>
				<a class="l-btn l-btn-small l-btn-plain">
					<span class="l-btn-left">
						<span class="l-btn-text">
							<button class="btn btn-danger" title="${param.item1}" onclick="location.href='${pageContext.request.contextPath}/${param.module}/${param.action1}'">
								<i class="glyphicon glyphicon-trash"></i>${param.item1}
							</button>
						</span>
					</span>
				</a>
			</td>
<!-- 			<td> -->
<!-- 				<a class="l-btn l-btn-small l-btn-plain"> -->
<!-- 					<span class="l-btn-left"> -->
<!-- 						<span class="l-btn-text"> -->
<%-- 							<button class="btn btn-info" title="${param.item1}" onclick="location.href='${pageContext.request.contextPath}/${param.module}/${param.action1}'"> --%>
<%-- 								<i class="glyphicon glyphicon-search"></i>${param.item1} --%>
<!-- 							</button> -->
<!-- 						</span> -->
<!-- 					</span> -->
<!-- 				</a> -->
<!-- 			</td> -->
			<td>
				<a class="l-btn l-btn-small l-btn-plain">
					<span class="l-btn-left">
						<span class="l-btn-text">
							<button class="btn btn-warning" title="${param.item2}" onclick="location.href='${pageContext.request.contextPath}/${param.module}/${param.action2}'">
									<i class="glyphicon glyphicon-pencil"></i>${param.item2}
							</button>
						</span>
					</span>
				</a>
			</td>
			<td><a class="l-btn l-btn-small l-btn-plain">
				<span class="l-btn-left">
					<span class="l-btn-text">
						<button class="btn btn-primary" title="${param.item3}" onclick="location.href='${pageContext.request.contextPath}/${param.module}/${param.action3}'">
								<i class="glyphicon glyphicon-file"></i>${param.item3}
							</button>
						</span>
					</span>
				</a>
			</td>
			<td>
				<a class="l-btn l-btn-small l-btn-plain">
					<span class="l-btn-left">
						<span class="l-btn-text">
						<c:choose>
							<c:when test="${sessionScope.account == null}">
								<button class="btn btn-inverse" title="${param.item4}" onclick="location.href='${pageContext.request.contextPath}/${param.module}/${param.action4}'">
									<i class="glyphicon glyphicon-cog"></i>${param.item4}
								</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-inverse" title="${param.item5}" onclick="location.href='${pageContext.request.contextPath}/${param.module}/${param.action5}'">
									<i class="glyphicon glyphicon-cog"></i>${param.item5}
								</button>
							</c:otherwise>
						</c:choose>
						</span>
					</span>
				</a>
			</td>
		</tr>
	</tbody>
</table>