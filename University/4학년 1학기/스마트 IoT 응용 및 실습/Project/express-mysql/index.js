var mysql      = require('mysql');
var express    = require('express');
var fs = require('fs');

var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '0000',
  port     : 3306,
  database : 'mobiusdb'
});

connection.connect();
var app = express();

app.set('view engine','ejs');
app.set('views','./views');

app.set('port', process.env.PORT || 3000);

app.get('/', function(req, res){
    connection.query('SELECT * from cin', 
        function (error, results, fields) {
        if (error) throw error;
        console.log(results);
        res.render('a', {rows:results});
        });

});


app.listen(app.get('port'), function () {
    console.log('Express server listening on port ' + app.get('port'));
  });