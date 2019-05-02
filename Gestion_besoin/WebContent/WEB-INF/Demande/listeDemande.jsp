  <%@ include file="../entete.jsp"%> 
  
    <!-- Last insertID BEGIN -->
 <c:choose>
 	<c:when test="${ !empty lastdemande }">
		 <c:forEach var="listedemandeid" items="${lastdemande}">
		 	<c:set var="lastInsertId" value="${listedemandeid.getDemandeID()}"/>
		 </c:forEach>
	 </c:when>
	 <c:otherwise><c:set var="lastInsertId" value="0"/></c:otherwise>
 </c:choose>
 <!-- Last insertID END -->
 
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
              <h3 class="page-header"><i class="fa fa-list"></i>DEMANDE</h3>
              <ol class="breadcrumb">
                <li><i class="fa fa-home"></i><a href="index">Home</a></li>
                <li><i class="fa fa-list"></i>Liste Demande</li>
              </ol>
            </div>
        </div>
        <div class="row">
         <div class="col-lg-6">
            <div id="html">
               <button data-toggle="modal" value="${lastInsertId}" data-backdrop="false" href="#formulaire" onclick="ajouter(this)" class="btn btn-danger btn-lg btn-xs"><div class="fa fa-plus"></div> Ajouter une demande</button>
            </div>
         </div>
        <div class="col-lg-6">
        <div class="nav search-row" style="margin:   0% 0% 1% 60%;">				    
		<form class="navbar-form">
			<input class="form-control" placeholder="Search" type="text" id="search">
		</form>
		</div>
		</div>
        </div>
     </section>
    <section class="wrapper" style="margin-top : -20px">
    <!-- page start-->
        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading tab-bg-info">
                <ul class="nav nav-tabs">
                  <li id="link_1" class="">
                    <a data-toggle="tab" href="#Demande_effectue">
                      <i class="icon-home"></i>Demande effectuée(s)
                    </a>
                  </li>
                  <li id="link_2" class="">
                    <a data-toggle="tab" href="#Demande_reçue">
                      <i class="icon-user"></i>Demande reçue(s)
                    </a>
                  </li>
                </ul>
              </header>
              <div class="panel-body">
                <div class="tab-content">
                  <div id="Demande_effectue" class="tab-pane">
                    <div class="profile-activity">
                      <div class="row">
                                <div class="col-lg-12">
                                  <section class="panel">
                               <table id="example1" class="table table-striped">
                                <thead>
                                  <tr>
                                   <th><i class="fa fa-warning"></i> Statut</th>
                                    <th><i class="fa fa-dashboard"></i> Numéro demande</th>
                                    <th><i class="fa fa-user"></i> Saisie par</th>
                                    <th><i class="fa fa-users"></i> Service Destinataire</th>
                                    <th><i class="fa fa-calendar"></i> Date demande</th>
                                    <th><i class="fa fa-check-square-o"></i> Validation</th> 
                                    <th><i class="fa fa-plus"></i> Ligne</th>                                  		  
                                    <th><i class="fa fa-cogs"></i> Action</th>                   
                                  </tr>
                                </thead>
                                <tbody>
	                                <c:forEach var="demande_effectuee" items="${listedemande_effectuee}">
	                                <c:forEach var="session" items="${sessionScope.session}">
	                                <c:if test="${session.getServiceID()==demande_effectuee.getServiceUserID()}">
	                                <tr>        
	                                  <td>                  
	                                   <input type="hidden" class="" id="base-${demande_effectuee.getDemandeID()}" value="${demande_effectuee.getStatut()}">
	                                   <input type="button" style="font-size:10px;" class="btn btn-danger btn-xs disabled" id="statut-${demande_effectuee.getDemandeID()}" value="Non validée">
	                                  </td>
	                                  <td>${demande_effectuee.getNumDemande()}</td> 
	                                  <td>${demande_effectuee.getUser()}  ${demande.getUserPrenom()}</td>
	                                  <td>${demande_effectuee.getService()}</td>
	                                  <td>${demande_effectuee.getDateDemande()}</td>
	                                  <td> 
	                                  
	                                  <c:set var="verifligne" value="0"/>                                 
	                                  <c:forEach var="lignedemande" items="${listelignedemande}" varStatus="stat">
	                                		<c:if test="${lignedemande.getDemandeID()==demande_effectuee.getDemandeID()}"><c:set var="verifligne" value="${verifligne+1}"/></c:if>
	                                  </c:forEach>
	                               
	                                 <c:if test="${demande_effectuee.getStatutID()<3}">
		                                  <form name="form" action="ControleDemande" method="post"> 
		                                        <input type="hidden" value="${demande_effectuee.getDemandeID()}" id="demandeid" name="demandeid">
		                                        <input type="hidden" name="ajout" value="editstatut">
		                                        <input type="button" data-toggle="modal" data-backdrop="false" href="#formulaireMessage" style="font-size:10px;" class="" id="btn-${demande_effectuee.getDemandeID()}" value="">
		                                  </form> 
		                              </c:if>                                 	                                                            
	                                      <script type="text/javascript">                                     
	                                         var x = document.getElementById("base-${demande_effectuee.getDemandeID()}");
	                                         var y = document.getElementById("statut-${demande_effectuee.getDemandeID()}");
	                                         var z = document.getElementById("btn-${demande_effectuee.getDemandeID()}");
	                                         
	                                         if(x.value == "Non valider"){                                                                               
	                                            y.setAttribute("class","btn btn-danger btn-xs disabled");  y.value="Non validée";
	                                            z.setAttribute("class","btn btn-warning btn-xs"); z.value="Valider";
	                                            <c:if test="${session.getPosteUser()==post[1]}"> z.setAttribute("disabled","disabled"); </c:if>                                          
	                                         }   
	                                         if(x.value == "Valider"){                                   
	                                        	 y.setAttribute("class","btn btn-warning btn-xs disabled");  y.value="Validée";
	                                             z.setAttribute("class","btn btn-primary btn-xs"); z.value="Envoyer"; 
	                                             <c:if test="${session.getPosteUser()==post[0]}"> z.setAttribute("disabled","disabled"); </c:if>
	                                          }
	                                         if(x.value == "Envoyer"){                                   
	                                        	 y.setAttribute("class","btn btn-primary btn-xs disabled");  y.value="Envoyée";
	                                          }
	                                         if(x.value == "Traiter"){                                   
	                                        	 y.setAttribute("class","btn btn-danger btn-xs disabled");  y.value="Traitée";
	                                          }
	                                         if(x.value == "Transferer"){                                   
	                                        	 y.setAttribute("class","btn btn-success btn-xs disabled");  y.value="Transférée"; 
	                                          }
	                                         
	                                         <c:if test="${verifligne!=0}">
		                                  		z.removeAttribute("data-toggle"); z.setAttribute("type","submit");
		                                  	 </c:if>
		                                  	 
	                                      </script> 
	                                      </td>                                                         
	                                  <td>
	                                  <c:if test="${demande_effectuee.getStatutID() ==1}">
	                                      <div id="ligne" >
	                                        <button id="num" value="${demande_effectuee.getNumDemande()}+${demande_effectuee.getDemandeID()}" onclick="ajoutLigne(this)" name="numero" data-toggle="modal" data-backdrop="false" href="#formulaireLigne" class="btn btn-primary btn-xs"><div class="fa fa-plus"></div></button>
	                                      </div>
	                                  </c:if>
	                                  </td>                                    
	                                  <td>  
	                                    <a class="btn btn-success btn-xs fa fa-eye" href="ControleLigneDemande?resultat=listelignedemande&amp;demandeid=${demande_effectuee.getDemandeID()}&amp;source=dmdeff"></a> 
	                                  <c:if test="${demande_effectuee.getStatutID() ==1}">                                 
	                                    <button id="btn_modif" data-toggle="modal" data-backdrop="false" value="${demande_effectuee.getNumDemande()}+${demande_effectuee.getService()}+${demande_effectuee.getDateDemande()}+${demande_effectuee.getUserID()}+${demande_effectuee.getStatutID()}+${demande_effectuee.getDemandeID()}+${demande_effectuee.getDateGraphe()}" href="#formulaire" class="btn btn-primary btn-xs fa fa-edit" onclick="modifier(this)"></button>                             
	                                    <button value="${demande_effectuee.getDemandeID()}" onclick="supprimer(this)" class="btn btn-danger btn-xs icon_close_alt2" href="#formulaireConfirmation" data-toggle="modal" data-backdrop="false"></button>
	                                  </c:if>                                
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
                  </div>
                  <!-- profile -->
                  <div id="Demande_reçue" class="tab-pane">
                  <section class="panel">
                    
                  <div class="row">
	                <div class="col-lg-12">
	                  <section class="panel">
              	              <table id="example1" class="table table-striped">
                                <thead>
                                  <tr>
                                   	<th><i class="fa fa-warning"></i> Statut</th>
                                    <th><i class="fa fa-dashboard"></i> Numéro demande</th>
                                    <th><i class="fa fa-user"></i> Saisi par</th>
                                    <th><i class="fa fa-users"></i> Service Demandeur</th>
                                    <th><i class="fa fa-calendar"></i> Date demande</th>
                                    <th><i class="fa fa-check-square-o"></i>Validation</th>
                                    <th><i class="fa fa-cogs"></i> Action</th>
                                                   
                                  </tr>
                                 
                                </thead>
                                <tbody>
                                
                               <!-- DEMANDE TRAITER -->
	                                   <c:forEach var="demandetraiter" items="${listedemanderecustraiter}">	 
	                                   <c:forEach var="session" items="${sessionScope.session}">
	                                   <c:if test="${session.getServiceID()==demandetraiter.getServiceID()}">	 
			                                <tr>     
			                                  <td>                  
			                                       <input type="hidden" class="btn btn-danger btn-xs" id="base-${demandetraiter.getDemandeID()}" value="${demandetraiter.getStatut()}"> 
			                                       <input style="font-size:10px;" type="button" class="btn btn-success btn-xs disabled" id="statut-${demandetraiter.getDemandeID()}" value="Reçue"> 
			                                  </td>
			                                  <td>${demandetraiter.getNumDemande()}</td> 
			                                  <td>${demandetraiter.getUser()}  ${demandetraiter.getUserPrenom()}</td>
			                                  <td>${demandetraiter.getService()}</td>  
			                                  <td>${demandetraiter.getDateDemande()}</td>                                  
			                                  <td>
			                                      <form name="form" action="ControleTransfert" method="post">
			     	                                <input type="hidden" value="${demandetraiter.getDemandeID()}" name="demandeid">
			                                        <input type="hidden" value="${demandetraiter.getStatutID()+1}" name="statutid">
			                                        <input type="hidden" value="${session.getUserID()}" name="userid">
			                                        <input type="hidden" value="${demandetraiter.getServiceUserID()}" id="idservice" name="serviceid">
			                                        <input type="hidden" value="TRSF_${demandetraiter.getDemandeID()}" name="numtransfert">
			                                        <input type="hidden" name="ajout" value="addtransfert">
			                                        <button style="font-size:10px;" class="btn btn-danger btn-xs" name="ajout" type="submit" id="btn-${demandetraiter.getDemandeID()}" value="">Traiter</button>
			                                     </form>
			                                  </td> 
			                                  <td> 
			                                  	<a class="btn btn-success btn-xs fa fa-eye" href="ControleLigneDemande?resultat=listelignedemande&amp;demandeid=${demandetraiter.getDemandeID()}&amp;source=dmdrec"></a>      
			                                  </td>
			                                </tr>
			                               <script type="text/javascript">	              	                  
			                              	 var x = document.getElementById("base-${demandetraiter.getDemandeID()}");
	                                         var y = document.getElementById("statut-${demandetraiter.getDemandeID()}");
	                                         var z = document.getElementById("btn-${demandetraiter.getDemandeID()}");
	                                         
		              		                      if(x.value == "Envoyer"){ 
		              		                    		y.setAttribute("class","btn btn-primary btn-xs"); 
		              		                    	    y.setAttribute("value","Reçue");
		              		                       }
			              		                  if(x.value == "Traiter"){
			              		                		y.setAttribute("class","btn btn-danger btn-xs disabled");
			          		                    	    y.setAttribute("value","Traitée");
			          		                    	    z.style.visibility="hidden";
			          		                       }
			              		              	  if(x.value == "Transferer"){
			              		                		y.setAttribute("class","btn btn-success btn-xs disabled");
			          		                    	    y.setAttribute("value","Transferée");
			          		                    	  	z.style.visibility="hidden";
		          		                       	   }
              	                			</script>
			                                </c:if>
			                                </c:forEach>
			                           </c:forEach>                           
                            </tbody>
                         </table> 
              	        	</section>
              	     	 </div>
              	    	</div>  
                    </section>
                  </div>
                  <!-- End table -->
                </div>
              </div>
            </section>
          </div>
        </div>
       
      </section>

    

