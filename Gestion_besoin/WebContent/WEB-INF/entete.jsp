<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*,modeleUser.*"%>
<%@ page import="java.util.*,modeleArticle.*"%>
<%@ page import="java.util.*,modeleCategorie.*"%>
<%@ page import="java.util.*,modeleDemande.*"%>
<%@ page import="java.util.*,modeleDirection.*"%>
<%@ page import="java.util.*,modeleLigneDemande.*"%>
<%@ page import="java.util.*,modeleLigneTransfert.*"%>
<%@ page import="java.util.*,modeleMateriel.*"%>
<%@ page import="java.util.*,modeleServiceAgence.*"%>
<%@ page import="java.util.*,modeleStatut.*"%>
<%@ page import="java.util.*,modeleTransfert.*"%>
<c:set var="val" value="0"/>
<c:set var="env" value="0"/>	
<c:set var="rec" value="0" />
<c:set var="trait" value="0" />
<c:set var="trans" value="0" />
<c:set var="tableau" value="Chef d'agence,Adjoint d'agence,GAC" />
<c:set var="post" value="${fn:split(tableau,',')}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="img/favicon.png">

<title>Gestion de besoin</title>
<link href="<c:url value="style/css/bootstrap.min.css"/>" rel="stylesheet">

<link href="<c:url value="style/css/bootstrap-theme.css"/>" rel="stylesheet">

<link href="<c:url value="style/css/elegant-icons-style.css"/>" rel="stylesheet" />

<link href="<c:url value="style/css/dataTables.bootstrap.css"/>" rel="stylesheet" />

<link href="<c:url value="style/css/font-awesome.min.css"/>" rel="stylesheet" />

<link href="<c:url value="style/css/style.css"/>" rel="stylesheet">

<link href="<c:url value="style/css/style-responsive.css"/>" rel="stylesheet" />

