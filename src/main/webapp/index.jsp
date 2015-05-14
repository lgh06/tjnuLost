<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
		<title>天津师范大学 失物招领平台</title>
		<link rel="stylesheet/less" type="text/css" href="css/index.less" charset="utf-8" />
		<link rel="stylesheet" href="otherres/font-awesome-4.3.0/css/font-awesome.min.css">
		<script src="js/jquery-1.10.2.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/less.min.js" type="text/javascript" charset="utf-8"></script>
		<!-- <script src="template/build/template.js" type="text/javascript" charset="utf-8"></script> -->
		<script src="js/tjnulost_init.jsp" type="text/javascript" charset="utf-8"></script>
		<script>
			jQuery(document).ready(function ($) {
				$.ajax({
					type:"get",
					url:"./info/getLostJSON",
					dataType:"json",
					success:function(data){
						var html = "<ul>";
						$.each(data.result, function(k,v) {
							html+="<li>";
							if(v.publishAdmin != undefined){
								html += v.publishAdmin.department.name + "";
							}else if(v.publishUser != undefined){
								html += v.publishUser.name + "";
							}
							
							html += " 在 "+v.place+" 丢失了 "+'<a href="./info/showInfo?id='+v.id+'">'+ v.item+"</a></li>";
						});
						html+="</ul>";
						$('.main .lost').append(html);
					}
				});
				$.ajax({
					type:"get",
					url:"./info/getFoundJSON",
					dataType:"json",
					success:function(data){
						var html = "<ul>";
                        $.each(data.result, function(k,v) {
                            html+="<li>";
                            if(v.publishAdmin != undefined){
                                html += v.publishAdmin.department.name + "";
                            }else if(v.publishUser != undefined){
                                html += v.publishUser.name + "";
                            }
                            
                            html += " 在 "+v.place+" 发现了 "+'<a href="./info/showInfo?id='+v.id+'">'+ v.item+"</a></li>";
                        });
						html+="</ul>";
						$('.main .found').append(html);
					}
				});
			});
		</script>	
	</head>

	<body>
		<div class="container">
			<%@ include file="/include/header.jsp"%>
			<%@ include file="/include/leftnav.jsp"%>
			<div class="main">
				<div class="found">
					<h2><a href="./info/showFound.jsp">认领</a></h2>
				</div>
				<div class="lost">
					<h2><a href="./info/showLost.jsp">求助</a></h2>
				</div>
			</div>
			<div class="footer">&copy;LGH 版权所有</div>
		</div>
	</body>

</html>