<%@ include file="../entete.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-users"></i>UTILISATEUR
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
					<li><i class="fa fa-arrow-down"></i>Autre</li>
					<li><i class="fa fa-list-alt"></i>Liste des utilisateurs</li>
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
								<c:forEach var="session" items="${sessionScope.session}">
									<c:if test="${session.getDroitUser()==1}">
									<div id="html">
										<button data-toggle="modal" data-backdrop="false" href="#formulaire" onclick="ajouter()" class="btn btn-danger btn-lg btn-sm"><div class="fa fa-plus"></div>
											Ajouter un utilisateur
										</button>
									</div>
									</c:if>
								</c:forEach>
								</div>
								<div class="col-lg-6">
									<div class="nav search-row" style="margin: 0% 0% 1% 60%;">
										<form class="navbar-form">
											<input class="form-control" placeholder="Search" type="text" id="search">
										</form>
									</div>
								</div>
							</div>
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										
										<th><i class="fa fa-user"></i> Nom </th>
										<th><i class="fa fa-user"></i> Prenom</th>
										<th><i class="fa fa-building"></i> Service</th>
										<th><i class="fa fa-graduation-cap"></i> Poste</th>
										<th><i class="fa fa-globe"></i> Adresse</th>
										<th><i class="fa fa-user"></i> Login</th>
										<th><i class="fa fa-unlock"></i> Mot de passe</th>
										<th><i class="fa fa-check"></i> Droit</th>
										<th><i class="fa fa-cogs"></i> Action</th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${liste}">
										<tr>
											
											<td>${user.getNomUser()}</td>
											<td>${user.getPrenomUser()}</td>
											<td>${user.getService()}</td>
											<td>${user.getPosteUser()}</td>
											<td>${user.getAdresseUser()}</td>
											<td>${user.getLoginUser()}</td>
											<td>${user.getMotDePass()}</td>
											
											<c:if test="${user.getDroitUser()==1}">
												<td>Administrateur</td>
											</c:if>
											<c:if test="${user.getDroitUser()==0}">
												<td>Utilisateur</td>
											</c:if>
											<c:forEach var="session" items="${sessionScope.session}">
											<c:if test="${session.getDroitUser()==1}">
											<td>
											<button id="btn_modif" data-toggle="modal" data-backdrop="false" value="${user.getUserID()}+${user.getNomUser()}+${user.getPrenomUser()}+${user.getLoginUser()}+${user.getPosteUser()}+${user.getAdresseUser()}+${user.getMotDePass()}+${user.getService()}+${user.getDroitUser()}" href="#formulaire" class="btn btn-primary btn-xs fa fa-edit" onclick="modifier(this)"></button>
											<button value="${user.getUserID()}" onclick="supprimer(this)" class="btn btn-danger btn-xs icon_close_alt2" href="#formulaireConfirmation" data-toggle="modal" data-backdrop="false"></button>
											</td>
											</c:if>
											</c:forEach>
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
			              		<a id="supp" href="ControleUser?resultat=suppr&id=" class="btn btn-success btn-sm">Confirmer</a>
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
									<h4 id="titre" class="modal-title">
										<i class="fa glyphicon-plus"></i> Ajouter
									</h4>
								</div>
								<div class="modal-body">
									<div class="panel-body">
										<div class="form">
											<form name="form" action="ControleUser"
												class="form-validate form-horizontal" id="feedback_form"
												method="post">
												<div class="form-group ">
												
													<input type="hidden" id="userid" name="userid" /> 
												
													<label for="" class="control-label">Droit utilisateur:</label>
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-check"></i></span> 
														<select id="droituser" class="form-control input-sm m-bot15" data-toggle="dropdown" name="droituser">
															<option value="0">Utilisateur</option>
															<option value="1">Administrateur</option>
														</select>
													</div> 
													
													
													<label for="" class="control-label"> Service : </label> 
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-building"></i></span> 
														<select id="serviceid" class="form-control input-sm m-bot15" data-toggle="dropdown" name="serviceid">
															<c:forEach var="service" items="${listeservice}">
																<option value="${service.getServiceID()}">${service.getNomService()}</option>
															</c:forEach>
														</select> 
													</div>
													
													<label for="" class="control-label"> Nom : </label>
													<div class="input-group">
														<span class="input-group-addon">
														<i class="fa fa-user"></i></span> <input id="nom" type="text" name="nom" value="" class="form-control ">
													</div>

													<label for="" class="control-label"> Prenom :</label>
													<div class="input-group">
														<span class="input-group-addon"> 
														<i class="fa fa-user"></i></span> 
														<input id="prenom" type="text" name="prenom" value="" class="form-control">
													</div>

													<label for="" class="control-label"> Adresse :</label>
													<div class="input-group">
														<span class="input-group-addon">
															<i class="fa fa-globe"></i>
														</span> 
														<input id="adresse"  type="text" name="adresse" value="" class="form-control">
													</div>

													<label for="" class="control-label">Poste :</label>
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-graduation-cap"></i></span> 
														<select id="poste" name="poste" class="form-control input-sm m-bot15">
															<option value="Chef d'agence">Chef d'agence</option>
															<option value="Adjoint d'agence">Adjoint d'agence</option>
															<option value="GAC">GAC</option>
														</select>
													</div>

													<label for="" class="control-label">Login :</label>
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-user"></i></span> <input id="login" type="text" name="login" value="" class="form-control" onkeyup="verifLoginPassword(this)">
													</div>

													<label for="" class="control-label">Mot de passe :</label>
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-lock"></i></span> <input id="pass" type="text" name="pass" value="" class="form-control" onblur="verifLoginPassword(this)">
													</div>

													<input id="addOrEdit" type="hidden" name="ajout"
														value="add">
													<div class="form-group">
														<div class="modal-footer">
															<button type="submit" name="ajout" id="button" class="btn btn-success" style="margin-top: 10px;">
																<div class="fa fa-plus"></div>
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

