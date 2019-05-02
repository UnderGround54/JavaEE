<%@ include file="../entete.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-building"></i>SERVICES ET AGENCES
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
					<li><i class="fa fa-arrow-down"></i>Autre</li>
					<li><i class="fa fa-list-alt"></i>Liste des services et agences</li>
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
										<button data-toggle="modal" data-backdrop="false" href="#formulaire" onclick="ajouter()" class="btn btn-danger btn-lg btn-sm">
											<div class="fa fa-plus"></div>
											Ajouter
										</button>
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
							<div class="row" style="margin: 0% 0% 1% -1%;">
								<div class="col-lg-3">
								<label for="" class="control-label"><h4> Type : </h4> </label> 
								<select id="dd" class="form-control" data-toggle="dropdown" name="typeservice" onchange="liste_par_agence_service(this)">
									<option value="TOUS">TOUS</option>
									<option value="SERVICE">SERVICE</option>
									<option value="AGENCE">AGENCE</option>
								</select>
								</div>
							</div>
							
							<input type="hidden" id="hidetype" value="">

							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><i class="fa fa-random"></i> Type </th>
										<th><i class="fa fa-building"></i> Nom </th>
										<th><i class="fa fa-tags"></i> Abréviation </th>
										<th><i class="fa fa-map-marker"></i> Lieu </th>
										<th><i class="fa fa-code-fork"></i> Code </th>
										<th><i class="fa fa-sitemap"></i> Dans la direction</th>
										<th><i class="fa fa-cogs"></i> Action </th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="service" items="${listeservice}">
										<tr>
											<td>${service.getTypeService()}</td>
											<td>${service.getNomService()}</td>
											<td>${service.getAbreviationService()}</td>
											<td>${service.getLieuService()}</td>
											<td>${service.getCodeService()}</td>
											<td>${service.getDirection()}</td>
											<td>
											<button id="btn_modif" data-toggle="modal" data-backdrop="false" value="${service.getServiceID()}+${service.getDirection()}+${service.getNomService()}+${service.getAbreviationService()}+${service.getTypeService()}+${service.getLieuService()}+${service.getCodeService()}" href="#formulaire" class="btn btn-primary btn-xs fa fa-edit" onclick="modifier(this)"></button>
											<button value="${service.getServiceID()}" onclick="supprimer(this)" class="btn btn-danger btn-xs icon_close_alt2" href="#formulaireConfirmation" data-toggle="modal" data-backdrop="false"></button>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</section>
					</div>
				</div>
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
			              		<a id="supp" href="ControleServiceAgence?resultat=supprservice&serviceid=" class="btn btn-success btn-sm">Confirmer</a>
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
							<h4 id="titre" class="modal-title">Ajouter</h4>
						</div>
						<div class="modal-body">
							<div class="panel-body">
								<div class="form">

									<form name="form" action="ControleServiceAgence"
										class="form-validate form-horizontal" id="feedback_form"
										method="post">
										<div class="form-group ">

											<label for="" class="control-label"> Type service : </label>
											<form>
												<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-random"></i></span>
												<select id="typesrvc" class="form-control" data-toggle="dropdown" name="typeservice" onchange="changeType(this)">
													<option value="SERVICE">SERVICE</option>
													<option value="AGENCE">AGENCE</option>
												</select>
												</div>
											</form>
											
											<input id="serviceid" name="serviceid" type="hidden" /> <label for="" class="control-label"> Direction:</label> 
												<div class="input-group">
													<span class="input-group-addon"><i class="fa fa-sitemap"></i></span>
													<select id="dirsrvc" class="form-control" data-toggle="dropdown" name="directionid">
													<c:forEach var="direction" items="${listedirection}">
														<option value="${direction.getDirectionID()}">${direction.getLibelleDirection()}</option>
													</c:forEach>
													</select>
												</div>
											 
											<label id="nomsrvc" for="" class="control-label">Nom du service:</label>
											<div class="input-group validate-input" data-validate="Veuillez completer le login">
												<span class="input-group-addon"><i class="fa fa-building"></i></span>
												 <input id="nomservice" type="text" name="nomservice" value="" class="form-control ">
											</div>
											<label id="abrsrvc" for="" class="control-label"> Abreviation du service:</label>
											<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-tags"></i></span> <input id="abreviationservice" type="text" name="abreviationservice" value="" class="form-control" onkeyup="autoCodeService(this)">
											</div>

											<label id="lieusrvc" for="" class="control-label">Lieu
												du service:</label>
											<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-map-marker"></i></span> <input id="lieuservice" type="text" name="lieuservice" value="Tsaralalana" class="form-control">
											</div>

											<label id="codesrvc" for="" class="control-label">Code
												du service:</label>
											<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-code-fork"></i></span> <input id="codeservice" type="text" name="codeservice" value="" class="form-control">
											</div>

											<input id="addOrEdit" type="hidden" name="ajout"
												value="addservice">
											<div class="form-group">
												
												<div class="modal-footer">
													<button type="submit" name="ajout" id="button" class="btn btn-success" style="margin-top: 10px;">
													<div class="glyphicon glyphicon-plus"></div>
													AJOUTER
												</button>
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
	</section>
