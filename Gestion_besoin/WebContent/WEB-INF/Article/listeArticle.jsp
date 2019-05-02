
<%@ include file="../entete.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-tasks"></i>ARTICLE
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
					<li><i class="fa fa-list-alt"></i>Liste des articles</li>
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
										<button onclick="ajouter()" data-toggle="modal" data-backdrop="false" href="#formulaire" class="btn btn-danger btn-lg btn-sm">
											<div class="fa fa-plus"></div>
											Ajouter des articles
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
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><i class="fa fa-qrcode"></i> Code article</th>
										<th><i class="fa fa-fax"></i> Categorie</th>
										<th><i class="fa fa-bookmark"></i> Nom article</th>
										<th><i class="fa fa-cubes"></i> Stock</th>
										<th><i class="fa fa-cogs"></i> Action</th>
										

									</tr>
								</thead>
								<tbody>
									<c:forEach var="article" items="${listearticle}">
										<tr>
											<td>${article.getCodeArticle()}</td>
											<td>${article.getCategorie()}</td>
											<td>${article.getNomArticle()}</td>
											<td>${article.getStock()}</td>
											<td>
												<button id="btn_modif" data-toggle="modal" data-backdrop="false" value="${article.getCodeArticle()}+${article.getCategorie()}+${article.getNomArticle()}+${article.getStock()}+${article.getEtat()}+${article.getArticleID()}" href="#formulaire" class="btn btn-primary btn-xs fa fa-edit" onclick="modifier(this)"></button>
												<button value="${article.getArticleID()}" onclick="supprimer(this)" class="btn btn-danger btn-xs icon_close_alt2" href="#formulaireConfirmation" data-toggle="modal" data-backdrop="false"></button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
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
			              		<a id="supp" href="ControleArticle?resultat=supprarticle&articleid=" class="btn btn-success btn-sm">Confirmer</a>
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
									<button type="button" class="close" data-dismiss="modal" style="color: red;">x</button>
									<h4 class="modal-title" id="titre">
										<i class="glyphicon glyphicon-plus"></i> Ajouter
									</h4>
								</div>
								<div class="modal-body">
									<div class="panel-body">
										<div class="form">
											<form name="form" action="ControleArticle" class="form-validate form-horizontal" id="feedback_form" method="post">
												<div class="form-group ">
												
													<input type="hidden" id="articleid" name="articleid" value="" class="form-control ">
													
													<label for="" class="control-label">Catégorie : </label>
													<div class="input-group">
														<span class="input-group-addon">
														<i class="fa fa-fax"></i>
														</span>
														<select class="form-control" id="categorieid" data-toggle="dropdown" name="categorieid">
															<c:forEach var="categorie" items="${listecategorie}">
																<option value="${categorie.getCategorieID()}">${categorie.getLibelleCategorie()}</option>
															</c:forEach>
														</select> 
													</div>
													<label for="" class="control-label">Nom article:</label>
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-bookmark"></i></span> 
														<input type="text" id="nomarticle" name="nomarticle" value="" class="form-control ">
													</div>
													
													<label for="" class="control-label">Avoir en stock:</label>
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-cubes"></i></span> 
														<input type="text" id="stock" name="stock" value="" class="form-control ">
													</div>
																								
														<input type="hidden" id="etat" name="etat" class="form-control " value="0">
		
													<label for="" class="control-label"> Code article :</label>
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-qrcode"></i></span> 
														<input type="text" id="codearticle" name="codearticle" value="" class="form-control">
													</div>
													<div class="form-group">
														<div class="modal-footer">
															<input type="hidden" id="add_edit" name="ajout" value="addarticle">
															<button type="submit" name="ajout" id="button" class="btn btn-success" style="margin-top: 10px;">
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
			</div>
		</div>
	</section>
</section>
<!-- MODALE END -->
<script type="text/javascript">

	function supprimer(champ)
	{
		$('#supp').attr('href',"ControleArticle?resultat=supprarticle&articleid="+champ.value);
	}

	function modifier(champ)
	{
		var chaine=champ.value;
		var liste = chaine.split("+");

		const textToFind1=liste[1];
		const dd1=document.getElementById('categorieid');
		dd1.selectedIndex=[...dd1.options].findIndex(option=>option.text===textToFind1);

		document.getElementById("codearticle").value=liste[0];
		document.getElementById("nomarticle").value=liste[2];
		document.getElementById("stock").value=liste[3];
		document.getElementById("etat").value=liste[4];
		document.getElementById("articleid").value=liste[5];
  		document.getElementById("add_edit").value="editarticle";
  		document.getElementById("titre").innerText="Modifier";
  		document.getElementById("button").innerText="Modifier";

	}
	
	function ajouter()
	{
		document.getElementById("codearticle").value="";
		document.getElementById("nomarticle").value="";
		document.getElementById("stock").value="";
		document.getElementById("articleid").value="";
  		document.getElementById("add_edit").value="addarticle";
  		document.getElementById("titre").innerText="Ajouter";
  		document.getElementById("button").innerText="Ajouter";

	}

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
            $('input#search').quicksearch('table tbody tr');
  </script>
<%@ include file="../footer.jsp"%>