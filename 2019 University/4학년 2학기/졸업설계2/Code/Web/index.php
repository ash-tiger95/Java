<!DOCTYPE html>
<!--
Transit by TEMPLATED
templated.co @templatedco
Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html lang="en">

<head>

  <meta charset="UTF-8">

  <title> P.W.D.I </title>

  <meta http-equiv="content-type" content="text/html; charset=utf-8" />

  <meta name="description" content="" />

  <meta name="keywords" content="" />

  <meta http-equiv="refresh" content="10">

  <!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->

  <script src="js/jquery.min.js"></script>

  <script src="js/skel.min.js"></script>

  <script src="js/skel-layers.min.js"></script>

  <script src="js/init.js"></script>

  <noscript>

    <link rel="stylesheet" href="css/skel.css" />

    <link rel="stylesheet" href="css/style.css" />

    <link rel="stylesheet" href="css/style-xlarge.css" />

  </noscript>

</head>


<body class="landing">


  <!-- Header -->

  <header id="header">
    <nav id="nav">

    </nav>

  </header>



  <!-- Banner -->

  <section id="banner">

    <h2>For motorcycle drivers</h2>

    <p>show place where driver is, and show emergency contact.</p>

  </section>



  <!-- One -->

  <section id="one" class="wrapper style1 special">

    <div class="container">

      <header class="major">


      </header>
      <!-- MAP -->
      <div class="row 150%">

        <div class="8u 12u$(medium)">
          <div id="title1" value="s31" style = "color : #F5F5F5;">
            36.761890,127.299112
          </div>
          <section class="box">


            <div align="center">
              <div id="map" style="width:100%; height:400px;"></div>
            </div>

            <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey="></script>
            <script>
            <?php
            $jb_conn = mysqli_connect( '', '', '', '' );
            $jb_sql = "SELECT * FROM gpsdata";
            $jb_result = mysqli_query( $jb_conn, $jb_sql );

            while( $jb_row = mysqli_fetch_array( $jb_result ) ) {
              $lat = $jb_row[ 'Latitude' ];
              $long = $jb_row['Longitude'];
            }
            ?>
            var lat = "<?=$lat?>";
            var long = "<?=$long?>";

            var mapContainer = document.getElementById('map'),
            mapOption = {
              center: new kakao.maps.LatLng(Number(lat), Number(long)),
              level: 3
            };

            var map = new kakao.maps.Map(mapContainer, mapOption);

            var imageSrc = 'https://i.imgur.com/STE2EJr.png',
            imageSize = new kakao.maps.Size(64, 69),
            imageOption = { offset: new kakao.maps.Point(27, 69) };

            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
            markerPosition = new kakao.maps.LatLng(Number(lat), Number(long));


            var marker = new kakao.maps.Marker({
              position: markerPosition
            });


            marker.setMap(map);


            // marker.setMap(null);
            </script>


          </section>

        </div>


        <!-- DB -->
        <div class="4u$ 12u$(medium)">
          <div id="title1" value="s31" style = "color : #F5F5F5;">
            36.761890,127.299112
          </div>
          <section class="box">
            <table border = 0.5 width = 400px>
              <tr>
                <td align = center>name</td>
                <td align = center>phone</td>
              </tr>
              <?php
              $jb_conn = mysqli_connect( '192.168.85.200', 'subin', '1234', 'phone' );
              $jb_sql = "SELECT * FROM phonedata;";
              $jb_result = mysqli_query( $jb_conn, $jb_sql );
              while( $jb_row = mysqli_fetch_array( $jb_result ) ){
                $name = $jb_row['name'];
                $phone = $jb_row['phone'];
                ?>
                <tr align = center>
                  <td> <?=$name?> </td>
                  <td> <?=$phone?> </td>
                </tr>
                <?php
              }
              ?>
            </section>

          </div>

        </div>

      </div>

    </section>
  </body>
  </html>
