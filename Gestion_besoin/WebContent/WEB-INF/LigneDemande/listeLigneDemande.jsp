<%@ include file="../entete.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i>Ligne Demande
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading"> </header>
							<div class="row">

								<div class="col-lg-6">
									<div id="html">
										<a class="btn btn-primary" href="${(retour=='dmdeff')?'listeDemandeEffectuee':'listeDemandeRecue'}"><i
											class="fa fa-reply-all"></i>
										</a>
										<!-- <button data-toggle="modal" data-backdrop="false" href="#formulaire" class="btn btn-danger btn-lg btn-sm"><div class="glyphicon glyphicon-plus"></div> Ajouter</button>-->
									</div>
								</div>
								<div class="col-lg-6">
									<div class="nav search-row" style="margin: 0% 0% 1% 60%;">
										<form class="navbar-form">
											<input class="form-control" placeholder="Search" type="text"
												id="search">
										</form>
									</div>
								</div>
							</div>
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										
										<th>Categorie</th>
										<th>demande</th>
										<th>qte</th>
										<th>commentaire</th>
										<c:if test="${statutdemande==1}">
										<th>Action</th>
										</c:if>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="lignedemande" items="${listelignedemande}">
										<tr>
											
											<td>${lignedemande.getCategorie()}</td>
											<td>${lignedemande.getDemande()}</td>
											<td>${lignedemande.getQteArticleDemande()}</td>
											<td>${lignedemande.getCommentaireDemande()}</td>
											<c:if test="${statutdemande==1}">
											<td>
											<button id="btn_modif" data-toggle="modal" data-backdrop="false" value="${lignedemande.getLigneDemandeID()}+${lignedemande.getCategorie()}+${lignedemande.getDemande()}+${lignedemande.getQteArticleDemande()}+${lignedemande.getCommentaireDemande()}+${lignedemande.getDemandeID()}" href="#formulaire" class="btn btn-primary btn-xs fa fa-edit" onclick="modifier(this)"></button>
											<button value="${lignedemande.getLigneDemandeID()}+${lignedemande.getDemandeID()}" onclick="supprimer(this)" class="btn btn-danger btn-xs icon_close_alt2" href="#formulaireConfirmation" data-toggle="modal" data-backdrop="false"></button>
											</td>
											</c:if>
											</tr>
									</c:forEach>
								</tbody>
							</table>
							</table>
						</section>
					</div>
				</div>
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
			              		<a id="supp" href="ControleLigneDemande?resultat=supprlignedemande&lignedemandeid=" class="btn btn-success btn-sm">Confirmer</a>
			          			<button class="btn btn-danger btn-sm" data-dismiss="modal">Annuler</button>
			       		 </div>
			            </div>
			          </div>
			          </div>
			          </div>
			<!-- MODALE END -->
			
				<!-- MODALE BEGIN -->
				<div class="col-md-6 col-xs-12 col-md-offset-3">
					<div class="modal fade" id="formulaire">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										style="color: red;">x</button>
									<h4 class="modal-title">Modifier
									</h4>
								</div>
								<div class="modal-body">
									<div class="panel-body">
										<div class="form">
											<form name="form" action="ControleLigneDemande"
												class="form-validate form-horizontal" id="feedback_form"
												method="post">
												<div class="form-group ">
												<input type="hidden" id="lignedemande" name="lignedemandeid" value="">
													<label for="" class="control-label">Nom categorie:</label> <select
														id="categorie" class="form-control" data-toggle="dropdown"
														name="categorieid">
														<c:forEach var="categorie" items="${listecategorie}">
															<option value="${categorie.getCategorieID()}">${categorie.getLibelleCategorie()}</option>
														</c:forEach>
													</select> <label for="" class="control-label">Numéro demande:</label> <select
														id="numdem" class="form-control" data-toggle="dropdown">
														<c:forEach var="demande" items="${listedemande}">
															<option value="${demande.getDemandeID()}">${demande.getNumDemande()}</option>
														</c:forEach>
													</select>
													
													<input type="hidden" id="hidnumdem" name="demandeid" value="">
													<input type="hidden" name="source" value="${retour}">
													
													 <label for="" class="control-label">Qte demandée:</label>
													<div class="input-group">
														<span class="input-group-addon"><i
															class="glyphicon glyphicon-user"></i></span> <input id="qte" type="text"
															name="qtearticledemande" value="" class="form-control ">
													</div>

												<label for="" class="control-label">Commentaire :</label>
						                          <div class="input-group">
						                              <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
						                              <textarea rows="5" cols="" name="commentairedemande" id="commentaire" class="form-control"></textarea>
						                          </div>

													<input type="hidden" name="ajout" value="editlignedemande">
													<div class="form-group">
														<div class="modal-footer">
															<button type="submit" name="ajout" id="button"
															class="btn btn-success" onclick="verifCode(this)"
															style="margin-top: 10px;"> Modifier </button>
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
			</div>
		</div>
	</section>
</section>
<!-- MODALE END -->
<script type="text/javascript">
  
      $("modalForm").submit(function(e) {
          e.preventDefault();
          var $modalForm = $(this);
          $.post($modalForm.attr("action"), $modalForm.serialize())
              .done(function(data) {
                  $("#html").html(data);
                  $("#formulaire").modal("hide");
                  $("table").load('readAllLcomCli.php table' ,function () {
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
      
      function supprimer(champ)
  	{
    	var liste = champ.value.split("+");
  		$('#supp').attr('href',"ControleLigneDemande?resultat=supprlignedemande&lignedemandeid="+liste[0]+"&demandeid="+liste[1]+"&source=${retour}");
  	}

      function modifier(champ)
     	{
     		var chaine=champ.value;
     		var liste = chaine.split("+");
				
             	const textToFind1=liste[2];
  			  	const dd1=document.getElementById('numdem');
  			  	dd1.selectedIndex=[...dd1.options].findIndex(option=>option.text===textToFind1);
  			  	document.getElementById("numdem").setAttribute('disabled','disabled');
  			  
    			const textToFind2=liste[1];
    			const dd2=document.getElementById('categorie');
    			dd2.selectedIndex=[...dd2.options].findIndex(option=>option.text===textToFind2);
    			
    			document.getElementById("hidnumdem").value=liste[5];
    			document.getElementById("lignedemande").value=liste[0];
    			document.getElementById("qte").value=liste[3];
    			document.getElementById("commentaire").value=liste[4]; 			
     	}
      
            $('input#search').quicksearch('table tbody tr');
  </script>
<%@ include file="../footer.jsp"%>