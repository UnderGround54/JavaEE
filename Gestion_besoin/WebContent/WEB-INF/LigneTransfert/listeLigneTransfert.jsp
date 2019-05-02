<%@ include file="../entete.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i> Table
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
					<li><i class="fa fa-table"></i>Table</li>
					<li><i class="fa fa-th-list"></i>Basic Table</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading">
						<div class="row">

								<div class="col-lg-6">
									<div id="html">
										<a class="btn btn-primary" href="${(retour=='trsftraite')?'listeTransfertTraite':'listeTransfertRecu'}"><i class="fa fa-reply-all"></i></a>																			
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
					</header>
					<table id="example1" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Article</th>
								<th>Transfert</th>
								<th>Qte</th>
								<th>Commentaire</th>
								<c:if test="${statuttransfert==4}">
								<th>Action</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lignetransfert" items="${listelignetransfert}">
								<tr>
									<td>${lignetransfert.getArticle()}</td>
									<td>${lignetransfert.getTransfert()}</td>
									<td>${lignetransfert.getQteArticleTransfert()}</td>
									<td>${lignetransfert.getCommentaireTransfert()}</td>
									<c:if test="${statuttransfert==4}">
									<td>
										<button id="btn_modif" data-toggle="modal" data-backdrop="false" value="${lignetransfert.getLigneTransfertID()}+${lignetransfert.getArticle()}+${lignetransfert.getTransfert()}+${lignetransfert.getQteArticleTransfert()}+${lignetransfert.getCommentaireTransfert()}+${lignetransfert.getTransfertID()}" href="#formulaire" class="btn btn-primary btn-xs fa fa-edit" onclick="modifier(this)"></button>
										<button value="${lignetransfert.getLigneTransfertID()}+${lignetransfert.getTransfertID()}" onclick="supprimer(this)" class="btn btn-danger btn-xs icon_close_alt2" href="#formulaireConfirmation" data-toggle="modal" data-backdrop="false"></button>
									</td>
								   </c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</section>
			</div>
		</div>
	</section>
</section>

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
					<h4 class="modal-title">Modifier</h4>
				</div>
				<div class="modal-body">
					<div class="panel-body">
						<div class="form">
							<form name="form" action="ControleLigneTransfert"
								class="form-validate form-horizontal" id="feedback_form"
								method="post">
								<div class="form-group ">
								<input type="hidden" id="lignetransfert" name="lignetransfertid" value="">
									<label for="" class="control-label">Article:</label> <select
										id="idarticle" class="form-control" data-toggle="dropdown" name="articleid">
										<c:forEach var="article" items="${listearticle}">
											<option value="${article.getArticleID()}">${article.getNomArticle()}</option>
										</c:forEach>
									</select> 
									
									<input type="hidden" id="hidnumtrans" name="transfertid" value="">
									<input type="hidden" name="source" value="${retour}">
									
									<label for="" class="control-label">Transfert:</label> <select
										id="numtrans" class="form-control" data-toggle="dropdown" name="transfertid">
										<c:forEach var="transfert" items="${listetransfert}">
											<option value="${transfert.getTransfertID()}">${transfert.getNumTransfert()}</option>
										</c:forEach>
									</select> 
									
									<label for="" class="control-label">qte transfert:</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> <input type="text" id="qte" name="qtearticletransfert" value="" class="form-control ">
									</div>

									<label for="" class="control-label">commentaire :</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> <input type="text" id="commentaire" name="commentairetransfert" value="" class="form-control">
									</div>

									<input type="hidden" name="ajout" value="editlignetransfert">
									<div class="form-group">
										<div class="modal-footer">
											<button type="submit" name="ajout" id="button" class="btn btn-success" onclick="verifCode(this)" style="margin-top: 10px;">Modifier</button>
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
    		$('#supp').attr('href',"ControleLigneTransfert?resultat=supprlignetransfert&lignetransfertid="+liste[0]+"&transfertid="+liste[1]+"&source=${retour}");
    	}

        function modifier(champ)
       	{
       		var chaine=champ.value;
       		var liste = chaine.split("+");
  				
               	const textToFind1=liste[2];
    			  	const dd1=document.getElementById('numtrans');
    			  	dd1.selectedIndex=[...dd1.options].findIndex(option=>option.text===textToFind1);
    			  	document.getElementById("numtrans").setAttribute('disabled','disabled');
    			  
      			const textToFind2=liste[1];
      			const dd2=document.getElementById('idarticle');
      			dd2.selectedIndex=[...dd2.options].findIndex(option=>option.text===textToFind2);
      			
      			document.getElementById("hidnumtrans").value=liste[5];
      			document.getElementById("lignetransfert").value=liste[0];
      			document.getElementById("qte").value=liste[3];
      			document.getElementById("commentaire").value=liste[4]; 			
       }
            $('input#search').quicksearch('table tbody tr');
            
  </script>
<%@ include file="../footer.jsp"%>