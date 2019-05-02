<%@ include file="../entete.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i>DIRECTION
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
					<li><i class="fa fa-arrow-down"></i>Autre</li>
					<li><i class="fa fa-list-alt"></i>Liste des directions</li>
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
										<button data-toggle="modal" data-backdrop="false" onclick="ajouter()" href="#formulaire" class="btn btn-danger btn-lg btn-sm">
											<div class="fa fa-plus"></div>
											Ajouter une direction
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
										<th><i class="fa fa-building"></i> Nom de la direction</th>
										<th><i class="fa fa-building"></i> Abreviation</th>
										<th><i class="fa fa-cogs"></i> Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="direction" items="${listedirection}">
										<tr>
											<td>${direction.getLibelleDirection()}</td>
											<td>${direction.getAbreviationDirection()}</td>
											
											<td>											
											<button id="btn_modif" data-toggle="modal" data-backdrop="false" value="${direction.getLibelleDirection()}+${direction.getAbreviationDirection()}+${direction.getDirectionID()}" href="#formulaire" class="btn btn-primary btn-xs fa fa-edit" onclick="modifier(this)"></button>
											<button value="${direction.getDirectionID()}" onclick="supprimer(this)" class="btn btn-danger btn-xs icon_close_alt2" href="#formulaireConfirmation" data-toggle="modal" data-backdrop="false"></button>
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
			              		<a id="supp" href="ControleDirection?resultat=supprdirection&directionid=" class="btn btn-success btn-sm">Confirmer</a>
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
											<form name="form" action="ControleDirection" class="form-validate form-horizontal" id="feedback_form" method="post">
												<div class="form-group ">
													<label for="" class="control-label">Nom de la direction : </label>
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-building"></i></span>
														 <input type="text" id="libelledirection" name="libelledirection" value="" class="form-control ">
													</div>
														<input type="hidden" id="directionid" name="directionid" value="" class="form-control ">
													<label for="" class="control-label">Abreviation : </label>
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-building"></i></span> 
														<input type="text" id="abreviationdirection" name="abreviationdirection" value="" class="form-control ">
													</div>
													<input type="hidden" id="add_edit" name="ajout" value="adddirection">
													<div class="form-group">
														<div class="modal-footer">
														<button type="submit" name="ajout" id="button" class="btn btn-success" style="margin-top: 10px;">
															AJOUTER <span class="fa fa-send"></span>
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
		$('#supp').attr('href',"ControleDirection?resultat=supprdirection&directionid="+champ.value);
	}

	function modifier(champ)
	{
		var chaine=champ.value;
		var liste = chaine.split("+");

			document.getElementById("libelledirection").value=liste[0];
			document.getElementById("abreviationdirection").value=liste[1];
			document.getElementById("directionid").value=liste[2];
	   		document.getElementById("add_edit").value="editdirection";
	   		document.getElementById("titre").innerText="Modifier";
	  		document.getElementById("button").innerText="Modifier";

	}
	
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
	
function ajouter()
{
	document.getElementById("libelledirection").value="";
	document.getElementById("abreviationdirection").value="";
	document.getElementById("directionid").value="";
	document.getElementById("add_edit").value="adddirection";
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
                  $("table").load('table' ,function () {
                });
              })
            });
  </script>
<%@ include file="../footer.jsp"%>