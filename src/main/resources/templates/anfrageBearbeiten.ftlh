<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        
        <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
        
        <title>Anfrage bearbeiten</title>
        
        <!-- Bootstrap core CSS -->
        <link rel="icon" type="image/x-icon" href="/assets/favicon.ico" />
        
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
        
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="/text/css" />
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
		style="background-image: url('/assets/img/bearbeiten-bg.jpg')">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1 class="dark_header">StattAuto</h1>
						<span class="subheading">Anfrage :: bearbeiten</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Main Content -->
	<div id="place" class="container">
		<div class="row">
			<div class="col-lg-12 col-md-10 mx-auto">
				<fieldset>
					<h1>Anfrage:</h1>
					<@spring.bind "prios"/>
					<@spring.bind "statuus"/>
					<form name="anfrage" action="" method="POST" modelAttribute="anfrage">
						<div class="form-group">
						    <label for="anfrage">Anfrage ID</label>
							<@spring.formInput "anfrage.id" "placeholder='id' readonly='true'" "text"/> <br />
						</div><br>
						<div class="form-group">
						    <label for="version">Version</label>
							<@spring.formInput "anfrage.version" "placeholder='version' readonly='false'" "text"/> <br />
						</div><br>
						<div class="form-group">
						    <label for="frage">Frage</label>
							<@spring.formTextarea "anfrage.frage" "rows='4' cols='60' readonly='true'" /> <br />
						</div><br>
						<div class="form-group">
						    <label for="anfrage">Antwort</label>
							<@spring.formTextarea "anfrage.antwort" "rows='4' cols='60'" /> <br />
						</div><br>
						<div class="form-group">
						    <label for="von">vom</label>
							<@spring.formInput "anfrage.von" "placeholder='von' readonly='true'" "text"/> <br />
						</div><br>
					    <div class="form-group">
					        <label for="status">Status</label>
					        <@spring.formSingleSelect "anfrage.status" statuus "style='width:200px'" />
					    </div><br>
					    <div class="form-group">
					        <label for="prio">Prio</label>
					        <@spring.formSingleSelect "anfrage.prio" prios "style='width:200px'" />
					    </div><br>  
						
						<input type="submit" value="Bearbeitet" />
					</form>
				</fieldset>
				<br>
			</div>
		</div>
	</div>

	<hr>

       <!-- Footer -->
	   <#include "/footer.ftlh">
		
</body>

</html>