</section>
<!-- MODALE END -->
<script type="text/javascript">

		function supprimer(champ)
		{
			$('#supp').attr('href',"ControleServiceAgence?resultat=supprservice&serviceid="+champ.value);
		}

      $("modalForm").submit(function(e) {
          e.preventDefault();
          var $modalForm = $(this);
          $.post($modalForm.attr("action"), $modalForm.serialize())
              .done(function(data) {
                  $("#html").html(data);
                  $("#formulaire").modal("hide");
                  $("table").load('listeServiceAgence.jsp table' ,function () {
                });
              })
            });

     	function modifier(champ)
     	{
     		var chaine=champ.value;
     		var liste = chaine.split("+");

     			const textToFind1=liste[4];
    			const dd1=document.getElementById('typesrvc');
    			dd1.selectedIndex=[...dd1.options].findIndex(option=>option.text===textToFind1);

    			const textToFind2=liste[1];
    			const dd2=document.getElementById('dirsrvc');
    			dd2.selectedIndex=[...dd2.options].findIndex(option=>option.text===textToFind2);

    			document.getElementById("serviceid").value=liste[0];
    			document.getElementById("nomservice").value=liste[2];
    			document.getElementById("abreviationservice").value=liste[3];
    			document.getElementById("lieuservice").value=liste[5];
    			document.getElementById("codeservice").value=liste[6];
          		document.getElementById("addOrEdit").value="editservice";
          		document.getElementById("titre").innerText="Modifier";
          		document.getElementById("button").innerText="Modifier";

     	}

    	function changeType(champ)
    	{
    		var select=champ.value;
    		if(select=="SERVICE"){
    			document.getElementById("nomsrvc").innerText="Nom du service:";
    			document.getElementById("abrsrvc").innerText="Abreviation du service:";
    			document.getElementById("lieusrvc").innerText="Lieu du service:";
    			document.getElementById("codesrvc").innerText="Code du service:";
    			document.getElementById("lieuservice").value="Tsaralalàna";
    		}else if(select=="AGENCE"){
    			const textToFind="Direction des relations avec la clientele";
    			const dd=document.getElementById('dirsrvc');
    			dd.selectedIndex=[...dd.options].findIndex(option=>option.text===textToFind);

    			document.getElementById("nomsrvc").innerText="Nom de l'agence:";
    			document.getElementById("abrsrvc").innerText="Abreviation de l'agence:";
    			document.getElementById("lieusrvc").innerText="Lieu de l'agence:";
    			document.getElementById("codesrvc").innerText="Code de l'agence:";
    			document.getElementById("lieuservice").value="";
    		}

    	}

    	function autoCodeService(champ)
    	{
    		if(document.getElementById("typesrvc").value=="SERVICE")
    			document.getElementById("codeservice").value=champ.value;	
    		else document.getElementById("codeservice").value="CEM 00";
    	}

    	function liste_par_agence_service(champ)
    	{
    		var select = champ.value;
    		if(select=="SERVICE")
    		{
    			document.getElementById("hidetype").value="SERVICE";
    		}
    		else if(select=="AGENCE")
    		{
    			document.getElementById("hidetype").value="AGENCE";
    		}
    		else{
    			document.getElementById("hidetype").value="";
    		}
    		$('input#hidetype').quicksearch('table tbody tr');
    	}

    	function ajouter()
        {
        	document.getElementById("nomservice").value="";
  			document.getElementById("abreviationservice").value="";
  			document.getElementById("codeservice").value="";
       		document.getElementById("addOrEdit").value="addservice";
       		document.getElementById("titre").innerText="Ajouter";
      		document.getElementById("button").innerText="Ajouter";
        }
  </script>
<%@ include file="../footer.jsp"%>