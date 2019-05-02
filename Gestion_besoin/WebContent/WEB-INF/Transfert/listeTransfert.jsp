 <%@ include file="../entete.jsp"%>
  <section id="main-content">
      <section class="wrapper">
	        <div class="row">
	            <div class="col-lg-12">
	              <h3 class="page-header"><i class="fa fa-send"></i>TRANSFERT</h3>
	              <ol class="breadcrumb">
	                <li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
	                <li><i class="fa fa-list-alt"></i>Liste des Transferts</li>
	              </ol>
	            </div>
	        </div>
	       <div class="row">
	         <div class="col-lg-6">
	
	         </div>
	        <div class="col-lg-6">
	        <div class="nav search-row" style="margin:   0% 0% 1% 60%;">				    
			  <form class="navbar-form">
				<input class="form-control" placeholder="Search" type="text" id="search" style="margin:   0% 0% 3% 5%;">
			  </form>
			</div>
		</div>
	   </div>
	 <!-- page start-->
   <div class="row">
     <div class="col-lg-12">
       <section class="panel">
              <header class="panel-heading tab-bg-info">
                <ul class="nav nav-tabs"> 
                  <li id="link_3" class="">
                    <a data-toggle="tab" href="#Transfert_traiter">
                      <i class="icon-user"></i>Transfert Traité(s)
                    </a>
                  </li>                
                  <li id="link_4" class="">
                    <a data-toggle="tab" href="#Transfert_reçue">
                      <i class="icon-envelope"></i>Transfert reçu(s)
                    </a>
                  </li>
                </ul>
              </header>
             <div class="panel-body">
                <div class="tab-content">
		         <!-- demande traiter --> 
		         
                    <div id="Transfert_traiter" class="tab-pane">
	                 	 <section class="panel">
                           <table id="example1" class="table table-striped">
                                <thead>
                                  <tr>
                                    <th><i class="fa fa-warning"></i> Statut</th>
                                    <th><i class="fa fa-dashboard"></i> Numéro transfert</th>
                                    <th><i class="fa fa-dashboard"></i> Reférencié à</th>
                                    <th><i class="fa fa-user"></i> Saisi par</th>
                                    <th><i class="fa fa-users"></i> Service Destinataire</th>
                                    <th><i class="fa fa-calendar"></i> Date transfert</th>
                                    
                                    <th><i class="fa fa-check-square-o"></i> Validation</th>
                                    <th><i class="fa fa-plus"></i> Ligne</th> 
                                    <th><i class="fa fa-cogs"></i> Action</th>                   
                                  </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="transfert" items="${listetransfert}">
                                <c:forEach var="session" items="${sessionScope.session}">
                                <c:if test="${session.getServiceID()==transfert.getServiceUserID()}">
                                <tr>     
                                  <td>                  
                                       <input type="hidden" class="btn btn-danger btn-xs" id="base-${transfert.getTransfertID()}" value="${transfert.getStatut()}"> 
                                       <input style="font-size:10px;" type="button" class="" id="statut-${transfert.getTransfertID()}" value="Traitée"> 
                                  </td>                               
                                  <td>${transfert.getNumTransfert()}</td>
                                  <td>${transfert.getDemande()}</td> 
                                  <td>${transfert.getUser()}</td>
                                  <td>${transfert.getService()}</td>  
                                  <td>${transfert.getDateTransfert()}</td>
                                 <td>  
                                 
                                 <c:set var="verifligne" value="0"/>                                 
                                  <c:forEach var="lignetransfert" items="${listelignetransfert}" varStatus="stat">
                                		<c:if test="${lignetransfert.getTransfertID()==transfert.getTransfertID()}"><c:set var="verifligne" value="${verifligne+1}"/></c:if>
                                  </c:forEach>
                                    
                                  <c:if test="${transfert.getStatut()!='Transferer'}">                       
                                  <form name="form" action="ControleTransfert" method="post">                                                
                                  		<input type="hidden" value="${transfert.getTransfertID()}" name="transfertid">
                                  		<input type="hidden" value="${transfert.getDemandeID()}" name="demandeid">                                                                
                                        <input type="hidden" name="ajout" value="editstatut">
                                        <input type="button" data-toggle="modal" data-backdrop="false" href="#formulaireMessage" style="font-size:10px;" class="" id="btn-${transfert.getTransfertID()}" value="">                                     
                                   </form>
                                   </c:if> 
                                   <script type="text/javascript">
	                                   var x = document.getElementById("base-${transfert.getTransfertID()}");
	                                   var y = document.getElementById("statut-${transfert.getTransfertID()}");
	                                   var z = document.getElementById("btn-${transfert.getTransfertID()}");
	                                   
	                                   if(x.value == "Traiter"){                                                                               
                                           y.setAttribute("class","btn btn-danger btn-xs disabled");  y.value="Traitée";
                                           z.setAttribute("class","btn btn-success btn-xs"); z.value="Transférer";
                                        }  
	                                   
	                                   if(x.value == "Transferer"){                                   
                                      	 y.setAttribute("class","btn btn-success btn-xs disabled");  y.value="Transférée"; 
                                        }
	                                   
	                                   <c:if test="${verifligne!=0}">
                                 		z.removeAttribute("data-toggle"); z.setAttribute("type","submit");
                                 	   </c:if>
                                 	   
                                   </script>
                                  </td>                                
                                 <c:forEach var="lignedemande" items="${listelignedemande}" varStatus="stat">
                                 		<c:if test="${lignedemande.getDemandeID()==transfert.getDemandeID()}">
                                 			<c:set var="listecategorie">${ stat.first ?  '' : listecategorie} ${lignedemande.getCategorie()} ${lignedemande.getQteArticleDemande()} => ${lignedemande.getCommentaireDemande()} /#&</c:set>
                                 		</c:if>
                                  </c:forEach>
                                  <td>	
                                  	<c:if test="${transfert.getStatut()!='Transferer'}"> 							 	 										 	
                                   	  <div id="ligne">
                                        <button id="button" name="ajout" type="submit" value="${transfert.getTransfertID()}+${transfert.getDemande()}+${transfert.getNumTransfert()}+${listecategorie}" onclick="ajoutLigne(this)" data-toggle="modal" data-backdrop="false" href="#formulaireLigne" class="btn btn-primary btn-xs"><div class="fa fa-plus"></div></button>
                                      </div> 
                                  	</c:if>                        
                                  </td> 
                                 
                                  <td> 
                                  	<a class="btn btn-success btn-xs fa fa-eye" href="ControleLigneTransfert?resultat=listelignetransfert&amp;transfertid=${transfert.getTransfertID()}&amp;source=trsftraite"></a>       
                                  </td>
                                </tr>  
                                </c:if>
                                </c:forEach>                            
                              </c:forEach>
                            </tbody>
                          </table>                          
		                </section>
		               </div>

		          					
		         
		         <!-- demande transférer -->
		          
                 <div id="Transfert_reçue" class="tab-pane">
                    <section class="panel">
                         <table id="example1" class="table table-striped">
                                <thead>
                                  <tr>
                                   <th><i class="fa fa-warning"></i> Statut</th>
                                    <th><i class="fa fa-dashboard"></i> Numéro transfert</th>
                                    <th><i class="fa fa-users"></i> Traité par</th>
                                    <th><i class="fa fa-user"></i>  Transféré par le service </th>                                  
                                    <th><i class="fa fa-calendar"></i> Date transfert</th>
                                    <th><i class="fa fa-cogs"></i> Action</th>                   
                                  </tr>
                                </thead>
                                <tbody>
	                                <c:forEach var="transferts" items="${listedemandetransfert}">
	                                <c:forEach var="session" items="${sessionScope.session}">
	                                <c:if test="${session.getServiceID() == transferts.getServiceID()}">
	                                <tr>     
	                                  <td>                  
	                                       <input type="hidden" class="btn btn-danger btn-xs" id="${transferts.getTransfertID()}" value="${transferts.getStatut()}"> 
	                                       <input style="font-size:10px;" type="button" class="btn btn-success btn-xs disabled" id="" value="Transférée"> 
	                                  </td>
	                                  <td>${transferts.getNumTransfert()}</td> 
	                                  <td>${transferts.getUser()}</td> 
	                                  <td>${transferts.getService()}</td>	                                   
	                                  <td>${transferts.getDateTransfert()}</td>
	                                  <td> 
	                                  	<a class="btn btn-success btn-xs fa fa-eye" href="ControleLigneTransfert?resultat=listelignetransfert&amp;transfertid=${transferts.getTransfertID()}&amp;source=trsfrecu"></a>                                         	
	                                    <a class="btn btn-danger btn-xs icon_close_alt2" href="ControleTransfert?resultat=supprtransfert&amp;transfertid=${transferts.getTransfertID()}"></a>
	                                  </td>
	                                </tr>
	                                </c:if>
	                                </c:forEach>                              
	                              </c:forEach>                               
                              </tbody>
                          	</table>                          
		                   </section>
		                </div>
		                </div>
		           </div>
		          
		            </section>
		          </div>
		       </div>
  	  </section>
 </section> 
 
 <!-- MODALE BEGIN -->
          <div class="col-md-6 col-xs-12 col-md-offset-3">
            <div class="modal fade" id="formulaireMessage">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" style="color: red;">x</button>
                  
                  <h4 id="titremsg" class="modal-title">Message d'erreur</h4>
                  </div>
                  <div class="modal-body">
                  <div class="panel-body">
                  		<h3 id="mess">Veuillez d'abord ajouter une ligne de transfert</h3>
                  </div>
              </div>
              <div class="modal-footer">
          			<button class="btn btn-danger" data-dismiss="modal">Annuler</button>
       		 </div>
            </div>
          </div>
          </div>
          </div>
