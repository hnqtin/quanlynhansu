<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.quanlynhansu.util.UrlConstants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Danh sách quyền</h4>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
			<a href="<c:url value="${ UrlConstants.URL_ROLE_ADD }" />" 
				class="btn btn-sm btn-success">Thêm mới</a>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /row -->
	<div class="row">
		<div class="col-md-6">
		
		 </div>
		 <div class="col-md-6">
		
		 </div>
		<div class="col-sm-12">
			<div class="white-box">
				<div class="table-responsive">
					<table class="table" id="example">
						<thead>
							<tr>
								<th>STT</th>
								<th>Tên Quyền</th>
								<th>Mô Tả</th>
								<th>Hành Động</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${ roles }" var="item" varStatus="loop">
							<tr>
								<td>${ item.id }</td>
								<td>${ item.name }</td>
								<td>${ item.description }</td>
								<td>
									<a 
										href="<c:url value="${ UrlConstants.URL_ROLE_EDIT }?id=${ item.id }" />" 
										class="btn btn-sm btn-primary">Sửa
									</a> 
									<a 
										href="<c:url value="${ UrlConstants.URL_ROLE_DELETE }?id=${ item.id }" />" 
										class="btn btn-sm btn-danger">Xóa
									</a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
</div>