<script type="text/javascript">
  	function loadPageNaka(){
  		
  		var url = window.location.href;
  		var chaine = "http://localhost:8081/Gestion_besoin/";
  		
  		<c:if test="${ empty session }">
  			alert("Vous êtes hors connection, Time out");
  			window.location.href = chaine+"login";
  		</c:if>
  		
  		<c:choose>
  		<c:when test="${ !empty message || !empty succes }"> $('#formulaireMessage').modal('show');</c:when>
  		<c:otherwise>
  		
	  		if(url == chaine+"index") $('#accueil').attr("class","active");
	  			  		
	  		if(url == chaine+"listeDemandeEffectuee") { $('#demande').attr("class","sub-menu active"); 
	  		$('#link_1').attr("class","active"); $('#Demande_effectue').attr("class","tab-pane active");
	  		}
	  		
	  		if(url == chaine+"listeDemandeRecue") { $('#demande').attr("class","sub-menu active"); 
	  		$('#link_2').attr("class","active"); $('#Demande_reçue').attr("class","tab-pane active");
  			}
		  	
	  		if(url == chaine+"listeTransfertTraite"){ $('#transfert').attr("class","sub-menu active");
	  		$('#link_3').attr("class","active"); $('#Transfert_traiter').attr("class","tab-pane active");
	  		}
	  		
	  		if(url == chaine+"listeTransfertRecu"){ $('#transfert').attr("class","sub-menu active");
	  		$('#link_4').attr("class","active"); $('#Transfert_reçue').attr("class","tab-pane active");
	  		}
	  		
	  		if(url == chaine+"listeArticle") $('#article').attr("class","sub-menu active");
		  		  
	  		if(url == chaine+"listeUtilisateur") { $('#listeUtilisateur').attr("class","active");
	  			$('#autre').attr("style","display: block;");
	  		}
		  		  
	  		if(url == chaine+"listeServiceAgence") { $('#listeService').attr("class","active");
  			$('#autre').attr("style","display: block;");
	  		}
		  		  
	  		if(url == chaine+"listeDirection") { $('#listeDirection').attr("class","active");
  			$('#autre').attr("style","display: block;");
	  		}
		  	
	  		if(url == chaine+"listeCategorie"){ $('#listeCategorie').attr("class","active");
  			$('#autre').attr("style","display: block;");
	  		}
	  		
  		</c:otherwise>	
  		</c:choose>
  	}
  </script>  
 <style type="text/css">
	span.label {color:black;width:60;height:30;text-align:center;margin-top:0;background:#ffF;font:bold 13px Arial}
	span.c1 {cursor:hand;color:black;width:60;height:30;text-align:center;margin-top:0;background:#ffF;font:bold 13px Arial}
	span.c2 {cursor:hand;color:red;width:60;height:30;text-align:center;margin-top:0;background:#ffF;font:bold 13px Arial}
	span.c3 {cursor:hand;color:#b0b0b0;width:60;height:30;text-align:center;margin-top:0;background:#ffF;font:bold 12px Arial}
</style>
</head>

<body onload="loadPageNaka()">
	<section id="container" class="">
		<header class="header dark-bg">
			<div class="toggle-nav">
				<div class="icon-reorder tooltips"
					data-original-title="Toggle Navigation" data-placement="bottom">
					<i class="icon_menu"></i>
				</div>
			</div>
		
			 

			<!--logo start-->
			<a href="index" class="logo">C<span class="lite">EM</span></a>
			<!--logo end-->

			<div class="top-nav notification-row">

				<ul class="nav pull-right top-menu">
								
          		<li class="dropdown">
	          		<a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span class="profile-ava" style="margin-left: 55%;"> <img alt="" src="<c:url value="style/img/images.jpg"/>">
						</span> <span class="username">
						 <c:forEach var="session" items="${sessionScope.session}">
					 		${session.getPrenomUser()}	 		
						 </c:forEach>		
						</span> <b class="caret"></b>
					</a>
						<ul class="dropdown-menu extended logout">
							<li class="eborder-top"><a href="#"><i
									class="icon_profile"></i> Mon Profile</a></li>
							<li><a href="login"><i class="icon_key_alt"></i> Log Out</a>
							</li>
						</ul>
				</li>
				
				
				<c:forEach var="session" items="${sessionScope.session}">
				 	<c:forEach var="demande" items="${listedemandemess}">
				 	
				 		<c:if test="${session.getServiceID() == demande.getServiceUserID() && session.getPosteUser() == post[0] && demande.getStatutID()==1}">
				 			<input type="hidden" value="${val=val+1}">	
				 		</c:if>
				 		
				 		<c:if test="${session.getServiceID() == demande.getServiceUserID() && session.getPosteUser() == post[1] && demande.getStatutID()==2}">
				 			<input type="hidden" value="${env=env+1}">
				 		</c:if>
				 		
				 		<c:if test="${session.getServiceID() == demande.getServiceID() && session.getPosteUser() == post[2] && demande.getStatutID()==3}">
				 			<input type="hidden" value="${rec=rec+1}">	
				 		</c:if>
				 		
				 		<c:if test="${session.getServiceID() == demande.getServiceUserID() && session.getPosteUser() == post[1] && demande.getStatutID() == 4}">
				 			<input type="hidden" value="${trait=trait+1}">	
				 		</c:if>
				 		
				 		<c:if test="${session.getServiceID() == demande.getServiceUserID() && session.getPosteUser() == post[1] && demande.getStatutID() == 5}">
				 			<input type="hidden" value="${trans=trans+1}">	
				 		</c:if>
				 		
				 	</c:forEach>
			 	</c:forEach>
			 
			  <!-- notification -->
			  
				<li id="alert_notificatoin_bar" class="dropdown" style="margin-left: -53%;" >
		            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
			            <i class="fa fa-bell"></i>
			            <c:if test="${val!=0}">
			            	<span class="badge bg-important">${val}</span>
					    </c:if>
					    					    
					    <c:if test="${env!=0}">
			            	<span class="badge bg-important">${env}</span>
					    </c:if>
					    
					    <c:if test="${trait!= 0}">
			            	<span class="badge bg-important">${trait}</span>
					    </c:if>
					    
					    <c:if test="${trans!= 0 || env!=0}">
			            	<span class="badge bg-important">${trans+env}</span>
					    </c:if>
			            
		            </a>
		           <ul class="dropdown-menu extended notification">
		              <div class="notify-arrow notify-arrow-blue"></div>
		              <li>
		        
		              <c:if test="${val!=0}">
		                <p class="gray">Il y a ${val} demande(s) à valider</p>
		              </c:if>
		              
		          	              
		              <c:if test="${trait!=0 || env!=0 || trans!= 0 }">
		                <p class="gray">Toutes les notifications</p>
		              </c:if>
		              
		              </li>
		             
			              <c:forEach var="session" items="${sessionScope.session}">
						 	<c:forEach var="demande" items="${listedemandemess}">
						 		<c:if test="${session.getServiceID() == demande.getServiceUserID() && session.getPosteUser() == post[0] && demande.getStatutID()==1}">
					              <li>
					                <a href="listeDemandeEffectuee">
					                    <span class="label label-primary"><i class="icon_profile"></i></span>
					                    	
					                    	par : ${demande.getUserPrenom()} <br>					                    	
					                    	demande numéro : ${demande.getNumDemande()}
					                 	<span class="small italic pull-right"></span>
					                 </a>
					              </li>
				              	</c:if>
						 	</c:forEach>
				 		  </c:forEach>
				 		  
				 		  
				 		  <c:forEach var="session" items="${sessionScope.session}">
						 	<c:forEach var="demande" items="${listedemandemess}">
						 		<c:if test="${session.getServiceID() == demande.getServiceUserID() && session.getPosteUser() == post[1] && demande.getStatutID()==2}">
					              <li>
					                <a href="listeDemandeEffectuee">
					                    <span class="label label-primary"></span>
					                    	demande validée <br>
					                    	par : Votre Chef <br>					                    	
					                    	demande numéro : ${demande.getNumDemande()}
					                 	<span class="small italic pull-right"></span>
					                 </a>
					              </li>
				              	</c:if>
						 	</c:forEach>
				 		  </c:forEach>
				 		  
				 		  
				 		  <c:forEach var="session" items="${sessionScope.session}">
						 	<c:forEach var="demande" items="${listedemandemess}">
						 		<c:if test="${session.getServiceID() == demande.getServiceUserID() && session.getPosteUser() == post[1] && demande.getStatutID() == 4}">
					              <li>
					                <a href="listeDemandeEffectuee">
					                    <span class="label label-primary"></span>
					                    	demande en cours de Traitement <br>
					                    	par : GAC <br>					                    	
					                    	demande : ${demande.getNumDemande()}
					                 	<span class="small italic pull-right"></span>
					                 </a>
					              </li>
				              	</c:if>
						 	</c:forEach>
				 		  </c:forEach>
				 		  
				 		  
				 		  <c:forEach var="session" items="${sessionScope.session}">
						 	<c:forEach var="demande" items="${listedemandemess}">
						 		<c:if test="${session.getServiceID() == demande.getServiceUserID() && session.getPosteUser() == post[1] && demande.getStatutID() == 5}">
					              <li>
					                <a href="listeDemandeEffectuee">
					                    <span class="label label-primary"></span>
					                    	votre demande tranféré <br>
					                    	par : GAC <br>					                    	
					                    	demande : ${demande.getNumDemande()}
					                 	<span class="small italic pull-right"></span>
					                 </a>
					              </li>
				              	</c:if>
						 	</c:forEach>
				 		  </c:forEach>
				 		  
				 		  
		              <li>
		                <a href="#">toutes les notifications</a>
		              </li>
		            </ul>
	          	</li>
	          
	          
			 <!-- notification -->
			 
			 <c:if test="${val!=0}">
			 	<!--<script>alert("${val} demande(s) doit être validée");</script>-->
			 </c:if>
							
		          <li id="mail_notificatoin_bar" class="dropdown" style="margin-left: -68%;">
		            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
		              <i class="fa fa-envelope"></i>
		              <c:if test="${rec!=0}">
		              	<span class="badge bg-important"> ${rec} </span>
		              </c:if>  
		            </a>
		            <ul class="dropdown-menu extended inbox">
		              <div class="notify-arrow notify-arrow-blue"></div>
		              <li>
		              <c:if test="${rec!=0}">
		                <p class="blue">Vous avez reçus ${rec} demande </p>
		              </c:if>  
		              </li>
		              
				         <c:forEach var="session" items="${sessionScope.session}">
					 		<c:forEach var="demande" items="${listedemandemess}">
					 		 <c:if test="${session.getServiceID()==demande.getServiceID() && session.getPosteUser()==post[2] && demande.getStatutID()==3}">
				              	<li>
				                	<a href="listeDemandeRecue">
				                  	<span class="subject">
				                    	<span class="form">
				                    	Nom : ${demande.getUser()}  ${demande.getUserPrenom()} <br> ${demande.getService()}  
				                    	</span>
				                  	</span>
				                  	<span style="margin-left:50px;" class="">
				                    	demande numéro : ${demande.getNumDemande()}
				                  	</span> 
				                	</a>
				              	</li>
				             </c:if>
				            </c:forEach>
						</c:forEach>
												
		              <li>
		                <a href="#">Tous les messages</a>
		              </li>
		            </ul>
		          </li>

		          <!-- inbox notificatoin end -->
				</ul>
			</div>
		</header>
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<ul class="sidebar-menu">
					<li id="accueil" style="" class="sub"><a class="link tooltip-link" 	data-toggle="tooltip" data-original-title="Une URL est une adresse web" href="index"> <i
							class="fa fa-home"></i> <span>ACCUEIL</span>
					</a>
					</li>

					<li id="demande" class="sub-menu"><a href="listeDemandeEffectuee" class=""> <i
							class="fa fa-list"></i> <span>DEMANDE</span>
					</a>
					</li>       	
                  		<li id="transfert" class="sub-menu"><a href="listeTransfertTraite" class=""> <i
							class="fa fa-send"></i> <span>TRANSFERT</span>
						</a></li>	
							
					<c:forEach var="session" items="${sessionScope.session}">
					<c:if test="${session.getTypeService()=='SERVICE'}">
					<li id="article" class="sub-menu">
					<a href="listeArticle" class=""> 
						<i class="fa fa-tasks"></i> <span>ARTICLE</span>
					</a>
					</li>
					</c:if>
					</c:forEach>

					<li id="active"  class="sub-menu" ><a href="#" class=""> <i
							class="fa fa-arrow-down"></i> <span>AUTRE</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul id="autre" class="sub" style="">
							<li id="listeUtilisateur" class=""><a class="" href="listeUtilisateur"><i class="fa fa-sign-in"></i>Utilisateur</a></li>
							<li id="listeService" class=""><a class="" href="listeServiceAgence"><i class="fa fa-sign-in"></i>Service et Agence</a></li>
							<li id="listeDirection" class=""><a class="" href="listeDirection"><i class="fa fa-sign-in"></i>Direction</a></li>
							<li id="listeCategorie" class=""><a class="" href="listeCategorie"><i class="fa fa-sign-in"></i>Categorie</a></li>
						</ul>
					</li>
				</ul>
				<!-- sidebar menu end-->
			</div>
		</aside>
		
	