<!-- MODALE END -->
  
<!-- modale ligne begin -->

<div class="col-md-6 col-xs-12 col-md-offset-3">
    <div class="modal fade" id="formulaireLigne">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" style="color: red;">x</button>
            <center>
              <h4 class="modal-title"><i class="fa fa-plus"></i> Ligne de Transfert</h4>
            </center>
          </div>
          <div class="modal-body">
          <div class="panel-body">
            <div class="form">
          <form name="form" action="ControleLigneTransfert" class="form-validate form-horizontal" id="feedback_form" method="post">
              <div class="form-group ">           
                  <center style="color : gray;">     
                  <h3><i class="fa fa-user"></i> Transféré par :   
                  		<c:forEach var="session" items="${sessionScope.session}"> 
                  			${ session.getPrenomUser()} ${ session.getNomUser().toUpperCase() } 
                  		</c:forEach>
                  </h3>
                  <h5>
		            LIGNE DE TRANSFERT : <span id="transfnum"></span><br>
		            POUR LA DEMANDE : <span id="valeur"></span><br>
                  </h5>
                  </center>
                  <h5>
                   CATEGORIE ARTICLE : <span id="categorie"></span>  
                   </h5>                     
                          <br>
                          <label for="" class="control-label">Article:</label>
                          <select class="form-control" data-toggle="dropdown" name="articleid" id="articleid" onchange="chercheArticle(this)">
	                          <c:forEach var="article" items="${listearticle}">
	                              <option value="${article.getArticleID()}">${article.getNomArticle()}
	                              </option>
	                          </c:forEach>        
                          </select>  
                          
                          <input type="hidden" id="hidnumArticle" value="">
                          <input type="hidden" id="transfertid" value="" name="transfertid">

                          <label for="" class="control-label">Qte transféré:</label>
                          <div class="input-group" >
                              <span class="input-group-addon"><i class="fa fa-th"></i></span>
                              <input type="number" name="qtearticletransfert" id="qtearticletransfert" value="" class="form-control " onkeyup="verifStock(this)">
                          </div>

                          <label for="" class="control-label">Commentaire :</label>
                          <div class="input-group">
                              <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                              <textarea rows="5" cols="" name="commentairetransfert" id="commentairetransfert" class="form-control"></textarea>
                          </div>
                  
                          <input type="hidden" name="ajout" value="addlignetransfert">
                      <div class="form-group">           
                  <div class="modal-footer">
                      <input type="button" id="btn_ajout" data-toggle="modal" data-backdrop="false" href="#formulaireMessage" class="btn btn-success fa fa-plus" style="margin-top: 10px;font-size:20px;" value="Ajouter" onclick="hideModal(this)">
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>
  </div>

