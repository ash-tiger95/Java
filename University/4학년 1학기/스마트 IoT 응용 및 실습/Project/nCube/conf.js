9/**
.. * Created by Il Yeup, Ahn in KETI on 2017-02-23.
 */

/**
 * Copyright (c) 2018, OCEAN
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

var ip = require("ip");

var conf = {};
var cse = {};
var ae = {};
var cnt_arr = [];
var sub_arr = [];
var acp = {};

conf.useprotocol = 'http'; // select one for 'http' or 'mqtt' or 'coap' or 'ws'

// build cse
//모비우스와 연동하기 위해 값을 모비우스와 동일하게 해준다.
cse.host        = '192.168.0.98'; //mobius host를 사용한다.
cse.port        = '7579';
cse.name        = 'Mobius';//Mobius usecsebase = 'Mobius'
cse.id          = '/Mobius2';//Mobius usecseid = '/Mobius2'
cse.mqttport    = '1883';//Mobius use_mqtt_port = '1883'
cse.wsport      = '7577';//Mobius usepxywsport = '7577'

// build ae
//Thyme Container 생성
ae.name         = 'edu6';

ae.id           = 'S' + ae.name;

ae.parent       = '/' + cse.name;
ae.appid        = 'measure_co2';
ae.port         = '9727';
ae.bodytype     = 'json'; // select 'json' or 'xml' or 'cbor'
ae.tasport      = '3105';//tas의 conf.xml의 parentport와 일치시켜준다.

// build cnt
//Thyme container 생성부분에 TAS에서 연결하려는 정보를 맞추어야한다.
var count = 0;
// cnt_arr[count] = {};
// cnt_arr[count].parent = '/' + cse.name + '/' + ae.name;
// cnt_arr[count++].name = 'co2';
// cnt_arr[count] = {};
// cnt_arr[count].parent = '/' + cse.name + '/' + ae.name;
// cnt_arr[count++].name = 'led';
// cnt_arr[count] = {};
// cnt_arr[count].parent = '/' + cse.name + '/' + ae.name;
// cnt_arr[count++].name = 'temp';
// cnt_arr[count] = {};
// cnt_arr[count].parent = '/' + cse.name + '/' + ae.name;
// cnt_arr[count++].name = 'tvoc';
// cnt_arr[count] = {};
// cnt_arr[count].parent = '/' + cse.name + '/' + ae.name;
// cnt_arr[count++].name = 'timer';
cnt_arr[count] = {};//tas 소켓에서 보낸 값을 cnt-class124이름을 확인하고 모비우스에 보낸다.
cnt_arr[count].parent = '/' + cse.name + '/' + ae.name;
cnt_arr[count++].name = 'cnt-class124';

// build sub
count = 0;
//sub_arr[count] = {};
//sub_arr[count].parent = '/' + cse.name + '/' + ae.name + '/' + cnt_arr[1].name;
//sub_arr[count].name = 'sub-ctrl';
//sub_arr[count++].nu = 'mqtt://' + cse.host + '/' + ae.id;

// --------
//sub_arr[count] = {};
//sub_arr[count].parent = '/' + cse.name + '/' + ae.name + '/' + cnt_arr[1].name;
//sub_arr[count].name = 'sub';
//sub_arr[count++].nu = 'mqtt://' + cse.host + '/' + ae.id + '?ct=' + ae.bodytype; // mqtt
//sub_arr[count++].nu = 'http://' + ip.address() + ':' + ae.port + '/noti?ct=json'; // http
//sub_arr[count++].nu = 'Mobius/'+ae.name; // mqtt
// --------

/*// --------
sub_arr[count] = {};
sub_arr[count].parent = '/' + cse.name + '/' + ae.name + '/' + cnt_arr[1].name;
sub_arr[count].name = 'sub2';
//sub_arr[count++].nu = 'http://' + ip.address() + ':' + ae.port + '/noti?ct=json'; // http
//sub_arr[count++].nu = 'mqtt://' + cse.host + '/' + ae.id + '?rcn=9&ct=' + ae.bodytype; // mqtt
sub_arr[count++].nu = 'mqtt://' + cse.host + '/' + ae.id + '?ct=json'; // mqtt
// -------- */

// build acp: not complete
acp.parent = '/' + cse.name + '/' + ae.name;
acp.name = 'acp-' + ae.name;
acp.id = ae.id;


conf.usesecure  = 'disable';

if(conf.usesecure === 'enable') {
    cse.mqttport = '8883';
}//올바른 MQTT를 사용한다.

conf.cse = cse;
conf.ae = ae;
conf.cnt = cnt_arr;
conf.sub = sub_arr;
conf.acp = acp;


module.exports = conf;
