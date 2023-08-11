<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        
        <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
        
        <script type="text/javascript">
      		$(function(){
      			$('.bearbeiten').click(function(e) {
      				var activeRow=$(this).closest("tr"); 
         
        			var anfrageId = activeRow.find("td:eq(0)").text();
        			
        			var url = "http://localhost:8081/anfrage/" + anfrageId + "/bearbeiten";
					console.log(url);
					window.location.href = url;
      			});
      			$('.weiterleiten').click(function(e) {
      				var activeRow=$(this).closest("tr"); 
         
        			var anfrageId = activeRow.find("td:eq(0)").text();
        			
        			var url = "http://localhost:8081/anfrage/" + anfrageId + "/weiterleiten";
					console.log(url);
					window.location.href = url;
      			});
      		});
        </script>
        
        <title>StattAuto</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" 
                    rel="stylesheet" type="/text/css" />
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" 
        rel="stylesheet" type="/text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/css/styles.css" rel="stylesheet" />
    </head>

<body>
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container" px-4 px-lg-5>
      <a class="navbar-brand" href="/">StattAuto</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ms-auto py-4 py-lg-0">
          <li class="nav-item"><a class="nav-link" href="/">Kundenservice</a></li>
          <li class="nav-item"><a class="nav-link" href="/contact.html">Kontakt</a>
          <li class="nav-item"><a class="nav-link" href="/post.html">News</a>
          <li class="nav-item"><a class="nav-link" href="/about.html">About</a></li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Header -->
  <header class="masthead" 
    style="background-image: url('/assets/img/anfragen-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="site-heading">
            <h1>StattAuto</h1>
            <span class="subheading">Kundenservice :: Anfragen</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Main Content -->
  <div id="place" class="container">
    <div class="row">
      <div class="col-lg-12 col-md-10 mx-auto">
	    	<h1>Anfragenordner</h1>
			<table class="table table-striped" border="1">
			    <tr>
			        <th>Anfragen Nr.</th>
			        <th style="display:none;">Version</th>
			        <th>Frage</th>
			        <th>Antwort</th>
			        <th>Prio</th>
			        <th>Aktion</th>
			    </tr>
			    <#list anfragen as anfrage>
					<tr>
					    <td>${anfrage.id}</td>
					    <td style="display:none;">${anfrage.version}</td>
					    <td>${anfrage.frage}</td>
					    <td>${anfrage.antwort}</td>
					    <td>${anfrage.prio}</td>
					    <td><button               type="button" class="bearbeiten material-icons"><i class="fas fa-edit"></i></button>
                            <button               type="button" class="loescfen material-icons"><i class="far fa-trash-alt"></i></button>
                            <button id="bn_check" type="button" class="check material-icons"><i class="fas fa-check"></i></button>
                            <button               type="button" class="weiterleiten material-icons"><i class="fas fa-file-export"></i></button></td>
			        </tr>
				</#list>
			</table>
		    <br>
		    <form method="get" action="/neueAnfrage" >
		   		<button id="bottom" type="submit" background-color="blue">Neue Anfrage stellen</button>
			</form>
			<br>
      </div>
    </div>
  </div>

  <hr>

       <!-- Footer -->
	   <#include "/footer.ftlh">


</body>

</html>