<!-- MODALE BEGIN -->
          <div class="col-md-6 col-xs-12 col-md-offset-3">
            <div class="modal fade" id="formulaire">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" style="color: red;">x</button>
                    <h4 id="titre" class="modal-title">DEMANDE</h4>
                  </div>
                  <div class="modal-body">
                  <div class="panel-body">
                    <div class="form">
                  <form name="form" action="ControleDemande" class="form-validate form-horizontal" id="feedback_form" method="post">
                      <div class="form-group "> 
                        <center style="color : gray;">     
                            <h3><i class="fa fa-user"></i> Demandeur :    
                                <c:forEach var="session" items="${sessionScope.session}"> 
                  				${ session.getPrenomUser()} ${ session.getNomUser().toUpperCase() }                 				
                                <input type="hidden" name="userid" id="iduser" value="${ session.getUserID() }" class="form-control">  
                                </c:forEach>       
                            </h3>
                        </center>
                       		<input type="hidden" name="statutid" id="idstatut" value="" class="form-control">
                      		<input type="hidden" name="demandeid" id="iddemande" value="" class="form-control">
                       		<label for="" class="control-label"> date :</label>
                              <div class="input-group">
                                  <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                  <input type="date" name="datedemande" id="datedmd" value="" class="form-control" onchange="setDateGraph(this)">
                              </div>
                      		  	
                      		  	<input type="hidden" id="dategraph" value="" name="dategraphe">
                      		  	
                              	<label for="" class="control-label">Service destinataire:</label>
                              	<select class="form-control" data-toggle="dropdown" id="idservice" name="serviceid">
	          	                <c:forEach var="service" items="${listeservice}">
		          	                <c:if test="${service.getTypeService()=='SERVICE'}">
		          	                	<option value="${service.getServiceID()}">${service.getNomService()}</option>
		          	                </c:if>
	          	                </c:forEach>
	          	              	</select>
          	              	
                               <label for="" class="control-label">numéro demande:</label>
                              <div class="input-group">
                                  <span class="input-group-addon"><i class="icon_calendar"></i></span>
                                  <input type="text" id="numdmd" name="numdemande" value="" class="form-control" readonly="readonly">
                              </div>	              	
                          <input type="hidden" name="ajout" value="adddemande" id="add_edit">
                          <div class="form-group">      
                          
                          <div class="modal-footer">
                              <button type="submit" id="button" class="btn btn-success" style="margin-top: 10px;"> AJOUTER </button>
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

