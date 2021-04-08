var http = require('http');
var fs = require('fs');

// 웹 서버 객체 만들기
var server = http.createServer();

// 웹 서버를 시작하여 3000번 포트에서 대기한다.
var port = 3000;
server.listen(port, function(){
    console.log("웹 서버가 시작되었습니다. : %d", port);
});

// Client 연결 이벤트 처리
server.on('connection', function(socket){ // on([이벤트 이름], [콜백 함수])
    var addr = socket.address();
    console.log("Client가 접속했습니다.: %s, %d",addr.address, addr.port);
});

// Client 요청 이벤트 처리
server.on('request', function(req,res){
    console.log("Client 요청이 들어왔습니다.");

    var filename = 'logo.png';
    var infile = fs.createReadStream(filename, {flags:'r'});
    var filelength = 0;
    var curlength = 0;

    fs.stat(filename, function(err, stats){
        filelength = stats.size;
    });

    // 헤더 쓰기
    res.writeHead(200, {"Content-Type":"image/png"});

    // 파일 내용을 스트림에서 읽어 본문 쓰기
	infile.on('readable', function() {
        var chunk;
        while (null !== (chunk = infile.read())) {
            console.log('읽어들인 데이터 크기 : %d 바이트', chunk.length);
            curlength += chunk.length;
            res.write(chunk, 'utf8', function(err) {
            	console.log('파일 부분쓰기 완료 : %d, 파일 크기 : %d', curlength, filelength);
            	if (curlength >= filelength) {
            		// 응답 전송하기
            		res.end();
            	}
            });
		}
	});

});

// Server 종료 이벤트 처리
server.on('close', function(){
    console.log('Server가 종료됩니다.');
});