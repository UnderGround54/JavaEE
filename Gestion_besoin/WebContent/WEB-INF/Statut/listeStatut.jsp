<%@ include file="../index.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i>UTILISATEUR
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
					<li><i class="fa fa-table"></i>Autre</li>
					<li><i class="fa fa-th-list"></i>Utilisateur</li>
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
										<button data-toggle="modal" data-backdrop="false"
											href="#formulaire" class="btn btn-danger btn-lg btn-sm">
											<div class="glyphicon glyphicon-plus"></div>
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
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>ID</th>
										<th>Nom de statut</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach var="statut" items="${listestatut}">
										<tr>
											<td>${statut.getStatutID()}</td>
											<td>${statut.getLibelleStatut()}</td>
											<td><a
												href="ControleStatut?resultat=modifstatut&amp;statutid=${statut.getStatutID()}">modifier</a></td>
											<td><a
												href="ControleStatut?resultat=supprstatut&amp;statutid=${statut.getStatutID()}">supprimer</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</table>
						</section>
					</div>
				</div>
				<!-- MODALE BEGIN -->
				<div class="col-md-6 col-xs-12 col-md-offset-3">
					<div class="modal fade" id="formulaire">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										style="color: red;">x</button>
									<h4 class="modal-title">
										<i class="glyphicon glyphicon-plus"></i> Ajouter
									</h4>
								</div>
								<div class="modal-body">
									<div class="panel-body">
										<div class="form">
											<form name="form" action="ControleStatut"
												class="form-validate form-horizontal" id="feedback_form"
												method="post">
												<div class="form-group ">
													<label for="" class="control-label">libelle:</label>
													<div class="input-group">
														<span class="input-group-addon"><i
															class="glyphicon glyphicon-user"></i></span> <input type="text"
															name="libellestatut" value="" class="form-control ">
													</div>
													<input type="hidden" name="ajout" value="addstatut">
													<div class="form-group">
														<button type="submit" name="ajout" id="button"
															class="btn btn-success" onclick="verifCode(this)"
															style="margin-top: 10px;">
															<div class="glyphicon glyphicon-plus"></div>
															AJOUTER <span class="glyphicon glyphicon-send"></span>
														</button>
														<div class="modal-footer">
															<button class="btn btn-danger" data-dismiss="modal">Annuler</button>
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
            $('input#search').quicksearch('table tbody tr');
  </script>
<%@ include file="../footer.jsp"%>