$("modalForm").submit(function(e) {
    e.preventDefault();
    var $modalForm = $(this);
    $.post($modalForm.attr("action"), $modalForm.serialize())
        .done(function(data) {
            $("#html").html(data);
            $("#formulaire").modal("hide");
            $("table").load(' table' ,function () {
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
		$('#supp').attr('href',"ControleUser?resultat=suppr&id="+champ.value);
	}
      
        function modifier(champ)
       	{
       		var chaine=champ.value;
       		var liste = chaine.split("+");

                const admin="Administrateur";
                const user="Utilisateur";
                const dd1=document.getElementById('droituser');
                if(liste[8]=='1') dd1.selectedIndex=[...dd1.options].findIndex(option=>option.text===admin);
                if(liste[8]=='0') dd1.selectedIndex=[...dd1.options].findIndex(option=>option.text===user);

                //$("#serviceid option[value='AGENCE Mahajanga']").attr("selected",true);
                
      			const textToFind2=liste[7];
      			const dd2=document.getElementById('serviceid');
      			dd2.selectedIndex=[...dd2.options].findIndex(option=>option.text===textToFind2);

      			document.getElementById("userid").value=liste[0];
      			document.getElementById("nom").value=liste[1];
      			document.getElementById("prenom").value=liste[2];
      			document.getElementById("adresse").value=liste[5];
      			document.getElementById("poste").value=liste[4];
      			document.getElementById("login").value=liste[3];
           		document.getElementById("pass").value=liste[6];
           		document.getElementById("addOrEdit").value="edit";
           		document.getElementById("titre").innerText="Modifier";
          		document.getElementById("button").innerText="Modifier";

       	}

        function ajouter()
        {
        	document.getElementById("nom").value="";
  			document.getElementById("prenom").value="";
  			document.getElementById("adresse").value="";
  			document.getElementById("login").value="";
       		document.getElementById("pass").value="";
       		document.getElementById("addOrEdit").value="add";
       		document.getElementById("titre").innerText="Ajouter";
      		document.getElementById("button").innerText="Ajouter";
      		
        }
 
        function verifLoginPassword(champ)
        {
        	if($('#addOrEdit').val()=="add"){
        	if (champ.id=="login"){
	        	<c:forEach var="user" items="${liste}">
	        		if(champ.value=="${user.getLoginUser()}"){
	        			alert("Login déjà utilisé");
	        			setTimeout(function(){document.getElementById('login').focus();},1);
	        		}
	        	</c:forEach>
	       
        	}     	
        	else if (champ.id=="pass"){
	        	<c:forEach var="user" items="${liste}">
	        		if(champ.value=="${user.getMotDePass()}"){
	        			alert("Mot de passe déjà utilisé");
	        			setTimeout(function(){document.getElementById('pass').focus();},1);
	        		}
	        	</c:forEach>
        	}
        	}
        }

</script>

<%@ include file="../footer.jsp"%>
