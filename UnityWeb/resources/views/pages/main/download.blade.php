@extends('app')

@section('content')

    <section class="hero hero-panel" style="background-image: url({{ asset('images/default-backgorund.jpg') }});">
	<div class="hero-bg"></div>
        <div class="container">
            <h3 style="color : #fcfcfc;">Download's</h3>
            <p>Game Download, Aion 4.9 Full Client, Pacthes and Launcher.</p>
            <div class="vertical-tab">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#vtab1" data-toggle="tab" style="color: #FFF; text-shadow : #000 1px 1px 2px;">Full Client</a></li>
                    <li><a href="#vtab2" data-toggle="tab" style="color: #FFF; text-shadow : #000 1px 1px 2px;">Pacthes</a></li>
                    <li><a href="#vtab3" data-toggle="tab" style="color: #FFF; text-shadow : #000 1px 1px 2px;">Launcher</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="vtab1">
                        <a href="https://mega.nz/#F!h8VSVZDJ" target="_blank"><button class="btn btn-warning">Mega</button></a>Decrypt Key : !e9-PKYE-lYMhC-D_ttHXvg
                        <a href="https://drive.google.com/open?id=0B5WH_vFJkfXANHJ2LXdkX20wQTA" target="_blank"><button class="btn btn-primary">Google Drive</button></a>
                        <!--<a href="#" target="_blank"><button class="btn btn-success">Openload</button></a>-->
                    </div>
                    <div class="tab-pane fade" id="vtab2">
                        <a href="https://drive.google.com/open?id=0B1UJK4xOW9JlbnpVRTE0VU1tQ0U" target="_blank"><button class="btn btn-warning">Bin32</button></a>
                        <a href="https://drive.google.com/open?id=0B0Tv6VLvIq5NQkxWcS1fQWtvcVE" target="_blank"><button class="btn btn-primary">Item.pak</button></a>
                       <!-- <a href="#" target="_blank"><button class="btn btn-success">Openload</button></a>-->
                    </div>
                    <div class="tab-pane fade" id="vtab3">
                        <a href="http://server.heat.web.id/download/Heat_Launcher.rar" target="_blank"><button class="btn btn-warning">Winxp/Win7 exe</button></a>
                        <a href="http://server.heat.web.id/download/Heat_Launcher.rar" target="_blank"><button class="btn btn-primary">Winxp/Win7 rar</button></a>
                        <a href="http://server.heat.web.id/download/Heat_Launcher8.rar" target="_blank"><button class="btn btn-success">Win8</button></a>
                    </div>
                </div>
            </div>
			<div class="pull-right" style="margin-top : 35px">
				<a class="btn btn-circle btn-social-icon btn-dropbox"><i class="fa fa-dropbox"></i></a>
				<a class="btn btn-circle btn-social-icon btn-facebook"><i class="fa fa-facebook"></i></a>
			</div>
        </div>
    </section>
	
@endsection