<!-- modale ligne end -->
  
  
  
  <script type="text/javascript">
      $("modalForm").submit(function(e) {
          e.preventDefault();
          var $modalForm = $(this);
          $.post($modalForm.attr("action"), $modalForm.serialize())
              .done(function(data) {
                  $("#ligne").html(data);
                  $("#formulaireLigne").modal("hide");
                  $("table").load('' ,function () {
                });
              })
        });   
      $("modalForm").submit(function(e) {
          e.preventDefault();
          var $modalForm = $(this);
          $.post($modalForm.attr("action"), $.serialize())
              .done(function(data) {
                  $("#msg").html(data);
                  $("#formulaireMessage").modal("hide");
                  $("table").load('listeDemande.jsp table' ,function () {
                });
              })
        });
      
      function hideModal(champ)
      {
    	  $('#formulaireLigne').modal('hide');
    	  $('#mess').text('Stock insuffisant');
      }
      
      function chercheArticle(champ)
      {
    	  document.getElementById("hidnumArticle").value=champ.value;
      }
      
      function verifStock(champ)
      {
    	  var articleID = document.getElementById("hidnumArticle").value;
    	  <c:forEach var="article" items="${listearticle}">
    	  	if(parseInt(articleID)=="${article.getArticleID()}")
    	  	{
    	  		var resteStock = parseInt("${article.getStock()}") - parseInt(champ.value);  	  		
    	  		if(parseInt(resteStock)>=0){
    	  			alert(resteStock);
    	  			document.getElementById("btn_ajout").removeAttribute("data-toggle");
    	  			document.getElementById("btn_ajout").setAttribute("type","submit");    	  			
    	  		}
    	  		else {
    	  			document.getElementById("btn_ajout").setAttribute("type","button");
    	  			document.getElementById("btn_ajout").setAttribute("data-toggle","modal");  	  			
    	  		}
    	  		
    	  	}   	  
    	  </c:forEach> 
      }
      
      function ajoutLigne(champ){
    	    var val = champ.value;
    	    var liste = val.split("+");
    	   document.getElementById("transfertid").value = liste[0];
    	   document.getElementById("valeur").innerText = liste[1];
    	   document.getElementById("transfnum").innerText =liste[2];
    	   document.getElementById("categorie").innerHTML ="";
    	   
    	   var categorie = liste[3].split("/#&");
    	    for (var i=0;i<categorie.length-1;i++){
    	   		document.getElementById("categorie").innerHTML+="<br>-"+categorie[i];   
    	    }
    	    
    	    document.getElementById("hidnumArticle").value=document.getElementById("articleid").value
    	    document.getElementById("qtearticletransfert").value="";
    	    document.getElementById("commentairetransfert").value="";
    	  }
    	  
  </script>
  
 <%@ include file="../footer.jsp"%>