#pragma GCC optimize ("-O2")

#include <Adafruit_GPS.h>
#include <SoftwareSerial.h>

SoftwareSerial mySerial(7, 6);//TX, RX
Adafruit_GPS GPS(&mySerial);

#define GPSECHO  true

//static float f1, f2;
static int before_x, before_y, before_z;

const int xPin = A0;
const int yPin = A1;
const int zPin = A2;
int minVal = 265;
int maxVal = 402;
int x;
int y;
int z;

int xRead;
int yRead;
int zRead;
int xAng;
int yAng;
int zAng;
double abs_x, abs_y, abs_z;

void setup() {
  Serial.begin(115200);
  delay(5000);
  GPS.begin(9600);
  GPS.sendCommand(PMTK_SET_NMEA_OUTPUT_RMCGGA);
  GPS.sendCommand(PMTK_SET_NMEA_UPDATE_1HZ); 
  GPS.sendCommand(PGCMD_ANTENNA);
  delay(1000);
  mySerial.println(PMTK_Q_RELEASE);
}

uint32_t timer = millis();

void loop() {
  xRead = analogRead(xPin);
  yRead = analogRead(yPin);
  zRead = analogRead(zPin);
  xAng = map(xRead, minVal, maxVal, -90, 90);
  yAng = map(yRead, minVal, maxVal, -90, 90);
  zAng = map(zRead, minVal, maxVal, -90, 90);
  char c = GPS.read();
  if (GPS.newNMEAreceived()) {
     if (!GPS.parse(GPS.lastNMEA())) 
       return; 
  }
  
 if(timer  > millis()) timer = millis();
 if(millis() - timer >100){
   timer = millis();
   x = RAD_TO_DEG * (atan2(-yAng, -zAng) + PI);
   y = RAD_TO_DEG * (atan2(-xAng, -zAng) + PI);
   z = RAD_TO_DEG * (atan2(-yAng, -xAng) + PI);
   
   
  abs_x = abs(x - before_x);
  abs_y = abs(y - before_y);
  abs_z = abs(z - before_z);
  
  before_x = x;
  before_y = y;
  before_z = z;
//  Serial.print("x: ");
//  Serial.print(abs_x);
//  Serial.print("y: ");
//  Serial.print(abs_y);
//  Serial.print("z: ");
//  Serial.println(abs_z);
  
  
  if(
    ((abs_x >= 30) && (abs_y >= 30) &&(abs_x <=300)&&(abs_y<=300))
  || ((abs_x >= 30) && (abs_z >= 30) &&(abs_x <=300)&&(abs_z<=300))
  || ((abs_y >= 30) && (abs_z >= 30)&&(abs_y <=300)&&(abs_z<=300))
  || ((abs_x >= 30) && (abs_y >= 30) && (abs_z >= 30)&&(abs_y <=300)&&(abs_z<=300)&&(abs_x<=300))
  ){
    Serial.print("3676.5410");
    Serial.print(" ");
    Serial.println("12728.2503");
//    if (GPS.fix) {
//          Serial.print(GPS.latitude, 4); 
//          Serial.print(" ");
//          Serial.println(GPS.longitude, 4); 
//    } 
  }
 }
}