<!-- MODALE END -->

<!-- MODALE BEGIN -->
          <div class="col-md-3 col-xs-6 col-md-offset-3">
            <div class="modal fade" id="formulaireConfirmation">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" style="color: red;">x</button>
                    <h4 class="modal-title">Boite de confirmation</h4>
                  </div>
                  <div class="modal-body">
                  <div class="panel-body">
                  		<h3>Voulez-vous vraiment supprimer?</h3>
                  </div>
              </div>

              <div class="modal-footer">
              		<a id="supp" href="ControleDemande?resultat=supprdemande&demandeid=" class="btn btn-success btn-xs">Confirmer</a>
          			<button class="btn btn-danger btn-xs" data-dismiss="modal">Annuler</button>
       		 </div>
            </div>
          </div>
          </div>
          </div>
<!-- MODALE END -->

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
                  		<h3>Veuillez d'abord ajouter une ligne de demande</h3>
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
              <h4 class="modal-title"><i class="fa fa-plus"></i> Ligne De Demande</h4>
            </center>
          </div>
          <div class="modal-body">
          <div class="panel-body">
            <div class="form">
          <form name="form" action="ControleLigneDemande" class="form-validate form-horizontal" id="feedback_form" method="post">
              <div class="form-group ">           
                  <center style="color : gray;">     
                  <h3><i class="fa fa-user"></i> Saisi(e) par :    
                      <c:forEach var="session" items="${sessionScope.session}"> 
                  			${ session.getPrenomUser()} ${ session.getNomUser().toUpperCase() } 
                  	  </c:forEach>        
                  </h3>
                  <h5>
                    DEMANDE NUMERO : <span id="valeur"></span>
                  </h5>
                  </center>
                          
                          <br>
                          <label for="" class="control-label"> Categorie d'article : </label>
                          <select class="form-control" data-toggle="dropdown" name="categorieid" id="categorieid">
                          <c:forEach var="categorie" items="${listecategorie}">
                              <option value="${categorie.getCategorieID()}">${categorie.getLibelleCategorie()}
                              </option>
                          </c:forEach>        
                          </select>
                          
                          <!--  <input type="hidden" id="hidnumdemande" value="" name="numdemande">-->
                          <input type="hidden" id="iddemandeligne" value="" name="demandeid">

                          <label for="" class="control-label"> Qte d'article :</label>
                          <div class="input-group" >
                              <span class="input-group-addon"><i class="fa fa-th"></i></span>
                              <input type="number" name="qtearticledemande" id="qtearticledemande" value="" class="form-control ">
                          </div>

                          <label for="" class="control-label">Commentaire :</label>
                          <div class="input-group">
                              <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                              <textarea rows="5" cols="" name="commentairedemande" id="commentairedemande" class="form-control"></textarea>
                          </div>
                  
                          
                      <div class="form-group">           
                  
                  <div class="modal-footer">
                  	  <input type="hidden" name="ajout" value="addlignedemande">
                      <button type="submit" name="ajout" id="buttonligne" class="btn btn-success" style="margin-top: 10px;"> <div class="fa fa-plus"></div> AJOUTER </button>
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
</section>
<script type="text/javascript">	
    $("modalForm").submit(function(e) {
        e.preventDefault();
        var $modalForm = $(this);
        $.post($modalForm.attr("action"), $modalForm.serialize())
            .done(function(data) {
                $("#html").html(data);
                $("#formulaire").modal("hide");
                $("table").load('listeDemande.jsp table' ,function () {
              });
            })
      });
    
      $("modalForm").submit(function(e) {
        e.preventDefault();
        var $modalForm = $(this);
        $.post($modalForm.attr("action"), $modalForm.serialize())
            .done(function(data) {
                $("#ligne").html(data);
                $("#formulaireLigne").modal("hide");
                $("table").load('listeDemande.jsp table' ,function () {
              });
            })
      });
      
      $("modalForm").submit(function(e) {
    	    e.preventDefault();
    	    var $modalForm = $(this);
    	    $.post($modalForm.attr("action"), $modalForm.serialize())
    	        .done(function(data) {
    	            $("#html").html(data);
    	            $("#formulaireConfirmation").modal("hide");
    	            $("table").load(' table' ,function () {
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
      
      
    function setDateGraph(champ)
    {
    	var chaine = champ.value;
    	var date = chaine.split("-");
    	$('#dategraph').val(date[0]);
    }
      
    function modifier(champ)
  	{
  		var chaine=champ.value;
  		var liste = chaine.split("+");		
  		document.getElementById("iddemande").value=liste[5];
  		document.getElementById("idstatut").value=liste[4];
  		document.getElementById("iduser").value=liste[3];
  		document.getElementById("datedmd").value=liste[2];
  		document.getElementById("numdmd").value=liste[0];
  		document.getElementById("dategraph").value=liste[6];
  		document.getElementById("add_edit").value="editdemande";
     	document.getElementById("titre").innerText="Modifier";
    	document.getElementById("button").innerText="Modifier";
    	
		const textToFind2=liste[1];
		const dd2=document.getElementById("idservice");
		dd2.selectedIndex=[...dd2.options].findIndex(option=>option.text===textToFind2);

  	}
    
    function supprimer(champ)
	{
		$('#supp').attr('href',"ControleDemande?resultat=supprdemande&demandeid="+champ.value);
	}
    
    function dateValue(){
    	var chaine = document.getElementById("datedemande").value;
  		var liste = chaine.split("-");
  		document.getElementById("dategraphe").value = liste[0];
    }
       
    function ajoutLigne(champ){
        var val = champ.value;
        var liste = val.split("+");
       document.getElementById("valeur").innerText = liste[0];
       document.getElementById("iddemandeligne").value = liste[1];
      }
    
    function ajouter(champ)
    {
    	var id=parseInt(champ.value)+1;
    	
    	/*if(id<10) $("#numdmd").val("DMD00"+id);
    	if(id>=10 && id<100) $("#numdmd").val("DMD0"+id);
    	if(id>=100) $("#numdmd").val("DMD"+id);*/
    	
    	$("#numdmd").val("DMD_"+id);
    	document.getElementById("add_edit").value="adddemande";
     	document.getElementById("titre").innerText="DEMANDE";
    	document.getElementById("button").innerText=" AJOUTER ";
    	
    	var currenDate = new Date();
    	document.getElementById("datedmd").value=currenDate.toISOString().slice(0,10);
    	document.getElementById("dategraph").value=currenDate.toISOString().slice(0,4);
    }
 
</script>
<%@ include file="../footer.jsp"%>